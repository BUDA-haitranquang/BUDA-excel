package com.buda.excel.util;

import org.springframework.web.multipart.MultipartFile;

public class ExcelHelper {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    public boolean hasExcelFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
          return false;
        }
        return true;
      }
}
