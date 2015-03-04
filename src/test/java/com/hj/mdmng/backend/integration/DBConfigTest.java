package com.hj.mdmng.backend.integration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=DBConfig.class)
public class DBConfigTest {

    @Autowired
    DataSource datasource;

    @Test
    public void testDatasource() throws Exception {

        JdbcTemplate jdbc = new JdbcTemplate(datasource);

        String result = jdbc.queryForObject("select * from country", new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {

                return rs.getString("name");
            }
        });

        System.out.println(result);


    }
}