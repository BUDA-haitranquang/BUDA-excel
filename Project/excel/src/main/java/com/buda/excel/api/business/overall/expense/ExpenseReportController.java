package com.buda.excel.api.business.overall.expense;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.buda.excel.security.JwtTokenResolver;
import com.buda.excel.service.login.LoginDTO;
import com.buda.excel.service.login.LoginService;
import com.buda.excel.util.ExcelResponseUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/business/overall/expense")
@CrossOrigin("*")
public class ExpenseReportController {
    private final ExpenseReportService expenseReportService;
    private final ExcelResponseUtil excelResponseUtil;
    private final LoginService loginService;

    @Autowired
    public ExpenseReportController(ExpenseReportService expenseReportService, ExcelResponseUtil excelResponseUtil,
            LoginService loginService) {
        this.loginService = loginService;
        this.expenseReportService = expenseReportService;
        this.excelResponseUtil = excelResponseUtil;
    }

    @PostMapping
    public void overallExpenseReport(HttpServletRequest httpServletRequest, HttpServletResponse response,
            @RequestBody LoginDTO loginDTO) throws Exception {
        Long userID = this.loginService.getUserID(loginDTO);
        this.excelResponseUtil.validateResponse(response, "expense_report");
        ExpenseReportExporter expenseReportExporter = this.expenseReportService.getExpenseReport(userID);
        expenseReportExporter.export(response);
    }
}
