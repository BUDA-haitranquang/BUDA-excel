package com.buda.excel.api.ingredient.add;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class NewIngredientImporter {
    public List<NewIngredientDTO> excelToIngredient(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();

            List<NewIngredientDTO> newIngredientDTOs = new ArrayList<NewIngredientDTO>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // skip header
                if (rowNumber == 0) {
                rowNumber++;
                continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();
                NewIngredientDTO newIngredientDTO = new NewIngredientDTO();
                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    switch (cellIdx) {
                    case 0: // name 
                        newIngredientDTO.setName(currentCell.getStringCellValue());
                        break;
                    
                    case 1: // ingredient sku 
                        if (currentCell != null && currentCell.getCellType() != CellType.BLANK) {
                            String ingredientSKU = "";
                            if (currentCell.getCellType().equals(CellType.NUMERIC)) {
                                ingredientSKU = String.valueOf(currentCell.getNumericCellValue());
                            }
                            else {
                                ingredientSKU = currentCell.getStringCellValue();
                            }
                            newIngredientDTO.setIngredientSKU(ingredientSKU);
                        }
                        break;

                    case 2: // amount left
                        newIngredientDTO.setAmountLeft(Integer.valueOf((int) currentCell.getNumericCellValue()));
                        break;

                    case 3: // aler amount
                        if (currentCell == null || currentCell.getCellType() == CellType.BLANK) {
                            newIngredientDTO.setAlertAmount(0);
                        }
                        else {
                            newIngredientDTO.setAlertAmount(Integer.valueOf((int) currentCell.getNumericCellValue()));
                        }
                        break;
                    
                    case 4: // description 
                        newIngredientDTO.setDescription(currentCell.getStringCellValue());
                    default:
                        break;
                    }

                    cellIdx++;
                }
                newIngredientDTOs.add(newIngredientDTO);
            }
            workbook.close();
            return newIngredientDTOs;
        }
        catch(IOException e) {
            e.printStackTrace();
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
        
    }
}
