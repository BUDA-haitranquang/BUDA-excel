package com.buda.excel.api.business.overall;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.buda.excel.security.JwtTokenResolver;
import com.buda.excel.util.ExcelResponseUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/business/overall")
@CrossOrigin("*")
public class OverallReportController {
    private final JwtTokenResolver jwtTokenResolver;
    private final ExcelResponseUtil excelResponseUtil;
    private final OverallReportService overallReportService;
    @Autowired
    public OverallReportController(JwtTokenResolver jwtTokenResolver, ExcelResponseUtil excelResponseUtil,
    OverallReportService overallReportService){
        this.jwtTokenResolver = jwtTokenResolver;
        this.excelResponseUtil = excelResponseUtil;
        this.overallReportService = overallReportService;
    }
    @GetMapping
    public void getOverallReport(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception{
        excelResponseUtil.validateResponse(httpServletResponse, "overall_report");
        Long userID = this.jwtTokenResolver.getUserIDFromToken(httpServletRequest);
        OverallReportExporter overallReportExporter = this.overallReportService.getOverallExport(userID);
        overallReportExporter.export(httpServletResponse);
    }
}
