package misc;

import static org.junit.Assert.*;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import module.zusearchive.misc.CopyToZuseFolder;
import module.zusearchive.vo.generated.ExcelEntry4PDF;

import org.junit.Test;

public class MiscTest {

	//@Test
	public void testCopyToFolder() throws BiffException, IOException {
		
		Workbook workbook = Workbook.getWorkbook(new File("src/test/resources/export/test3.xls"));
		Sheet sheet = workbook.getSheet(0);
		
		int filenameCol = CopyToZuseFolder.getColumnNum(sheet, "Filename");
		int folderCol = CopyToZuseFolder.getColumnNum(sheet, "Sys");
		
		System.out.println("Filename col: " + filenameCol);
		System.out.println("Folder col: " + folderCol);
		workbook.close();
	}
	
	//@Test
	public void testAnyPropertyDescriptor() {
		try {
			for(PropertyDescriptor propertyDescriptor : 
			    Introspector.getBeanInfo(ExcelEntry4PDF.class).getPropertyDescriptors()){

				if (propertyDescriptor.getReadMethod().getName().equalsIgnoreCase("getClass")) {
					System.out.println("i have this getClass");
					continue;
				}
				
//				if (propertyDescriptor.getReadMethod().getReturnType() == String.class)
					System.out.println(propertyDescriptor.getReadMethod().getName());
				
			    
			    
			}
		} catch (IntrospectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	@Test
	public void testDigitParsing() {
		
		String str = "113 Bl. gedr., ms.12 1 u. hektogr. (1 Bd. Kopie)";
        str = str.replaceAll("\\D+"," ");
        System.out.println(str);
	}
	
//	@Test
	public void addVerwarhtungsort() throws IOException {
		
		Path pathIn = Paths.get("src/test/resources/ingest_final/ZusePMNormalized.xml");
		Path pathOut = Paths.get("src/test/resources/ingest_final/ZusePMNormalizedOut.xml");
		
		Charset charset = StandardCharsets.UTF_8;

		String content = new String(Files.readAllBytes(pathIn), charset);
		content = content.replaceAll("</oUnterlagen>", "<verwahrungsort>Deutsches Museum, Munich, Archives (DMA)</verwahrungsort>\n</oUnterlagen>");
		Files.write(pathOut, content.getBytes(charset));
	}
	
	@Test
	public void addLicense() throws IOException {
		
		Path pathIn = Paths.get("src/test/resources/ingest_final/ZusePMNormalizedFinalWithOriLoc.xml");
		Path pathOut = Paths.get("src/test/resources/ingest_final/ZusePMNormalizedFinalWithOriLocAndLisense.xml");
		
		Charset charset = StandardCharsets.UTF_8;

		String content = new String(Files.readAllBytes(pathIn), charset);
		content = content.replaceAll("</oUnterlagen>", "<license><![CDATA[<a href=\"http://creativecommons.org/licenses/by-nc-sa/3.0/\" target=\"_blank\">]]>CC BY-NC-SA<![CDATA[</a>]]></license>\n</oUnterlagen>");
		Files.write(pathOut, content.getBytes(charset));
	}

}
