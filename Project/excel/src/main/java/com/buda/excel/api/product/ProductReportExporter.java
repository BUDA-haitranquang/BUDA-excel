package com.buda.excel.api.product;

import java.util.List;

import com.aspose.cells.Worksheet;
import com.buda.excel.general.GeneralExporter;

import lombok.Getter;
@Getter
public class ProductReportExporter extends GeneralExporter{
    private List<Worksheet> sheets;
    
}
