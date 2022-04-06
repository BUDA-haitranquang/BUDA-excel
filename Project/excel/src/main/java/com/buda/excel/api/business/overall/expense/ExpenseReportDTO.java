package com.buda.excel.api.business.overall.expense;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseReportDTO {
    private String timePeriod;
    private Double expense;
}
