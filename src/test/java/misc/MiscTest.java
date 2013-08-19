package misc;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import module.zusearchive.misc.CopyToZuseFolder;

import org.junit.Test;

public class MiscTest {

	@Test
	public void test() throws BiffException, IOException {
		
		Workbook workbook = Workbook.getWorkbook(new File("src/test/resources/export/test3.xls"));
		Sheet sheet = workbook.getSheet(0);
		
		int filenameCol = CopyToZuseFolder.getColumnNum(sheet, "Filename");
		int folderCol = CopyToZuseFolder.getColumnNum(sheet, "Sys");
		
		System.out.println("Filename col: " + filenameCol);
		System.out.println("Folder col: " + folderCol);
		workbook.close();
	}

}
