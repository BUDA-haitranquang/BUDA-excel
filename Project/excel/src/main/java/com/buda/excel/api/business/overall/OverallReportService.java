package com.buda.excel.api.business.overall;

import com.buda.excel.api.business.overall.expense.ExpenseReportExporter;
import com.buda.excel.api.business.overall.expense.ExpenseReportService;
import com.buda.excel.api.business.overall.revenue.RevenueReportExporter;
import com.buda.excel.api.business.overall.revenue.RevenueReportService;
import com.buda.excel.api.product.ProductReportExporter;
import com.buda.excel.api.product.ProductReportService;

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
        this.revenueReportService = revenueReportService;
        this.expenseReportService = expenseReportService;
        this.productReportService = productReportService;
    }
    public OverallReportExporter getOverallExport(Long userID) throws Exception {
        OverallReportExporter overallReportExporter = new OverallReportExporter();
        ExpenseReportExporter expenseReportExporter = this.expenseReportService.getExpenseReport(userID);
        RevenueReportExporter revenueReportExporter = this.revenueReportService.getRevenueReport(userID);
        ProductReportExporter productReportExporter = this.productReportService.getProductReport(userID);
        overallReportExporter.getWorkbook().combine(expenseReportExporter.getWorkbook());
        overallReportExporter.getWorkbook().combine(revenueReportExporter.getWorkbook());
        overallReportExporter.getWorkbook().combine(productReportExporter.getWorkbook());
        return overallReportExporter;
    }
}
