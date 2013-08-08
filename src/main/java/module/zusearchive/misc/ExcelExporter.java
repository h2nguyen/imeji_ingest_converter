package module.zusearchive.misc;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import module.zusearchive.misc.ZuseXmlItemReader.SysSigVor;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;


public class ExcelExporter {
		
	public static final String EXTENSION = ".jpg";
	
	public ExcelExporter() {
		// TODO Auto-generated constructor stub
	}
	
	public static void exportSysSigVor(ArrayList<SysSigVor> sysSigVor, String filename) throws IOException, RowsExceededException, WriteException {
		if(!filename.endsWith(".xls") || !filename.endsWith(".xlsx"))
			filename.concat(".xls");
		
		WritableWorkbook workbook = Workbook.createWorkbook(new File(filename));
		
		WritableSheet sheet = workbook.createSheet("Sources", 0);
		
		// headers for the column
		Label labelSys = new Label(0, 0, "Sys");
		Label labelSig = new Label(1, 0, "Signatur");
		Label labelVor = new Label(2, 0, "Vorl__Nr_");
		Label labelFilename = new Label(3, 0, "Filename");
		sheet.addCell(labelSys);
		sheet.addCell(labelSig);
		sheet.addCell(labelVor);		
		sheet.addCell(labelFilename);
		
		for (int i = 0; i < sysSigVor.size(); i++) {
			sheet.addCell(new Label(0, i+1, sysSigVor.get(i).sys));
			sheet.addCell(new Label(1, i+1, sysSigVor.get(i).sig));
			sheet.addCell(new Label(2, i+1, sysSigVor.get(i).vor));
			
			String imageFN = "zuse_archive_";
			if(sysSigVor.get(i).sig.isEmpty()) {
				String vor = sysSigVor.get(i).vor.replace("/", "_").replace(" ", "");
				imageFN += vor + EXTENSION;
			} else {
				String sig = sysSigVor.get(i).sig.replace("P ", "").replace("/",	"_").replace(" ", "_");
				imageFN += sig + EXTENSION;
			}
			
			
			sheet.addCell(new Label(3, i+1, imageFN));
		}
		
		workbook.write(); 
		workbook.close();
	}

}
