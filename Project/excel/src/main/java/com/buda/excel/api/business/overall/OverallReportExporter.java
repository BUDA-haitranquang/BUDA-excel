package com.buda.excel.api.business.overall;

import java.util.ArrayList;
import java.util.List;

import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import com.buda.excel.general.GeneralExporter;

import lombok.Getter;
@Getter
public class OverallReportExporter extends GeneralExporter{
    private List<Worksheet> sheets;
    public OverallReportExporter() {
        workbook = new Workbook();
        sheets = new ArrayList<Worksheet>();
    }
}
