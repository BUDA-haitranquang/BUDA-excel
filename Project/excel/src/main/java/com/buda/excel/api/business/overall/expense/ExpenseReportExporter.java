package com.buda.excel.api.business.overall.expense;

import java.util.ArrayList;
import java.util.List;

import com.buda.excel.general.GeneralExporter;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import lombok.Getter;
@Getter
public class ExpenseReportExporter extends GeneralExporter {
    private List<XSSFSheet> sheets;
    public ExpenseReportExporter() {
        workbook = new XSSFWorkbook();
        sheets = new ArrayList<XSSFSheet>();
        writeHeaderLine();
    }

    private void createCell(XSSFRow row, XSSFSheet sheet, int columnCount, Object value, XSSFCellStyle style) {
        sheet.autoSizeColumn(columnCount);
        XSSFCell cell = row.createCell(columnCount);
        if (value instanceof Double) {
            cell.setCellValue(((Double) value).doubleValue());
        }
        else cell.setCellValue(value.toString());
        cell.setCellStyle(style);
    }
    private void writeHeaderLine() {
        sheets.add(workbook.createSheet("Expense - Last two months"));
        sheets.add(workbook.createSheet("Expense - Recent months"));
        sheets.add(workbook.createSheet("Expense - Recent weeks"));
        for (XSSFSheet sheet: sheets) {
            XSSFRow row = sheet.createRow(0);
            XSSFCellStyle style = workbook.createCellStyle();
            XSSFFont font = workbook.createFont();
            font.setBold(true);
            font.setFontHeight(18);
            style.setFont(font);
            createCell(row, sheet, 0, "Time period", style);
            createCell(row, sheet, 1, "Total Expense", style);
        }
    }

    public void writeDataLines(XSSFSheet sheet, List<ExpenseReportDTO> expenseReportDTOs) {
        int rowCount = 1;
        XSSFCellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
        for (ExpenseReportDTO expenseReportDTO: expenseReportDTOs) {
            XSSFRow row = sheet.createRow(rowCount++);
            int columnCount = 0;
            createCell(row, sheet, columnCount++, expenseReportDTO.getTimePeriod(), style);
            createCell(row, sheet, columnCount++, expenseReportDTO.getExpense(), style);
        }
    }
}
