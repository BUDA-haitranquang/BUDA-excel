package com.buda.excel.api.overall.expense;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/overall/expense")
@CrossOrigin("*")
public class ExpenseReportController {
    private final ExpenseReportService expenseReportService;
    @Autowired
    public ExpenseReportController(ExpenseReportService expenseReportService){
        this.expenseReportService = expenseReportService;
    }
    @GetMapping("/userID/{userID}")
    public void overallExpenseReport(@PathVariable Long userID, HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=expense-report.xlsx";
        response.setHeader(headerKey, headerValue);
        ExpenseReportExporter expenseReportExporter = this.expenseReportService.getExpenseReport(userID);
        expenseReportExporter.export(response);
    }
}
