package utilityTools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReadWrite {
	
	File f;
	FileInputStream fis;
	XSSFWorkbook wb;
	//XSSFSheet sheet;
	
	public ExcelReadWrite(String file) {
		
		try {
			
			f = new File(file);	
			fis = new FileInputStream(f);
			wb = new XSSFWorkbook(fis);
			//sheet = xlsx.getSheetAt(0);
			
		} catch (Exception e) {
			
			//e.printStackTrace();
			System.out.println(e.getMessage());
		
		}
	}
	
	public String getDataCell(int sheetIndex, int rowIndex, int colIndex) {
		try {
			
			return wb.getSheetAt(sheetIndex).getRow(rowIndex).getCell(colIndex).getStringCellValue();
		
		} catch(Exception e) {
			
			System.out.println("getDataCell : " + e.getMessage());
		}
		
		return null;
	}
	
	public void writeDataCell(int sheetIndex, int rowIndex, int colIndex, String value) {
		
		try {
			
			wb.getSheetAt(sheetIndex).getRow(rowIndex).createCell(colIndex).setCellValue(value);
			
		} catch(Exception e) {
			
			System.out.println("writeDataCell : Exception : " + e.getMessage());
		}
	}
	
	public void appendNewDataRow(int sheetIndex, String[] dataRow) {
		
		Sheet sheet = wb.getSheetAt(sheetIndex);
		int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();
		Row row = sheet.getRow(0);
		Row newRow = sheet.createRow(rowCount+1);
		
		for(int j = 0; j < row.getLastCellNum(); j++){

	        //Fill data in row
	        Cell cell = newRow.createCell(j);
	        cell.setCellValue(dataRow[j]);
	    }
	}
	
	
	public void saveAndClose() {
		try {
			
			fis.close();
			
			FileOutputStream fos = new FileOutputStream(f);
			wb.write(fos);
			fos.close();
			
		} catch(Exception e) {
			
			System.out.println("closeFile : Exception : " + e.getMessage());
		}
	}

}
