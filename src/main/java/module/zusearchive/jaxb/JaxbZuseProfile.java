///**
// * 
// */
//package module.zusearchive.jaxb;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//
//import javax.xml.bind.JAXBException;
//
//import module.zusearchive.jaxb.interfaces.IJaxbOFoto;
//import module.zusearchive.jaxb.interfaces.IJaxbOUnterlagen;
//import module.zusearchive.jaxb.interfaces.IJaxbZUSE;
//import module.zusearchive.jaxb.interfaces.IJaxbZuseArchiveStatementsIdMapper;
//import module.zusearchive.vo.generated.OFoto;
//import module.zusearchive.vo.generated.OUnterlagen;
//import module.zusearchive.vo.generated.ZUSE;
//
//import org.xml.sax.SAXException;
//
//import core.jaxb.JaxbIngestProfile;
//import core.mapper.StatementsIdMapper;
//
///**
// * @author hnguyen
// * 
// */
//public class JaxbZuseProfile extends JaxbIngestProfile implements
//		IJaxbOUnterlagen, IJaxbOFoto, IJaxbZUSE,
//		IJaxbZuseArchiveStatementsIdMapper {
//
//	public JaxbZuseProfile() {
//		// TODO Auto-generated constructor stub
//	}
//
//	public void marshalOUnterlagen(String xmlFilename, OUnterlagen oul)
//			throws JAXBException, SAXException, FileNotFoundException {
//		super.marshal(IJaxbOUnterlagen.xsdFilename, xmlFilename, oul);
//	}
//
//	public OUnterlagen unmarshalOUnterlagen(String xmlFilename)
//			throws JAXBException, SAXException {
//		return super.unmarshal(IJaxbOUnterlagen.xsdFilename, xmlFilename,
//				OUnterlagen.class);
//	}
//
//	public void marshalOFoto(String xmlFilename, OFoto fot)
//			throws JAXBException, SAXException, FileNotFoundException {
//		super.marshal(IJaxbOFoto.xsdFilename, xmlFilename, fot);
//	}
//
//	public OFoto unmarshalOFoto(String xmlFilename) throws JAXBException,
//			SAXException {
//		return super
//				.unmarshal(IJaxbOFoto.xsdFilename, xmlFilename, OFoto.class);
//	}
//
//	public ZUSE unmarshalZuseObject(String xmlFilename) throws JAXBException,
//			SAXException {
//		return super.unmarshal(IJaxbZUSE.xsdFilename, xmlFilename, ZUSE.class);
//	}
//
//	public void marshalZuseObject(String xmlFilename, ZUSE zo)
//			throws JAXBException, SAXException, FileNotFoundException {
//		super.marshal(IJaxbZUSE.xsdFilename, xmlFilename, zo);
//	}
//
//	public void marshalStsIdMapper(String xmlFilename,
//			StatementsIdMapper stsIdMapper) throws FileNotFoundException,
//			JAXBException, SAXException {
//		super.marshal(IJaxbZuseArchiveStatementsIdMapper.xsdFilename,
//				xmlFilename, stsIdMapper);
//	}
//
//	public StatementsIdMapper unmarshalStsIdMapper(String xmlFilename)
//			throws JAXBException, SAXException {
//		return super.unmarshal(IJaxbZuseArchiveStatementsIdMapper.xsdFilename,
//				xmlFilename, StatementsIdMapper.class);
//	}
//
//	public void marshalStsIdMapper(File xmlFile, StatementsIdMapper stsIdMapper)
//			throws JAXBException, SAXException, FileNotFoundException {
//		this.marshalStsIdMapper(xmlFile.getName(), stsIdMapper);
//	}
//
//	public StatementsIdMapper unmarshalStsIdMapper(File xmlFile)
//			throws JAXBException, SAXException {
//		return this.unmarshalStsIdMapper(xmlFile.getName());
//	}
//}
