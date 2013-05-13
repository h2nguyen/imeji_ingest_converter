/**
 * 
 */
package module.zusearchive.jaxb.interfaces;


import java.io.FileNotFoundException;

import javax.xml.bind.JAXBException;

import module.zusearchive.jaxb.ZuseArchiveSchemaFilename;
import module.zusearchive.vo.generated.ZUSE;

import org.xml.sax.SAXException;




/**
 * @author hnguyen
 *
 */
public interface IJaxbZUSE {	
	
	public String xsdFilename = ZuseArchiveSchemaFilename.ZUSEARCHIVE_XSDFILE;
	
	public void marshalZuseObject( String xmlFilename, ZUSE zo) throws JAXBException, SAXException, FileNotFoundException;

	public ZUSE unmarshalZuseObject(String xmlFilename) throws JAXBException, SAXException;
}
