package com.lab.manus.util;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Service;
 
@Service
public class ApplicationConfig {
 
 
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/labmanuspoc");
        dataSource.setUsername("postgres");
        dataSource.setPassword("root");
        System.out.println("----DataSource created-------------------");
        return dataSource;
    }
 
    @Bean
    @Required
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        
        System.out.println("----JdbcTemplate created-------------------");
        return jdbcTemplate;
    }
 
}
