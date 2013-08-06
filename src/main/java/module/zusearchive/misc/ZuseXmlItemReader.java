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

	// private void readData() throws FileNotFoundException, XMLStreamException
	// {
	// InputStream is = new FileInputStream(this.xmlFile);
	// XMLInputFactory factory = XMLInputFactory.newInstance();
	// XMLStreamReader reader = factory.createXMLStreamReader(is);
	//
	//
	// while(reader.hasNext())
	// {
	//
	// if(reader.hasText())
	// {
	// if(reader.hasName())
	// System.out.println(reader.getName());
	// System.out.println(reader.getText());
	// }
	// reader.next();
	// }
	// }

	public void getAllUniqueSys() {

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

			ArrayList<String> oul = new ArrayList<String>();
			
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
							System.out.println("Sys: " + value);
						}

						// System.out.println("Sys: "
						// + nodeList.item(0).getChildNodes().item(0)
						// .getNodeValue());

						// nodeList = e.getElementsByTagName("grade");
						// System.out.println("Grade: "
						// + nodeList.item(0).getChildNodes().item(0)
						// .getNodeValue());
						//
						// nodeList = e.getElementsByTagName("age");
						// System.out.println("Age: "
						// + nodeList.item(0).getChildNodes().item(0)
						// .getNodeValue());
					}
				}
			} else {
				System.exit(1);
			}
		}

	}

}
