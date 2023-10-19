package utils;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ExcelReader {
    public static List<Map<String, String>> read(String sheetName, String path) {
        FileInputStream fileInputStream = null;
        List<Map<String, String>> excelData = new ArrayList<>();
        try {
            fileInputStream = new FileInputStream(path);
            // that special call which knows how to read the data from Excel files
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fileInputStream);
            Sheet sheet = xssfWorkbook.getSheet(sheetName);

            //this row is for keys
            Row headerRow = sheet.getRow(0);
            //for row we take 1 index bc 0 is already used for header
            for (int rows = 1; rows < sheet.getPhysicalNumberOfRows(); rows++) {
                Row row = sheet.getRow(rows);

                //this row is for values
                Map<String, String> rowMap = new LinkedHashMap<>();
                //here we take all the columns
                for (int col = 0; col < row.getPhysicalNumberOfCells(); col++) {
                    String key = headerRow.getCell(col).toString();
                    String value = row.getCell(col).toString();
                    rowMap.put(key, value);
                }
                excelData.add(rowMap);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return excelData;
    }
}