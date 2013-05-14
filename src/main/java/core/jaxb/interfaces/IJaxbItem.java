/**
 * 
 */
package core.jaxb.interfaces;

import java.io.File;
import java.io.FileNotFoundException;

import javax.xml.bind.JAXBException;

import org.xml.sax.SAXException;

import core.jaxb.ImejiSchemaFilename;
import core.vo.imeji.Item;

/**
 * @author hnguyen
 */
public interface IJaxbItem
{
    public String xsdFilename = ImejiSchemaFilename.IMEJI_ITEM_XSDFILE;

    /**
     * This method exports the item to the xml file through the given schema file.
     * 
     * @param xmlFile, the xml file to output
     * @throws JAXBException
     * @throws SAXException
     * @throws FileNotFoundException
     */
    public void marshalItem(String xmlFilename, Item item) throws JAXBException, SAXException, FileNotFoundException;

    /**
     * This method generates the item from the xml file.
     * 
     * @param xmlFile, the xml file specified the content of the item information
     * @return the MetadataProfile object
     * @throws JAXBException
     * @throws SAXException
     */
    public Item unmarshalItem(String xmlFilename) throws JAXBException, SAXException;

    /**
     * This method exports the item to the xml file through the given schema file.
     * 
     * @param xmlFile, the xml file to output
     * @throws JAXBException
     * @throws SAXException
     * @throws FileNotFoundException
     */
    public void marshalItem(File xmlFile, Item item) throws JAXBException, SAXException, FileNotFoundException;

    /**
     * This method generates the item from the xml file.
     * 
     * @param xmlFile, the xml file specified the content of the item information
     * @return the MetadataProfile object
     * @throws JAXBException
     * @throws SAXException
     */
    public Item unmarshalItem(File xmlFile) throws JAXBException, SAXException;
}
