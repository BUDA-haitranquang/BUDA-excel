package com.buda.excel.api.product.sale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductSaleReportService {

    private final ProductSaleReportRepository productReportRepository;

    @Autowired
    public ProductSaleReportService(ProductSaleReportRepository productReportRepository) {
        this.productReportRepository = productReportRepository;
    }

    public ProductSaleReportExporter getProductReport(Long userID) throws Exception {
        ProductSaleReportExporter productReportExporter = new ProductSaleReportExporter();
        productReportExporter.writeDataLines(productReportExporter.getWorkbook().getWorksheets().get(0), 
        this.productReportRepository.last30Days(userID));
        return productReportExporter;
    }
}
