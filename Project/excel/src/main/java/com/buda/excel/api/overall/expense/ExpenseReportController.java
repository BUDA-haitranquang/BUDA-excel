package com.buda.excel.api.overall.expense;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/overall/expense")
@CrossOrigin("*")
public class ExpenseReportController {
    private final ExpenseReportService expenseReportService;
    @Autowired
    public ExpenseReportController(ExpenseReportService expenseReportService){
        this.expenseReportService = expenseReportService;
    }
    @GetMapping("/{userID}")
    public ResponseEntity<?> overallExpenseReport(@PathVariable Long userID) {
        return ResponseEntity.ok().body(this.expenseReportService.overallExpenseReport(userID));
    }
}
