package misc;

import module.zusearchive.misc.CreateZuseCollectionFolder;
import module.zusearchive.misc.ZuseXmlItemReader;

import org.junit.Test;

public class ZuseXmlItemReaderTest {

	@Test
	public void test() {
		String filename = "C:/Users/hnguyen/Documents/My Dropbox/zuse_local/zuse_metadata_issue/xmls/_130806_xml_issues/ZusePM_without_ptag.xml";
		ZuseXmlItemReader zxmlir = new ZuseXmlItemReader(filename);
		CreateZuseCollectionFolder.createFolders(zxmlir.getAllUniqueSys());
	}

}
