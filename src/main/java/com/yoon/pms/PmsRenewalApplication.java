package com.yoon.pms;

import org.springframework.boot.SpringApplication;    

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
//DataSourceAutoConfiguration.class

import com.yoon.pms.aop.AspectAdvice;
import com.yoon.pms.config.QuerydslConfig;
@EnableJpaAuditing
@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
@Import(QuerydslConfig.class)
public class PmsRenewalApplication {

	public static void main(String[] args) {
		SpringApplication.run(PmsRenewalApplication.class, args);
	}
	


}

