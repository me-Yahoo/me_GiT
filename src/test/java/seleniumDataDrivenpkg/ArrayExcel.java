package seleniumDataDrivenpkg;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ArrayExcel {

	// public static void main(String[] args) throws Exception {

	/*
	 * ArrayExcel obj = new ArrayExcel();
	 * 
	 * obj.getExcelData();
	 * 
	 * }
	 */
	DataFormatter format = new DataFormatter(); 
	
	@Test(dataProvider="excell")
	public void testData(String uname, String pwd, String id) throws Exception {
	
		//Object data = getExcelData();
		System.out.println(uname+pwd+id);
	}
	
	
	@DataProvider(name="excell")
	public Object[][] getExcelData() throws Exception {

		FileInputStream fis = new FileInputStream("C:\\Users\\Admin\\Documents\\DataDrivenSelenium.xlsx");

		XSSFWorkbook workbook = new XSSFWorkbook(fis);

		XSSFSheet sheet = workbook.getSheetAt(0);
		int rows = sheet.getPhysicalNumberOfRows(); // returns number

		Row zerothRow = sheet.getRow(0);
		// sheet.getLastRowNum();
		short columnCount = zerothRow.getLastCellNum();// returns index
		Object data[][] = new Object[rows - 1][columnCount];

		for (int i =0;i<rows-1;i++) {
			
			XSSFRow  row = sheet.getRow(i+1);
			
			for (int j=0;j<columnCount;j++) {
				
				
					XSSFCell cell = row.getCell(j);
					data[i][j]=format.formatCellValue(cell);
				
			}

		}
		return data;
	}
}
