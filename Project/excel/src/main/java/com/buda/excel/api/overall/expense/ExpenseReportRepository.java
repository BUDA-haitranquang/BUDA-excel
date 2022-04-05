package com.buda.excel.api.overall.expense;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class ExpenseReportRepository {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    public List<?> overallExpenseReport(Long userID){
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("userID", userID);
        return jdbcTemplate.queryForList("select DATE_FORMAT(creation_time, '%d-%m-%Y') as timePeriod, sum(Expense) as Expense "
        + " from (select creation_time, total_cost as Expense from buy_order where user_id = :userID and status = 'FINISHED'"
        + " union all"
        + " select creation_time, total_spend as Expense from fixed_cost_bill where user_id = :userID"
        + " union all"
        + " select creation_time, total_cost as Expense from other_cost where user_id = :userID"
        + " union all"
        + " select creation_time, total_cost as Expense from pay_slip where user_id = :userID"
        + " ) as ctrctr"
        + " GROUP BY DATE_FORMAT(creation_time, '%d-%m-%Y')"
        + " order by creation_time", sqlParameterSource);
    };
}