package misc;


import javax.xml.parsers.ParserConfigurationException;

import module.zusearchive.misc.ZuseXmlItemReader;
import module.zusearchive.misc.ZuseXmlItemReader;

import org.junit.Test;

public class ZuseXmlItemReaderTest {

	@Test
	public void test() {
		String filename = "C:/Users/hnguyen/Documents/My Dropbox/zuse_local/_130806newXml/ZusePM_without_ptag.xml";
		ZuseXmlItemReader zxmlir = new ZuseXmlItemReader(filename);
		zxmlir.getAllUniqueSys();
//		zxmlir2.printUniqueSys();
	}

}
