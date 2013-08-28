package misc;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import module.zusearchive.misc.CreateZuseCollectionFolder;
import module.zusearchive.misc.ExcelExporter;
import module.zusearchive.misc.ZuseXmlItemReader;
import module.zusearchive.misc.ZuseXmlItemReader.SysSigVor;
import module.zusearchive.misc.ZuseXmlItemReader.SysSigVorUmf;
import module.zusearchive.misc.ZuseXmlItemReader.SysSigVorUmfPag;
import module.zusearchive.misc.ZuseXmlItemReader.SysSy2SigVorUmfPag;

import org.junit.Test;

public class ZuseExcelExportTest {

	//@Test
	public void test() throws RowsExceededException, WriteException, IOException {
		String filename = "C:/Users/hnguyen/Documents/My Dropbox/zuse_local/zuse_metadata_issue/xmls/_130823_xml_issues/ZusePMWithoutPTagsKorrected.xml";
		
		String filenameExcel = "src/test/resources/export/test5.xls";
		
		ZuseXmlItemReader zxmlir = new ZuseXmlItemReader(filename);
		ArrayList<SysSigVorUmfPag> sysSigVorUmfPage = zxmlir.getSysAndExtItsIdsAndPages();
		
//		ExcelExporter.exportSysSigVorUmf(sysSigVorUmf, filenameExcel);
		ExcelExporter.exportVorPages(sysSigVorUmfPage, filenameExcel);
		
	}
	
	@Test
	public void createFolders() throws RowsExceededException, WriteException, IOException {
		String filename = "C:/Users/hnguyen/Documents/My Dropbox/zuse_local/zuse_metadata_issue/xmls/_130823_xml_issues/ZusePM_wo_scannen.xml";
		
		ZuseXmlItemReader zxmlir = new ZuseXmlItemReader(filename);
		ArrayList<String> unique = zxmlir.getAllUniqueSys();
		ArrayList<String> unique2 = zxmlir.getAllUniqueSys2();
		
		for (String string : unique) {
			System.out.println(string);
		}
		
		for (String string : unique2) {
			System.out.println(string);
		}
		
		CreateZuseCollectionFolder.createFolders(unique);
		CreateZuseCollectionFolder.createFolders(unique2);
		
	}
	
	//@Test
	public void exportExcelFileInFolder() throws RowsExceededException, WriteException, IOException {
		//String filename = "C:/Users/hnguyen/Documents/My Dropbox/zuse_local/zuse_metadata_issue/xmls/_130823_xml_issues/ZusePMWithoutPTagsKorrected.xml";
		String filename = "C:/Users/hnguyen/Documents/My Dropbox/zuse_local/zuse_metadata_issue/xmls/_130823_xml_issues/ZusePM_wo_scannen.xml";
		
		String filenameExcel = "src/test/resources/export/fileInFolder.xls";
		
		ZuseXmlItemReader zxmlir = new ZuseXmlItemReader(filename);
		ArrayList<SysSy2SigVorUmfPag> sysSy2SigVorUmfPage = zxmlir.getSysSy2AndExtItsIdsAndPages();
		
//			ExcelExporter.exportSysSigVorUmf(sysSigVorUmf, filenameExcel);
		ExcelExporter.exportSysSy2SigVorUmfPag(sysSy2SigVorUmfPage, filenameExcel);
		
	}

}
