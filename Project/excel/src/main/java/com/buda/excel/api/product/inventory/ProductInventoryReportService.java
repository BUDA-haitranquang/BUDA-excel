package com.buda.excel.api.product.inventory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductInventoryReportService {
    private final ProductInventoryReportRepository productInventoryReportRepository;

    @Autowired
    public ProductInventoryReportService(ProductInventoryReportRepository productInventoryReportRepository) {
        this.productInventoryReportRepository = productInventoryReportRepository;
    }

    public ProductInventoryReportExporter getOverallInventoryChange(Long userID) throws Exception {
        ProductInventoryReportExporter productInventoryReportExporter = new ProductInventoryReportExporter();
        productInventoryReportExporter.addSheet("Product Inventory - 7 Days");
        productInventoryReportExporter.writeDataLines(
                productInventoryReportExporter.getWorkbook().getWorksheets()
                        .get("Product Inventory - 7 Days"),
                this.productInventoryReportRepository.getInventoryReportLastXDays(userID, Long.valueOf(7)));
        productInventoryReportExporter.addSheet("Product Inventory - 30 Days");
        productInventoryReportExporter.writeDataLines(
                productInventoryReportExporter.getWorkbook().getWorksheets()
                        .get("Product Inventory - 30 Days"),
                this.productInventoryReportRepository.getInventoryReportLastXDays(userID, Long.valueOf(30)));
        productInventoryReportExporter.addSheet("Product Inventory - 90 Days");
        productInventoryReportExporter.writeDataLines(
                productInventoryReportExporter.getWorkbook().getWorksheets()
                        .get("Product Inventory - 90 Days"),
                this.productInventoryReportRepository.getInventoryReportLastXDays(userID, Long.valueOf(90)));
        return productInventoryReportExporter;
    }

}
