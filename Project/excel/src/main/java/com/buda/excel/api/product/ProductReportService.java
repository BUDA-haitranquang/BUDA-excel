package com.buda.excel.api.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductReportService {

    private final ProductReportRepository productReportRepository;

    @Autowired
    public ProductReportService(ProductReportRepository productReportRepository) {
        this.productReportRepository = productReportRepository;
    }

    public ProductReportExporter getProductReport(Long userID) throws Exception {
        ProductReportExporter productReportExporter = new ProductReportExporter();
        productReportExporter.writeDataLines(productReportExporter.getWorkbook().getWorksheets().get(0), 
        this.productReportRepository.last30Days(userID));
        return productReportExporter;
    }
}
