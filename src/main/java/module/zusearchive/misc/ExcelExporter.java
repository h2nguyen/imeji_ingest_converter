package module.zusearchive.misc;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import module.zusearchive.misc.ZuseXmlItemReader.SysSigVor;
import module.zusearchive.misc.ZuseXmlItemReader.SysSigVorUmf;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.Number;
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
	
	public static void exportSysSigVorUmf(ArrayList<SysSigVorUmf> sysSigVorUmfList, String filename) throws IOException, RowsExceededException, WriteException {
		if(!filename.endsWith(".xls") || !filename.endsWith(".xlsx"))
			filename.concat(".xls");
		
		WritableWorkbook workbook = Workbook.createWorkbook(new File(filename));
		
		WritableSheet sheet = workbook.createSheet("Sources", 0);
		
		// headers for the column
		Label labelSys = new Label(0, 0, "Sys");
		Label labelSig = new Label(1, 0, "Signatur");
		Label labelVor = new Label(2, 0, "Vorl__Nr_");
		Label labelUmf = new Label(3, 0, "Umfang");
		Label labelFilename = new Label(4, 0, "Filename");
		
		sheet.addCell(labelSys);
		sheet.addCell(labelSig);
		sheet.addCell(labelVor);		
		sheet.addCell(labelUmf);
		sheet.addCell(labelFilename);
		
		int rowCounter = 1;
		
		for (SysSigVorUmf sysSigVorUmf : sysSigVorUmfList) {
			
		
						
			String imageFN = "zuse_archive_";
			
			if(sysSigVorUmf.sig.isEmpty()) {
				String vor = sysSigVorUmf.vor.replace("/", "_").replace(" ", "");
				imageFN += vor;
			} else {
				String sig = sysSigVorUmf.sig.replace("P ", "").replace("/",	"_").replace(" ", "_");
				imageFN += sig;
			}
			
			int pages = 0;
			
			if(!sysSigVorUmf.umf.isEmpty()) {
				String[] umf = sysSigVorUmf.umf.split(" ");
				if(umf.length > 0) {
					try {
						pages = Integer.parseInt(umf[0]);
					} catch (NumberFormatException nfe) {
						nfe.printStackTrace();
					}
					
				}
			}
			
			if(pages > 0) {
				
				for (int j = 1; j <= pages; j++) {
					String otherFN = imageFN + "-" + String.format("%03d", j) + EXTENSION; 

					sheet.addCell(new Label(0, rowCounter, sysSigVorUmf.sys));
					sheet.addCell(new Label(1, rowCounter, sysSigVorUmf.sig));
					sheet.addCell(new Label(2, rowCounter, sysSigVorUmf.vor));
					sheet.addCell(new Label(3, rowCounter, sysSigVorUmf.umf));
					sheet.addCell(new Label(4, rowCounter, otherFN));
					++rowCounter;
				}
			} else {
				imageFN += EXTENSION;
				
				sheet.addCell(new Label(0, rowCounter, sysSigVorUmf.sys));
				sheet.addCell(new Label(1, rowCounter, sysSigVorUmf.sig));
				sheet.addCell(new Label(2, rowCounter, sysSigVorUmf.vor));
				sheet.addCell(new Label(3, rowCounter, sysSigVorUmf.umf));
				sheet.addCell(new Label(4, rowCounter, imageFN));
				
				++rowCounter;
			}
			
			
		}
		
		workbook.write(); 
		workbook.close();
	}
	
	public static void exportVorPages(ArrayList<SysSigVorUmf> sysSigVorUmfList, String filename) throws IOException, RowsExceededException, WriteException {
		if(!filename.endsWith(".xls") || !filename.endsWith(".xlsx"))
			filename.concat(".xls");
		
		WritableWorkbook workbook = Workbook.createWorkbook(new File(filename));
		
		WritableSheet sheet = workbook.createSheet("Sources", 0);
		
		// headers for the column
		Label labelVor = new Label(0, 0, "Vorlage");
		Label labelPag = new Label(1, 0, "Pages");
		sheet.addCell(labelVor);
		sheet.addCell(labelPag);
		
		for (int i = 0; i < sysSigVorUmfList.size(); i++) {
			if(!sysSigVorUmfList.get(i).vor.isEmpty() && !sysSigVorUmfList.get(i).umf.isEmpty()) {
				String vor = "207_" + sysSigVorUmfList.get(i).vor.replace("/", "_").replace(" ", "");
				String[] umf = sysSigVorUmfList.get(i).umf.split(" ");
				int pages = 0;
				if(umf.length > 0) {
					try {
						pages = Integer.parseInt(umf[0]);
					} catch (NumberFormatException nfe) {
						nfe.printStackTrace();
					}
				}
				
				sheet.addCell(new Label(0, i+1, vor));
				sheet.addCell(new Number(1, i+1, pages));
			}
		}
		
		workbook.write(); 
		workbook.close();
	}

}
