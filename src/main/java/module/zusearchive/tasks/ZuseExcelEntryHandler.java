package module.zusearchive.tasks;


import java.io.File; 
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import module.zusearchive.vo.generated.ExcelEntry4PDF;
import module.zusearchive.vo.generated.ExcelMetadataEntry;
import module.zusearchive.vo.generated.formats.enums.ExcelEntryEnum;
import module.zusearchive.vo.generated.formats.enums.ExcelZuseEntryEnum;

import jxl.*;
import jxl.read.biff.BiffException;

public class ZuseExcelEntryHandler {
	
	private static String ENCODING = "Cp1252";
	
	public static ArrayList<ExcelEntry4PDF> getDataFromExcelFile4PDF(String filePath) throws BiffException, IOException, URISyntaxException {
		return ZuseExcelEntryHandler.getDataFromExcelFile4PDF(new File(filePath));
	}
	
	public static ArrayList<ExcelEntry4PDF> getDataFromExcelFile4PDF(File file) throws BiffException, IOException, URISyntaxException {
		ArrayList<ExcelEntry4PDF> data = new ArrayList<ExcelEntry4PDF>();
		
		WorkbookSettings ws = new WorkbookSettings();
		ws.setEncoding(ENCODING);
		
		Workbook workbook = Workbook.getWorkbook(file,ws);
		
		Sheet sheet = workbook.getSheet(0);
		
		int rows = sheet.getRows();
		int multiEnabledRow = 0;
		int enabledRow = 1;
		
		for (int row = 3; row < rows; row++) {			
			ExcelEntry4PDF entry = new ExcelEntry4PDF(
					sheet.getCell(ExcelEntryEnum.TYPE.getOrd(), row).getContents(), Boolean.valueOf(sheet.getCell(ExcelEntryEnum.TYPE.getOrd(), enabledRow).getContents()), Boolean.valueOf(sheet.getCell(ExcelEntryEnum.TYPE.getOrd(), multiEnabledRow).getContents()),
					sheet.getCell(ExcelEntryEnum.ZIA_ID.getOrd(), row).getContents(), Boolean.valueOf(sheet.getCell(ExcelEntryEnum.ZIA_ID.getOrd(), enabledRow).getContents()), Boolean.valueOf(sheet.getCell(ExcelEntryEnum.ZIA_ID.getOrd(), multiEnabledRow).getContents()),
					sheet.getCell(ExcelEntryEnum.GMD_NR.getOrd(), row).getContents(), Boolean.valueOf(sheet.getCell(ExcelEntryEnum.GMD_NR.getOrd(), enabledRow).getContents()), Boolean.valueOf(sheet.getCell(ExcelEntryEnum.GMD_NR.getOrd(), multiEnabledRow).getContents()),
					sheet.getCell(ExcelEntryEnum.AUTHOR.getOrd(), row).getContents(), Boolean.valueOf(sheet.getCell(ExcelEntryEnum.AUTHOR.getOrd(), enabledRow).getContents()), Boolean.valueOf(sheet.getCell(ExcelEntryEnum.AUTHOR.getOrd(), multiEnabledRow).getContents()),
					sheet.getCell(ExcelEntryEnum.YEAR.getOrd(), row).getContents(), Boolean.valueOf(sheet.getCell(ExcelEntryEnum.YEAR.getOrd(), enabledRow).getContents()), Boolean.valueOf(sheet.getCell(ExcelEntryEnum.YEAR.getOrd(), multiEnabledRow).getContents()),
					sheet.getCell(ExcelEntryEnum.TITLE.getOrd(), row).getContents(), Boolean.valueOf(sheet.getCell(ExcelEntryEnum.TITLE.getOrd(), enabledRow).getContents()), Boolean.valueOf(sheet.getCell(ExcelEntryEnum.TITLE.getOrd(), multiEnabledRow).getContents()),
					sheet.getCell(ExcelEntryEnum.FILE.getOrd(), row).getContents(), Boolean.valueOf(sheet.getCell(ExcelEntryEnum.FILE.getOrd(), enabledRow).getContents()), Boolean.valueOf(sheet.getCell(ExcelEntryEnum.FILE.getOrd(), multiEnabledRow).getContents()),
					sheet.getCell(ExcelEntryEnum.CHRONOLOGICAL.getOrd(), row).getContents(), Boolean.valueOf(sheet.getCell(ExcelEntryEnum.CHRONOLOGICAL.getOrd(), enabledRow).getContents()), Boolean.valueOf(sheet.getCell(ExcelEntryEnum.CHRONOLOGICAL.getOrd(), multiEnabledRow).getContents()),
					sheet.getCell(ExcelEntryEnum.THEMATIC.getOrd(), row).getContents(), Boolean.valueOf(sheet.getCell(ExcelEntryEnum.THEMATIC.getOrd(), enabledRow).getContents()), Boolean.valueOf(sheet.getCell(ExcelEntryEnum.THEMATIC.getOrd(), multiEnabledRow).getContents()),
					sheet.getCell(ExcelEntryEnum.LANGUAGE.getOrd(), row).getContents(), Boolean.valueOf(sheet.getCell(ExcelEntryEnum.LANGUAGE.getOrd(), enabledRow).getContents()), Boolean.valueOf(sheet.getCell(ExcelEntryEnum.LANGUAGE.getOrd(), multiEnabledRow).getContents()),
					sheet.getCell(ExcelEntryEnum.DESCRIPTION.getOrd(), row).getContents(), Boolean.valueOf(sheet.getCell(ExcelEntryEnum.DESCRIPTION.getOrd(), enabledRow).getContents()), Boolean.valueOf(sheet.getCell(ExcelEntryEnum.DESCRIPTION.getOrd(), multiEnabledRow).getContents()),
					sheet.getCell(ExcelEntryEnum.URL.getOrd(), row).getContents(), Boolean.valueOf(sheet.getCell(ExcelEntryEnum.URL.getOrd(), enabledRow).getContents()), Boolean.valueOf(sheet.getCell(ExcelEntryEnum.URL.getOrd(), multiEnabledRow).getContents()),
					sheet.getCell(ExcelEntryEnum.PUBLISHED_BY.getOrd(), row).getContents(), Boolean.valueOf(sheet.getCell(ExcelEntryEnum.PUBLISHED_BY.getOrd(), enabledRow).getContents()), Boolean.valueOf(sheet.getCell(ExcelEntryEnum.PUBLISHED_BY.getOrd(), multiEnabledRow).getContents()));
				
			data.add(entry);			
		}
		return data;
	}
	
	public static ExcelEntry4PDF getMetadataFromExcelFile4PDF(String filePath) throws BiffException, IOException {
		return ZuseExcelEntryHandler.getMetadataFromExcelFile4PDF(new File(filePath));
	}
	
	public static ExcelEntry4PDF getMetadataFromExcelFile4PDF(File file) throws BiffException, IOException {
		WorkbookSettings ws = new WorkbookSettings();
		ws.setEncoding(ENCODING);
		
		Workbook workbook = Workbook.getWorkbook(file,ws);
		Sheet sheet = workbook.getSheet(0);
		int multiEnabledRow = 0;
		int enabledRow = 1;	
		int metadataRow = 2;
		ExcelEntry4PDF metadataEntry = new ExcelEntry4PDF(
				sheet.getCell(ExcelEntryEnum.TYPE.getOrd(), metadataRow).getContents(), Boolean.valueOf(sheet.getCell(ExcelEntryEnum.TYPE.getOrd(), enabledRow).getContents()), Boolean.valueOf(sheet.getCell(ExcelEntryEnum.TYPE.getOrd(), multiEnabledRow).getContents()),
				sheet.getCell(ExcelEntryEnum.ZIA_ID.getOrd(), metadataRow).getContents(), Boolean.valueOf(sheet.getCell(ExcelEntryEnum.ZIA_ID.getOrd(), enabledRow).getContents()), Boolean.valueOf(sheet.getCell(ExcelEntryEnum.ZIA_ID.getOrd(), multiEnabledRow).getContents()),
				sheet.getCell(ExcelEntryEnum.GMD_NR.getOrd(), metadataRow).getContents(), Boolean.valueOf(sheet.getCell(ExcelEntryEnum.GMD_NR.getOrd(), enabledRow).getContents()), Boolean.valueOf(sheet.getCell(ExcelEntryEnum.GMD_NR.getOrd(), multiEnabledRow).getContents()),
				sheet.getCell(ExcelEntryEnum.AUTHOR.getOrd(), metadataRow).getContents(), Boolean.valueOf(sheet.getCell(ExcelEntryEnum.AUTHOR.getOrd(), enabledRow).getContents()), Boolean.valueOf(sheet.getCell(ExcelEntryEnum.AUTHOR.getOrd(), multiEnabledRow).getContents()),
				sheet.getCell(ExcelEntryEnum.YEAR.getOrd(), metadataRow).getContents(), Boolean.valueOf(sheet.getCell(ExcelEntryEnum.YEAR.getOrd(), enabledRow).getContents()), Boolean.valueOf(sheet.getCell(ExcelEntryEnum.YEAR.getOrd(), multiEnabledRow).getContents()),
				sheet.getCell(ExcelEntryEnum.TITLE.getOrd(), metadataRow).getContents(), Boolean.valueOf(sheet.getCell(ExcelEntryEnum.TITLE.getOrd(), enabledRow).getContents()), Boolean.valueOf(sheet.getCell(ExcelEntryEnum.TITLE.getOrd(), multiEnabledRow).getContents()),
				sheet.getCell(ExcelEntryEnum.FILE.getOrd(), metadataRow).getContents(), Boolean.valueOf(sheet.getCell(ExcelEntryEnum.FILE.getOrd(), enabledRow).getContents()), Boolean.valueOf(sheet.getCell(ExcelEntryEnum.FILE.getOrd(), multiEnabledRow).getContents()),
				sheet.getCell(ExcelEntryEnum.CHRONOLOGICAL.getOrd(), metadataRow).getContents(), Boolean.valueOf(sheet.getCell(ExcelEntryEnum.CHRONOLOGICAL.getOrd(), enabledRow).getContents()), Boolean.valueOf(sheet.getCell(ExcelEntryEnum.CHRONOLOGICAL.getOrd(), multiEnabledRow).getContents()),
				sheet.getCell(ExcelEntryEnum.THEMATIC.getOrd(), metadataRow).getContents(), Boolean.valueOf(sheet.getCell(ExcelEntryEnum.THEMATIC.getOrd(), enabledRow).getContents()), Boolean.valueOf(sheet.getCell(ExcelEntryEnum.THEMATIC.getOrd(), multiEnabledRow).getContents()),
				sheet.getCell(ExcelEntryEnum.LANGUAGE.getOrd(), metadataRow).getContents(), Boolean.valueOf(sheet.getCell(ExcelEntryEnum.LANGUAGE.getOrd(), enabledRow).getContents()), Boolean.valueOf(sheet.getCell(ExcelEntryEnum.LANGUAGE.getOrd(), multiEnabledRow).getContents()),
				sheet.getCell(ExcelEntryEnum.DESCRIPTION.getOrd(), metadataRow).getContents(), Boolean.valueOf(sheet.getCell(ExcelEntryEnum.DESCRIPTION.getOrd(), enabledRow).getContents()), Boolean.valueOf(sheet.getCell(ExcelEntryEnum.DESCRIPTION.getOrd(), multiEnabledRow).getContents()),
				sheet.getCell(ExcelEntryEnum.URL.getOrd(), metadataRow).getContents(), Boolean.valueOf(sheet.getCell(ExcelEntryEnum.URL.getOrd(), enabledRow).getContents()), Boolean.valueOf(sheet.getCell(ExcelEntryEnum.URL.getOrd(), multiEnabledRow).getContents()),
				sheet.getCell(ExcelEntryEnum.PUBLISHED_BY.getOrd(), metadataRow).getContents(), Boolean.valueOf(sheet.getCell(ExcelEntryEnum.PUBLISHED_BY.getOrd(), enabledRow).getContents()), Boolean.valueOf(sheet.getCell(ExcelEntryEnum.PUBLISHED_BY.getOrd(), multiEnabledRow).getContents()));		
		
		return metadataEntry;
	}
	
	public static ArrayList<ExcelMetadataEntry> getMetadataFromExcelFile(String filePath) throws BiffException, IOException {
		return ZuseExcelEntryHandler.getMetadataFromExcelFile(new File(filePath));
	}
	
	public static ArrayList<ExcelMetadataEntry> getMetadataFromExcelFile(File file) throws BiffException, IOException {
		WorkbookSettings ws = new WorkbookSettings();
		ws.setEncoding(ENCODING);
		
		Workbook workbook = Workbook.getWorkbook(file,ws);
		Sheet sheet = workbook.getSheet(0);		
		ArrayList<ExcelMetadataEntry> metadataEntries = new ArrayList<ExcelMetadataEntry>(sheet.getRows()-1);
		
		for (int i = 1; i < sheet.getRows(); i++) {
			ExcelMetadataEntry metadataEntry = new ExcelMetadataEntry(
					sheet.getCell(ExcelZuseEntryEnum.POSITION.getOrd(), i).getContents(),
					Boolean.parseBoolean(sheet.getCell(ExcelZuseEntryEnum.ACTIVE.getOrd(), i).getContents()),
					sheet.getCell(ExcelZuseEntryEnum.ORIGINAL_TAG.getOrd(), i).getContents(),
					sheet.getCell(ExcelZuseEntryEnum.NORMALIZED_TAG.getOrd(), i).getContents(),
					sheet.getCell(ExcelZuseEntryEnum.METHOD_NAME.getOrd(), i).getContents(),
					Boolean.parseBoolean(sheet.getCell(ExcelZuseEntryEnum.MULTIPLICITY.getOrd(), i).getContents()),
					sheet.getCell(ExcelZuseEntryEnum.TYPE.getOrd(), i).getContents(),
					sheet.getCell(ExcelZuseEntryEnum.LANGUAGE_DE.getOrd(), i).getContents(),
					sheet.getCell(ExcelZuseEntryEnum.LANGUAGE_EN.getOrd(), i).getContents());
			metadataEntries.add(metadataEntry);
		}
		
		
		
		return metadataEntries;
	}
	
}
