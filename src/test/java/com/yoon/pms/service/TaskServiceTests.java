package com.yoon.pms.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
	
	
	//@Mock: 대체할 모듈   -> service는 repository를 호출하여 사용하기 때문
	@Mock
	TaskRepository repository;
	
	@Test
	@DisplayName("register 동작 테스트")
	public void 등록_테스트(){
		
		//given 
		TaskDTO givenDTO = TaskFactory.makeTaskDTO();
		
		//mocking
		BDDMockito
			.given(repository.save(any())).
			willReturn(givenDTO.dtoToEntity(givenDTO));
				
		//when
		Long result = service.register(givenDTO);
		
		//then
		//결과값
		Assertions.assertThat(result).isEqualTo(givenDTO.getTid());
		//호출횟수
		BDDMockito.verify(repository,times(1)).save(any());
	}
	
	@Test
	@DisplayName("getTaskOne 기능 동작 테스트")
	public void getTaskOne_테스트(){
		
		//given 
		Long givenId = 1L;
		Task givenTask = TaskFactory.makeTaskEntity();
		//mocking
		BDDMockito
			.given(repository.findById(anyLong())).
			willReturn(Optional.of(givenTask));

		TaskDTO result = service.getTaskOne(givenId);
		System.out.println(result);
		//then
		//결과값
		Assertions.assertThat(result).isEqualTo(result.entityToDTO(givenTask));
		//호출횟수
		BDDMockito.verify(repository,times(1)).findById(givenId);
	}
	
	@Test
	@DisplayName("modify 기능 동작 테스트")
	public void modify_테스트(){
		
		//given 
		Task givenEntity = TaskFactory.makeTaskEntity();
		TaskDTO givenDTO = TaskFactory.makeTaskDTO();
		
		//mocking
		BDDMockito
			.given(repository.findById(givenEntity.getTid())).
			willReturn(Optional.of(givenEntity));
		BDDMockito
			.given(repository.save(givenEntity)).
			willReturn(givenEntity);

		//when
		long result = service.modify(givenDTO);
		
		//then
		Assertions.assertThat(result).isEqualTo(givenEntity.getTid());
		BDDMockito.verify(repository,times(1)).save(givenEntity);
	}
	
}
