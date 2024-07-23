package com.shyam.config.db.postgres;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(
    basePackages = "com.shyam.repositories.postgres",
    transactionManagerRef = "JpaTransactionManager",
    entityManagerFactoryRef = "pgEntityManager"
)
@EnableTransactionManagement
public class PostgresJPAConfig {
    
    @Bean
    LocalContainerEntityManagerFactoryBean pgEntityManager(
        EntityManagerFactoryBuilder factory,
        @Qualifier("pgDataSource") DataSource dataSource
    ) {
        return factory
                .dataSource(dataSource)
                .persistenceUnit("postgres")
                .packages("com.shyam.entities.postgres")
                .build();
    }

    @Bean
    @SuppressWarnings("null")
    PlatformTransactionManager pgTransactionManager(
        @Qualifier("pgEntityManager") LocalContainerEntityManagerFactoryBean pgEntityManager
    ) {
        return new JpaTransactionManager(pgEntityManager.getObject());
    }

}
