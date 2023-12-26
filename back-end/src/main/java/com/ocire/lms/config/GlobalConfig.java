package com.ocire.lms.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import liquibase.integration.spring.SpringLiquibase;

@Configuration
public class GlobalConfig {

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean(name = "innitTable")
	public SpringLiquibase innitTable(DataSource data) {
		final SpringLiquibase base = new SpringLiquibase();
		base.setChangeLog("classpath:/db/migration/script/init_table_v001.sql");
		base.setDataSource(data);
		return base;
	}
	
	@Bean(name = "innitData")
	@DependsOn("innitTable")
	public SpringLiquibase innitData(DataSource data) {
		final SpringLiquibase base = new SpringLiquibase();
		base.setChangeLog("classpath:/db/migration/script/init_data_v001.sql");
		base.setDataSource(data);
		return base;
	}

}
