package com.buda.excel.api.customer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.buda.excel.security.RequestResolver;
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
    private final RequestResolver requestResolver;
    private final CustomerReportService customerReportService;
    private final ExcelResponseUtil excelResponseUtil;

    @Autowired
    public CustomerReportController(RequestResolver requestResolver, CustomerReportService customerReportService,
            ExcelResponseUtil excelResponseUtil) {
        this.requestResolver = requestResolver;
        this.customerReportService = customerReportService;
        this.excelResponseUtil = excelResponseUtil;
    }

    @GetMapping
    public void getCustomerReport(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse
    ) throws Exception {
        excelResponseUtil.validateResponse(httpServletResponse, "customer_report");
        Long userID = this.requestResolver.getProUserIDFromUserToken(httpServletRequest);
        CustomerReportExporter customerReportExporter = customerReportService.getCustomerReport(userID);
        customerReportExporter.export(httpServletResponse);
    }
}
