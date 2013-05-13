/**
 * 
 */
package main.java.core.jaxb.interfaces;


import java.io.FileNotFoundException;

import javax.xml.bind.JAXBException;

import main.java.core.jaxb.SchemaFilename;
import main.java.core.vo.generated.OUnterlagen;

import org.xml.sax.SAXException;


/**
 * @author hnguyen
 *
 */
public interface IJaxbOUnterlagen {	
		
	public String xsdFilename = SchemaFilename.ZUSE_OUNTERLAGEN_XSDFILE;
	
	public void marshalOUnterlagen( String xmlFilename, OUnterlagen oul) throws JAXBException, SAXException, FileNotFoundException;

	public OUnterlagen unmarshalOUnterlagen(String xmlFilename) throws JAXBException, SAXException;
}
