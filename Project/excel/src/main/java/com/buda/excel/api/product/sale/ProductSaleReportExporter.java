package com.buda.excel.api.product.sale;

import java.util.List;

import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import com.buda.excel.general.GeneralExporter;

import lombok.Getter;

@Getter
public class ProductSaleReportExporter extends GeneralExporter {

    public ProductSaleReportExporter() {
        this.workbook = new Workbook();
        this.workbook.getWorksheets().clear();
        writeHeaderLines();
    }
    public void writeHeaderLines() {
        workbook.getWorksheets().add("Product last two months");
    }

    public void writeDataLines(Worksheet worksheet, List<ProductSaleReportDTO> productReportDTOs) throws Exception {
        worksheet.getCells().importCustomObjects(productReportDTOs,
                new String[] { "Name", "Revenue", "Profit", "SellNumber", "ReturnNumber" }, true, 0, 0,
                productReportDTOs.size(), true, "dd/mm/yyyy",
                false);
        worksheet.autoFitColumns();
    }
}
