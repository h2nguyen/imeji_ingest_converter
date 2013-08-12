package misc;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import module.zusearchive.misc.ExcelExporter;
import module.zusearchive.misc.ZuseXmlItemReader;
import module.zusearchive.misc.ZuseXmlItemReader.SysSigVor;
import module.zusearchive.misc.ZuseXmlItemReader.SysSigVorUmf;

import org.junit.Test;

public class ZuseExcelExportTest {

	@Test
	public void test() throws RowsExceededException, WriteException, IOException {
		String filename = "C:/Users/hnguyen/Documents/My Dropbox/zuse_local/zuse_metadata_issue/xmls/_130806_xml_issues/ZusePM_final.xml";
		
		String filenameExcel = "src/test/resources/export/test3.xls";
		
		ZuseXmlItemReader zxmlir = new ZuseXmlItemReader(filename);
		ArrayList<SysSigVorUmf> sysSigVorUmf = zxmlir.getSysAndExtItsIds();
		
		ExcelExporter.exportSysSigVorUmf(sysSigVorUmf, filenameExcel);
		
	}

}
