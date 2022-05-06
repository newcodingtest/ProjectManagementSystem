package com.yoon.pms.repository;


import org.junit.jupiter.api.DisplayName; 
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

import com.yoon.pms.aop.AspectAdvice;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Import(AspectAdvice.class)
@Transactional
public class SubTaskRepositoryTests{
	
	@Autowired
	private SubTaskRepository repository;
	
	@Test
	@DisplayName("같은 상위작업을 같은 하위작업 삭제")
	void findById_테스트() {
		//given
		Long parentId = 2L;
		//when
		repository.deleteByTid(parentId);
	}
	
}
