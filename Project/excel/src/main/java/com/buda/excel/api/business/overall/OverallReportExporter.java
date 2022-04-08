package com.buda.excel.api.business.overall;

import java.util.ArrayList;
import java.util.List;

import com.buda.excel.general.GeneralExporter;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import lombok.Getter;
@Getter
public class OverallReportExporter extends GeneralExporter{
    private List<XSSFSheet> sheets;
    public OverallReportExporter() {
        workbook = new XSSFWorkbook();
        sheets = new ArrayList<XSSFSheet>();
    }
}
