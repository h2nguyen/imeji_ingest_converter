/**
 * 
 */
package main.java.core.jaxb.interfaces;

import java.io.File;
import java.io.FileNotFoundException;

import javax.xml.bind.JAXBException;

import main.java.core.jaxb.SchemaFilename;
import main.java.core.vo.MetadataProfiles;

import org.xml.sax.SAXException;

/**
 * @author hnguyen
 *
 */
public interface IJaxbMetadataProfiles {
	
	public String xsdFilename = SchemaFilename.IMEJI_METADATAPROFILES_XSDFILE;
	
	/**
	 * This method exports the meta data profiles to the xml file through the given schema file.
	 * @param xmlFile, the xml file to output
	 * @throws JAXBException
	 * @throws SAXException
	 * @throws FileNotFoundException 
	 */
	public void marshalMdProfiles(String xmlFilename, MetadataProfiles mdp) throws JAXBException, SAXException, FileNotFoundException;	
	
	/**
	 * This method generates the meta data profiles from the xml file.
	 * @param xmlFile, the xml file specified a list of meta data profile
	 * @return the MetadataProfile object
	 * @throws JAXBException
	 * @throws SAXException
	 */	
	public MetadataProfiles unmarshalMdProfiles(String xmlFilename) throws JAXBException, SAXException;
	
	/**
	 * This method exports the meta data profiles to the xml file through the given schema file.
	 * @param xmlFile, the xml file to output
	 * @throws JAXBException
	 * @throws SAXException
	 * @throws FileNotFoundException 
	 */
	public void marshalMdProfiles(File xmlFile, MetadataProfiles mdp) throws JAXBException, SAXException, FileNotFoundException;	
	
	/**
	 * This method generates the meta data profiles from the xml file.
	 * @param xmlFile, the xml file specified a list of meta data profile
	 * @return the MetadataProfile object
	 * @throws JAXBException
	 * @throws SAXException
	 */	
	public MetadataProfiles unmarshalMdProfiles(File xmlFile) throws JAXBException, SAXException;
}
