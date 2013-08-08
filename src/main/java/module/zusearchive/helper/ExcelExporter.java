package module.zusearchive.helper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import module.zusearchive.misc.ZuseXmlItemReader.SysSigVor;

public class ExcelExporter {
		
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
		sheet.addCell(labelSys);
		sheet.addCell(labelSig);
		sheet.addCell(labelVor);
		
		
		
	}

}
