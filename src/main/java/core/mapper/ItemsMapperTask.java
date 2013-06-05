/**
 * 
 */
package core.mapper;

import java.util.Iterator;

import javax.xml.bind.JAXBException;

import org.xml.sax.SAXException;

import core.task.enums.Task;
import core.task.enums.Update;
import core.vo.imeji.Item;
import core.vo.imeji.Items;
import core.vo.imeji.Metadata;
import core.vo.imeji.MetadataSet;

/**
 * @author hnguyen
 *
 */
public class ItemsMapperTask extends MapperTask<Items, Void> {
	
	private Update updateBy = Update.UPDATE_BY_FILENAME;

	/**
	 * 
	 * @param itemsOnlineFilename
	 * @param itemsOfflineFilename
	 * @param task
	 * @param updateBy
	 * @throws JAXBException
	 * @throws SAXException
	 */
	public ItemsMapperTask(String itemsOnlineFilename,
			String itemsOfflineFilename, Task task, Update updateBy)
			throws JAXBException, SAXException {
		super(itemsOnlineFilename, itemsOfflineFilename, task, Items.class);
		this.setUpdateBy(updateBy);
	}
	
	
	/**
	 * @return the updateBy
	 */
	public Update getUpdateBy() {
		return updateBy;
	}


	/**
	 * @param updateBy the updateBy to set
	 */
	public void setUpdateBy(Update updateBy) {
		this.updateBy = updateBy;
	}


	@Override
	protected Items doInBackground() throws Exception {

		this.setObjectMapped(this.getObjectOnline().clone());
		this.getObjectMapped().getItem().clear();
		
		switch (this.getUpdateBy()) {
			case UPDATE_BY_ID:
				
				for (Iterator<Item> itItOn = this.getObjectOnline().getItem().iterator(); itItOn.hasNext();) {
					Item itemOn = itItOn.next();
					for (Iterator<Item> itItOff = this.getObjectOffline().getItem().iterator(); itItOff.hasNext();) {
						Item itemOff = itItOff.next();
						if (itemOn.getId().compareTo(itemOff.getId()) == 0) {
							this.getObjectMapped().getItem().add(this.mergeItem(itemOn,itemOff));
							break;
						}
					}
				}

				break;
			case UPDATE_BY_FILENAME:
				
				for (Iterator<Item> itItOn = this.getObjectOnline().getItem().iterator(); itItOn.hasNext();) {
					Item itemOn = itItOn.next();
					for (Iterator<Item> itItOff = this.getObjectOffline().getItem().iterator(); itItOff.hasNext();) {
						Item itemOff = itItOff.next();
						if (itemOn.getFilename().equals(itemOff.getFilename())) {
							this.getObjectMapped().getItem().add(this.mergeItem(itemOn,itemOff));
							break;
						}
					}
				}
				
				break;
			case UPDATE_BY_STORAGE_ID:
				for (Iterator<Item> itItOn = this.getObjectOnline().getItem().iterator(); itItOn.hasNext();) {
					Item itemOn = itItOn.next();
					for (Iterator<Item> itItOff = this.getObjectOffline().getItem().iterator(); itItOff.hasNext();) {
						Item itemOff = itItOff.next();
						if (itemOn.getStorageId().equals(itemOff.getStorageId())) {
							this.getObjectMapped().getItem().add(this.mergeItem(itemOn,itemOff));
							break;
						}
					}
				}
				
				break;
			case UPDATE_BY_CHECKSUM:
				for (Iterator<Item> itItOn = this.getObjectOnline().getItem().iterator(); itItOn.hasNext();) {
					Item itemOn = itItOn.next();
					for (Iterator<Item> itItOff = this.getObjectOffline().getItem().iterator(); itItOff.hasNext();) {
						Item itemOff = itItOff.next();
						if (itemOn.getChecksum().equals(itemOff.getChecksum())) {
							this.getObjectMapped().getItem().add(this.mergeItem(itemOn,itemOff));
							break;
						}
					}
				}
				break;
			default:
				break;
		}

		return this.getObjectMapped();
	}

	
	protected Item mergeItem(Item objectOnline, Item objectOffline)
			throws CloneNotSupportedException {
		Item item = objectOnline.clone();
		
		MetadataSet mdsOff = objectOffline.getMetadataSet();
		MetadataSet mdsOn = objectOnline.getMetadataSet();
		
		MetadataSet mdsItem = item.getMetadataSet();
		
		
		switch (this.getTask()) {
			case OVERWRITE:
				if(mdsOff.getProfile().compareTo(mdsOn.getProfile()) == 0) {
					if(mdsItem == null) {
						mdsItem = new MetadataSet();
					}
					mdsItem.setMetadata(mdsOff.getMetadata());
				}
				break;
			case UPDATE:
				
				if(mdsOff.getProfile().compareTo(mdsOn.getProfile()) == 0) {
					if(mdsItem == null || mdsItem.getMetadata().isEmpty()) {
						if(mdsOff.getMetadata() != null || !mdsOff.getMetadata().isEmpty()) {
							if(mdsItem == null) {
								mdsItem = new MetadataSet();
							}
							mdsItem.setMetadata(mdsOff.getMetadata());
						}						
					} else {
						for (Iterator<Metadata> itMdOn = mdsItem.getMetadata().iterator(); itMdOn.hasNext();) {
							Metadata mdOn = itMdOn.next();
	
							for (Iterator<Metadata> itMdOff = mdsOff.getMetadata().iterator(); itMdOff.hasNext();) {
								Metadata mdOff = itMdOff.next();
								if(mdOn.getStatement().compareTo(mdOff.getStatement()) == 0) {
									mdOn = mdOff;
									break;
								}
							}
						}
					}
				}
				
				break;
			default:
				break;
		}
		
		return item;
	}


	public static String getMergedItemsFilename(String inputFilename) {
		int i = inputFilename.lastIndexOf('.');
		if(i < 0)
			return inputFilename + "_merged_items_final";
		return inputFilename.substring(0, i) + "_merged_items_final" + inputFilename.substring(i);
	}
}
