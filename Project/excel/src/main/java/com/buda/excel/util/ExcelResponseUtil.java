package com.buda.excel.util;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

@Service
public class ExcelResponseUtil {
    public HttpServletResponse validateResponse(HttpServletResponse response, String fileName) {
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=" + fileName + ".xlsx";
        response.setHeader(headerKey, headerValue);
        return response;
    }
}
