package com.buda.excel.api.product;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.buda.excel.security.JwtTokenResolver;
import com.buda.excel.util.ExcelResponseUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/product")
@CrossOrigin("*")
public class ProductReportController {
    private final ExcelResponseUtil excelResponseUtil;
    private final ProductReportService productReportService;
    private final JwtTokenResolver jwtTokenResolver;
    @Autowired
    public ProductReportController(ExcelResponseUtil excelResponseUtil, ProductReportService productReportService,
    JwtTokenResolver jwtTokenResolver) {
        this.productReportService = productReportService;
        this.excelResponseUtil = excelResponseUtil;
        this.jwtTokenResolver = jwtTokenResolver;
    }
    @GetMapping
    public void lastMonthReport(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
        this.excelResponseUtil.validateResponse(httpServletResponse, "product_report");
        Long userID = this.jwtTokenResolver.getUserIDFromToken(httpServletRequest);
        ProductReportExporter productReportExporter = this.productReportService.getProductReport(userID);
        productReportExporter.export(httpServletResponse);
    }
}
