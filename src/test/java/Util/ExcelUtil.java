package Util;


import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtil {

    public String ReadExcel(int row,int col) throws IOException {
        String stringValue = null;
        XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream
                ("C:\\Programming\\Projects\\TrainingProject\\InputFile.xlsx"));
        XSSFSheet sheet = workbook.getSheet("LoginDetails");
        XSSFCell cell = sheet.getRow(row).getCell(col);
        stringValue = cell.getStringCellValue();
        return stringValue;
    }
}
