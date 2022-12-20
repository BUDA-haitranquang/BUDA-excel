package com.buda.excel.api.product.add;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ImportNewProductRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ImportNewProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    } 

    public void saveNewProducts(List<NewProductDTO> newProducts, Long userID) {
        this.jdbcTemplate.batchUpdate("insert into product (name, description, amount_left, alert_amount, cost_per_unit, selling_price, user_id) values(?,?,?,?,?,?,?)", new BatchPreparedStatementSetter() {
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1, newProducts.get(i).getName());
                ps.setString(2, newProducts.get(i).getDescription());
                ps.setInt(3, newProducts.get(i).getAmountLeft());
                ps.setInt(4, newProducts.get(i).getAlertAmount());
                ps.setDouble(5, newProducts.get(i).getCostPerUnit());
                ps.setDouble(6, newProducts.get(i).getSellingPrice());
                ps.setLong(7, userID);
            }

            @Override
            public int getBatchSize() {
                return newProducts.size();
            }
         });
    }
}
