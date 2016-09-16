package fr.treeptik.rest.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableScheduling
@Import(value = { DatabaseConfigurationWithDatasource.class, DatabaseConfigurationWithEmbedded.class })
@ComponentScan(basePackages = { "fr.treeptik.rest.service", "fr.treeptik.rest.dao" })
@EnableTransactionManagement
public class ApplicationConfiguration {

}
