package com.buda.excel.api.product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class ProductReportRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    @Autowired
    public ProductReportRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<ProductReportDTO> genericConverter(List<Map<String, Object>> rows) {
        List<ProductReportDTO> productReportDTOs = new ArrayList<ProductReportDTO>();
        for (Map<String, Object> row: rows) {
            ProductReportDTO productReportDTO = new ProductReportDTO();
            productReportDTO.setName((String) row.get("name"));
            productReportDTO.setRevenue((Double) row.get("revenue"));
            productReportDTO.setProfit((Double) row.get("profit"));
            productReportDTO.setSellNumber((BigDecimal) row.get("sellNumber"));
            productReportDTO.setReturnNumber((BigDecimal) row.get("returnNumber"));
            productReportDTOs.add(productReportDTO);
        }
        return productReportDTOs;
    }
    public List<ProductReportDTO> last30Days(Long userID) {
        SqlParameterSource source = new MapSqlParameterSource("userID", userID);
        return genericConverter(jdbcTemplate.queryForList("select i.product_id as productID, p.name as name, "
        + "sum(if(s.status = 'FINISHED', i.price_per_unit * i.quantity, 0)) as revenue, "
        + "sum(if(s.status = 'FINISHED', (i.price_per_unit - i.cost_per_unit) * i.quantity, 0)) as profit, "
        + "sum(if(s.status = 'FINISHED', i.quantity, 0)) as sellNumber, "
        + "sum(if(s.status = 'RETURNED', i.quantity, 0)) as returnNumber, "
        + "sum(if(s.status = 'RETURNED', i.price_per_unit * i.quantity, 0)) as returnPrice "
        + "from sell_order_item i " + "inner join product p on i.product_id = p.product_id " + "inner join sell_order s on s.sell_order_id = i.sell_order_id "
        + "where i.user_id = :userID and s.finish_time BETWEEN DATE_SUB(NOW(), INTERVAL 30 DAY) and now() "
        + "group by i.product_id "
        + "order by sellNumber asc, p.name asc ", source));
    }
}
