package core;



import static org.junit.Assert.fail;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import module.zusearchive.converter.MetadataProfileConverter;
import module.zusearchive.helper.ZuseMdProfile;
import module.zusearchive.jaxb.JaxbIngestProfileZuse;
import module.zusearchive.jaxb.ZuseArchiveSchemaFilename;
import module.zusearchive.vo.generated.OUnterlagen;
import module.zusearchive.vo.generated.ZUSE;

import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import core.helper.Download;
import core.jaxb.JaxbUtil;
import core.mapper.StatementIdMapper;
import core.mapper.StatementsIdMapper;
import core.task.ItemConverterTask;
import core.vo.Item;
import core.vo.Items;
import core.vo.MetadataProfile;


public class ZuseTest {
	
	
	@Test
	public void ingestConverterProcessTest() throws JAXBException, SAXException {
		String filenameUnmarshal = "src/test/resources/_10_entries.xml";
		
		JaxbIngestProfileZuse jmp = new JaxbIngestProfileZuse();
		
		OUnterlagen oul = jmp.unmarshalOUnterlagen(filenameUnmarshal);
		
		JaxbUtil.toString(oul);
		
		
	}
	
	
	public void testXMLdownTest() throws IOException, ParserConfigurationException, SAXException, TransformerException {
		
			
		URL url = new URL("http://dev-faces.mpdl.mpg.de/export?format=rdf&type=image&n=10000&col=15&q=");
		URLConnection conn = url.openConnection();
	
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(conn.getInputStream());
	
		TransformerFactory factory2 = TransformerFactory.newInstance();
		Transformer xform = factory2.newTransformer();
	
		// that’s the default xform; use a stylesheet to get a real one
		xform.transform(new DOMSource(doc), new StreamResult(System.out));
	}
	
	public void anyPropertyDescriptor() {
		try {
			for(PropertyDescriptor propertyDescriptor : 
			    Introspector.getBeanInfo(OUnterlagen.class).getPropertyDescriptors()){

			    // propertyEditor.getReadMethod() exposes the getter
			    // btw, this may be null if you have a write-only property
			    System.out.println(propertyDescriptor.getReadMethod().getName());
			}
		} catch (IntrospectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public void zuseMdProfileTest() {
		List<?> z = (List<?>) ZuseMdProfile.enum2list(ZuseMdProfile.class);
		for (int i = 0; i < z.size(); i++) {
			System.out.println(((ZuseMdProfile) z.get(i)).getAttributes()[ZuseMdProfile.Column.TYPE.ordinal()]);
		}
		
//		System.out.println(ZuseMdProfile.ALT_SIGNATUR.getAttributes()[1]);
		
//		for (int i = 0; i < ZuseMdProfile.getAttributes().length; i++) {
//			System.out.println(zmdp.getAttributes()[i].toString());
//		}
		
	}
	
	public void testMetadataProfileConveter() {
		try {

			String filenameUnmarshal = "./dump/ts_unmarshal.xml";
			String filenameMarshal = "./dump/ts_unmarshal_marshal2.xml";
			
			
			JaxbIngestProfileZuse jmp = new JaxbIngestProfileZuse();
			
			OUnterlagen oul = jmp.unmarshalOUnterlagen(filenameUnmarshal);
			
			MetadataProfile mdp = MetadataProfileConverter.convertFromZuseMdProfile(oul,"Title","Description");
			
			jmp.marshalMdProfile(filenameMarshal, mdp);
			
			
//			JaxbIngestProfile.toString(mdp);
			
		} catch (JAXBException e) {			
			e.printStackTrace();
			fail("JAXBException");
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("SAXException");
		} catch (IntrospectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("IntrospectionException");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	

	}
	
	public void testItemConveter() {
		try {

			String filename = "./dump/ts.xml";
			String filenameMarshal = "./dump/ts_marshal2.xml";
			JaxbIngestProfileZuse jmp = new JaxbIngestProfileZuse();
			
			OUnterlagen oul = jmp.unmarshalOUnterlagen(filename);
			
			Item item = ItemConverterTask.convertFromZuseMdProfile(oul);
			
			jmp.marshalItem(filenameMarshal, item);
//			JaxbIngestProfileZuse.toString(item);
			
		} catch (JAXBException e) {			
			e.printStackTrace();
			fail("JAXBException");
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("SAXException");
		} catch (IntrospectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("IntrospectionException");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	

	}
	
	public void testItemsConveter() {
		try {

			String filenameUnmarshal = "./dump/test_samples-converted.xml";
			String filenameMarshal = "./dump/test_samples-converted-marshal-items.xml";
			JaxbIngestProfileZuse jmp = new JaxbIngestProfileZuse();
			
			ZUSE zo = jmp.unmarshalZuseObject(filenameUnmarshal);
			
			Items items = new Items();
			items.setItem(ItemConverterTask.convertFromZuseMdProfile(zo.getoUnterlagen()));
			
			jmp.marshalItems(filenameMarshal, items);
			
//			JaxbIngestProfile.toString(items);
	
		} catch (JAXBException e) {
			
			e.printStackTrace();
			fail("JAXBException");
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("SAXException");
		} catch (IntrospectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("IntrospectionException");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void testZuseObjectUnmarshal() {
		try {

			String filename = "./dump/test_samples-converted.xml";
			JaxbIngestProfileZuse jmp = new JaxbIngestProfileZuse();
			
			ZUSE zo = jmp.unmarshalZuseObject(filename);
			
			JaxbIngestProfileZuse.toString(zo);
			
		} catch (JAXBException e) {
			
			e.printStackTrace();
			fail("JAXBException");
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("SAXException");
		}
	}
	
	public void testZuseObjectUnmarshalMarshal() {
		try {
	
			String filenameUnmarshal = "./dump/test_samples-converted.xml";
			String filenameMarshal = "./dump/test_samples-converted-marshal.xml";
			JaxbIngestProfileZuse jmp = new JaxbIngestProfileZuse();
			
			ZUSE zo = jmp.unmarshalZuseObject(filenameUnmarshal);
			
			jmp.marshalZuseObject(filenameMarshal, zo);
			JaxbIngestProfileZuse.toString(zo);
			
		} catch (JAXBException e) {
			
			e.printStackTrace();
			fail("JAXBException");
		}
		catch (SAXException e) {
			e.printStackTrace();
			fail("SAXException");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void testOUnterlagenUnmarshal() {
		try {

			String filename = "./dump/ts.xml";
			JaxbIngestProfileZuse jmp = new JaxbIngestProfileZuse();
			
			OUnterlagen oul = jmp.unmarshalOUnterlagen(filename);
			
			JaxbIngestProfileZuse.toString(oul);
			
		} catch (JAXBException e) {
			
			e.printStackTrace();
			fail("JAXBException");
		} catch (SAXException e) {
			e.printStackTrace();
			fail("SAXException");
		}
	}
	
	public void testOUnterlageMarshal() {
		try {

			String filename = "./dump/ts_marshal.xml";
			JaxbIngestProfileZuse jmp = new JaxbIngestProfileZuse();
			
			OUnterlagen oul = new OUnterlagen();
			oul.setTitel("der Titel");
			oul.setDarstellung("die Darstellung");
			
			jmp.marshalOUnterlagen(filename,oul);
			
			JaxbIngestProfileZuse.toString(oul);
			
		} catch (JAXBException e) {
			
			e.printStackTrace();
			fail("JAXBException");
		} catch (SAXException e) {
			e.printStackTrace();
			fail("SAXException");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void testOUnterlageUnmarshalMarshal() {
		try {

			String filenameUnmarshal = "./dump/ts.xml";
			String filenameMarshal = "./dump/ts_marshal.xml";
			JaxbIngestProfileZuse jmp = new JaxbIngestProfileZuse();
			
			OUnterlagen oul = jmp.unmarshalOUnterlagen(filenameUnmarshal);
			
			jmp.marshalOUnterlagen(filenameMarshal,oul);
			
//			jmp.toString(oul);
			
		} catch (JAXBException e) {
			
			e.printStackTrace();
			fail("JAXBException");
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("SAXException");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void testOnlineProfileUnmarshal() throws JAXBException, SAXException {
		String filenameUnmarshal = "./dump/online_raw_empty_profile.xml";
		
		JaxbIngestProfileZuse jmp = new JaxbIngestProfileZuse();
		
		MetadataProfile mdp = jmp.unmarshalMdProfile(filenameUnmarshal);
		
		JaxbIngestProfileZuse.toString(mdp);
		
	}
		
	public void testDownloadXML() {
		Download.getXML("http://localhost:8080/imeji/export?format=xml&type=profile&id=http%3A//zuse.zib.de/metadataProfile/35");
	}
	
	public void testCreateStatementIdMapper() throws FileNotFoundException, JAXBException, SAXException {
		StatementsIdMapper stsIdMapper = new StatementsIdMapper();
		
		StatementIdMapper stIdMapper1 = new StatementIdMapper();
		stIdMapper1.setStatementOffline("stOffline1");
		stIdMapper1.setStatementOnline("stOnline1");
		stIdMapper1.setTagName("tagName1");
		StatementIdMapper stIdMapper2 = new StatementIdMapper();
		stIdMapper2.setStatementOffline("stOffline2");
		stIdMapper2.setStatementOnline("stOnline2");
		stIdMapper2.setTagName("tagName2");
		
		stsIdMapper.getStatementIdMapper().add(stIdMapper1);
		stsIdMapper.getStatementIdMapper().add(stIdMapper2);		
		
		
		JaxbUtil.marshal(ZuseArchiveSchemaFilename.ZUSE_STATEMENTSIDMAPPING_XSDFILE, "dump/mapping.xml", stsIdMapper);
	}
}
