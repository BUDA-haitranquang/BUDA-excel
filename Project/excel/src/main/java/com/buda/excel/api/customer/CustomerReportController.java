package com.buda.excel.api.customer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.buda.excel.service.login.LoginDTO;
import com.buda.excel.service.login.LoginService;
import com.buda.excel.util.ExcelResponseUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/customer")
@CrossOrigin("*")
@RestController
public class CustomerReportController {
    private final LoginService loginService;
    private final CustomerReportService customerReportService;
    private final ExcelResponseUtil excelResponseUtil;

    @Autowired
    public CustomerReportController(LoginService loginService, CustomerReportService customerReportService,
            ExcelResponseUtil excelResponseUtil) {
        this.loginService = loginService;
        this.customerReportService = customerReportService;
        this.excelResponseUtil = excelResponseUtil;
    }

    @PostMapping
    public void getCustomerReport(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
    @RequestBody LoginDTO loginDTO) throws Exception {
        excelResponseUtil.validateResponse(httpServletResponse, "customer_report");
        Long userID = this.loginService.getUserID(loginDTO);
        CustomerReportExporter customerReportExporter = customerReportService.getCustomerReport(userID);
        customerReportExporter.export(httpServletResponse);
    }
}
