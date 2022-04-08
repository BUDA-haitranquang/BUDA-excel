package com.buda.excel.general;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.aspose.cells.Workbook;

import lombok.Getter;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@Getter
public abstract class GeneralExporter {
    public Workbook workbook;
    public void export(HttpServletResponse response) throws Exception {
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.save(outputStream, 2);
        outputStream.close();
    }
}
