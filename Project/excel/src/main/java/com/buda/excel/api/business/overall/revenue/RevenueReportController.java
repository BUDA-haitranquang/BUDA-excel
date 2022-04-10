package com.buda.excel.api.business.overall.revenue;

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

@RestController
@RequestMapping("api/business/overall/revenue")
@CrossOrigin("*")
public class RevenueReportController {
    private final RevenueReportService revenueReportService;
    private final ExcelResponseUtil excelResponseUtil;
    private final LoginService loginService;
    @Autowired
    public RevenueReportController(RevenueReportService revenueReportService, ExcelResponseUtil excelResponseUtil,
    LoginService loginService) {
        this.loginService = loginService;
        this.revenueReportService = revenueReportService;
        this.excelResponseUtil = excelResponseUtil;
    }
    @PostMapping
    public void getRevenueReport(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
    @RequestBody LoginDTO loginDTO) throws Exception{
        this.excelResponseUtil.validateResponse(httpServletResponse, "revenue_report");
        Long userID = this.loginService.getUserID(loginDTO);
        RevenueReportExporter revenueReportExporter = this.revenueReportService.getRevenueReport(userID);
        revenueReportExporter.export(httpServletResponse);
    }
}
