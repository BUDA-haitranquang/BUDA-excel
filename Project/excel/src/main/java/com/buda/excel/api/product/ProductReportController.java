package com.buda.excel.api.product;

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
@RequestMapping("api/product")
@CrossOrigin("*")
public class ProductReportController {
    private final ExcelResponseUtil excelResponseUtil;
    private final ProductReportService productReportService;
    @Autowired
    public ProductReportController(ExcelResponseUtil excelResponseUtil, ProductReportService productReportService) {
        this.productReportService = productReportService;
        this.excelResponseUtil = excelResponseUtil;
    }
    @GetMapping(path = "userID/{userID}")
    public void lastMonthReport(@PathVariable Long userID, HttpServletResponse httpServletResponse) throws IOException
    {
        this.excelResponseUtil.validateResponse(httpServletResponse, "product_report");
        ProductReportExporter productReportExporter = this.productReportService.getProductReport(userID);
        productReportExporter.export(httpServletResponse);
    }
}
