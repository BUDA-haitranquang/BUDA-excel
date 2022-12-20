package com.buda.excel.api.product.add;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.buda.excel.security.RequestResolver;
import com.buda.excel.util.ExcelHelper;
import com.buda.excel.util.ResponseMessage;

@RequestMapping("api/product/add")
@RestController
@CrossOrigin("*")
public class ImportNewProductController {
    private final ImportNewProductService importNewProductService;
    private final ExcelHelper excelHelper;
    private final RequestResolver requestResolver;

    @Autowired
    public ImportNewProductController(ImportNewProductService importNewProductService, 
                        ExcelHelper excelHelper, RequestResolver requestResolver) {
        this.importNewProductService = importNewProductService;
        this.requestResolver = requestResolver;
        this.excelHelper =  excelHelper;
    }

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> importProductByExcel(HttpServletRequest httpServletRequest, @RequestParam("file") MultipartFile file) throws Exception {
        Long userID = this.requestResolver.getProUserIDFromUserToken(httpServletRequest);
        String message = "";
        if (excelHelper.hasExcelFormat(file)) {
            try {
                importNewProductService.importProductByExcel(userID, file);
                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            }
            catch (Exception e) {
                e.printStackTrace();
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
              }
        }
        message = "Please upload an excel file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));   
    }
}
