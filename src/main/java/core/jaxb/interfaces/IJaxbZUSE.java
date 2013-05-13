/**
 * 
 */
package main.java.core.jaxb.interfaces;


import java.io.FileNotFoundException;

import javax.xml.bind.JAXBException;

import main.java.core.jaxb.SchemaFilename;
import main.java.core.vo.generated.ZUSE;

import org.xml.sax.SAXException;



/**
 * @author hnguyen
 *
 */
public interface IJaxbZUSE {	
	
	public String xsdFilename = SchemaFilename.ZUSE_XSDFILE;
	
	public void marshalZuseObject( String xmlFilename, ZUSE zo) throws JAXBException, SAXException, FileNotFoundException;

	public ZUSE unmarshalZuseObject(String xmlFilename) throws JAXBException, SAXException;
}
