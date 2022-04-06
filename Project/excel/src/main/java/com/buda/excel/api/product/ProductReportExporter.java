package com.buda.excel.api.product;

import java.math.BigDecimal;
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
public class ProductReportExporter extends GeneralExporter{
    private List<XSSFSheet> sheets;
    public ProductReportExporter() {
        this.workbook = new XSSFWorkbook();
        this.sheets = new ArrayList<XSSFSheet>();
        writeHeaderLine();
    }
    private void createCell(XSSFRow row, XSSFSheet sheet, int columnCount, Object value, XSSFCellStyle style) {
        sheet.autoSizeColumn(columnCount);
        XSSFCell cell = row.createCell(columnCount);
        if (value instanceof Double) {
            cell.setCellValue(((Double) value).doubleValue());
        }
        if (value instanceof BigDecimal) {
            cell.setCellValue(((BigDecimal) value).doubleValue());
        }
        else cell.setCellValue(value.toString());
        cell.setCellStyle(style);
    }
    public void writeHeaderLine()
    {
        sheets.add(workbook.createSheet("Product - last 30 days"));
        for (XSSFSheet sheet: sheets) {
            XSSFRow row = sheet.createRow(0);
            XSSFCellStyle style = workbook.createCellStyle();
            XSSFFont font = workbook.createFont();
            font.setBold(true);
            font.setFontHeight(18);
            style.setFont(font);
            createCell(row, sheet, 0, "Name", style);
            createCell(row, sheet, 1, "Revenue", style);
            createCell(row, sheet, 2, "Profit", style);
            createCell(row, sheet, 3, "Sell Number", style);
            createCell(row, sheet, 4, "Return Number", style);
        }
    }
    public void writeDataLines(XSSFSheet sheet, List<ProductReportDTO> productReportDTOs) {
        int rowCount = 1;
        XSSFCellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
        for (ProductReportDTO productReportDTO: productReportDTOs) {
            XSSFRow row = sheet.createRow(rowCount++);
            int columnCount = 0;
            createCell(row, sheet, columnCount++, productReportDTO.getName(), style);
            createCell(row, sheet, columnCount++, productReportDTO.getRevenue(), style);
            createCell(row, sheet, columnCount++, productReportDTO.getProfit(), style);
            createCell(row, sheet, columnCount++, productReportDTO.getSellNumber(), style);
            createCell(row, sheet, columnCount++, productReportDTO.getReturnNumber(), style);
        }
    }
}
