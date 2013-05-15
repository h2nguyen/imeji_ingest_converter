/**
 * 
 */
package module.zusearchive.jaxb;

import java.io.File;
import java.io.FileNotFoundException;

import javax.xml.bind.JAXBException;


import org.xml.sax.SAXException;

import core.jaxb.JaxbUtil;
import core.jaxb.interfaces.IJaxbObject;

/**
 * @author hnguyen
 * @param <T>
 *
 */
public class JaxbOType<T> extends JaxbUtil implements IJaxbObject<T> {

	
	private Class<T> type;
	
	
    public JaxbOType(Class<T> type) {
         this.type = type;
    }

    public Class<T> getClassType() {
        return this.type;
    }
	
	public String xsdFilename = ZuseArchiveSchemaFilename.ZUSEARCHIVE_XSDFILE;		
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
