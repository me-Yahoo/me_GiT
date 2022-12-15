package seleniumDataDrivenpkg;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class PractiseDataDriver {

	public static void main(String[] args) throws Exception {

		PractiseDataDriver obj = new PractiseDataDriver();
		ArrayList dataReturn = obj.anotherTimeExcel("carry");
		System.out.println(dataReturn);
	}

	public ArrayList anotherTimeExcel(String SheetName) throws Exception {
		ArrayList data = new ArrayList();
		FileInputStream fis = new FileInputStream("C:\\Users\\Admin\\Documents\\DataDrivenSelenium.xlsx");

		XSSFWorkbook workbuk = new XSSFWorkbook(fis);

		// 3:30

		int totalSheet = workbuk.getNumberOfSheets();

		for (int i = 0; i < totalSheet; i++) {

			XSSFSheet locatedSheet = workbuk.getSheetAt(i);
			if (locatedSheet.getSheetName().equalsIgnoreCase(SheetName)) {
				Iterator<Row> rows = locatedSheet.iterator();
				Row row = rows.next();
				Iterator<Cell> totalCells = row.cellIterator();

				while (rows.hasNext()) {

					Row firstRow = rows.next();
					// System.out.println(firstRow);
					Iterator<Cell> cellCounts = firstRow.cellIterator();
					Cell eachCell = cellCounts.next();
					if (eachCell.getStringCellValue().equalsIgnoreCase("d")) {

						Iterator<Cell> dcellcounts = firstRow.cellIterator();

							
						while (dcellcounts.hasNext()) {
							
							
							Cell singleCell = dcellcounts.next();
							//Cell dRowCell = cellCounts.next();
							if (singleCell.getCellType() == CellType.STRING) {
								data.add(singleCell.getStringCellValue());
							} else {
								data.add(singleCell.getNumericCellValue());
							}
						}
					}

				}

			}

		} return data;

	}

}
