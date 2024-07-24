package com.shyam.config.db.mysql;

import javax.sql.DataSource;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class MySQLMigration {

    @Value("${spring.mysql.datasource.migration-location}")
    private String mysqlMigrationLocation;
    
    @Autowired(required = true)
    @Qualifier(value = "mysqlDataSource")
    private DataSource mysqlDataSource;

    @PostConstruct
    public void migrateMySQLDB() {
        Flyway
        .configure()
        .dataSource(mysqlDataSource)
        .locations(mysqlMigrationLocation)
        .load()
        .migrate();
    }
}
