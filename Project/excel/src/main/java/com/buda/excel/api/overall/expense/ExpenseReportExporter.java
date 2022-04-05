package com.buda.excel.api.overall.expense;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.result.Row;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExpenseReportExporter {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<ExpenseReportDTO> expenseReportDTOs;

    public ExpenseReportExporter(List<ExpenseReportDTO> expenseReportDTOs) {
        this.expenseReportDTOs = expenseReportDTOs;
        workbook = new XSSFWorkbook();
    }

    private void createCell(XSSFRow row, int columnCount, Object value, XSSFCellStyle style) {
        sheet.autoSizeColumn(columnCount);
        XSSFCell cell = row.createCell(columnCount);
        cell.setCellValue(value.toString());
        cell.setCellStyle(style);
    }
    private void writeHeaderLine() {
        sheet = workbook.createSheet("Expense - Last two months");
        XSSFRow row = sheet.createRow(0);
        XSSFCellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(18);
        style.setFont(font);
        createCell(row, 0, "Date", style);
        createCell(row, 1, "Total Expense", style);
    }

    private void writeDataLines() {
        int rowCount = 1;
        XSSFCellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
        for (ExpenseReportDTO expenseReportDTO: expenseReportDTOs) {
            XSSFRow row = sheet.createRow(rowCount++);
            int columnCount = 0;
            createCell(row, columnCount++, expenseReportDTO.getTimePeriod(), style);
            createCell(row, columnCount++, expenseReportDTO.getExpense(), style);
        }
    }
    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }
}
