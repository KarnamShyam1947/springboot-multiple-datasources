package com.shyam.config.db.postgres;

import javax.sql.DataSource;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class PostgresMigration {

    @Value("${spring.pg.datasource.migration-location}")
    private String postgresMigrationLocation;

    @Autowired(required = true)
    @Qualifier(value = "pgDataSource")
    private DataSource pgDataSource;

    @PostConstruct
    public void migratePostgresDb() {
        Flyway
        .configure()
        .dataSource(pgDataSource)
        .locations(postgresMigrationLocation)
        .load()
        .migrate();
    }

}
