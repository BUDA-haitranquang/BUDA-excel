package com.buda.excel.api.business.overall;

import com.buda.excel.api.business.overall.expense.ExpenseReportExporter;
import com.buda.excel.api.business.overall.expense.ExpenseReportService;
import com.buda.excel.api.business.overall.revenue.RevenueReportExporter;
import com.buda.excel.api.business.overall.revenue.RevenueReportService;
import com.buda.excel.api.product.ProductReportExporter;
import com.buda.excel.api.product.ProductReportService;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OverallReportService {
    private final ExpenseReportService expenseReportService;
    private final RevenueReportService revenueReportService;
    private final ProductReportService productReportService;
    @Autowired
    public OverallReportService(ExpenseReportService expenseReportService, RevenueReportService revenueReportService,
    ProductReportService productReportService) {
        this.revenueReportService =revenueReportService;
        this.expenseReportService = expenseReportService;
        this.productReportService = productReportService;
    }
    public OverallReportExporter getOverallExport(Long userID) {
        OverallReportExporter overallReportExporter = new OverallReportExporter();
        ExpenseReportExporter expenseReportExporter = this.expenseReportService.getExpenseReport(userID);
        RevenueReportExporter revenueReportExporter = this.revenueReportService.getRevenueReport(userID);
        ProductReportExporter productReportExporter = this.productReportService.getProductReport(userID);
        for (XSSFSheet sheet: expenseReportExporter.getSheets()) {
            overallReportExporter.getSheets().add(sheet);
        }
        for (XSSFSheet sheet: revenueReportExporter.getSheets()) {
            overallReportExporter.getSheets().add(sheet);
        }
        for (XSSFSheet sheet: productReportExporter.getSheets()) {
            overallReportExporter.getSheets().add(sheet);
        }
        return overallReportExporter;
    }
}
