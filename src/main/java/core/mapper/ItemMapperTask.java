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
import core.vo.imeji.Metadata;
import core.vo.imeji.MetadataSet;

/**
 * @author hnguyen
 * 
 */
public class ItemMapperTask extends MapperTask<Item, Void> {

	private Update updateBy = Update.UPDATE_BY_FILENAME;

	public ItemMapperTask(String itemOnlineFilename,
			String itemOfflineFilename, Task task, Update updateBy)
			throws JAXBException, SAXException {
		super(itemOnlineFilename, itemOfflineFilename, task, Item.class);
		this.setUpdater(updateBy);
	}

	/**
	 * @return the updater
	 */
	public Update getUpdater() {
		return this.updateBy;
	}

	/**
	 * @param updater
	 *            the updater to set
	 */
	public void setUpdater(Update updateBy) {
		this.updateBy = updateBy;
	}

	@Override
	protected Item doInBackground() throws Exception {

		switch (this.getUpdater()) {
			case UPDATE_BY_ID:
				if (this.getObjectOnline().getId().toString()
						.equals(this.getObjectOffline().getId().toString())) {
					this.setObjectMapped(this.mergeItem(this.getObjectOnline(),
							this.getObjectOffline()));
				} else {
					this.setObjectMapped(null);
				}
				break;
			case UPDATE_BY_FILENAME:
				if (this.getObjectOnline().getFilename()
						.equals(this.getObjectOffline().getFilename())) {
					this.setObjectMapped(this.mergeItem(this.getObjectOnline(),
							this.getObjectOffline()));
				} else {
					this.setObjectMapped(null);
				}
				break;
			case UPDATE_BY_STORAGE_ID:
				if (this.getObjectOnline().getStorageId()
						.equals(this.getObjectOffline().getStorageId())) {
					this.setObjectMapped(this.mergeItem(this.getObjectOnline(),
							this.getObjectOffline()));
				} else {
					this.setObjectMapped(null);
				}
				break;
			case UPDATE_BY_CHECKSUM:
				if (this.getObjectOnline().getChecksum()
						.equals(this.getObjectOffline().getChecksum())) {
					this.setObjectMapped(this.mergeItem(this.getObjectOnline(),
							this.getObjectOffline()));
				} else {
					this.setObjectMapped(null);
				}
				break;
			default:
				break;
		}

		return this.getObjectMapped();
	}

	protected Item mergeItem(Item objectOnline, Item objectOffline) {
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