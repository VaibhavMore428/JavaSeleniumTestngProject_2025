package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
	public static List<Object[]> getTestDataAsMap(String filePath, String sheetName) throws IOException {

		FileInputStream fis = new FileInputStream(filePath);
		XSSFWorkbook workBook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workBook.getSheet(sheetName);

		XSSFRow headerRow = sheet.getRow(0);
		int totalrows = sheet.getLastRowNum();
		int totalcols = headerRow.getLastCellNum();

		List<Object[]> testData = new ArrayList<Object[]>();

		for (int i = 1; i <= totalrows; i++) {
			XSSFRow row = sheet.getRow(i);
			Map<String, String> datamap = new LinkedHashMap<String, String>();

			for (int j = 0; j < totalcols; j++) {
				String key = headerRow.getCell(j).toString();
				String value = (row.getCell(j) == null) ? "" : row.getCell(j).toString();
				datamap.put(key.trim(), value.trim());
			}
			testData.add(new Object[] { datamap });
		}
		workBook.close();
		fis.close();
		return testData;
	}
}