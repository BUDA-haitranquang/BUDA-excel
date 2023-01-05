package com.buda.excel.api.ingredient.add;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewIngredientDTO {
    private String name;
    private Integer amountLeft;
    private Integer alertAmount;
    private String description;
    private Double price;
    private String ingredientSKU;
}
