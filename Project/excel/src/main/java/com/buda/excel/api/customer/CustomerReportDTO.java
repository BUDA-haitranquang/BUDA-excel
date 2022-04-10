package com.buda.excel.api.customer;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerReportDTO {
    private String name;
    private String address;
    private String phoneNumber;
    private String ageGroup;
    private String gender;
    private BigDecimal totalSpendPeriod;
}
