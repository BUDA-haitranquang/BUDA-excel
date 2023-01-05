package com.buda.excel.api.ingredient.add;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ImportNewIngredientRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ImportNewIngredientRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    } 

    public void saveNewIngredients(List<NewIngredientDTO> newIngredients, Long userID) {
        this.jdbcTemplate.batchUpdate("insert into ingredient (name, description, amount_left, alert_amount, user_id) values(?,?,?,?,?,?,?)", new BatchPreparedStatementSetter() {
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1, newIngredients.get(i).getName());
                ps.setString(2, newIngredients.get(i).getDescription());
                ps.setInt(3, newIngredients.get(i).getAmountLeft());
                ps.setInt(4, newIngredients.get(i).getAlertAmount());
                ps.setLong(5, userID);
            }

            @Override
            public int getBatchSize() {
                return newIngredients.size();
            }
         });
    }
}
