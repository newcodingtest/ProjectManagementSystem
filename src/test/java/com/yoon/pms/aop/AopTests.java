package com.yoon.pms.aop;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import com.yoon.pms.repository.TaskRepository;
import com.yoon.pms.service.TaskService;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
@Import(AspectAdvice.class)
public class AopTests {

	@Autowired
	TaskService service;
	
	@Autowired
	TaskRepository repository;
	
	@Test
	@DisplayName("appInfo")
	public void appInfo() {
		log.info("isAopProxy, service= {}", AopUtils.isAopProxy(service));
		log.info("isAopProxy, repository= {}", AopUtils.isAopProxy(repository));
	}
	
	
	@Test
	@DisplayName("appInfo")
	public void getClassInfo() {
		  log.info("service class={}", service.getClass());
	        log.info("repository class={}", repository.getClass());
	}
}
