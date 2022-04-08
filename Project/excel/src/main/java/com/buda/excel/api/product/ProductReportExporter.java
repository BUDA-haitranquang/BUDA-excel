package com.buda.excel.api.product;

import java.util.ArrayList;
import java.util.List;

import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import com.buda.excel.general.GeneralExporter;

import lombok.Getter;

@Getter
public class ProductReportExporter extends GeneralExporter {
    private List<Worksheet> sheets;

    public ProductReportExporter() {
        this.workbook = new Workbook();
        this.workbook.getWorksheets().clear();
        this.sheets = new ArrayList<Worksheet>();
        writeHeaderLines();
    }
    public void writeHeaderLines() {
        sheets.add(workbook.getWorksheets().add("Product last two months"));
    }

    public void writeDataLines(Worksheet worksheet, List<ProductReportDTO> productReportDTOs) throws Exception {
        worksheet.getCells().importCustomObjects(productReportDTOs,
                new String[] { "Name", "Revenue", "Profit", "SellNumber", "ReturnNumber" }, true, 0, 0,
                productReportDTOs.size(), true, "dd/mm/yyyy",
                false);
        worksheet.autoFitColumns();
    }
}
