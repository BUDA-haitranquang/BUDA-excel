package com.buda.excel.api.product.sale;

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
@RequestMapping("api/product/sale")
@CrossOrigin("*")
public class ProductSaleReportController {
    private final ExcelResponseUtil excelResponseUtil;
    private final ProductSaleReportService productReportService;
    private final LoginService loginService;
    @Autowired
    public ProductSaleReportController(ExcelResponseUtil excelResponseUtil, ProductSaleReportService productReportService,
    LoginService loginService) {
        this.productReportService = productReportService;
        this.excelResponseUtil = excelResponseUtil;
        this.loginService = loginService;
    }
    @PostMapping
    public void lastMonthReport(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
    @RequestBody LoginDTO loginDTO) throws Exception
    {
        this.excelResponseUtil.validateResponse(httpServletResponse, "product_report");
        Long userID = this.loginService.getUserID(loginDTO);
        ProductSaleReportExporter productReportExporter = this.productReportService.getProductReport(userID);
        productReportExporter.export(httpServletResponse);
    }
}