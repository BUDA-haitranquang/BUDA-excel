package com.buda.excel.api.customer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.buda.excel.security.JwtTokenResolver;
import com.buda.excel.util.ExcelResponseUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/customer")
@CrossOrigin("*")
@RestController
public class CustomerReportController {
    private final JwtTokenResolver jwtTokenResolver;
    private final CustomerReportService customerReportService;
    private final ExcelResponseUtil excelResponseUtil;

    @Autowired
    public CustomerReportController(JwtTokenResolver jwtTokenResolver, CustomerReportService customerReportService,
            ExcelResponseUtil excelResponseUtil) {
        this.jwtTokenResolver = jwtTokenResolver;
        this.customerReportService = customerReportService;
        this.excelResponseUtil = excelResponseUtil;
    }

    @GetMapping
    public void getCustomerReport(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        excelResponseUtil.validateResponse(httpServletResponse, "cool");
        Long userID = this.jwtTokenResolver.getUserIDFromToken(httpServletRequest);
        CustomerReportExporter customerReportExporter = new CustomerReportExporter();
        customerReportExporter.export(httpServletResponse);
    }
}
