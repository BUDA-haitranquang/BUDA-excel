package com.buda.excel.api.ingredient.add;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


@Service
public class ImportNewIngredientService {
    private ImportNewIngredientRepository importNewIngredientRepository;

    @Autowired
    public ImportNewIngredientService(ImportNewIngredientRepository importNewIngredientRepository) {
        this.importNewIngredientRepository = importNewIngredientRepository;
    }

    @Transactional
    public void importIngredientByExcel(Long userID, MultipartFile file) {
        try {
            NewIngredientImporter newIngredientImporter = new NewIngredientImporter();
            List<NewIngredientDTO> newIngredientDTOs =  newIngredientImporter.excelToIngredient(file.getInputStream());
            importNewIngredientRepository.saveNewIngredients(newIngredientDTOs, userID);
        }
        catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }
}
