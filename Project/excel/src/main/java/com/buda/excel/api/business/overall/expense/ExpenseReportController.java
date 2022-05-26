package com.buda.excel.api.business.overall.expense;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.buda.excel.security.RequestResolver;
import com.buda.excel.util.ExcelResponseUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/business/overall/expense")
@CrossOrigin("*")
public class ExpenseReportController {
    private final ExpenseReportService expenseReportService;
    private final ExcelResponseUtil excelResponseUtil;
    private final RequestResolver requestResolver;

    @Autowired
    public ExpenseReportController(ExpenseReportService expenseReportService, ExcelResponseUtil excelResponseUtil,
            RequestResolver requestResolver) {
        this.requestResolver = requestResolver;
        this.expenseReportService = expenseReportService;
        this.excelResponseUtil = excelResponseUtil;
    }

    @GetMapping
    public void overallExpenseReport(HttpServletRequest httpServletRequest, HttpServletResponse response) throws Exception {
        Long userID = this.requestResolver.getProUserIDFromUserToken(httpServletRequest);
        this.excelResponseUtil.validateResponse(response, "expense_report");
        ExpenseReportExporter expenseReportExporter = this.expenseReportService.getExpenseReport(userID);
        expenseReportExporter.export(response);
    }
}
