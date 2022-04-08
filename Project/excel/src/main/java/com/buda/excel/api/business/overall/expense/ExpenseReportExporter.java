package com.buda.excel.api.business.overall.expense;

import java.util.ArrayList;
import java.util.List;

import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import com.buda.excel.general.GeneralExporter;

import lombok.Getter;

@Getter
public class ExpenseReportExporter extends GeneralExporter {
    private List<Worksheet> sheets;

    public ExpenseReportExporter() {
        workbook = new Workbook();
        workbook.getWorksheets().clear();
        sheets = new ArrayList<Worksheet>();
        writeHeaderLine();
    }

    private void writeHeaderLine() {
        sheets.add(workbook.getWorksheets().add("Expense - Last two months"));
        sheets.add(workbook.getWorksheets().add("Expense - Recent months"));
        sheets.add(workbook.getWorksheets().add("Expense - Recent weeks"));
    }

    public void writeDataLines(Worksheet sheet, List<ExpenseReportDTO> expenseReportDTOs) throws Exception {
        sheet.getCells().importCustomObjects(expenseReportDTOs, new String[] { "TimePeriod", "Expense" }, true, 0, 0,
                expenseReportDTOs.size(), true, "dd/mm/yyyy",
                false);
        sheet.autoFitColumns();
    }
}
