package core;

import static org.junit.Assert.*;

import java.beans.IntrospectionException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;

import javax.xml.bind.JAXBException;

import jxl.read.biff.BiffException;

import module.zusearchive.converter.ZuseExcelConverter;
import org.junit.Test;
import org.xml.sax.SAXException;

import core.jaxb.JaxbGenericObject;
import core.jaxb.JaxbUtil;
import core.vo.imeji.Items;
import core.vo.imeji.MetadataProfile;

public class ZuseExcelConverterTest {

	@Test
	public void testGetMdProfile() throws BiffException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException, JAXBException {
		File file = new File("src/test/resources/excelmd/zuse_pdf_list.xls");
		ZuseExcelConverter zec = new ZuseExcelConverter();
		MetadataProfile mdp = zec.getMdProfile(file, "PDF Metadata", "Description of the metadata for the pdf files");
		JaxbUtil.toString(mdp);
	}

	@Test
	public void testGetItemsFile() throws BiffException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IntrospectionException, IOException, URISyntaxException, JAXBException, SAXException {
		File excelFile = new File("src/test/resources/excelmd/zuse_pdf_list.xls");
		File xmlFile = new File("src/test/resources/excelmd/online_mpd.xml");
		OutputStream os = new FileOutputStream("src/test/resources/excelmd/items_offline.xml");

		JaxbGenericObject<MetadataProfile> jzgo = new JaxbGenericObject<MetadataProfile>(MetadataProfile.class);
		MetadataProfile mdp = jzgo.unmarshal(xmlFile);
		
		ZuseExcelConverter zec = new ZuseExcelConverter();
		Items items = zec.getItems(excelFile,mdp);
		
		JaxbUtil.writeToOutputStream(items, os);
	}

	@Test
	public void testGetItemsListOfExcelEntry() {
		fail("Not yet implemented");
	}

}
