package com.yoon.pms.service;

import static org.mockito.ArgumentMatchers.any; 
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.yoon.pms.ProjectFactory;
import com.yoon.pms.TaskFactory;
import com.yoon.pms.dto.ProjectDTO;
import com.yoon.pms.dto.SubTaskDTO;
import com.yoon.pms.dto.TaskDTO;
import com.yoon.pms.entity.Project;
import com.yoon.pms.entity.Task;
import com.yoon.pms.repository.ProjectRepository;
import com.yoon.pms.repository.TaskRepository;


@ExtendWith(MockitoExtension.class) // Junit 5
//@SpringBootTest 안씀: IOC 컨테이너가 생성되지 않으며(Spring에 의존x) 테스트가 필요한 객체만 실제로 생성되어 매우 빠른 테스트 제공
class ProjectServiceTests {

	@InjectMocks
	ProjectServiceImpl service;
	
	//@Mock: 대체할 모듈   -> service는 repository를 호출하여 사용하기 때문
	@Mock
	ProjectRepository repository;
	
	@Test
	@DisplayName("상_하위_같이_register 동작 테스트")
	public void 등록_테스트(){
		//Given 
		ProjectDTO givenDTO = ProjectFactory.makeProjectkDTO();
		
		//Mocking
		BDDMockito
			.given(repository.save(any())).
			willReturn(ProjectDTO.dtoToEntity(givenDTO));
				
		//When
		Long result = service.register(givenDTO);
		
		//Then
		//결과값
		Assertions.assertThat(result).isGreaterThan(0);
		//호출횟수
		BDDMockito.verify(repository,times(1)).save(any());
	}
	
	
	@Test
	@DisplayName("modify 기능 동작 테스트")
	public void modify_테스트(){
		
		//Given 
		Project givenEntity = ProjectFactory.makeProjectEntity();
		ProjectDTO givenDTO = ProjectFactory.makeProjectkDTO();
		
		//Mocking
		BDDMockito
			.given(repository.findById(givenEntity.getId()))
			.willReturn(Optional.of(givenEntity));
			
		BDDMockito
			.given(repository.save(any())).
			willReturn(givenEntity);

		//When
		 service.modify(givenDTO);
		 
		
		//Then
		BDDMockito.verify(repository,times(1)).save(givenEntity);
		BDDMockito.verify(repository,times(1)).findById(givenEntity.getId());
	}

	
	
	
	
}
