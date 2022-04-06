package com.buda.excel.api.business.overall.revenue;
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
public class RevenueReportExporter extends GeneralExporter{
    
    public List<XSSFSheet> sheets;
    public RevenueReportExporter() {
        workbook = new XSSFWorkbook();
        sheets = new ArrayList<XSSFSheet>();
        writeHeaderLine();
    }

    public void createCell(XSSFRow row, XSSFSheet sheet, int columnCount, Object value, XSSFCellStyle style) {
        sheet.autoSizeColumn(columnCount);
        XSSFCell cell = row.createCell(columnCount);
        if (value instanceof Double) {
            cell.setCellValue(((Double) value).doubleValue());
        }
        else cell.setCellValue(value.toString());
        cell.setCellStyle(style);
    }
    public void writeHeaderLine() {
        sheets.add(workbook.createSheet("Revenue - Last two months"));
        sheets.add(workbook.createSheet("Revenue - Recent months"));
        sheets.add(workbook.createSheet("Revenue - Recent weeks"));
        for (XSSFSheet sheet: sheets) {
            XSSFRow row = sheet.createRow(0);
            XSSFCellStyle style = workbook.createCellStyle();
            XSSFFont font = workbook.createFont();
            font.setBold(true);
            font.setFontHeight(18);
            style.setFont(font);
            createCell(row, sheet, 0, "Time period", style);
            createCell(row, sheet, 1, "Total Revenue", style);
        }
    }

    public void writeDataLines(XSSFSheet sheet, List<RevenueReportDTO> revenueReportDTOs) {
        int rowCount = 1;
        XSSFCellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
        for (RevenueReportDTO revenueReportDTO: revenueReportDTOs) {
            XSSFRow row = sheet.createRow(rowCount++);
            int columnCount = 0;
            createCell(row, sheet, columnCount++, revenueReportDTO.getTimePeriod(), style);
            createCell(row, sheet, columnCount++, revenueReportDTO.getRevenue(), style);
        }
    }
    
}
