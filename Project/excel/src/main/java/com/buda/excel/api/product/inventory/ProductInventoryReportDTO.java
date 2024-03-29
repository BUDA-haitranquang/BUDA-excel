package com.buda.excel.api.product.inventory;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductInventoryReportDTO {
    private Long productID;
    private String name;
    private Integer amountLeft;
    private BigDecimal amountChange;
    private String productSKU;
}
