/**
 * 
 */
package core.mapper;

import java.util.Iterator;
import java.util.List;

import javax.ejb.Init;
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

		this.setObjectMapped(this.getObjectOnline());
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
		Item item = objectOnline;
			
		switch (this.getTask()) {
			case OVERWRITE:
				
				break;
			case UPDATE:
						
				MetadataSet mdsOff = objectOffline.getMetadataSet();
				MetadataSet mdsOn = item.getMetadataSet();
				
				if(mdsOff.getProfile().compareTo(mdsOn.getProfile()) == 0) {
					
					for (Iterator<Metadata> itMdOn = mdsOn.getMetadata().iterator(); itMdOn.hasNext();) {
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
				
				break;
			default:
				break;
		}
		
		return item;
	}
}