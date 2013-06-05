/**
 * 
 */
package core.helper;

import java.io.File;
import java.io.IOException;

import jxl.Workbook;
import jxl.write.WritableWorkbook;

/**
 * @author hnguyen
 *
 */
public class ExcelReader {

	
	protected WritableWorkbook workbook;
	
	/**
	 * @throws IOException 
	 * 
	 */
	public ExcelReader(String filename) throws IOException {
		this(new File("output.xls"));
	}
	
	/**
	 * @throws IOException 
	 * 
	 */
	public ExcelReader(File file) throws IOException {
		this.workbook = Workbook.createWorkbook(file);
	}

}
