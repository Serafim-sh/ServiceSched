/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.train_schedule.connection;

import com.example.train_schedule.configuration.ConfigProperties;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.vibur.dbcp.ViburDBCPDataSource;

/**
 *
 * @author simik
 */
@Configuration
public class DbConfPostgreSql {
    
    ConfigProperties configProp = new ConfigProperties();
    private ViburDBCPDataSource dsPSql;

    @PostConstruct
    public void init() {
        ViburDBCPDataSource dsPSqll = new ViburDBCPDataSource();
        dsPSqll.setDriverClassName(configProp.getConfigValue("driverClassNamePs"));
        dsPSqll.setJdbcUrl(configProp.getConfigValue("urlPs"));
        dsPSqll.setUsername(configProp.getConfigValue("usernamePs"));
        dsPSqll.setPassword(configProp.getConfigValue("passwordPs"));
        dsPSqll.start();
        this.dsPSql = dsPSqll;
    }

    @PreDestroy
    public void clean() {
        dsPSql.terminate();
    }

    public ViburDBCPDataSource getConnectPostgreSql() {
        return dsPSql;
    }

    @Bean
    public JdbcTemplate jtPSql() {
        return new JdbcTemplate(getConnectPostgreSql());
    }
    
}
