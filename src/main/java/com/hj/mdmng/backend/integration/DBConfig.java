package com.hj.mdmng.backend.integration;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by heiko on 01.03.15.
 */
@Configuration
@ComponentScan("com.hj.mdmng.backend.integration")
@EnableTransactionManagement
public class DBConfig {
    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("classpath:schema.sql")
                .addScript("classpath:data.sql")
                .build();
    }

    @Bean
    PlatformTransactionManager transactionManager(SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory (DataSource ds) {
        LocalSessionFactoryBean sfb = new LocalSessionFactoryBean();
        sfb.setDataSource(ds);
        sfb.setPackagesToScan(new String [] {"com.hj.mdmng.backend.integration.domain"});

        Properties props = new Properties();
        props.setProperty("dialect", "org.hibernate.dialect.HSQLDialect");
        sfb.setHibernateProperties(props);
        return sfb;
    }


    @Bean
    JdbcOperations jdbcOperations(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    NamedParameterJdbcOperations namedJdbcOperations(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Bean
    public BeanPostProcessor persistenceTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

}
