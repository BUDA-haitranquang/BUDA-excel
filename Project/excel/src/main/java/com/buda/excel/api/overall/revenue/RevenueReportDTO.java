package com.buda.excel.api.overall.revenue;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RevenueReportDTO {
    private String timePeriod;
    private Double revenue;
}
