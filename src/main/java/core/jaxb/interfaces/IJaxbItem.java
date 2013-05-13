/**
 * 
 */
package main.java.core.jaxb.interfaces;


import java.io.File;
import java.io.FileNotFoundException;

import javax.xml.bind.JAXBException;

import main.java.core.jaxb.SchemaFilename;
import main.java.core.vo.Item;

import org.xml.sax.SAXException;

/**
 * @author hnguyen
 *
 */
public interface IJaxbItem {	
	
	public String xsdFilename = SchemaFilename.IMEJI_ITEM_XSDFILE;
	
	/**
	 * This method exports the item to the xml file through the given schema file.
	 * @param xmlFile, the xml file to output
	 * @throws JAXBException
	 * @throws SAXException
	 * @throws FileNotFoundException 
	 */
	public void marshalItem(String xmlFilename, Item item) throws JAXBException, SAXException, FileNotFoundException;

	
	/**
	 * This method generates the item from the xml file.
	 * @param xmlFile, the xml file specified the content of the item information
	 * @return the MetadataProfile object
	 * @throws JAXBException
	 * @throws SAXException
	 */
	public Item unmarshalItem(String xmlFilename) throws JAXBException, SAXException;
	
	/**
	 * This method exports the item to the xml file through the given schema file.
	 * @param xmlFile, the xml file to output
	 * @throws JAXBException
	 * @throws SAXException
	 * @throws FileNotFoundException 
	 */
	public void marshalItem(File xmlFile, Item item) throws JAXBException, SAXException, FileNotFoundException;

	
	/**
	 * This method generates the item from the xml file.
	 * @param xmlFile, the xml file specified the content of the item information
	 * @return the MetadataProfile object
	 * @throws JAXBException
	 * @throws SAXException
	 */
	public Item unmarshalItem(File xmlFile) throws JAXBException, SAXException;
}
