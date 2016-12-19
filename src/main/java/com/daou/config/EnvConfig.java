package com.daou.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan("com.daou")
@Lazy
@EnableTransactionManagement
@PropertySources({
	@PropertySource("classpath:hibernate-basic.properties")
})
public class EnvConfig {
	@Value("${datasource.driver.classname}") private String driverClassName;
	@Value("${datasource.url}") private String url;
	@Value("${datasource.username}") private String userName;
	@Value("${datasource.password}") private String password;

	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		BasicDataSource basicDataSource = new BasicDataSource();

		basicDataSource.setDriverClassName(this.driverClassName);
		basicDataSource.setUrl(this.url);
		basicDataSource.setUsername(this.userName);
		basicDataSource.setPassword(this.password);
		// Method org.postgresql.jdbc4.Jdbc4Connection.isValid(int) is not yet implemented
		// basicDataSource.setValidationQuery("SELECT 1");

		return basicDataSource;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		Properties jpaProperties = new Properties();
		// http://docs.jboss.org/hibernate/orm/4.3/manual/en-US/html_single/#configuration-optional-dialects 지원가능한 방언 리스트

		jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL9Dialect");

		// jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");

		// 하이버네이트가 실행한 SQL 쿼리를 출력한다.
		jpaProperties.put("hibernate.show_sql", true);

		// 하이버네이트가 실행한 SQL 쿼리를 출력할 때 보기 쉽게 정렬한다.
		jpaProperties.put("hibernate.format_sql", true);

		// 쿼리를 출력할 때 주석도 함께 출력한다.
		jpaProperties.put("hibernate.use_sql_comments", true);

		// JPA 표준에 맞춘 새로운 키 생성 전략을 사용한다.
		jpaProperties.put("hibernate.id.new_generator_mappings", true);

		jpaProperties.put("hibernate.hbm2ddl.auto", "create");

		LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		localContainerEntityManagerFactoryBean.setDataSource(this.dataSource());
		localContainerEntityManagerFactoryBean.setPackagesToScan("com.daou");
		localContainerEntityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		localContainerEntityManagerFactoryBean.setJpaProperties(jpaProperties);

		return localContainerEntityManagerFactoryBean;
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
		jpaTransactionManager.setEntityManagerFactory(this.entityManagerFactory().getObject());
		return jpaTransactionManager;
	}
}
