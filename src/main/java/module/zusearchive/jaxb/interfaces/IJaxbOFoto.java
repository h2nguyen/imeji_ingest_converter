/**
 * 
 */
package module.zusearchive.jaxb.interfaces;


import java.io.FileNotFoundException;

import javax.xml.bind.JAXBException;

import module.zusearchive.jaxb.ZuseArchiveSchemaFilename;
import module.zusearchive.vo.generated.OFoto;

import org.xml.sax.SAXException;


/**
 * @author hnguyen
 *
 */
public interface IJaxbOFoto {	
	
	public String xsdFilename = ZuseArchiveSchemaFilename.ZUSEARCHIVE_XSDFILE;
	
	public void marshalOFoto( String xmlFilename, OFoto fot) throws JAXBException, SAXException, FileNotFoundException;

	public OFoto unmarshalOFoto(String xmlFilename) throws JAXBException, SAXException;
}
