package module.zusearchive.tasks;


import java.io.File; 
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import module.zusearchive.vo.generated.ExcelEntry;
import module.zusearchive.vo.generated.formats.enums.ExcelEntryEnum;

import jxl.*;
import jxl.read.biff.BiffException;

public class ExcelEntryHandler {
	
	public static ArrayList<ExcelEntry> getDataFromExcelFile(String filePath) throws BiffException, IOException, URISyntaxException {
		return getDataFromExcelFile(new File(filePath));
	}
	
	public static ArrayList<ExcelEntry> getDataFromExcelFile(File file) throws BiffException, IOException, URISyntaxException {
		ArrayList<ExcelEntry> data = new ArrayList<ExcelEntry>();	
		Workbook workbook = Workbook.getWorkbook(file);
		
		Sheet sheet = workbook.getSheet(0);
		
		int rows = sheet.getRows();
		
		int enabledRow = 0;
		
		for (int row = 2; row < rows; row++) {			
			ExcelEntry entry = new ExcelEntry(
					sheet.getCell(ExcelEntryEnum.TYPE.getOrd(), row).getContents(), Boolean.valueOf(sheet.getCell(ExcelEntryEnum.TYPE.getOrd(), enabledRow).getContents()),
					sheet.getCell(ExcelEntryEnum.ZIA_ID.getOrd(), row).getContents(), Boolean.valueOf(sheet.getCell(ExcelEntryEnum.ZIA_ID.getOrd(), enabledRow).getContents()),
					sheet.getCell(ExcelEntryEnum.GMD_NR.getOrd(), row).getContents(), Boolean.valueOf(sheet.getCell(ExcelEntryEnum.GMD_NR.getOrd(), enabledRow).getContents()),
					sheet.getCell(ExcelEntryEnum.AUTHOR.getOrd(), row).getContents(), Boolean.valueOf(sheet.getCell(ExcelEntryEnum.AUTHOR.getOrd(), enabledRow).getContents()),
					sheet.getCell(ExcelEntryEnum.YEAR.getOrd(), row).getContents(), Boolean.valueOf(sheet.getCell(ExcelEntryEnum.YEAR.getOrd(), enabledRow).getContents()),
					sheet.getCell(ExcelEntryEnum.TITLE.getOrd(), row).getContents(), Boolean.valueOf(sheet.getCell(ExcelEntryEnum.TITLE.getOrd(), enabledRow).getContents()),
					sheet.getCell(ExcelEntryEnum.FILE.getOrd(), row).getContents(), Boolean.valueOf(sheet.getCell(ExcelEntryEnum.FILE.getOrd(), enabledRow).getContents()),
					sheet.getCell(ExcelEntryEnum.CHRONOLOGICAL.getOrd(), row).getContents(), Boolean.valueOf(sheet.getCell(ExcelEntryEnum.CHRONOLOGICAL.getOrd(), enabledRow).getContents()),
					sheet.getCell(ExcelEntryEnum.THEMATIC.getOrd(), row).getContents(), Boolean.valueOf(sheet.getCell(ExcelEntryEnum.THEMATIC.getOrd(), enabledRow).getContents()),
					sheet.getCell(ExcelEntryEnum.LANGUAGE.getOrd(), row).getContents(), Boolean.valueOf(sheet.getCell(ExcelEntryEnum.LANGUAGE.getOrd(), enabledRow).getContents()),
					sheet.getCell(ExcelEntryEnum.DESCRIPTION.getOrd(), row).getContents(), Boolean.valueOf(sheet.getCell(ExcelEntryEnum.DESCRIPTION.getOrd(), enabledRow).getContents()),
					sheet.getCell(ExcelEntryEnum.URL.getOrd(), row).getContents(), Boolean.valueOf(sheet.getCell(ExcelEntryEnum.URL.getOrd(), enabledRow).getContents()),
					sheet.getCell(ExcelEntryEnum.DIRECTORY.getOrd(), row).getContents(), Boolean.valueOf(sheet.getCell(ExcelEntryEnum.DIRECTORY.getOrd(), enabledRow).getContents()),
					sheet.getCell(ExcelEntryEnum.PUBLISHED_BY.getOrd(), row).getContents(), Boolean.valueOf(sheet.getCell(ExcelEntryEnum.PUBLISHED_BY.getOrd(), enabledRow).getContents()));
				
			data.add(entry);			
		}
		return data;
	}
	
	public static ExcelEntry getMetadataFromExcelFile(File file) throws BiffException, IOException {
		Workbook workbook = Workbook.getWorkbook(file);
		Sheet sheet = workbook.getSheet(0);
		int enabledRow = 0;	
		int metadataRow = 1;
		ExcelEntry metadataEntry = new ExcelEntry(
				sheet.getCell(ExcelEntryEnum.TYPE.getOrd(), metadataRow).getContents(), Boolean.valueOf(sheet.getCell(ExcelEntryEnum.TYPE.getOrd(), enabledRow).getContents()),
				sheet.getCell(ExcelEntryEnum.ZIA_ID.getOrd(), metadataRow).getContents(), Boolean.valueOf(sheet.getCell(ExcelEntryEnum.ZIA_ID.getOrd(), enabledRow).getContents()),
				sheet.getCell(ExcelEntryEnum.GMD_NR.getOrd(), metadataRow).getContents(), Boolean.valueOf(sheet.getCell(ExcelEntryEnum.GMD_NR.getOrd(), enabledRow).getContents()),
				sheet.getCell(ExcelEntryEnum.AUTHOR.getOrd(), metadataRow).getContents(), Boolean.valueOf(sheet.getCell(ExcelEntryEnum.AUTHOR.getOrd(), enabledRow).getContents()),
				sheet.getCell(ExcelEntryEnum.YEAR.getOrd(), metadataRow).getContents(), Boolean.valueOf(sheet.getCell(ExcelEntryEnum.YEAR.getOrd(), enabledRow).getContents()),
				sheet.getCell(ExcelEntryEnum.TITLE.getOrd(), metadataRow).getContents(), Boolean.valueOf(sheet.getCell(ExcelEntryEnum.TITLE.getOrd(), enabledRow).getContents()),
				sheet.getCell(ExcelEntryEnum.FILE.getOrd(), metadataRow).getContents(), Boolean.valueOf(sheet.getCell(ExcelEntryEnum.FILE.getOrd(), enabledRow).getContents()),
				sheet.getCell(ExcelEntryEnum.CHRONOLOGICAL.getOrd(), metadataRow).getContents(), Boolean.valueOf(sheet.getCell(ExcelEntryEnum.CHRONOLOGICAL.getOrd(), enabledRow).getContents()),
				sheet.getCell(ExcelEntryEnum.THEMATIC.getOrd(), metadataRow).getContents(), Boolean.valueOf(sheet.getCell(ExcelEntryEnum.THEMATIC.getOrd(), enabledRow).getContents()),
				sheet.getCell(ExcelEntryEnum.LANGUAGE.getOrd(), metadataRow).getContents(), Boolean.valueOf(sheet.getCell(ExcelEntryEnum.LANGUAGE.getOrd(), enabledRow).getContents()),
				sheet.getCell(ExcelEntryEnum.DESCRIPTION.getOrd(), metadataRow).getContents(), Boolean.valueOf(sheet.getCell(ExcelEntryEnum.DESCRIPTION.getOrd(), enabledRow).getContents()),
				sheet.getCell(ExcelEntryEnum.URL.getOrd(), metadataRow).getContents(), Boolean.valueOf(sheet.getCell(ExcelEntryEnum.URL.getOrd(), enabledRow).getContents()),
				sheet.getCell(ExcelEntryEnum.DIRECTORY.getOrd(), metadataRow).getContents(), Boolean.valueOf(sheet.getCell(ExcelEntryEnum.DIRECTORY.getOrd(), enabledRow).getContents()),
				sheet.getCell(ExcelEntryEnum.PUBLISHED_BY.getOrd(), metadataRow).getContents(), Boolean.valueOf(sheet.getCell(ExcelEntryEnum.PUBLISHED_BY.getOrd(), enabledRow).getContents()));		
		
		return metadataEntry;
	}
	
}
