package framework.keyworddriven;

import java.io.FileInputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelHelper {
	Workbook book;
	Sheet sh;
	
	//to read the data from an excel creating openExcel method
	public void openExcel(String folderName, String fileName, String sheetName) {
		try {
			FileInputStream fis = new FileInputStream(GenericHelper.getFilePath(folderName, fileName));
			if(fileName.endsWith("xls")) {
				book = new HSSFWorkbook(fis);
			}else if(fileName.endsWith("xlsx")) {
				book = new XSSFWorkbook();
			}
			sh = book.getSheet(sheetName);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	// get the number of rows 
	public int getRowCount() {
		return sh.getLastRowNum();
	}
	
	//get the number of columns
	public int getColumnCount() {
		return sh.getRow(0).getLastCellNum();
	}
	
	// get the cell data
	public String getCellData(int rnum, int cnum) {
		Cell cell = sh.getRow(rnum).getCell(cnum);
		try {
			CellType cellTypeEnum = cell.getCellType();
			String data = null;
			switch (cellTypeEnum) {
			case STRING:
				data = cell.getStringCellValue();
				break;
			case NUMERIC:
				double d = cell.getNumericCellValue();
				int i = (int) d;
				data = Integer.toString(i);
				break;
			case BLANK:
				data = "";
				break;
			default:
//			System.out.println("invalid cell type");
				data = "";
				break;
			}
			return data;
		} catch (Exception e) {
			return "";
		}
	}
	
	//close excel 
	public void closeExcel() {
		try {
			book.close();
		} catch (Exception e) {
			System.out.println("there is problem in closing the excel document");
		}
	}
	
}
