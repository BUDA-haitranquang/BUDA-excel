package com.buda.excel.api.product.inventory;

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

@RequestMapping("api/product/inventory")
@RestController
@CrossOrigin("*")
public class ProductInventoryReportController {
    private final ProductInventoryReportService productInventoryReportService;
    private final ExcelResponseUtil excelResponseUtil;
    private final LoginService loginService;

    @Autowired
    public ProductInventoryReportController(ProductInventoryReportService productInventoryReportService,
            ExcelResponseUtil excelResponseUtil, LoginService loginService) {
        this.productInventoryReportService = productInventoryReportService;
        this.loginService = loginService;
        this.excelResponseUtil = excelResponseUtil;
    }

    @PostMapping
    public void getInventoryReport(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
            @RequestBody LoginDTO loginDTO) throws Exception {
        this.excelResponseUtil.validateResponse(httpServletResponse, "product_inventory");
        Long userID = this.loginService.getUserID(loginDTO);
        ProductInventoryReportExporter productInventoryReportExporter = this.productInventoryReportService
                .getOverallInventoryChange(userID);
        productInventoryReportExporter.export(httpServletResponse);
    }
}
