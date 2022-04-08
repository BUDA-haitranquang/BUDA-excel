package com.buda.excel.api.customer;

import java.util.List;

import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import com.buda.excel.general.GeneralExporter;

public class CustomerReportExporter extends GeneralExporter{
    private List<Worksheet> sheets;
    public CustomerReportExporter() {
        workbook = new Workbook();
        Worksheet worksheet = workbook.getWorksheets().add("Great");
    }
    
}
