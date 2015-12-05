package org.bdickele.ambiguous.configuration;

import org.bdickele.ambiguous.security.SpTranspUserDetailsService;
import org.bdickele.ambiguous.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableTransactionManagement
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@EnableJpaRepositories(basePackages = {"org.bdickele.ambiguous.repository"})
public class DatabaseTestConfig {

    @Autowired
    Environment env;

    @Bean
    public AgreementRuleService agreementRuleService() {
        return new AgreementRuleService();
    }

    @Bean
    public CustomerService customerService() {
        return new CustomerService();
    }

    @Bean
    public EmployeeService employeeService() {
        return new EmployeeService();
    }

    @Bean
    public RequestService requestService() {
        return new RequestService();
    }

    @Bean
    public UserService userService() {
        return new UserService();
    }

    @Bean
    public SpTranspUserDetailsService userDetailsService() {
        return new SpTranspUserDetailsService();
    }

    @Bean
    public PasswordEncoder devPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(new String[]{"org.bdickele.ambiguous.domain"});

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(getHibernateProperties());

        return em;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    private Properties getHibernateProperties() {
        Properties prop = new Properties();
        prop.put("hibernate.format_sql", "true");
        prop.put("hibernate.show_sql", "true");
        prop.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        return prop;
    }

    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                .setName("testdb")
                /*
                .addScript("classpath:sql/01_create_seq_and_tables.sql")
                .addScript("classpath:sql/02_basedata.sql")
                .addScript("classpath:sql/03_testdata_users.sql")
                .addScript("classpath:sql/testdata_rules.sql")
                */
                .build();
    }
}
