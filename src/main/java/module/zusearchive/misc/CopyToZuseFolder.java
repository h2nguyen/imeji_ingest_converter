package module.zusearchive.misc;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import jxl.LabelCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import static java.nio.file.StandardCopyOption.*;

public class CopyToZuseFolder {

	public CopyToZuseFolder() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String args[]) throws IOException, BiffException,
			URISyntaxException {

		File f = new File("."); // current directory

		File[] files = f.listFiles();
		for (File file : files) {
			if (file.isFile()) {
				String filename = file.getName();
				if (filename.endsWith("xls") || filename.endsWith("xlsx")) {
					moveFile(filename);
					return;
				}
			}
		}

	}

	public static void moveFile(String filename) throws BiffException,
			IOException, URISyntaxException {
		Workbook workbook = Workbook.getWorkbook(new File(filename));
		Sheet sheet = workbook.getSheet(0);
		
		int filenameCol = getColumnNum(sheet, "Filename");
		int folderCol = getColumnNum(sheet, "Sys");
		int folder2Col = getColumnNum(sheet, "Sys2");
		
		for (int i = 1; i < sheet.getRows(); i++) {
			try {
				
				File afile = new File(sheet.getCell(filenameCol, i).getContents());
				
				
				if(!sheet.getCell(folder2Col, i).getContents().isEmpty()) {
					if (afile.renameTo(new File(sheet.getCell(folder2Col, i).getContents()
							+ "/" + afile.getName()))) {
						System.out.println("File: "+afile.getName()+" is moved successfully!");
					}
				} else {
					if (afile.renameTo(new File(sheet.getCell(folderCol, i).getContents()
							+ "/" + afile.getName()))) {
						System.out.println("File: "+afile.getName()+" is moved successfully!");
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		workbook.close();
	}
	
	public static int getColumnNum(Sheet sheet, String colName) {
		return sheet.findLabelCell(colName).getColumn();
	}

}
