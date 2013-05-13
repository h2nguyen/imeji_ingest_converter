/**
 * 
 */
package main.java.core.mapper;

import java.util.List;

import javax.swing.SwingWorker;

import main.java.core.vo.Item;
import main.java.core.vo.MetadataProfile;

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
		this.mdProfile = mdp;
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
