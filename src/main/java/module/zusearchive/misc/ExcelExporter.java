package module.zusearchive.misc;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import module.zusearchive.misc.ZuseXmlItemReader.SysSigVor;
import module.zusearchive.misc.ZuseXmlItemReader.SysSigVorUmf;
import module.zusearchive.misc.ZuseXmlItemReader.SysSigVorUmfPag;
import module.zusearchive.misc.ZuseXmlItemReader.SysSy2SigVorUmfPag;

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
	
	public static void exportSysSigVorUmfPag(ArrayList<SysSigVorUmfPag> sysSigVorUmfPagList, String filename) throws IOException, RowsExceededException, WriteException {
		if(!filename.endsWith(".xls") || !filename.endsWith(".xlsx"))
			filename.concat(".xls");
		
		WritableWorkbook workbook = Workbook.createWorkbook(new File(filename));
		
		WritableSheet sheet = workbook.createSheet("Sources", 0);
		
		// headers for the column
		Label labelSys = new Label(0, 0, "Sys");
		Label labelSig = new Label(1, 0, "Signatur");
		Label labelVor = new Label(2, 0, "Vorl__Nr_");
		Label labelUmf = new Label(3, 0, "Umfang");
		Label labelPag = new Label(4, 0, "Seiten_-Digitalisate-");
		Label labelFilename = new Label(5, 0, "Filename");
		
		sheet.addCell(labelSys);
		sheet.addCell(labelSig);
		sheet.addCell(labelVor);		
		sheet.addCell(labelUmf);
		sheet.addCell(labelPag);
		sheet.addCell(labelFilename);
		
		int rowCounter = 1;
		
		for (SysSigVorUmfPag sysSigVorUmfPag : sysSigVorUmfPagList) {
			
		
						
			String imageFN = "zuse_archive_";
			
			if(sysSigVorUmfPag.sig.isEmpty()) {
				String vor = sysSigVorUmfPag.vor.replace("/", "_").replace(" ", "");
				imageFN += vor;
			} else {
				String sig = sysSigVorUmfPag.sig.replace("P ", "p").replace("/",	"_").replace(" ", "_");
				imageFN += sig;
			}
			
			int pages = 0;
			
			if(!sysSigVorUmfPag.pag.isEmpty()) {
				pages = Integer.parseInt(sysSigVorUmfPag.pag.trim());
			}
			
			if(pages > 0) {
				
				for (int j = 1; j <= pages; j++) {
					String otherFN = imageFN + "-" + String.format("%03d", j) + EXTENSION; 

					sheet.addCell(new Label(0, rowCounter, sysSigVorUmfPag.sys));
					sheet.addCell(new Label(1, rowCounter, sysSigVorUmfPag.sig));
					sheet.addCell(new Label(2, rowCounter, sysSigVorUmfPag.vor));
					sheet.addCell(new Label(3, rowCounter, sysSigVorUmfPag.umf));
					sheet.addCell(new Label(4, rowCounter, sysSigVorUmfPag.pag));
					sheet.addCell(new Label(5, rowCounter, otherFN));
					++rowCounter;
				}
			} else {
				imageFN += EXTENSION;
				
				sheet.addCell(new Label(0, rowCounter, sysSigVorUmfPag.sys));
				sheet.addCell(new Label(1, rowCounter, sysSigVorUmfPag.sig));
				sheet.addCell(new Label(2, rowCounter, sysSigVorUmfPag.vor));
				sheet.addCell(new Label(3, rowCounter, sysSigVorUmfPag.umf));
				sheet.addCell(new Label(4, rowCounter, sysSigVorUmfPag.pag));
				sheet.addCell(new Label(5, rowCounter, imageFN));
				
				++rowCounter;
			}
			
			
		}
		
		workbook.write(); 
		workbook.close();
	}
	
	public static void exportSysSy2SigVorUmfPag(ArrayList<SysSy2SigVorUmfPag> sysSy2SigVorUmfPagList, String filename) throws IOException, RowsExceededException, WriteException {
		if(!filename.endsWith(".xls") || !filename.endsWith(".xlsx"))
			filename.concat(".xls");
		
		WritableWorkbook workbook = Workbook.createWorkbook(new File(filename));
		
		WritableSheet sheet = workbook.createSheet("Sources", 0);
		
		// headers for the column
		Label labelSys = new Label(0, 0, "Sys");
		Label labelSy2 = new Label(1, 0, "Sys2");
		Label labelSig = new Label(2, 0, "Signatur");
		Label labelVor = new Label(3, 0, "Vorl__Nr_");
		Label labelUmf = new Label(4, 0, "Umfang");
		Label labelPag = new Label(5, 0, "Seiten_-Digitalisate-");
		Label labelFilename = new Label(6, 0, "Filename");
		
		sheet.addCell(labelSys);
		sheet.addCell(labelSy2);
		sheet.addCell(labelSig);
		sheet.addCell(labelVor);		
		sheet.addCell(labelUmf);
		sheet.addCell(labelPag);
		sheet.addCell(labelFilename);
		
		int rowCounter = 1;
		
		for (SysSy2SigVorUmfPag sysSigVorUmfPag : sysSy2SigVorUmfPagList) {
			
		
						
			String imageFN = "zuse_archive_";
			
			if(sysSigVorUmfPag.sig.isEmpty()) {
				String vor = sysSigVorUmfPag.vor.replace("/", "_").replace(" ", "");
				imageFN += vor;
			} else {
				String sig = sysSigVorUmfPag.sig.replace("P ", "p").replace("/",	"_").replace(" ", "_");
				imageFN += sig;
			}
			
			int pages = 0;
			
			if(!sysSigVorUmfPag.pag.isEmpty()) {
				pages = Integer.parseInt(sysSigVorUmfPag.pag.trim());
			}
			
			if(pages > 0) {
				
				for (int j = 1; j <= pages; j++) {
					String otherFN = imageFN + "-" + String.format("%03d", j) + EXTENSION; 

					sheet.addCell(new Label(0, rowCounter, sysSigVorUmfPag.sys));
					sheet.addCell(new Label(1, rowCounter, sysSigVorUmfPag.sy2));
					sheet.addCell(new Label(2, rowCounter, sysSigVorUmfPag.sig));
					sheet.addCell(new Label(3, rowCounter, sysSigVorUmfPag.vor));
					sheet.addCell(new Label(4, rowCounter, sysSigVorUmfPag.umf));
					sheet.addCell(new Label(5, rowCounter, sysSigVorUmfPag.pag));
					sheet.addCell(new Label(6, rowCounter, otherFN));
					++rowCounter;
				}
			} else {
				imageFN += EXTENSION;
				
				sheet.addCell(new Label(0, rowCounter, sysSigVorUmfPag.sys));
				sheet.addCell(new Label(1, rowCounter, sysSigVorUmfPag.sy2));
				sheet.addCell(new Label(2, rowCounter, sysSigVorUmfPag.sig));
				sheet.addCell(new Label(3, rowCounter, sysSigVorUmfPag.vor));
				sheet.addCell(new Label(4, rowCounter, sysSigVorUmfPag.umf));
				sheet.addCell(new Label(5, rowCounter, sysSigVorUmfPag.pag));
				sheet.addCell(new Label(6, rowCounter, imageFN));
				
				++rowCounter;
			}
			
			
		}
		
		workbook.write(); 
		workbook.close();
	}
	
	public static void exportVorPages(ArrayList<SysSigVorUmfPag> sysSigVorUmfPagList, String filename) throws IOException, RowsExceededException, WriteException {
		if(!filename.endsWith(".xls") || !filename.endsWith(".xlsx"))
			filename.concat(".xls");
		
		WritableWorkbook workbook = Workbook.createWorkbook(new File(filename));
		
		WritableSheet sheet = workbook.createSheet("Sources", 0);
		
		// headers for the column
		Label labelVor = new Label(0, 0, "Mappe");
		Label labelPag = new Label(1, 0, "Pages");
		sheet.addCell(labelVor);
		sheet.addCell(labelPag);
		
		int counter = 1;
		for (int i = 0; i < sysSigVorUmfPagList.size(); i++) {
			if(!sysSigVorUmfPagList.get(i).vor.isEmpty() && !sysSigVorUmfPagList.get(i).pag.isEmpty()) {
				try {
					String vor = "207_" + sysSigVorUmfPagList.get(i).vor.replace("/", "_").replace(" ", "");
					int pages = Integer.parseInt(sysSigVorUmfPagList.get(i).pag.trim());
					sheet.addCell(new Label(0, counter, vor));
					sheet.addCell(new Number(1, counter, pages));
					++counter;
				} catch (NumberFormatException nfe) {
					nfe.printStackTrace();
				}
				
			}
		}
		
		workbook.write(); 
		workbook.close();
	}

}
