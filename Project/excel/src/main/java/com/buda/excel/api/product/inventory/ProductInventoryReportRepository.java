package com.buda.excel.api.product.inventory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class ProductInventoryReportRepository {
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public ProductInventoryReportRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<ProductInventoryReportDTO> genericConverter(List<Map<String, Object>> rows) {
        List<ProductInventoryReportDTO> productInventoryReportDTOs = new ArrayList<ProductInventoryReportDTO>();
        for (Map<String, Object> row : rows) {
            ProductInventoryReportDTO productInventoryReportDTO = new ProductInventoryReportDTO();
            productInventoryReportDTO.setProductID((Long) row.get("product_id"));
            productInventoryReportDTO.setProductSKU((String) row.get("product_sku"));
            productInventoryReportDTO.setAmountChange((BigDecimal) row.get("amount_change"));
            productInventoryReportDTO.setName((String) row.get("name"));
            productInventoryReportDTO.setAmountLeft((Integer) row.get("amount_left"));
            productInventoryReportDTOs.add(productInventoryReportDTO);
        }
        return productInventoryReportDTOs;
    }

    public List<ProductInventoryReportDTO> getInventoryReportLastXDays(Long userID, Long X) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userID", userID);
        params.put("X", X);
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource(params);
        return genericConverter(jdbcTemplate
                .queryForList("select p.product_id as product_id, p.product_sku as product_sku, p.name as name, p.amount_left as amount_left "
                        + ", sum(pll.amount_left_change) as amount_change from product p, product_left_log as pll"
                        + " where p.product_id = pll.product_id and pll.creation_time > ((now() - INTERVAL :X day))"
                        + " and p.user_id = :userID group by p.product_id;", sqlParameterSource));
    }
}
