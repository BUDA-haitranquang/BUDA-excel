package com.buda.excel.service.login;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

@Repository
public class LoginRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    public LoginRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }
    public Long getUserID(LoginDTO loginDTO) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("email", loginDTO.getEmail());
        Map<String, Object> map = jdbcTemplate.queryForMap("select * from user where email = :email", sqlParameterSource);
        Long userID = (Long) map.get("user_id");
        String password = (String) map.get("password");
        if ((userID!=null) && (bCryptPasswordEncoder.matches(loginDTO.getPassword(), password))){
            return userID;
        }
        else throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid credentials");
    }
}
