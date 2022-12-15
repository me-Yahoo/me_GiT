package seleniumDataDrivenpkg;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataDrivenTest {

	/*
	 * @Test(dataProvider = "excelHelp") public void dataDrivenTest() throws
	 * IOException {
	 * 
	 * DataDrivenTest obj = new DataDrivenTest(); Row eachRow = obj.getData(null);
	 * System.out.println(eachRow); // System.out.println(data[0][2]); }
	 */
	
	 
	  public static void main(String args[]) throws Exception { DataDrivenTest
	  obj = new DataDrivenTest(); 
	  ArrayList<String> data = obj.getData("Professional");
	  System.out.println(data);
	  }
	  
	  
	 
	 
	/*
	 * @Test(dataProvider = "excel") public void dataDrivenTest() throws IOException
	 * {
	 * 
	 * DataDrivenTest obj = new DataDrivenTest(); obj.getData("football");
	 * //System.out.println(eachRow); // System.out.println(data[0][2]); } }
	 */

	// Cell eachcell = null;
	/*
	 * Object[][] data = new Object[][] {
	 * {"Try",1,"Hello"},{"abc",8,"tell"},{"acb",6,"moon"} };
	 * 
	 * return data;
	 */

	public ArrayList<String> getData(String testcase) throws Exception {

		ArrayList<String> a = new ArrayList<String>();
		FileInputStream fis = new FileInputStream("C:\\Users\\Admin\\Documents\\DataDrivenSelenium.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);

		int sheets = workbook.getNumberOfSheets();
		for (int i = 0; i < sheets; i++) {
			// get our sheet index
			XSSFSheet sheet = workbook.getSheetAt(i);
			if (sheet.getSheetName().equalsIgnoreCase("sheet1")) {
				Iterator<Row> rows = sheet.iterator();
				Row eachRow = rows.next();
				Iterator<Cell> allCells = eachRow.cellIterator();
				int count = 0;
				int column = 0;
				while (allCells.hasNext()) {

					Cell cell = allCells.next();

					if (cell.getStringCellValue().equalsIgnoreCase(testcase)) {
						column = count;
					}
					count++;
				}
				System.out.println(column);

				while (rows.hasNext()) {
					Row r = rows.next();
					if (r.getCell(column).getStringCellValue().equalsIgnoreCase("Fball")) {

						Iterator<Cell> cellValue = r.cellIterator();
						while (cellValue.hasNext()) {
							Cell eachValue = cellValue.next();
							if (eachValue.getCellType() == CellType.STRING) {

								a.add(eachValue.getStringCellValue());
							} else {
								a.add(NumberToTextConverter.toText((eachValue.getNumericCellValue())));

							}
						}
					}

				}

			}

		}
		return a;

	}
}
