package com.buda.excel.api.product.sale;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductSaleReportDTO {
    private String name;
    private Double revenue;
    private Double profit;
    private BigDecimal sellNumber;
    private BigDecimal returnNumber;
}
