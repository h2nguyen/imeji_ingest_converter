/**
 * 
 */
package main.java.core.task;

import java.net.URI;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

import main.java.core.jaxb.JaxbIngestProfile;
import main.java.core.mapper.StatementIdMapper;
import main.java.core.mapper.StatementsIdMapper;
import main.java.core.vo.Item;
import main.java.core.vo.Items;
import main.java.core.vo.Metadata;
import main.java.core.vo.MetadataProfile;
import main.java.core.vo.MetadataSet;


/**
 * @author hnguyen
 *
 */
public class ItemWithProfileAndStatementMappingConverterTask extends SwingWorker<String, String> {

	private StatementsIdMapper ssidm;
	private MetadataProfile mdp;
	protected JProgressBar progressBar;
	protected JLabel label;
	
	protected Items items;	
	protected String inputFilename;
	protected String outputFilename;
	protected boolean stopFlag;
	
	
	public ItemWithProfileAndStatementMappingConverterTask(String inputFilename, Items items, MetadataProfile mdp, StatementsIdMapper ssidm, JProgressBar progressBar, JLabel label) {
		this(inputFilename,getOutputFilename(inputFilename),items,mdp,ssidm,progressBar,label);		
	}
	
	public ItemWithProfileAndStatementMappingConverterTask(String inputFilename, String outputFilename, Items items, MetadataProfile mdp, StatementsIdMapper ssidm, JProgressBar progressBar, JLabel label) {		
		this.mdp = mdp;
		this.ssidm = ssidm;
		this.items = items;		
		this.progressBar = progressBar;
		this.label = label;
		this.inputFilename = inputFilename;
		this.outputFilename = outputFilename;
		
		init();
	}

	
	protected void init() {
		this.stopFlag = false;
	}
	

	private static String getOutputFilename(String inputFilename) {
		int i = inputFilename.lastIndexOf('.');
		if(i < 0)
			return inputFilename + "_itemizedMergedProfileAndStatementMapping";
		return inputFilename.substring(0, i) + "_itemizedMergedProfileAndStatementMapping" + inputFilename.substring(i);		
	}
	
	
	@Override
	protected String doInBackground() throws Exception {
		
		for (int i = 0; i < this.items.getItem().size() && !this.stopFlag; i++) {
			ItemWithProfileAndStatementMappingConverterTask.mapWithProfile(this.items.getItem().get(i), this.mdp, this.ssidm);
			int val = (int) (100 * (double)(i+1) / this.items.getItem().size());
			this.progressBar.setValue(val);
			this.label.setText("Converting items job: "+ val + " % done!" );
		}
		
		new JaxbIngestProfile().marshalItems(this.outputFilename, this.items);
		
		this.stop();
		return this.outputFilename;
	}

	private static Item mapWithProfile(Item item, MetadataProfile mdProfile, StatementsIdMapper statementsIdMapper) {

		item.setCreated(mdProfile.getCreated());
		item.setCreatedBy(mdProfile.getCreatedBy());
		item.setDiscardComment(mdProfile.getDescription());
		item.setModified(mdProfile.getModified());
		item.setModifiedBy(mdProfile.getCreatedBy());
		item.setStatus(mdProfile.getStatus());
		item.setVersion(mdProfile.getVersion());
		item.setVersionDate(mdProfile.getVersionDate());
		item.setCollection(mdProfile.getId());
		
		List<MetadataSet> mdsl = item.getMetadataSets();
		
		for (MetadataSet mds : mdsl) {
			Collection<Metadata> mdl  = mds.getMetadata();
			for (Metadata metadata : mdl) {
				metadata.setStatement(ItemWithProfileAndStatementMappingConverterTask.getMappedStatementId(metadata.getStatement(),statementsIdMapper));
			}
		}		
		
		return item;
	}

	private static URI getMappedStatementId(URI uri, StatementsIdMapper statementsIdMapper) {

		for (StatementIdMapper statementIdMapper : statementsIdMapper.getStatementIdMapper()) {
			if(statementIdMapper.getStatementOffline().equalsIgnoreCase(uri.toString())) {
				return URI.create(statementIdMapper.getStatementOnline());
			}
		}
		
		return null;
	}

	
	public void done() {
	    this.progressBar.setValue(progressBar.getMinimum());
	    this.stopFlag = false;
	    this.label.setText("Item file generated!" );		
	}

	protected void stop() {
		this.stopFlag = true;
		this.progressBar.setValue(this.progressBar.getMinimum());		
	}
	

}
