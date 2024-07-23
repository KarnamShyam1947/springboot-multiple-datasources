package com.shyam.config.db.mysql;

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
    basePackages = "com.shyam.repositories.mysql",
    entityManagerFactoryRef = "mysqlEntityManager",
    transactionManagerRef = "mysqlTransactionManager"
)
@EnableTransactionManagement
public class MySQLJPAConfig {
    
    @Bean
    LocalContainerEntityManagerFactoryBean mysqlEntityManager(
        EntityManagerFactoryBuilder factory,
        @Qualifier("mysqlDataSource") DataSource mysqlDataSource
    ) {
        return factory
                .dataSource(mysqlDataSource)
                .persistenceUnit("mysql")
                .packages("com.shyam.entities.mysql")
                .build();
                
    }

    @Bean
    @SuppressWarnings("null")
    PlatformTransactionManager mysqlTransactionManager(
        @Qualifier("mysqlEntityManager") LocalContainerEntityManagerFactoryBean mysqlEntityManager
    ) {
        return new JpaTransactionManager(mysqlEntityManager.getObject());
    }

}
