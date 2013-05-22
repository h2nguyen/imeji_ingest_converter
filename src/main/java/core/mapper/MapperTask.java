/**
 * 
 */
package core.mapper;

import javax.swing.SwingWorker;
import javax.xml.bind.JAXBException;

import org.xml.sax.SAXException;

import core.jaxb.JaxbGenericObject;
import core.task.enums.Task;

/**
 * @author hnguyen
 * @param <T>
 * @param <V>
 *
 */
public abstract class MapperTask<T, V> extends SwingWorker<T, V> {

	protected T objectOnline;
	protected T objectOffline;
	protected T objectMapped;
	protected Task task;

	public MapperTask(String objectOnlineFilename, String objectOfflineFilename, Task task, Class<T> classType) throws JAXBException, SAXException {
		
		this(new JaxbGenericObject<T>(classType).unmarshal(objectOnlineFilename),
			 new JaxbGenericObject<T>(classType).unmarshal(objectOfflineFilename), task);
	}
	
	public MapperTask(T objectOnline, T objectOffline, Task task) {
		this.setObjectOnline(objectOnline);
		this.setObjectOffline(objectOffline);
		this.setTask(task);
	}
	
	public T getObjectOnline() {
		return objectOnline;
	}


	public void setObjectOnline(T objectOnline) {
		this.objectOnline = objectOnline;
	}


	public T getObjectOffline() {
		return objectOffline;
	}


	public void setObjectOffline(T objectOffline) {
		this.objectOffline = objectOffline;
	}


	public T getObjectMapped() {
		return objectMapped;
	}


	public void setObjectMapped(T objectMapped) {
		this.objectMapped = objectMapped;
	}


	public Task getTask() {
		return task;
	}


	public void setTask(Task task) {
		this.task = task;
	}


	@Override
	protected abstract T doInBackground() throws Exception;

}
