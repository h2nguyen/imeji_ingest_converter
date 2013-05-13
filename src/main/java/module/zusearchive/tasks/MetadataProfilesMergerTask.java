/**
 * 
 */
package module.zusearchive.tasks;



import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

import module.zusearchive.jaxb.JaxbIngestProfileZuse;

import core.vo.MetadataProfile;


/**
 * @author hnguyen
 *
 */
public class MetadataProfilesMergerTask extends SwingWorker<String, Void> {

	protected String outputFilename;
	protected MetadataProfile offline;
	protected MetadataProfile online;
	
	protected JProgressBar progressBar;
	protected JLabel label;
	
	public MetadataProfilesMergerTask(String outputFilename,
			MetadataProfile offline, MetadataProfile online,
			JProgressBar progressBar, JLabel label) {
		this.outputFilename = outputFilename;
		this.offline = offline;
		this.online = online;
		
		this.progressBar = progressBar;
		this.label = label;
	}

	@Override
	protected String doInBackground() throws Exception {
		this.offline.setCreated(this.online.getCreated());
		this.offline.setCreatedBy(this.online.getCreatedBy());
		this.offline.setDescription(this.online.getDescription());
		this.offline.setDiscardComment(this.online.getDiscardComment());
		this.offline.setId(this.online.getId());
		this.offline.setModified(this.online.getModified());
		this.offline.setModifiedBy(this.online.getModifiedBy());
		this.offline.setStatus(this.online.getStatus());
		this.offline.setTitle(this.online.getTitle());
		this.offline.setVersion(this.online.getVersion());
		this.offline.setVersionDate(this.online.getVersionDate());
		
		new JaxbIngestProfileZuse().marshalMdProfile(this.outputFilename, this.offline);
		
		return this.outputFilename;
	}

	public void done() {
		
	}
	
}
