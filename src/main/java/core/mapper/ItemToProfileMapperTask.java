/**
 * 
 */
package core.mapper;

import java.util.List;

import javax.swing.SwingWorker;

import core.vo.Item;
import core.vo.MetadataProfile;

/**
 * @author hnguyen
 *
 */
public class ItemToProfileMapperTask extends SwingWorker<String, Void> {	
	private MetadataProfile mdProfile;
	private List<Item> items;
	
	/**
	 * 
	 */
	public ItemToProfileMapperTask(MetadataProfile mdp, List<Item> items) {
		this.setMdProfile(mdp);
		this.setItems(items);
	}

	public MetadataProfile getMdProfile() {
		return mdProfile;
	}

	public void setMdProfile(MetadataProfile mdProfile) {
		this.mdProfile = mdProfile;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	/* (non-Javadoc)
	 * @see javax.swing.SwingWorker#doInBackground()
	 */
	@Override
	protected String doInBackground() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 
	 */
	public void done() {
		
	}

}
