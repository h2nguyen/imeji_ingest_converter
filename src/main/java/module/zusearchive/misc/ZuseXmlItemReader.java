package module.zusearchive.misc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ZuseXmlItemReader {

	private File xmlFile;
	private DocumentBuilderFactory dbf;
	private DocumentBuilder db;

	public ZuseXmlItemReader(String filename) {

		this.xmlFile = new File(filename);
		this.dbf = DocumentBuilderFactory.newInstance();
		try {
			this.db = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public ArrayList<String> getAllUniqueSys() {

		ArrayList<String> oul = new ArrayList<String>();
		
		if (this.xmlFile.exists()) {
			Document doc = null;
			Element docEle = null;
			try {
				doc = this.db.parse(this.xmlFile);
				docEle = doc.getDocumentElement();
			} catch (SAXException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			

			// Print root element of the document
			System.out.println("Root element of the document: "
					+ docEle.getNodeName());

			NodeList itemList = docEle.getElementsByTagName("oUnterlagen");

			System.out.println("Total item: " + itemList.getLength());

			
			
			if (itemList != null && itemList.getLength() > 0) {
				for (int i = 0; i < itemList.getLength(); i++) {

					Node node = itemList.item(i);					

					if (node.getNodeType() == Node.ELEMENT_NODE) {

						// System.out
						// .println("=====================");

						Element e = (Element) node;
						NodeList nodeList = e.getElementsByTagName("Sys");

						String value = nodeList.item(0).getChildNodes().item(0)
								.getNodeValue();

						boolean canAdd = true;

						for (int j = 0; j < oul.size(); j++) {
							if (value.equalsIgnoreCase(oul.get(j))) {
								canAdd = false;
								break;
							}
						}

						if (canAdd) {
							oul.add(value);
							//System.out.println("Sys: " + value);
						}

					}
				}
			} else {
				System.exit(1);
			}
		}

		return oul;
	}

	public ArrayList<SysSigVor> getSysAndItsIds() {

		if (this.xmlFile.exists()) {
			
			ArrayList<SysSigVor> sysSigVors = new ArrayList<ZuseXmlItemReader.SysSigVor>();
			
			Document doc = null;
			Element docEle = null;
			try {
				doc = this.db.parse(this.xmlFile);
				docEle = doc.getDocumentElement();
			} catch (SAXException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			NodeList itemList = docEle.getElementsByTagName("oUnterlagen");

			NodeList nodeListSys = null;
			NodeList nodeListSig = null;
			NodeList nodeListVor = null;

			if (itemList != null && itemList.getLength() > 0) {
				for (int i = 0; i < itemList.getLength(); i++) {

					Node node = itemList.item(i);

					if (node.getNodeType() == Node.ELEMENT_NODE) {

						Element e = (Element) node;
						
						
						nodeListSys = e.getElementsByTagName("Sys");
						String sys = "";
						if(nodeListSys.item(0).getChildNodes().getLength() > 0)
							sys = nodeListSys.item(0).getChildNodes().item(0).getNodeValue();
						
						nodeListSig = e.getElementsByTagName("Signatur");
						String sig = "";
						if(nodeListSig.item(0).getChildNodes().getLength() > 0)
							sig = nodeListSig.item(0).getChildNodes().item(0).getNodeValue();						
						
						nodeListVor = e.getElementsByTagName("Vorl__Nr_");
						String vor = "";
						if(nodeListVor.item(0).getChildNodes().getLength() > 0)
							vor = nodeListVor.item(0).getChildNodes().item(0).getNodeValue();
							
						sysSigVors.add(new SysSigVor(sys, sig, vor));
					}
				}
			}
			
			return sysSigVors;
		}
		return null;
		
	}
	
	public ArrayList<SysSigVorUmf> getSysAndExtItsIds() {

		if (this.xmlFile.exists()) {
			
			ArrayList<SysSigVorUmf> sysSigVorUmfs = new ArrayList<ZuseXmlItemReader.SysSigVorUmf>();
			
			Document doc = null;
			Element docEle = null;
			try {
				doc = this.db.parse(this.xmlFile);
				docEle = doc.getDocumentElement();
			} catch (SAXException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			NodeList itemList = docEle.getElementsByTagName("oUnterlagen");

			NodeList nodeListSys = null;
			NodeList nodeListSig = null;
			NodeList nodeListVor = null;
			NodeList nodeListUmf = null;

			if (itemList != null && itemList.getLength() > 0) {
				for (int i = 0; i < itemList.getLength(); i++) {

					Node node = itemList.item(i);

					if (node.getNodeType() == Node.ELEMENT_NODE) {

						Element e = (Element) node;
						
						
						nodeListSys = e.getElementsByTagName("Sys");
						String sys = "";
						if(nodeListSys.item(0).getChildNodes().getLength() > 0)
							sys = nodeListSys.item(0).getChildNodes().item(0).getNodeValue();
						
						nodeListSig = e.getElementsByTagName("Signatur");
						String sig = "";
						if(nodeListSig.item(0).getChildNodes().getLength() > 0)
							sig = nodeListSig.item(0).getChildNodes().item(0).getNodeValue();						
						
						nodeListVor = e.getElementsByTagName("Vorl__Nr_");
						String vor = "";
						if(nodeListVor.item(0).getChildNodes().getLength() > 0)
							vor = nodeListVor.item(0).getChildNodes().item(0).getNodeValue();
						
						nodeListUmf = e.getElementsByTagName("Umfang");
						String umf = "";
						if(nodeListUmf.item(0).getChildNodes().getLength() > 0)
							umf = nodeListUmf.item(0).getChildNodes().item(0).getNodeValue();
							
						sysSigVorUmfs.add(new SysSigVorUmf(sys, sig, vor, umf));
					}
				}
			}
			
			return sysSigVorUmfs;
		}
		return null;
		
	}
	
	public class SysSigVor {
		public String sys;
		public String sig;
		public String vor;
		
		public SysSigVor(String sys, String sig, String vor) {
			this.sys = sys;
			this.sig = sig;
			this.vor = vor;
		}
	}
	
	public class SysSigVorUmf extends SysSigVor {
		public String umf;
		
		public SysSigVorUmf(String sys, String sig, String vor, String umf) {
			super(sys,sig,vor);
			this.umf = umf;
		}
	}
	
}
