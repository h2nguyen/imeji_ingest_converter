/**
 * 
 */
package main.java.core.jaxb;

import java.io.File;
import java.io.FileNotFoundException;

import javax.xml.bind.JAXBException;

import main.java.core.jaxb.interfaces.IJaxbIngestProfile;
import main.java.core.jaxb.interfaces.IJaxbItem;
import main.java.core.jaxb.interfaces.IJaxbItems;
import main.java.core.jaxb.interfaces.IJaxbMetadataProfile;
import main.java.core.jaxb.interfaces.IJaxbMetadataProfiles;
import main.java.core.jaxb.interfaces.IJaxbOFoto;
import main.java.core.jaxb.interfaces.IJaxbOUnterlagen;
import main.java.core.jaxb.interfaces.IJaxbStatementsIdMapper;
import main.java.core.jaxb.interfaces.IJaxbZUSE;
import main.java.core.mapper.StatementsIdMapper;
import main.java.core.vo.IngestProfile;
import main.java.core.vo.Item;
import main.java.core.vo.Items;
import main.java.core.vo.MetadataProfile;
import main.java.core.vo.MetadataProfiles;
import main.java.core.vo.generated.OFoto;
import main.java.core.vo.generated.OUnterlagen;
import main.java.core.vo.generated.ZUSE;

import org.xml.sax.SAXException;


/**
 * @author hnguyen
 *
 */
public class JaxbIngestProfileZuse extends JaxbIngestProfile implements IJaxbOUnterlagen, IJaxbOFoto, IJaxbZUSE, IJaxbStatementsIdMapper {
	
	/**
	 * 
	 */
	public JaxbIngestProfileZuse() {
		// TODO Auto-generated constructor stub
	}	

	
	public void marshalOUnterlagen(String xmlFilename, OUnterlagen oul)
			throws JAXBException, SAXException, FileNotFoundException {
		String xsdFile = IJaxbOUnterlagen.xsdFilename;
		
		if( xmlFilename.isEmpty() || xsdFile.isEmpty() )
		{
			System.err.println("\nSchema file or Xml file not provided!");						
		}

		super.marshal( xsdFile, xmlFilename, oul );
	}

	
	public OUnterlagen unmarshalOUnterlagen(String xmlFilename) throws JAXBException,
			SAXException {
		String xsdFile = IJaxbOUnterlagen.xsdFilename;
		
		if( xmlFilename.isEmpty() || xsdFile.isEmpty() )
		{
			System.err.println("\nSchema file or Xml file not provided!");			
			return null;
		}		

		return super.unmarshal( xsdFile , xmlFilename, OUnterlagen.class );
	}

	
	public void marshalOFoto(String xmlFilename, OFoto fot)
			throws JAXBException, SAXException, FileNotFoundException {
		String xsdFile = IJaxbOFoto.xsdFilename;
		
		if( xmlFilename.isEmpty() || xsdFile.isEmpty() )
		{
			System.err.println("\nSchema file or Xml file not provided!");						
		}

		super.marshal( xsdFile, xmlFilename, fot );
	}

	
	public OFoto unmarshalOFoto(String xmlFilename) throws JAXBException,
			SAXException {
		String xsdFile = IJaxbOFoto.xsdFilename;
		
		if( xmlFilename.isEmpty() || xsdFile.isEmpty() )
		{
			System.err.println("\nSchema file or Xml file not provided!");			
			return null;
		}		

		return super.unmarshal( xsdFile , xmlFilename, OFoto.class );
	}
	
	
	public ZUSE unmarshalZuseObject(String xmlFilename) throws JAXBException, SAXException {
		String xsdFile = IJaxbZUSE.xsdFilename;
		
		if( xmlFilename.isEmpty() || xsdFile.isEmpty() )
		{
			System.err.println("\nSchema file or Xml file not provided!");			
			return null;
		}		

		return super.unmarshal( xsdFile , xmlFilename, ZUSE.class );
	}

	
	public void marshalZuseObject(String xmlFilename, ZUSE zo)
			throws JAXBException, SAXException, FileNotFoundException {
		String xsdFile = IJaxbZUSE.xsdFilename;
		
		if( xmlFilename.isEmpty() || xsdFile.isEmpty() )
		{
			System.err.println("\nSchema file or Xml file not provided!");						
		}

		super.marshal( xsdFile, xmlFilename, zo );
	}

	
	public void marshalStsIdMapper(String xmlFilename,
			StatementsIdMapper stsIdMapper) throws JAXBException, SAXException,
			FileNotFoundException {
		String xsdFile = IJaxbStatementsIdMapper.xsdFilename;
		
		if( xmlFilename.isEmpty() || xsdFile.isEmpty() )
		{
			System.err.println("\nSchema file or Xml file not provided!");						
		}

		super.marshal( xsdFile, xmlFilename, stsIdMapper );
	}

	
	public StatementsIdMapper unmarshalStsIdMapper(String xmlFilename)
			throws JAXBException, SAXException {
		String xsdFile = IJaxbStatementsIdMapper.xsdFilename;
		
		if( xmlFilename.isEmpty() || xsdFile.isEmpty() )
		{
			System.err.println("\nSchema file or Xml file not provided!");			
			return null;
		}		

		return super.unmarshal( xsdFile , xmlFilename, StatementsIdMapper.class );
	}

	
	public void marshalStsIdMapper(File xmlFile, StatementsIdMapper stsIdMapper)
			throws JAXBException, SAXException, FileNotFoundException {
		this.marshalStsIdMapper(xmlFile.getName(), stsIdMapper);
	}

	
	public StatementsIdMapper unmarshalStsIdMapper(File xmlFile)
			throws JAXBException, SAXException {
		return this.unmarshalStsIdMapper(xmlFile.getName());
	}
}
