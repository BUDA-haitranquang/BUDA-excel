package com.buda.excel.api.business.overall;

import com.buda.excel.api.business.overall.expense.ExpenseReportExporter;
import com.buda.excel.api.business.overall.expense.ExpenseReportService;
import com.buda.excel.api.business.overall.revenue.RevenueReportExporter;
import com.buda.excel.api.business.overall.revenue.RevenueReportService;
import com.buda.excel.api.product.inventory.ProductInventoryReportExporter;
import com.buda.excel.api.product.inventory.ProductInventoryReportService;
import com.buda.excel.api.product.sale.ProductSaleReportExporter;
import com.buda.excel.api.product.sale.ProductSaleReportService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OverallReportService {
    private final ExpenseReportService expenseReportService;
    private final RevenueReportService revenueReportService;
    private final ProductSaleReportService productSaleReportService;
    private final ProductInventoryReportService productInventoryReportService;
    @Autowired
    public OverallReportService(ExpenseReportService expenseReportService, RevenueReportService revenueReportService,
    ProductSaleReportService productReportService, ProductInventoryReportService productInventoryReportService) {
        this.revenueReportService = revenueReportService;
        this.productInventoryReportService = productInventoryReportService;
        this.expenseReportService = expenseReportService;
        this.productSaleReportService = productReportService;
    }
    public OverallReportExporter getOverallExport(Long userID) throws Exception {
        OverallReportExporter overallReportExporter = new OverallReportExporter();
        ExpenseReportExporter expenseReportExporter = this.expenseReportService.getExpenseReport(userID);
        RevenueReportExporter revenueReportExporter = this.revenueReportService.getRevenueReport(userID);
        ProductSaleReportExporter productSaleReportExporter = this.productSaleReportService.getProductReport(userID);
        ProductInventoryReportExporter productInventoryReportExporter = this.productInventoryReportService.getOverallInventoryChange(userID);
        overallReportExporter.getWorkbook().combine(expenseReportExporter.getWorkbook());
        overallReportExporter.getWorkbook().combine(revenueReportExporter.getWorkbook());
        overallReportExporter.getWorkbook().combine(productSaleReportExporter.getWorkbook());
        overallReportExporter.getWorkbook().combine(productInventoryReportExporter.getWorkbook());
        return overallReportExporter;
    }
}
