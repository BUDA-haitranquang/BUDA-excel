package com.buda.excel.api.product.add;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImportNewProductService {
    
    private ImportNewProductRepository importNewProductRepository;

    @Autowired
    public ImportNewProductService(ImportNewProductRepository importNewProductRepository) {
        this.importNewProductRepository = importNewProductRepository;
    }

    @Transactional
    public void importProductByExcel(Long userID, MultipartFile file) {
        try {
            NewProductImporter newProductImporter = new NewProductImporter();
            List<NewProductDTO> newProductDTOs =  newProductImporter.excelToProduct(file.getInputStream());
            importNewProductRepository.saveNewProducts(newProductDTOs, userID);
        }
        catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }
}
