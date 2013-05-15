/**
 * 
 */
package module.zusearchive.jaxb;

import java.io.File;
import java.io.FileNotFoundException;

import javax.xml.bind.JAXBException;

import org.xml.sax.SAXException;

import core.jaxb.JaxbGenericObject;
import core.jaxb.JaxbUtil;
import core.jaxb.interfaces.IJaxbGenericObject;

/**
 * @author hnguyen
 * @param <T>
 *
 */
public class JaxbZuseGenericObject<T> extends JaxbGenericObject<T> {

	public String xsdFilename = ZuseArchiveSchemaFilename.ZUSEARCHIVE_XSDFILE;
	
	public JaxbZuseGenericObject(Class<T> type) {
		super(type);	
	}
	
	@Override
	public void marshal(String xmlFilename, T t) throws JAXBException,
			SAXException, FileNotFoundException {
		super.marshal(this.xsdFilename, xmlFilename, t);
	}

	@Override
	public T unmarshal(String xmlFilename) throws JAXBException, SAXException {		
		return super.unmarshal(this.xsdFilename, xmlFilename, this.getClassType() );
	}

	@Override
	public void marshal(File xmlFile, T t) throws JAXBException, SAXException,
			FileNotFoundException {
		super.marshal(this.xsdFilename, xmlFile, t);
	}

	@Override
	public T unmarshal(File xmlFile) throws JAXBException, SAXException {
		return unmarshal(this.xsdFilename, xmlFile, this.getClassType());
	}

}
