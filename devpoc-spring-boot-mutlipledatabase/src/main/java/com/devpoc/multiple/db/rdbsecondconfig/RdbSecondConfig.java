package com.devpoc.multiple.db.rdbsecondconfig;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.devpoc.multiple.db.rdbsecondentity.Hospital;
import com.devpoc.multiple.db.rdbsecondrepo.HospitalRepository;



@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackageClasses = {HospitalRepository.class},
        entityManagerFactoryRef = "db2EntityManagerFactory",
        transactionManagerRef = "db2TransactionManager"
)
public class RdbSecondConfig {

    private static final Logger LOG = LoggerFactory.getLogger(RdbSecondConfig.class);

    @Bean
    @ConfigurationProperties("app.datasource.db2")
    public DataSourceProperties db2DataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("app.jpa.db2")
    public JpaProperties db2JpaProperties() {
        return new JpaProperties();
    }

    @Bean(name = "db2DataSource")
   // @ConditionalOnClass(org.apache.tomcat.jdbc.pool.DataSource.class)
    @ConfigurationProperties("app.datasource.db2.tomcat")
    public DataSource db1DataSource() {
        return db2DataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Bean(name = "db2EntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean db2EntityManagerFactory(final EntityManagerFactoryBuilder builder,
                                                                          @Qualifier("db2DataSource") final DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages(Hospital.class)
                .persistenceUnit("db2")
                .properties(db2JpaProperties().getProperties())
                .build();
    }

    @Bean(name = "db2TransactionManager")
    public PlatformTransactionManager db2TransactionManager(@Qualifier("db2EntityManagerFactory") final EntityManagerFactory entityManagerFactory) {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

}
