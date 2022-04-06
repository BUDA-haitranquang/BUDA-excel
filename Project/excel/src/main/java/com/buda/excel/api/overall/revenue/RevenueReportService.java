package com.buda.excel.api.overall.revenue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RevenueReportService {
    private RevenueReportRepository revenueReportRepository;
    @Autowired
    public RevenueReportService(RevenueReportRepository revenueReportRepository) {
        this.revenueReportRepository = revenueReportRepository;
    }
    public RevenueReportExporter getRevenueReport(Long userID) {
        RevenueReportExporter revenueReportExporter = new RevenueReportExporter();
        revenueReportExporter.writeDataLines(revenueReportExporter.getSheets().get(0), this.revenueReportRepository.lastTwoMonthsReport(userID));
        revenueReportExporter.writeDataLines(revenueReportExporter.getSheets().get(1), this.revenueReportRepository.recentMonthsReport(userID));
        revenueReportExporter.writeDataLines(revenueReportExporter.getSheets().get(2), this.revenueReportRepository.recentWeeksReport(userID));
        return revenueReportExporter;
    }
}
