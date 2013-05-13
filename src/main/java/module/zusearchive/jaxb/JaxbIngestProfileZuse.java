/**
 * 
 */
package module.zusearchive.jaxb;

import java.io.File;
import java.io.FileNotFoundException;

import javax.xml.bind.JAXBException;

import module.zusearchive.jaxb.interfaces.IJaxbOFoto;
import module.zusearchive.jaxb.interfaces.IJaxbOUnterlagen;
import module.zusearchive.jaxb.interfaces.IJaxbZUSE;
import module.zusearchive.jaxb.interfaces.IJaxbZuseArchiveStatementsIdMapper;
import module.zusearchive.vo.generated.OFoto;
import module.zusearchive.vo.generated.OUnterlagen;
import module.zusearchive.vo.generated.ZUSE;

import org.xml.sax.SAXException;

import core.jaxb.JaxbIngestProfile;
import core.mapper.StatementsIdMapper;


/**
 * @author hnguyen
 *
 */
public class JaxbIngestProfileZuse extends JaxbIngestProfile implements IJaxbOUnterlagen, IJaxbOFoto, IJaxbZUSE, IJaxbZuseArchiveStatementsIdMapper {
	
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
		String xsdFile = IJaxbZuseArchiveStatementsIdMapper.xsdFilename;
		
		if( xmlFilename.isEmpty() || xsdFile.isEmpty() )
		{
			System.err.println("\nSchema file or Xml file not provided!");						
		}

		super.marshal( xsdFile, xmlFilename, stsIdMapper );
	}

	
	public StatementsIdMapper unmarshalStsIdMapper(String xmlFilename)
			throws JAXBException, SAXException {
		String xsdFile = IJaxbZuseArchiveStatementsIdMapper.xsdFilename;
		
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
