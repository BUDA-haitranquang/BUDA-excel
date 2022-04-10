package com.buda.excel.api.product.inventory;

import java.util.List;

import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import com.buda.excel.general.GeneralExporter;

import lombok.Getter;
@Getter
public class ProductInventoryReportExporter extends GeneralExporter {

    public ProductInventoryReportExporter() {
        this.workbook = new Workbook();
        workbook.getWorksheets().clear();
    }

    public void addSheet(String sheetName) {
        workbook.getWorksheets().add(sheetName);
    }
    public void writeDataLines(Worksheet sheet, List<ProductInventoryReportDTO> productInventoryReportDTOs)
            throws Exception {
        sheet.getCells().importCustomObjects(productInventoryReportDTOs,
        new String[] { "ProductID", "Name", "AmountLeft", "AmountChange" }, true, 0, 0,
        productInventoryReportDTOs.size(), true, "dd/mm/yyyy",
        false);
        sheet.autoFitColumns();
    }
}
