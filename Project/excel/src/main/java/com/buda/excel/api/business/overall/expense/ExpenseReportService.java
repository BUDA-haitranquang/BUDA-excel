package com.buda.excel.api.business.overall.expense;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpenseReportService {
    private final ExpenseReportRepository expenseReportRepository;

    @Autowired
    public ExpenseReportService(ExpenseReportRepository expenseReportRepository) {
        this.expenseReportRepository = expenseReportRepository;
    }

    public ExpenseReportExporter getExpenseReport(Long userID) throws Exception {
        ExpenseReportExporter expenseReportExporter = new ExpenseReportExporter();
        expenseReportExporter.writeDataLines(expenseReportExporter.getWorkbook().getWorksheets().get(0),
                this.expenseReportRepository.lastTwoMonthsReport(userID));
        expenseReportExporter.writeDataLines(expenseReportExporter.getWorkbook().getWorksheets().get(1), 
                this.expenseReportRepository.recentMonthsReport(userID));
        expenseReportExporter.writeDataLines(expenseReportExporter.getWorkbook().getWorksheets().get(2), 
                this.expenseReportRepository.recentWeeksReport(userID));
        return expenseReportExporter;
    }
}
