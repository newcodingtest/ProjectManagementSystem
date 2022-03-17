package com.yoon.pms;

import org.springframework.boot.SpringApplication; 
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
//DataSourceAutoConfiguration.class
@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
public class PmsRenewalApplication {

	public static void main(String[] args) {
		SpringApplication.run(PmsRenewalApplication.class, args);
	}

}

