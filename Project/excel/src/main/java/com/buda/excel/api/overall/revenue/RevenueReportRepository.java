package com.buda.excel.api.overall.revenue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class RevenueReportRepository {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    public List<RevenueReportDTO> genericConverter(List<Map<String, Object>> rows) {
        List<RevenueReportDTO> revenueReportDTOs = new ArrayList<RevenueReportDTO>();
        for (Map<String, Object> row: rows) {
            RevenueReportDTO reportDTO = new RevenueReportDTO();
            reportDTO.setRevenue((Double) row.get("revenue"));
            reportDTO.setTimePeriod((String) row.get("timePeriod"));
            revenueReportDTOs.add(reportDTO);
        }
        return revenueReportDTOs;
    }
    public List<RevenueReportDTO> lastTwoMonthsReport(Long userID) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("userID", userID);
        return genericConverter(jdbcTemplate.queryForList("select DATE_FORMAT(creation_time, '%d-%m-%Y') as timePeriod, sum(revenue) as revenue "
        + " from (select creation_time, final_cost as revenue from sell_order where user_id = :userID and status = 'FINISHED'"
        + " and creation_time > date_sub(now(), interval 60 DAY)"
        + " union all"
        + " select creation_time, total_cost as revenue from receipt where user_id = :userID"
        + " and creation_time > date_sub(now(), interval 60 DAY)) as ctrctr"
        + " GROUP BY DATE_FORMAT(creation_time, '%d-%m-%Y')"
        + " order by creation_time", sqlParameterSource));
    }
    public List<RevenueReportDTO> recentMonthsReport(Long userID) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("userID", userID);
        return genericConverter(jdbcTemplate.queryForList("select DATE_FORMAT(creation_time, '%m-%Y') as timePeriod, sum(revenue) as revenue "
        + " from (select creation_time, final_cost as revenue from sell_order where user_id = :userID and status = 'FINISHED'"
        + " union all"
        + " select creation_time, total_cost as revenue from receipt where user_id = :userID"
        + " ) as ctrctr"
        + " GROUP BY DATE_FORMAT(creation_time, '%m-%Y')"
        + " order by creation_time", sqlParameterSource));
    }
    public List<RevenueReportDTO> recentWeeksReport(Long userID) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("userID", userID);
        return genericConverter(jdbcTemplate.queryForList("select DATE_FORMAT(creation_time, '%V-%X') as timePeriod, sum(revenue) as revenue "
        + " from (select creation_time, final_cost as revenue from sell_order where user_id = :userID and status = 'FINISHED'"
        + " union all"
        + " select creation_time, total_cost as revenue from receipt where user_id = :userID"
        + " ) as ctrctr"
        + " GROUP BY DATE_FORMAT(creation_time, '%V-%X')"
        + " order by creation_time", sqlParameterSource));
    }
}
