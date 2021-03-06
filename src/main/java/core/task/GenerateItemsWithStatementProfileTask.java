/**
 * 
 */
package core.task;

import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.xml.bind.JAXBException;

import module.zusearchive.jaxb.JaxbIngestProfileZuse;
import module.zusearchive.tasks.GenerateZuseItemsTask;

import org.xml.sax.SAXException;

import core.mapper.StatementsIdMapper;
import core.vo.Items;
import core.vo.MetadataProfile;

/**
 * @author hnguyen
 *
 */
public class GenerateItemsWithStatementProfileTask extends GenerateZuseItemsTask {

	private String statementMappingProfileFilename;
	private String profileFilename;
	
	public GenerateItemsWithStatementProfileTask(String inputFilename, String profileFilename, String statementMappingProfileFilename, JProgressBar progressBar, JLabel lblNotification) {		
		this(inputFilename, getOutputFilename(inputFilename), profileFilename, statementMappingProfileFilename, progressBar, lblNotification);
	}

	public GenerateItemsWithStatementProfileTask(String inputFilename, String profileFilename, String outputFilename, String statementMappingProfileFilename, JProgressBar progressBar, JLabel lblNotification) {
		super(inputFilename, getOutputFilename(inputFilename), progressBar, lblNotification);
		this.profileFilename = profileFilename;
		this.statementMappingProfileFilename = statementMappingProfileFilename;
	}

	private static String getOutputFilename(String inputFilename) {
		int i = inputFilename.lastIndexOf('.');
		if(i < 0)
			return inputFilename + "_itemizedMergedProfileAndStatement";
		return inputFilename.substring(0, i) + "_itemizedMergedProfileAndStatement" + inputFilename.substring(i);		
	}
	
	@Override
	protected String doInBackground() throws Exception {

		JaxbIngestProfileZuse jmp = new JaxbIngestProfileZuse();
		
		Items items = null;
		MetadataProfile mdp = null;
		StatementsIdMapper ssidm = null;
		
		try {			
			this.timer.start();
			items = jmp.unmarshalItems(this.inputFilename);
			mdp = jmp.unmarshalMdProfile(this.profileFilename);
			ssidm = jmp.unmarshalStsIdMapper(this.statementMappingProfileFilename);
			this.stop();
			
			ItemWithProfileAndStatementMappingConverterTask iwsmct = new ItemWithProfileAndStatementMappingConverterTask(this.inputFilename, items, mdp, ssidm, progressBar, label);
			iwsmct.execute();
			
		} catch (JAXBException e1) {
			this.label.setText("Invalid XML file");
		} catch (SAXException e1) { 
			this.label.setText("Invalid XML file");
		}
		
		return this.outputFilename;
	}
}
