package com.buda.excel.api.business.overall.revenue;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.buda.excel.security.JwtTokenResolver;
import com.buda.excel.util.ExcelResponseUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/business/overall/revenue")
@CrossOrigin("*")
public class RevenueReportController {
    private final RevenueReportService revenueReportService;
    private final ExcelResponseUtil excelResponseUtil;
    private final JwtTokenResolver jwtTokenResolver;
    @Autowired
    public RevenueReportController(RevenueReportService revenueReportService, ExcelResponseUtil excelResponseUtil,
    JwtTokenResolver jwtTokenResolver) {
        this.jwtTokenResolver = jwtTokenResolver;
        this.revenueReportService = revenueReportService;
        this.excelResponseUtil = excelResponseUtil;
    }
    @GetMapping
    public void getRevenueReport(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception{
        this.excelResponseUtil.validateResponse(httpServletResponse, "revenue_report");
        Long userID = this.jwtTokenResolver.getUserIDFromToken(httpServletRequest);
        RevenueReportExporter revenueReportExporter = this.revenueReportService.getRevenueReport(userID);
        revenueReportExporter.export(httpServletResponse);
    }
}
