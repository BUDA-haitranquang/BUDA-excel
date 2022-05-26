package com.buda.excel.api.product.inventory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.buda.excel.security.RequestResolver;
import com.buda.excel.util.ExcelResponseUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/product/inventory")
@RestController
@CrossOrigin("*")
public class ProductInventoryReportController {
    private final ProductInventoryReportService productInventoryReportService;
    private final ExcelResponseUtil excelResponseUtil;
    private final RequestResolver requestResolver;

    @Autowired
    public ProductInventoryReportController(ProductInventoryReportService productInventoryReportService,
            ExcelResponseUtil excelResponseUtil, RequestResolver requestResolver) {
        this.productInventoryReportService = productInventoryReportService;
        this.requestResolver = requestResolver;
        this.excelResponseUtil = excelResponseUtil;
    }

    @GetMapping
    public void getInventoryReport(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        this.excelResponseUtil.validateResponse(httpServletResponse, "product_inventory");
        Long userID = this.requestResolver.getProUserIDFromUserToken(httpServletRequest);
        ProductInventoryReportExporter productInventoryReportExporter = this.productInventoryReportService
                .getOverallInventoryChange(userID);
        productInventoryReportExporter.export(httpServletResponse);
    }
}
