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
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan("com.daou")
@Lazy
@EnableTransactionManagement
@EnableJpaRepositories(basePackages="com.daou.repository")
@PropertySources({
	@PropertySource("classpath:hibernate-basic.properties")
})
public class EnvConfig {
	@Value("${datasource.driver.classname}") private String driverClassName;
	@Value("${datasource.url}") private String url;
	@Value("${datasource.username}") private String userName;
	@Value("${datasource.password}") private String password;
	@Value("${packages.to.scan}") private String packagesToScan;

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
		// http://docs.jboss.org/hibernate/orm/4.3/manual/en-US/html_single/#configuration-optional-dialects 지원 가능한 방언 리스트
		//jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL9Dialect");
		jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");

		// 하이버네이트가 실행한 SQL 쿼리를 출력한다.
		jpaProperties.put("hibernate.show_sql", true);

		// 하이버네이트가 실행한 SQL 쿼리를 출력할 때 보기 쉽게 정렬한다.
		jpaProperties.put("hibernate.format_sql", true);

		// 쿼리를 출력할 때 주석도 함께 출력한다.
		jpaProperties.put("hibernate.use_sql_comments", true);

		// 이 클래스는 테이블 명이나 컬럼 명이 생략되면 자바의 카멜 표기법을 테이블의 언더스코어 표기법으로 매핑한다.
		// -> 설정이 안 먹힌다.
		//jpaProperties.put("hibernate.ejb.naming_strategy", "org.hibernate.cfg.ImprovedNamingStrategy");
		//jpaProperties.put("hibernate.ejb.naming_strategy", "ImprovedNamingStrategy.INSTANCE");

		// JPA 표준에 맞춘 새로운 키 생성 전략을 사용한다.
		jpaProperties.put("hibernate.id.new_generator_mappings", true);

		// p.127 자바 ORM 표준 JPA 프로그래밍
		// create: 기존 테이블을 삭제하고 새로 생성한다. DROP + CREATE
		// create-drop: 애플리케이션을 종료할 때 생성한 DDL을 제거한다. DROP + CREATE + DROP
		// update: 데이터베이스 테이블과 엔티티 매핑정보를 비교해서 변경 사항만 수정한다.
		// validate: 데이터베이스 테이블과 엔티티 매핑정보를 비교해서 차이가 있으면 경고를 남기고 애플리케이션을 실행하지 않는다. 이 설정은 DDL을 수정하지 않는다.
		// none: 자동 생성기능을 사용하지 않는다.
		jpaProperties.put("hibernate.hbm2ddl.auto", "create");

		LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		localContainerEntityManagerFactoryBean.setDataSource(this.dataSource());
		localContainerEntityManagerFactoryBean.setPackagesToScan(this.packagesToScan);
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