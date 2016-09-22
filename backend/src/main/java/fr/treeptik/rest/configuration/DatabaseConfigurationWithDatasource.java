package fr.treeptik.rest.configuration;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.jboss.logging.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.persistence.SharedCacheMode;
import javax.sql.DataSource;
import java.util.Properties;

@Profile("datasource")
@Configuration
public class DatabaseConfigurationWithDatasource {

    private Logger logger = Logger.getLogger(DatabaseConfigurationWithDatasource.class);

    @Resource(mappedName = "java:/jdbc/StockDS")
    private DataSource dataSource;

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        logger.info("Configuring EntityManager");
        LocalContainerEntityManagerFactoryBean lcemfb = new LocalContainerEntityManagerFactoryBean();
        lcemfb.setPersistenceProvider(new HibernatePersistenceProvider());
        lcemfb.setDataSource(dataSource);
        lcemfb.setJpaDialect(new HibernateJpaDialect());
        lcemfb.setJpaVendorAdapter(jpaVendorAdapter());
        lcemfb.setSharedCacheMode(SharedCacheMode.ENABLE_SELECTIVE);
        Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.generate_statistics", false);
        lcemfb.setJpaProperties(jpaProperties);
        lcemfb.setPackagesToScan("fr.treeptik.rest.model");
        lcemfb.afterPropertiesSet();
        return lcemfb.getObject();
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setGenerateDdl(true);
        jpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.MySQLDialect");
        return jpaVendorAdapter;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

}
