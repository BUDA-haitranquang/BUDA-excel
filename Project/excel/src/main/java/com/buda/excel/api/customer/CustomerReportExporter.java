package com.buda.excel.api.customer;

import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.aspose.cells.SaveOptions;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;

public class CustomerReportExporter {
    private Workbook workbook;
    private List<Worksheet> sheets;
    public CustomerReportExporter() {
        workbook = new Workbook();
        Worksheet worksheet = workbook.getWorksheets().add("Great");
    }
    public void export(HttpServletResponse httpServletResponse) throws Exception {
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        workbook.save(servletOutputStream, 2);
        servletOutputStream.close();
    }
}
