package com.buda.excel.api.overall.expense;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class ExpenseReportRepository {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    public List<ExpenseReportDTO> genericConverter(List<Map<String, Object>> rows){
        List<ExpenseReportDTO> expenseReportDTOs = new ArrayList<ExpenseReportDTO>();
        for (Map<String, Object> row: rows) {
            ExpenseReportDTO expenseReportDTO = new ExpenseReportDTO((String) row.get("timePeriod"), (Double) row.get("Expense"));
            expenseReportDTOs.add(expenseReportDTO);
        }
        return expenseReportDTOs;
    }
    public List<ExpenseReportDTO> lastTwoMonthsReport(Long userID){
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("userID", userID);
        return genericConverter(jdbcTemplate.queryForList("select DATE_FORMAT(creation_time, '%d-%m-%Y') as timePeriod, sum(Expense) as expense "
        + " from (select creation_time, total_cost as Expense from buy_order where user_id = :userID and status = 'FINISHED'"
        + " and creation_time > date_sub(now(), interval 600 DAY)"
        + " union all"
        + " select creation_time, total_spend as Expense from fixed_cost_bill where user_id = :userID"
        + " and creation_time > date_sub(now(), interval 600 DAY)"
        + " union all"
        + " select creation_time, total_cost as Expense from other_cost where user_id = :userID"
        + " and creation_time > date_sub(now(), interval 600 DAY)"
        + " union all"
        + " select creation_time, total_cost as Expense from pay_slip where user_id = :userID"
        + " and creation_time > date_sub(now(), interval 600 DAY)"
        + " ) as ctrctr"
        + " GROUP BY DATE_FORMAT(creation_time, '%d-%m-%Y')"
        + " order by creation_time", sqlParameterSource));
    };
}