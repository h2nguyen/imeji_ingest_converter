/**
 * 
 */
package module.zusearchive.jaxb.interfaces;


import java.io.FileNotFoundException;

import javax.xml.bind.JAXBException;

import module.zusearchive.jaxb.ZuseArchiveSchemaFilename;
import module.zusearchive.vo.generated.OUnterlagen;

import org.xml.sax.SAXException;


/**
 * @author hnguyen
 *
 */
public interface IJaxbOUnterlagen {	
		
	public String xsdFilename = ZuseArchiveSchemaFilename.ZUSEARCHIVE_XSDFILE;
	
	public void marshalOUnterlagen( String xmlFilename, OUnterlagen oul) throws JAXBException, SAXException, FileNotFoundException;

	public OUnterlagen unmarshalOUnterlagen(String xmlFilename) throws JAXBException, SAXException;
}
