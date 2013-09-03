package misc;

import static org.junit.Assert.*;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.IOException;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import module.zusearchive.misc.CopyToZuseFolder;
import module.zusearchive.vo.generated.ExcelEntry;

import org.junit.Test;

public class MiscTest {

	
	public void testCopyToFolder() throws BiffException, IOException {
		
		Workbook workbook = Workbook.getWorkbook(new File("src/test/resources/export/test3.xls"));
		Sheet sheet = workbook.getSheet(0);
		
		int filenameCol = CopyToZuseFolder.getColumnNum(sheet, "Filename");
		int folderCol = CopyToZuseFolder.getColumnNum(sheet, "Sys");
		
		System.out.println("Filename col: " + filenameCol);
		System.out.println("Folder col: " + folderCol);
		workbook.close();
	}
	
	@Test
	public void testAnyPropertyDescriptor() {
		try {
			for(PropertyDescriptor propertyDescriptor : 
			    Introspector.getBeanInfo(ExcelEntry.class).getPropertyDescriptors()){

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

}
