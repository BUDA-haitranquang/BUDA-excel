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
        writeHeaderLines();
    }
    public void writeHeaderLines() {
        sheets.add(workbook.getWorksheets().add("Customer - Last two months"));
        sheets.add(workbook.getWorksheets().add("Customer - All time"));
        sheets.add(workbook.getWorksheets().add("Customer - By Age Group"));
        sheets.add(workbook.getWorksheets().add("Customer - By Gender"));
    }
    public void writeDataLines(Worksheet worksheet, List<CustomerReportDTO> customerReportDTOs) throws Exception {
        worksheet.getCells().importCustomObjects(customerReportDTOs, new String[] { "Name", "Address", "PhoneNumber", "AgeGroup", "Gender", "TotalSpendPeriod" }, true, 0, 0,
                customerReportDTOs.size(), true, "dd/mm/yyyy",
                false);
        worksheet.autoFitColumns();
    }
}
