/**
 * 
 */
package core.mapper;

import java.net.URI;
import java.util.Calendar;

import javax.swing.SwingWorker;
import javax.xml.bind.JAXBException;

import org.xml.sax.SAXException;

import core.jaxb.JaxbGenericObject;
import core.vo.imeji.MetadataProfile;
import core.vo.imeji.Properties.Status;

/**
 * @author hnguyen
 *
 */
public class MdProfileMapperTask extends SwingWorker<MetadataProfile, Void> {
	
	public enum Task {
		OVERWRITE, UPDATE;
	}
	
	private MetadataProfile mdpOnline;
	private MetadataProfile mdpOffline;
	private MetadataProfile mappedMpd;
	private Task task;
	
	
	/**
	 * 
	 * @param mdpOnline
	 * @param mdpOffline
	 * @throws SAXException 
	 * @throws JAXBException 
	 */
	public MdProfileMapperTask(String mdpOnlineFilename, String mdpOfflineFilename, Task task) throws JAXBException, SAXException {
		
		this(new JaxbGenericObject<MetadataProfile>(MetadataProfile.class).unmarshal(mdpOnlineFilename),
			 new JaxbGenericObject<MetadataProfile>(MetadataProfile.class).unmarshal(mdpOfflineFilename), task);
	}
	
	
	public MdProfileMapperTask(MetadataProfile mdpOnline, MetadataProfile mdpOffline, Task task) {
		this.setMdpOnline(mdpOnline);
		this.setMdpOffline(mdpOffline);
		this.setTask(task);
	}


	/**
	 * @return the mdpOnline
	 */
	public MetadataProfile getMdpOnline() {
		return mdpOnline;
	}


	/**
	 * @param mdpOnline the mdpOnline to set
	 */
	public void setMdpOnline(MetadataProfile mdpOnline) {
		this.mdpOnline = mdpOnline;
	}


	/**
	 * @return the mdpOffline
	 */
	public MetadataProfile getMdpOffline() {
		return mdpOffline;
	}


	/**
	 * @param mdpOffline the mdpOffline to set
	 */
	public void setMdpOffline(MetadataProfile mdpOffline) {
		this.mdpOffline = mdpOffline;
	}


	public MetadataProfile getMappedMpd() {
		return mappedMpd;
	}


	public void setMappedMpd(MetadataProfile mappedMpd) {
		this.mappedMpd = mappedMpd;
	}


	/**
	 * @return the task
	 */
	public Task getTask() {
		return task;
	}


	/**
	 * @param task the task to set
	 */
	public void setTask(Task task) {
		this.task = task;
	}


	@Override
	protected MetadataProfile doInBackground() throws Exception {
		
		this.mappedMpd = this.mdpOnline;
		
		// maps metadata properties
		if(this.mdpOnline.getCreated() == null) {
			if (this.mdpOffline.getCreated() == null)
				this.mappedMpd.setCreated(Calendar.getInstance());
			else
				this.mappedMpd.setCreated(this.mdpOffline.getCreated());
		} else if (this.task == Task.OVERWRITE) {
			if (this.mdpOffline.getCreated() != null)
				this.mappedMpd.setCreated(this.mdpOffline.getCreated());
		}
		
		if(this.mdpOnline.getCreatedBy() == null) {
			if (this.mdpOffline.getCreatedBy() == null)
				this.mappedMpd.setCreatedBy(new URI("http://an.URI.to.set"));
			else
				this.mappedMpd.setCreatedBy(this.mdpOffline.getCreatedBy());
		} else if (this.task == Task.OVERWRITE) {
			if (this.mdpOffline.getCreatedBy() != null)
				this.mappedMpd.setCreatedBy(this.mdpOffline.getCreatedBy());
		}
		
		if(this.mdpOnline.getDiscardComment() == null) {
			if (this.mdpOffline.getDiscardComment() == null)
				this.mappedMpd.setDiscardComment("");
			else
				this.mappedMpd.setDiscardComment(this.mdpOffline.getDiscardComment());
		} else if (this.task == Task.OVERWRITE) {
			if (this.mdpOffline.getDiscardComment() != null)
				this.mappedMpd.setDiscardComment(this.mdpOffline.getDiscardComment());
		}
		
		if(this.mdpOnline.getModified() == null) {
			if (this.mdpOffline.getModified() == null)
				this.mappedMpd.setModified(Calendar.getInstance());
			else
				this.mappedMpd.setModified(this.mdpOffline.getModified());
		} else if (this.task == Task.OVERWRITE) {
			if (this.mdpOffline.getModified() != null)
				this.mappedMpd.setModified(this.mdpOffline.getModified());
		}
		
		if(this.mdpOnline.getModifiedBy() == null) {
			if (this.mdpOffline.getModifiedBy() == null)
				this.mappedMpd.setModifiedBy(new URI("http://an.URI.to.set"));
			else
				this.mappedMpd.setModifiedBy(this.mdpOffline.getModifiedBy());
		} else if (this.task == Task.OVERWRITE) {
			if (this.mdpOffline.getModifiedBy() != null)
				this.mappedMpd.setModifiedBy(this.mdpOffline.getModifiedBy());
		}
		
		if(this.mdpOnline.getStatus() == null) {
			if (this.mdpOffline.getStatus() == null)
				this.mappedMpd.setStatus(Status.PENDING);
			else
				this.mappedMpd.setStatus(this.mdpOffline.getStatus());
		} else if (this.task == Task.OVERWRITE) {
			if (this.mdpOffline.getStatus() != null)
				this.mappedMpd.setStatus(this.mdpOffline.getStatus());
		}
		
		if(this.mdpOnline.getDescription() == null) {
			if (this.mdpOffline.getDescription() == null)
				this.mappedMpd.setDescription("");
			else
				this.mappedMpd.setDescription(this.mdpOffline.getDescription());
		} else if (this.task == Task.OVERWRITE) {
			if (this.mdpOffline.getDescription() != null)
				this.mappedMpd.setDescription(this.mdpOffline.getDescription());
		}
		
		// merge statements here
		if(this.mdpOnline.getStatements() == null || this.mdpOnline.getStatements().isEmpty()) {
			if(this.mdpOffline.getStatements() == null || this.mdpOffline.getStatements().isEmpty()) {
				
			} else {
				this.mappedMpd.setStatements(this.mdpOffline.getStatements());
			}
		} else if (this.task == Task.OVERWRITE) {
			if (this.mdpOffline.getStatements() != null)
				this.mappedMpd.setStatements(this.mdpOffline.getStatements());
		}
		
		if(this.mdpOnline.getTitle() == null) {
			if (this.mdpOffline.getTitle() == null)
				this.mappedMpd.setTitle("");
			else
				this.mappedMpd.setTitle(this.mdpOffline.getTitle());
		}  else if (this.task == Task.OVERWRITE) {
			if (this.mdpOffline.getTitle() != null)
				this.mappedMpd.setTitle(this.mdpOffline.getTitle());
		}

		return this.mappedMpd;
	}
	

}
