/**
 * 
 */
package core.mapper;

import java.net.URI;
import java.util.Calendar;

import javax.xml.bind.JAXBException;

import org.xml.sax.SAXException;

import core.task.enums.Task;
import core.vo.imeji.MetadataProfile;
import core.vo.imeji.Properties.Status;

/**
 * @author hnguyen
 *
 */
public class MdProfileMapperTask extends MapperTask<MetadataProfile, Void> {
	
	/**
	 * 
	 * @param mdpOnlineFilename
	 * @param mdpOfflineFilename
	 * @param task
	 * @throws JAXBException
	 * @throws SAXException
	 */
	public MdProfileMapperTask(String mdpOnlineFilename, String mdpOfflineFilename, Task task) throws JAXBException, SAXException {
		super(mdpOnlineFilename,mdpOfflineFilename,task,MetadataProfile.class);		
	}

	@Override
	protected MetadataProfile doInBackground() throws Exception {
		
		this.setObjectMapped(this.getObjectOnline());
		
		// maps metadata properties
		if(this.getObjectOnline().getCreated() == null) {
			if (this.getObjectOffline().getCreated() == null)
				this.getObjectMapped().setCreated(Calendar.getInstance());
			else
				this.getObjectMapped().setCreated(this.getObjectOffline().getCreated());
		} else if (this.task == Task.OVERWRITE) {
			if (this.getObjectOffline().getCreated() != null)
				this.getObjectMapped().setCreated(this.getObjectOffline().getCreated());
		}
		
		if(this.getObjectOnline().getCreatedBy() == null) {
			if (this.getObjectOffline().getCreatedBy() == null)
				this.getObjectMapped().setCreatedBy(new URI("http://an.URI.to.set"));
			else
				this.getObjectMapped().setCreatedBy(this.getObjectOffline().getCreatedBy());
		} else if (this.task == Task.OVERWRITE) {
			if (this.getObjectOffline().getCreatedBy() != null)
				this.getObjectMapped().setCreatedBy(this.getObjectOffline().getCreatedBy());
		}
		
		if(this.getObjectOnline().getDiscardComment() == null) {
			if (this.getObjectOffline().getDiscardComment() == null)
				this.getObjectMapped().setDiscardComment("");
			else
				this.getObjectMapped().setDiscardComment(this.getObjectOffline().getDiscardComment());
		} else if (this.task == Task.OVERWRITE) {
			if (this.getObjectOffline().getDiscardComment() != null)
				this.getObjectMapped().setDiscardComment(this.getObjectOffline().getDiscardComment());
		}
		
		if(this.getObjectOnline().getModified() == null) {
			if (this.getObjectOffline().getModified() == null)
				this.getObjectMapped().setModified(Calendar.getInstance());
			else
				this.getObjectMapped().setModified(this.getObjectOffline().getModified());
		} else if (this.task == Task.OVERWRITE) {
			if (this.getObjectOffline().getModified() != null)
				this.getObjectMapped().setModified(this.getObjectOffline().getModified());
		}
		
		if(this.getObjectOnline().getModifiedBy() == null) {
			if (this.getObjectOffline().getModifiedBy() == null)
				this.getObjectMapped().setModifiedBy(new URI("http://an.URI.to.set"));
			else
				this.getObjectMapped().setModifiedBy(this.getObjectOffline().getModifiedBy());
		} else if (this.task == Task.OVERWRITE) {
			if (this.getObjectOffline().getModifiedBy() != null)
				this.getObjectMapped().setModifiedBy(this.getObjectOffline().getModifiedBy());
		}
		
		if(this.getObjectOnline().getStatus() == null) {
			if (this.getObjectOffline().getStatus() == null)
				this.getObjectMapped().setStatus(Status.PENDING);
			else
				this.getObjectMapped().setStatus(this.getObjectOffline().getStatus());
		} else if (this.task == Task.OVERWRITE) {
			if (this.getObjectOffline().getStatus() != null)
				this.getObjectMapped().setStatus(this.getObjectOffline().getStatus());
		}
		
		if(this.getObjectOnline().getDescription() == null) {
			if (this.getObjectOffline().getDescription() == null)
				this.getObjectMapped().setDescription("");
			else
				this.getObjectMapped().setDescription(this.getObjectOffline().getDescription());
		} else if (this.task == Task.OVERWRITE) {
			if (this.getObjectOffline().getDescription() != null)
				this.getObjectMapped().setDescription(this.getObjectOffline().getDescription());
		}
		
		// merge statements here
		if(this.getObjectOnline().getStatements() == null || this.getObjectOnline().getStatements().isEmpty()) {
			if(this.getObjectOffline().getStatements() == null || this.getObjectOffline().getStatements().isEmpty()) {
				
			} else {
				this.getObjectMapped().setStatements(this.getObjectOffline().getStatements());
			}
		} else if (this.task == Task.OVERWRITE) {
			if (this.getObjectOffline().getStatements() != null)
				this.getObjectMapped().setStatements(this.getObjectOffline().getStatements());
		}
		
		if(this.getObjectOnline().getTitle() == null) {
			if (this.getObjectOffline().getTitle() == null)
				this.getObjectMapped().setTitle("");
			else
				this.getObjectMapped().setTitle(this.getObjectOffline().getTitle());
		}  else if (this.task == Task.OVERWRITE) {
			if (this.getObjectOffline().getTitle() != null)
				this.getObjectMapped().setTitle(this.getObjectOffline().getTitle());
		}

		return this.getObjectMapped();
	}

	public static String getOfflineMDFilename(String inputFilename) {
		int i = inputFilename.lastIndexOf('.');
		if(i < 0)
			return inputFilename + "_merged_md";
		return inputFilename.substring(0, i) + "_merged_md" + inputFilename.substring(i);
	}
	

}
