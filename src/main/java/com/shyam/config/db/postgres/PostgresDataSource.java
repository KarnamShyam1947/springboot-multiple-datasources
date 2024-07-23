package com.shyam.config.db.postgres;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PostgresDataSource {
    
    @Bean
    @ConfigurationProperties(prefix = "spring.pg.datasource")
    DataSourceProperties pgDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    DataSource pgDataSource() {
        return pgDataSourceProperties().initializeDataSourceBuilder().build();
    }

}
