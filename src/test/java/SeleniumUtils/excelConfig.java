package SeleniumUtils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class excelConfig {
    protected static FileInputStream myxlsFile;
    protected static XSSFWorkbook book;
    protected static XSSFSheet excelSheet;
    protected static XSSFRow excelrow;

    public static String readFromCell(String file, String sheetName, int column, String rowToMatch) throws IOException, IOException {
        myxlsFile = new FileInputStream(file);
        book = new XSSFWorkbook(myxlsFile);
        excelSheet = book.getSheet(sheetName);

        if (excelSheet == null) {
            throw new IllegalArgumentException("Sheet '" + sheetName + "' not found in the Excel file.");
        }

        int lastrow = excelSheet.getLastRowNum();
        excelrow = null;

        for (int i = 1; i <= lastrow; i++) {
            if (excelSheet.getRow(i).getCell(0) == null) {
                System.out.println("No File found");
            } else if (excelSheet.getRow(i).getCell(0).toString().equals(rowToMatch)) {
                excelrow = excelSheet.getRow(i);
                break;
            }
        }

        if (excelrow != null) {
            return excelrow.getCell(column).getStringCellValue();
        } else {
            // Handle the case when no matching row is found
            return "No matching row found";
        }
    }
    public static void writeDataToExcel(String file, String sheetName, int rowNum, int cellNum, String data) throws IOException {
        myxlsFile = new FileInputStream(file);
        book = new XSSFWorkbook(myxlsFile);
        excelSheet = book.getSheet(sheetName);

        // Get or create the row
        Row row = excelSheet.getRow(rowNum);
        if (row == null) {
            row = excelSheet.createRow(rowNum);
        }

        // Get the cell at the specified column index (cellNum) or create it if it doesn't exist
        Cell cell = row.getCell(cellNum, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);

        // Set the cell value with the new data
        cell.setCellValue(data);

        // Open the same Excel file for writing
        FileOutputStream outputStream = new FileOutputStream(file);

        // Save and close the Excel file
        book.write(outputStream);
        book.close();
        outputStream.close();

        System.out.println("Data has been written to the Excel file.");
    }

}




