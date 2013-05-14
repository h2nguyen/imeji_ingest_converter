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

import module.zusearchive.converter.ZuseMdProfileConverter;
import module.zusearchive.helper.ZuseNormalizer;
import module.zusearchive.jaxb.JaxbZuseProfile;
import module.zusearchive.jaxb.ZuseArchiveSchemaFilename;
import module.zusearchive.vo.generated.OUnterlagen;
import module.zusearchive.vo.generated.ZUSE;
import module.zusearchive.vo.generated.enums.ZuseOUnterlagenEMdProfileFormat;
import module.zusearchive.vo.generated.formats.ZuseNormFormat;

import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import core.helper.Download;
import core.jaxb.JaxbUtil;
import core.mapper.StatementIdMapper;
import core.mapper.StatementsIdMapper;
import core.task.ItemConverterTask;
import core.vo.imeji.Item;
import core.vo.imeji.Items;
import core.vo.imeji.MetadataProfile;


public class ZuseTest {
	
	
	
	//@Test
	public void normalizeProcessTest() {
		String inputFilename = "src/test/resources/_10_entries.xml";
		String outputFilename = "src/test/resources/_10_entries_out.xml";
		ZuseNormalizer n = new ZuseNormalizer(inputFilename,outputFilename);
		n.getNormalizedFile();
	}
	
	@Test
	public void ingestConverterProcessTest() throws JAXBException, SAXException, IntrospectionException {
		String filenameUnmarshal = "src/test/resources/_10_entries_out.xml";
		JaxbZuseProfile jmp = new JaxbZuseProfile();
		
		ZUSE zo = jmp.unmarshalZuseObject(filenameUnmarshal);
		//JaxbUtil.toString(zo);
		
		OUnterlagen oul = zo.getoUnterlagen().get(0);
		//JaxbUtil.toString(oul);
		
		ZuseNormFormat znf = new ZuseNormFormat();
		
		ZuseMdProfileConverter zmdpconv = new ZuseMdProfileConverter();
		
		MetadataProfile mdp = zmdpconv.getMdProfile(oul, "profile name", "profile description",znf.getZEMdProfileFormat());
		
		JaxbUtil.toString(mdp);
		
		
	}
	
	
//	public void testXMLdownTest() throws IOException, ParserConfigurationException, SAXException, TransformerException {
//		
//			
//		URL url = new URL("http://dev-faces.mpdl.mpg.de/export?format=rdf&type=image&n=10000&col=15&q=");
//		URLConnection conn = url.openConnection();
//	
//		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//		DocumentBuilder builder = factory.newDocumentBuilder();
//		Document doc = builder.parse(conn.getInputStream());
//	
//		TransformerFactory factory2 = TransformerFactory.newInstance();
//		Transformer xform = factory2.newTransformer();
//	
//		// that’s the default xform; use a stylesheet to get a real one
//		xform.transform(new DOMSource(doc), new StreamResult(System.out));
//	}
//	
//	public void anyPropertyDescriptor() {
//		try {
//			for(PropertyDescriptor propertyDescriptor : 
//			    Introspector.getBeanInfo(OUnterlagen.class).getPropertyDescriptors()){
//
//			    // propertyEditor.getReadMethod() exposes the getter
//			    // btw, this may be null if you have a write-only property
//			    System.out.println(propertyDescriptor.getReadMethod().getName());
//			}
//		} catch (IntrospectionException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//
//	public void zuseMdProfileTest() {
//		List<?> z = (List<?>) ZuseNormFormat.MdProfileFormat.enum2list(ZuseNormFormat.MdProfileFormat.class);
//		for (int i = 0; i < z.size(); i++) {
//			System.out.println(((ZuseNormFormat.MdProfileFormat) z.get(i)).getAttributes()[module.zusearchive.vo.generated.formats.Column.TYPE.ordinal()]);
//		}
//		
////		System.out.println(ZuseMdProfile.ALT_SIGNATUR.getAttributes()[1]);
//		
////		for (int i = 0; i < ZuseMdProfile.getAttributes().length; i++) {
////			System.out.println(zmdp.getAttributes()[i].toString());
////		}
//		
//	}
//	
//	public void testMetadataProfileConveter() {
//		try {
//
//			String filenameUnmarshal = "./dump/ts_unmarshal.xml";
//			String filenameMarshal = "./dump/ts_unmarshal_marshal2.xml";
//			
//			
//			JaxbZuseProfile jmp = new JaxbZuseProfile();
//			
//			OUnterlagen oul = jmp.unmarshalOUnterlagen(filenameUnmarshal);
//			
//			MetadataProfile mdp = ZuseMdProfileConverter.getMdProfile(oul,"Title","Description");
//			
//			jmp.marshalMdProfile(filenameMarshal, mdp);
//			
//			
////			JaxbIngestProfile.toString(mdp);
//			
//		} catch (JAXBException e) {			
//			e.printStackTrace();
//			fail("JAXBException");
//		} catch (SAXException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			fail("SAXException");
//		} catch (IntrospectionException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			fail("IntrospectionException");
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}	
//
//	}
//	
//	public void testItemConveter() {
//		try {
//
//			String filename = "./dump/ts.xml";
//			String filenameMarshal = "./dump/ts_marshal2.xml";
//			JaxbZuseProfile jmp = new JaxbZuseProfile();
//			
//			OUnterlagen oul = jmp.unmarshalOUnterlagen(filename);
//			
//			Item item = ItemConverterTask.convertFromZuseMdProfile(oul);
//			
//			jmp.marshalItem(filenameMarshal, item);
////			JaxbIngestProfileZuse.toString(item);
//			
//		} catch (JAXBException e) {			
//			e.printStackTrace();
//			fail("JAXBException");
//		} catch (SAXException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			fail("SAXException");
//		} catch (IntrospectionException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			fail("IntrospectionException");
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}	
//
//	}
//	
//	public void testItemsConveter() {
//		try {
//
//			String filenameUnmarshal = "./dump/test_samples-converted.xml";
//			String filenameMarshal = "./dump/test_samples-converted-marshal-items.xml";
//			JaxbZuseProfile jmp = new JaxbZuseProfile();
//			
//			ZUSE zo = jmp.unmarshalZuseObject(filenameUnmarshal);
//			
//			Items items = new Items();
//			items.setItem(ItemConverterTask.convertFromZuseMdProfile(zo.getoUnterlagen()));
//			
//			jmp.marshalItems(filenameMarshal, items);
//			
////			JaxbIngestProfile.toString(items);
//	
//		} catch (JAXBException e) {
//			
//			e.printStackTrace();
//			fail("JAXBException");
//		} catch (SAXException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			fail("SAXException");
//		} catch (IntrospectionException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			fail("IntrospectionException");
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	public void testZuseObjectUnmarshal() {
//		try {
//
//			String filename = "./dump/test_samples-converted.xml";
//			JaxbZuseProfile jmp = new JaxbZuseProfile();
//			
//			ZUSE zo = jmp.unmarshalZuseObject(filename);
//			
//			JaxbZuseProfile.toString(zo);
//			
//		} catch (JAXBException e) {
//			
//			e.printStackTrace();
//			fail("JAXBException");
//		} catch (SAXException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			fail("SAXException");
//		}
//	}
//	
//	public void testZuseObjectUnmarshalMarshal() {
//		try {
//	
//			String filenameUnmarshal = "./dump/test_samples-converted.xml";
//			String filenameMarshal = "./dump/test_samples-converted-marshal.xml";
//			JaxbZuseProfile jmp = new JaxbZuseProfile();
//			
//			ZUSE zo = jmp.unmarshalZuseObject(filenameUnmarshal);
//			
//			jmp.marshalZuseObject(filenameMarshal, zo);
//			JaxbZuseProfile.toString(zo);
//			
//		} catch (JAXBException e) {
//			
//			e.printStackTrace();
//			fail("JAXBException");
//		}
//		catch (SAXException e) {
//			e.printStackTrace();
//			fail("SAXException");
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	public void testOUnterlagenUnmarshal() {
//		try {
//
//			String filename = "./dump/ts.xml";
//			JaxbZuseProfile jmp = new JaxbZuseProfile();
//			
//			OUnterlagen oul = jmp.unmarshalOUnterlagen(filename);
//			
//			JaxbZuseProfile.toString(oul);
//			
//		} catch (JAXBException e) {
//			
//			e.printStackTrace();
//			fail("JAXBException");
//		} catch (SAXException e) {
//			e.printStackTrace();
//			fail("SAXException");
//		}
//	}
//	
//	public void testOUnterlageMarshal() {
//		try {
//
//			String filename = "./dump/ts_marshal.xml";
//			JaxbZuseProfile jmp = new JaxbZuseProfile();
//			
//			OUnterlagen oul = new OUnterlagen();
//			oul.setTitel("der Titel");
//			oul.setDarstellung("die Darstellung");
//			
//			jmp.marshalOUnterlagen(filename,oul);
//			
//			JaxbZuseProfile.toString(oul);
//			
//		} catch (JAXBException e) {
//			
//			e.printStackTrace();
//			fail("JAXBException");
//		} catch (SAXException e) {
//			e.printStackTrace();
//			fail("SAXException");
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	public void testOUnterlageUnmarshalMarshal() {
//		try {
//
//			String filenameUnmarshal = "./dump/ts.xml";
//			String filenameMarshal = "./dump/ts_marshal.xml";
//			JaxbZuseProfile jmp = new JaxbZuseProfile();
//			
//			OUnterlagen oul = jmp.unmarshalOUnterlagen(filenameUnmarshal);
//			
//			jmp.marshalOUnterlagen(filenameMarshal,oul);
//			
////			jmp.toString(oul);
//			
//		} catch (JAXBException e) {
//			
//			e.printStackTrace();
//			fail("JAXBException");
//		} catch (SAXException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			fail("SAXException");
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	public void testOnlineProfileUnmarshal() throws JAXBException, SAXException {
//		String filenameUnmarshal = "./dump/online_raw_empty_profile.xml";
//		
//		JaxbZuseProfile jmp = new JaxbZuseProfile();
//		
//		MetadataProfile mdp = jmp.unmarshalMdProfile(filenameUnmarshal);
//		
//		JaxbZuseProfile.toString(mdp);
//		
//	}
//		
//	public void testDownloadXML() {
//		Download.getXML("http://localhost:8080/imeji/export?format=xml&type=profile&id=http%3A//zuse.zib.de/metadataProfile/35");
//	}
//	
//	public void testCreateStatementIdMapper() throws FileNotFoundException, JAXBException, SAXException {
//		StatementsIdMapper stsIdMapper = new StatementsIdMapper();
//		
//		StatementIdMapper stIdMapper1 = new StatementIdMapper();
//		stIdMapper1.setStatementOffline("stOffline1");
//		stIdMapper1.setStatementOnline("stOnline1");
//		stIdMapper1.setTagName("tagName1");
//		StatementIdMapper stIdMapper2 = new StatementIdMapper();
//		stIdMapper2.setStatementOffline("stOffline2");
//		stIdMapper2.setStatementOnline("stOnline2");
//		stIdMapper2.setTagName("tagName2");
//		
//		stsIdMapper.getStatementIdMapper().add(stIdMapper1);
//		stsIdMapper.getStatementIdMapper().add(stIdMapper2);		
//		
//		
//		JaxbUtil.marshal(ZuseArchiveSchemaFilename.ZUSE_STATEMENTSIDMAPPING_XSDFILE, "dump/mapping.xml", stsIdMapper);
//	}
}
