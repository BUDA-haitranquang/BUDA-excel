package com.buda.excel.api.business.overall.revenue;

import java.util.ArrayList;
import java.util.List;

import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import com.buda.excel.general.GeneralExporter;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import lombok.Getter;

@Getter
public class RevenueReportExporter extends GeneralExporter {

    public List<Worksheet> sheets;

    public RevenueReportExporter() {
        workbook = new Workbook();
        sheets = new ArrayList<Worksheet>();
        writeHeaderLine();
    }


    public void writeHeaderLine() {
        sheets.add(workbook.getWorksheets().add("Revenue - Last two months"));
        sheets.add(workbook.getWorksheets().add("Revenue - Recent months"));
        sheets.add(workbook.getWorksheets().add("Revenue - Recent weeks"));

    }

    public void writeDataLines(Worksheet sheet, List<RevenueReportDTO> revenueReportDTOs) throws Exception {
        sheet.getCells().importCustomObjects(revenueReportDTOs, new String[] { "TimePeriod", "Revenue" }, true, 0, 0,
                revenueReportDTOs.size(), true, "dd/mm/yyyy",
                false);
        sheet.autoFitColumns();
    }

}
