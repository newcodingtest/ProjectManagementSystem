package com.yoon.pms.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.yoon.pms.TaskFactory;
import com.yoon.pms.dto.SubTaskDTO;
import com.yoon.pms.entity.SubTask;
import com.yoon.pms.repository.SubTaskRepository;

@ExtendWith(MockitoExtension.class)
public class SubTaskServiceTests {

	@InjectMocks
	SubTaskServiceImpl service;
	
	@Mock
	SubTaskRepository repository;
	
	@Test
	@DisplayName("test")
	void 서브테스트_등록테스트() {
		//Given
		SubTask givenEntity = TaskFactory.makeSubTaskEntity();
		SubTaskDTO givenDTO = TaskFactory.makeSubTaskDTO();
		
		//Mocking
		BDDMockito
			.given(repository.save(any()))
			.willReturn(givenEntity);
			
		//when
		Long result = service.register(givenDTO);
		
		//Then
		BDDMockito.verify(repository, times(1)).save(any());
		assertThat(result).isEqualTo(givenEntity.getSid());
		
	}
	
}
