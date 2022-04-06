package com.buda.excel.api.overall.expense;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.buda.excel.util.ExcelResponseUtil;

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
    private final ExcelResponseUtil excelResponseUtil;
    @Autowired
    public ExpenseReportController(ExpenseReportService expenseReportService, ExcelResponseUtil excelResponseUtil){
        this.expenseReportService = expenseReportService;
        this.excelResponseUtil = excelResponseUtil;
    }
    @GetMapping("/userID/{userID}")
    public void overallExpenseReport(@PathVariable Long userID, HttpServletResponse response) throws IOException {
        this.excelResponseUtil.validateResponse(response, "expense_report");
        ExpenseReportExporter expenseReportExporter = this.expenseReportService.getExpenseReport(userID);
        expenseReportExporter.export(response);
    }
}
