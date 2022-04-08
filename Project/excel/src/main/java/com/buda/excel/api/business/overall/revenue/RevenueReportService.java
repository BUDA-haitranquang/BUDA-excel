package com.buda.excel.api.business.overall.revenue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RevenueReportService {
    private RevenueReportRepository revenueReportRepository;

    @Autowired
    public RevenueReportService(RevenueReportRepository revenueReportRepository) {
        this.revenueReportRepository = revenueReportRepository;
    }

    public RevenueReportExporter getRevenueReport(Long userID) throws Exception {
        RevenueReportExporter revenueReportExporter = new RevenueReportExporter();
        revenueReportExporter.writeDataLines(revenueReportExporter.getWorkbook().getWorksheets().get(1),
                this.revenueReportRepository.lastTwoMonthsReport(userID));
        revenueReportExporter.writeDataLines(revenueReportExporter.getWorkbook().getWorksheets().get(2),
                this.revenueReportRepository.recentMonthsReport(userID));
        revenueReportExporter.writeDataLines(revenueReportExporter.getWorkbook().getWorksheets().get(3),
                this.revenueReportRepository.recentWeeksReport(userID));
        return revenueReportExporter;
    }
}
