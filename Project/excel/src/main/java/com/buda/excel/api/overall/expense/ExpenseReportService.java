package com.buda.excel.api.overall.expense;

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
    public List<ExpenseReportDTO> lastTwoMonthsReport(Long userID){
        return this.expenseReportRepository.lastTwoMonthsReport(userID);
    }
}
