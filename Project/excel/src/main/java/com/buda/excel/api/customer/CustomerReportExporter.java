package com.buda.excel.api.customer;

import java.util.ArrayList;
import java.util.List;

import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import com.buda.excel.general.GeneralExporter;

public class CustomerReportExporter extends GeneralExporter{
    private List<Worksheet> sheets;
    public CustomerReportExporter() {
        workbook = new Workbook();
        workbook.getWorksheets().clear();
        sheets = new ArrayList<Worksheet>();
    }
    public void writeHeaderLines() {
        sheets.add(workbook.getWorksheets().add("Customer Report - Last two months"));
        sheets.add(workbook.getWorksheets().add("Customer Report - All time"));
    }
    public void writeDataLines(Worksheet worksheet, List<CustomerReportDTO> customerReportDTOs) throws Exception {

    }
}
