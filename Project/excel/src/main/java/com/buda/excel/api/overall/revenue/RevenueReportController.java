package com.buda.excel.api.overall.revenue;

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
@RequestMapping("api/overall/revenue")
@CrossOrigin("*")
public class RevenueReportController {
    private final RevenueReportService revenueReportService;
    private final ExcelResponseUtil excelResponseUtil;
    @Autowired
    public RevenueReportController(RevenueReportService revenueReportService, ExcelResponseUtil excelResponseUtil) {
        this.revenueReportService = revenueReportService;
        this.excelResponseUtil = excelResponseUtil;
    }
    @GetMapping(path = "userID/{userID}")
    public void getRevenueReport(@PathVariable Long userID, HttpServletResponse httpServletResponse) throws IOException{
        this.excelResponseUtil.validateResponse(httpServletResponse, "revenue_report");
        RevenueReportExporter revenueReportExporter = this.revenueReportService.getRevenueReport(userID);
        revenueReportExporter.export(httpServletResponse);
    }
}
