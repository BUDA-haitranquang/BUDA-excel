package com.buda.excel.api.product.add;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewProductDTO {
    private String name;
    private Integer amountLeft;
    private Integer alertAmount;
    private String description;
    private Double sellingPrice;
    private String productSKU;
    private Double costPerUnit;
}
