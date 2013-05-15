/**
 * 
 */
package module.zusearchive.tasks;

import java.util.Hashtable;

import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

import module.zusearchive.jaxb.JaxbOType;
import module.zusearchive.vo.generated.OZuse;

import core.vo.imeji.Item;
import core.vo.imeji.Items;

/**
 * @author hnguyen
 *
 */
public class MergeOfflineAndOnlineItemsTask extends SwingWorker<String, Void> {
	protected String offlineItemsFilename;
	protected String onlineItemsFilename;
	protected String outputFilename;
	protected JProgressBar progressBar;
	protected JLabel lblNotification;
	
	public MergeOfflineAndOnlineItemsTask(String offlineItemsFilename, String onlineItemsFilename, JProgressBar progressBar, JLabel lblNotification) {
		this(offlineItemsFilename, onlineItemsFilename, getOutputFilename(offlineItemsFilename), progressBar, lblNotification);
	}
	
	public MergeOfflineAndOnlineItemsTask(String offlineItemsFilename, String onlineItemsFilename, String outputFilename, JProgressBar progressBar, JLabel lblNotification) {
		this.offlineItemsFilename = offlineItemsFilename;
		this.onlineItemsFilename = onlineItemsFilename;
		this.outputFilename = outputFilename;
		this.progressBar = progressBar;
		this.lblNotification = lblNotification;
	}
	
	private static String getOutputFilename(String inputFilename) {
		int i = inputFilename.lastIndexOf('.');
		if(i < 0)
			return inputFilename + "_imejiItemized";
		return inputFilename.substring(0, i) + "_imejiItemized" + inputFilename.substring(i);		
	}

	@Override
	protected String doInBackground() throws Exception {
		JaxbOType<Items> jipz = new JaxbOType<Items>(Items.class);
		Items offlineItems = jipz.unmarshal(this.offlineItemsFilename);
		Items onlineItems = jipz.unmarshal(this.onlineItemsFilename);
		
		Hashtable<String, Item> offlineItemsHashtable = new Hashtable<String, Item>(offlineItems.getItem().size());
		
		for (Item item : offlineItems.getItem()) {
			offlineItemsHashtable.put(item.getFilename(),item);
		}
		
		for (Item onItem : onlineItems.getItem()) {

			Item offItem = offlineItemsHashtable.get(onItem.getFilename());
			
			if(offItem == null) continue;
			
			this.mergeStatementContent(offItem, onItem);
		}
		
		jipz.marshal(this.outputFilename, onlineItems);
		
		return this.outputFilename;
	}
	
	public void done() {
		
	}
	
	private Item mergeStatementContent(Item offline, Item online) {
		
		online.setMetadataSets(offline.getMetadataSets());
		
		return online;
	}

}
