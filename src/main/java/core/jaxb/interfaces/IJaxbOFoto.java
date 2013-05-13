/**
 * 
 */
package main.java.core.jaxb.interfaces;


import java.io.FileNotFoundException;

import javax.xml.bind.JAXBException;

import main.java.core.jaxb.SchemaFilename;
import main.java.core.vo.generated.OFoto;

import org.xml.sax.SAXException;


/**
 * @author hnguyen
 *
 */
public interface IJaxbOFoto {	
	
	public String xsdFilename = SchemaFilename.ZUSE_OFOTO_XSDFILE;
	
	public void marshalOFoto( String xmlFilename, OFoto fot) throws JAXBException, SAXException, FileNotFoundException;

	public OFoto unmarshalOFoto(String xmlFilename) throws JAXBException, SAXException;
}
