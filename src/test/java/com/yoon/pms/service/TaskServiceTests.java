package com.yoon.pms.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.yoon.pms.TaskFactory;
import com.yoon.pms.dto.TaskDTO;
import com.yoon.pms.entity.Task;
import com.yoon.pms.repository.TaskRepository;


@ExtendWith(MockitoExtension.class) // Junit 5
//@SpringBootTest 안씀: IOC 컨테이너가 생성되지 않으며(Spring에 의존x) 테스트가 필요한 객체만 실제로 생성되어 매우 빠른 테스트 제공
class TaskServiceTests {

	@InjectMocks
	TaskServiceImpl service;
	
	
	// @Mock: 대체할 모듈   -> service는 repository를 호출하여 사용하기 때문
	@Mock
	TaskRepository repository;
	
	@Test
	@DisplayName("새 Task 저장")
	public void 등록_테스트(){
		
		//given 
		TaskDTO craeteDTO = TaskFactory.makeTaskDTO();
		
		//mocking
		BDDMockito
			.given(repository.save(any())).
			willReturn(craeteDTO.createEntity());
				
		//when
		Task result = service.register(craeteDTO);
		
		//then
		//결과값
		Assertions.assertThat(result.getContents()).isEqualTo(craeteDTO.createEntity().getContents());
		//호출횟수
		BDDMockito.verify(repository,times(1)).save(any());
	}
	
	@Test
	void 그냥() {
		System.out.println("test");
	}
}
