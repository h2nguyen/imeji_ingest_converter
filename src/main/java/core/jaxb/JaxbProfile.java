/**
 * 
 */
package core.jaxb;

import java.io.File;
import java.io.FileNotFoundException;

import javax.xml.bind.JAXBException;

import org.xml.sax.SAXException;

import core.jaxb.interfaces.IJaxbObject;

/**
 * 
 * @author hnguyen
 *
 * @param <T>
 */
public abstract class JaxbProfile<T> extends JaxbUtil implements IJaxbObject<T> {

	@Override
	public void marshal(String xmlFilename, T t) throws JAXBException,
			SAXException, FileNotFoundException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public T unmarshal(String xmlFilename) throws JAXBException, SAXException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void marshal(File xmlFile, T t) throws JAXBException, SAXException,
			FileNotFoundException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public T unmarshal(File xmlFile) throws JAXBException, SAXException {
		// TODO Auto-generated method stub
		return null;
	}


}
