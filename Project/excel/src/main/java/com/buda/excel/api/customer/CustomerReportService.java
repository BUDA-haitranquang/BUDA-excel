package com.buda.excel.api.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerReportService {
    private final CustomerReportRepository customerReportRepository;

    @Autowired
    public CustomerReportService(CustomerReportRepository customerReportRepository) {
        this.customerReportRepository = customerReportRepository;
    }

    public CustomerReportExporter getCustomerReport(Long userID) throws Exception {
        CustomerReportExporter customerReportExporter = new CustomerReportExporter();
        customerReportExporter.writeDataLines(customerReportExporter.getWorkbook().getWorksheets().get("Customer - Last two months"),
                this.customerReportRepository.getCustomerReportLastXDays(userID, Long.valueOf(60)));
        customerReportExporter.writeDataLines(customerReportExporter.getWorkbook().getWorksheets().get("Customer - All time"), 
                this.customerReportRepository.getCustomerReportLastXDays(userID, Long.valueOf(50000)));
        return customerReportExporter;
    }
}
