/**
 * 
 */
package module.zusearchive.jaxb.interfaces;

import java.io.File;
import java.io.FileNotFoundException;

import javax.xml.bind.JAXBException;

import module.zusearchive.jaxb.ZuseArchiveSchemaFilename;

import org.xml.sax.SAXException;

import core.mapper.StatementsIdMapper;

/**
 * @author hnguyen
 *
 */
public interface IJaxbZuseArchiveStatementsIdMapper {	
	
	public String xsdFilename = ZuseArchiveSchemaFilename.ZUSE_STATEMENTSIDMAPPING_XSDFILE;
	
	/**
	 * This method exports the stsIdMapper to the xml file through the given schema file.
	 * @param xmlFileName, the xml filename to output
	 * @throws JAXBException
	 * @throws SAXException
	 * @throws FileNotFoundException 
	 */
	public void marshalStsIdMapper( String xmlFilename, StatementsIdMapper stsIdMapper) throws JAXBException, SAXException, FileNotFoundException;

	
	/**
	 * This method generates the stsIdMapper from the xml file.
	 * @param xmlFileName, the xml filename specified the content of the item information
	 * @return the MetadataProfile object
	 * @throws JAXBException
	 * @throws SAXException
	 */
	public StatementsIdMapper unmarshalStsIdMapper(String xmlFilename) throws JAXBException, SAXException;
	
	/**
	 * This method exports the stsIdMapper to the xml file through the given schema file.
	 * @param xmlFileName, the xml filename to output
	 * @throws JAXBException
	 * @throws SAXException
	 * @throws FileNotFoundException 
	 */
	public void marshalStsIdMapper(File xmlFile, StatementsIdMapper stsIdMapper) throws JAXBException, SAXException, FileNotFoundException;

	
	/**
	 * This method generates the stsIdMapper from the xml file.
	 * @param xmlFileName, the xml filename specified the content of the item information
	 * @return the MetadataProfile object
	 * @throws JAXBException
	 * @throws SAXException
	 */
	public StatementsIdMapper unmarshalStsIdMapper(File xmlFile) throws JAXBException, SAXException;
}
