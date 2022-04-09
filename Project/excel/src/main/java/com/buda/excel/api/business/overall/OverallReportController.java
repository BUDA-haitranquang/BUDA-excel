package com.buda.excel.api.business.overall;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.buda.excel.security.JwtTokenResolver;
import com.buda.excel.service.login.LoginDTO;
import com.buda.excel.service.login.LoginService;
import com.buda.excel.util.ExcelResponseUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/business/overall")
@CrossOrigin("*")
public class OverallReportController {
    private final LoginService loginService;
    private final ExcelResponseUtil excelResponseUtil;
    private final OverallReportService overallReportService;

    @Autowired
    public OverallReportController(LoginService loginService, ExcelResponseUtil excelResponseUtil,
            OverallReportService overallReportService) {
        this.loginService = loginService;
        this.excelResponseUtil = excelResponseUtil;
        this.overallReportService = overallReportService;
    }

    @PostMapping
    public void getOverallReport(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
            @RequestBody LoginDTO loginDTO) throws Exception {
        excelResponseUtil.validateResponse(httpServletResponse, "overall_report");
        Long userID = this.loginService.getUserID(loginDTO);
        OverallReportExporter overallReportExporter = this.overallReportService.getOverallExport(userID);
        overallReportExporter.export(httpServletResponse);
    }
}
