package com.demoqa.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ExcelDataProvider {
    private static String sheetName;

    // Method to set the sheet name
    public static void setSheetName(String sheetName) {
        ExcelDataProvider.sheetName = sheetName;
    }

    /**
     * DataProvider method to fetch data from an Excel file.
     * 
     * @return A 2D array of Objects containing the data from the Excel sheet.
     * @throws EncryptedDocumentException
     * @throws IOException
     */
    @DataProvider(name = "Excel")
    public Object[][] getdata() throws EncryptedDocumentException, IOException {
        
        File file = new File("TestData\\ElementsData.xlsx");
        // Try-with-resources to ensure FileInputStream and Workbook are closed after use
        try (FileInputStream fis = new FileInputStream(file);
             Workbook wb = WorkbookFactory.create(fis)) {
            
            Sheet sheet = wb.getSheet(sheetName);
            // Get the total number of rows
            int totalRows = sheet.getLastRowNum();
            // Get the total number of columns
            Row rowCells = sheet.getRow(0);
            int totalCols = rowCells.getLastCellNum();

            // Loop through each row and cell to fetch data
            DataFormatter format = new DataFormatter();
            Object[][] testData = new Object[totalRows][totalCols];
            
            int rowIndex = 0;  // To keep track of actual data rows

            for (int i = 1; i <= totalRows; i++) {  // Starting from row 1 (skipping header)
                Row row = sheet.getRow(i);

                if (row == null) continue;  // Skip empty rows

                boolean isEmptyRow = true;  // Flag to check if the row is empty

                for (int j = 0; j < totalCols; j++) {
                    // Handle null or empty cells
                    if (row.getCell(j) == null || format.formatCellValue(row.getCell(j)).isEmpty()) {
                        testData[rowIndex][j] = "null";  // Assign "null" to empty cells
                    } else {
                        String cellValue = format.formatCellValue(row.getCell(j));
                        testData[rowIndex][j] = cellValue;
                        isEmptyRow = false;  // Row has data
                    }
                }

                // Skip the row if it's completely empty (contains only null values)
                if (!isEmptyRow) {
                    rowIndex++;
                }
            }

            // Trim the data array to only include rows with data
            Object[][] trimmedData = new Object[rowIndex][totalCols];
            System.arraycopy(testData, 0, trimmedData, 0, rowIndex);

            return trimmedData;
        }
    }

    /**
     * Test method that uses data from the Excel DataProvider.
     * 
     * @param data Varargs parameter to accept multiple data values from the DataProvider.
     */
    @Test(dataProvider = "Excel")
    public void testAdHocPayment(Object... data) {
        // Print the data to the console for verification
        for (Object value : data) {
            System.out.print(value + " ");
        }
        System.out.println();

        // You can replace this with actual test logic
        // If data contains "null", you can handle it like this:
        for (Object value : data) {
            if ("null".equals(value)) {
                System.out.println("Skipping test due to missing data...");
                return;  // Exit the test if invalid data is encountered
            }
        }

        // Actual test logic (when all data is valid)
    }
}
