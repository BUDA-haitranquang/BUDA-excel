package com.buda.excel.api.customer;

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
public class CustomerReportRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public CustomerReportRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<CustomerReportDTO> genericConverter(List<Map<String, Object>> rows) {
        List<CustomerReportDTO> customerReportDTOs = new ArrayList<CustomerReportDTO>();
        for (Map<String, Object> row : rows) {
            CustomerReportDTO customerReportDTO = new CustomerReportDTO();
            customerReportDTO.setName((String) row.get("name"));
            customerReportDTO.setAddress((String) row.get("address"));
            customerReportDTO.setAgeGroup((String) row.get("age_group"));
            customerReportDTO.setGender((String) row.get("gender"));
            customerReportDTO.setPhoneNumber("'" + (String) row.get("phone_number"));
            customerReportDTO.setTotalSpendPeriod(BigDecimal.valueOf((Double) row.get("total_spend_period")));
            customerReportDTOs.add(customerReportDTO);
        }
        return customerReportDTOs;
    }

    public List<CustomerReportDTO> getCustomerReportLastXDays(Long userID, Long X) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userID", userID);
        params.put("X", X);
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource(params);
        return genericConverter(jdbcTemplate.queryForList(
                "select c.*, ifnull(sum(s.final_cost), 0) as total_spend_period from customer c, sell_order s" +
                        " where c.customer_id = s.customer_id and c.user_id = :userID" +
                        " and s.creation_time > ((now() - interval :X day)) group by c.customer_id",
                sqlParameterSource));
    }
}
