package org.itstep.testPars;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class ManagerDb {
    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public ManagerDb(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public static void main(String[] args) {

    }
}
