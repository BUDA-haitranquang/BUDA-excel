package com.buda.excel.api.product.add;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class NewProductImporter {
    public List<NewProductDTO> excelToProduct(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();

            List<NewProductDTO> newProductDTOs = new ArrayList<NewProductDTO>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // skip header
                if (rowNumber == 0) {
                rowNumber++;
                continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();
                NewProductDTO newProductDTO = new NewProductDTO();
                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    switch (cellIdx) {
                    case 0: // name 
                        newProductDTO.setName(currentCell.getStringCellValue());
                        break;
                    
                    case 1: // product sku 
                        if (currentCell != null && currentCell.getCellType() != CellType.BLANK) {
                            newProductDTO.setProductSKU(currentCell.getStringCellValue());
                        }
                        break;

                    case 2: // amount left
                        newProductDTO.setAmountLeft(Integer.valueOf((int) currentCell.getNumericCellValue()));
                        break;

                    case 3: // aler amount
                        if (currentCell == null || currentCell.getCellType() == CellType.BLANK) {
                            newProductDTO.setAlertAmount(0);
                        }
                        else {
                            newProductDTO.setAlertAmount(Integer.valueOf(currentCell.getStringCellValue()));
                        }
                        break;

                    case 4: // selling price 
                        if (currentCell != null && currentCell.getCellType() != CellType.BLANK) {
                            newProductDTO.setSellingPrice((Double) currentCell.getNumericCellValue());
                        }
                        break;
                    
                    case 5: // cost per unit 
                        if (currentCell != null && currentCell.getCellType() != CellType.BLANK) {
                            newProductDTO.setCostPerUnit((Double) currentCell.getNumericCellValue());
                        }
                        break;
                    
                    case 6: // description 
                        newProductDTO.setDescription(currentCell.getStringCellValue());
                    default:
                        break;
                    }

                    cellIdx++;
                }
                newProductDTOs.add(newProductDTO);
            }
            workbook.close();
            return newProductDTOs;
        }
        catch(IOException e) {
            e.printStackTrace();
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
        
    }
}