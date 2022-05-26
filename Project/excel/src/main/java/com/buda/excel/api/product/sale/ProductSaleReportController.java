package com.buda.excel.api.product.sale;

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
@RequestMapping("api/product/sale")
@CrossOrigin("*")
public class ProductSaleReportController {
    private final ExcelResponseUtil excelResponseUtil;
    private final ProductSaleReportService productReportService;
    private final RequestResolver requestResolver;
    @Autowired
    public ProductSaleReportController(ExcelResponseUtil excelResponseUtil, ProductSaleReportService productReportService,
    RequestResolver requestResolver) {
        this.productReportService = productReportService;
        this.excelResponseUtil = excelResponseUtil;
        this.requestResolver = requestResolver;
    }
    @GetMapping
    public void lastMonthReport(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
        this.excelResponseUtil.validateResponse(httpServletResponse, "product_report");
        Long userID = this.requestResolver.getProUserIDFromUserToken(httpServletRequest);
        ProductSaleReportExporter productReportExporter = this.productReportService.getProductReport(userID);
        productReportExporter.export(httpServletResponse);
    }
}
