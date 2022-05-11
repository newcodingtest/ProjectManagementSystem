package com.yoon.pms.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any; 
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;

import com.yoon.pms.TaskFactory;
import com.yoon.pms.dto.SubTaskDTO;
import com.yoon.pms.dto.TaskDTO;
import com.yoon.pms.entity.Task;
import com.yoon.pms.repository.SubTaskRepository;
import com.yoon.pms.repository.TaskRepository;


@ExtendWith(MockitoExtension.class) // Junit 5
//@SpringBootTest 안씀: IOC 컨테이너가 생성되지 않으며(Spring에 의존x) 테스트가 필요한 객체만 실제로 생성되어 매우 빠른 테스트 제공
class TaskServiceTests {

	@InjectMocks
	TaskServiceImpl service;
	//@Mock: 대체할 모듈   -> service는 repository를 호출하여 사용하기 때문
	@Mock
	TaskRepository repository;
	@Mock
	SubTaskRepository subRepository;
	
	@Test
	@DisplayName("register 동작 테스트")
	public void 등록_테스트(){
		
		//Given 
		TaskDTO givenDTO = TaskFactory.makeTaskDTOWithSubTaskDTO();
		System.out.println(givenDTO.getSubTaskDTOList().size());
		
		//Mocking
		BDDMockito
			.given(repository.save(any())).
			willReturn(TaskDTO.dtoToEntity(givenDTO));
				
		//When
		Long result = service.register(givenDTO);
		
		//Then
		//결과값
		Assertions.assertThat(result).isEqualTo(givenDTO.getTid());
		//호출횟수
		BDDMockito.verify(repository,times(1)).save(any());
	}
	
	@Test
	@DisplayName("상하위 동시 register 동작 테스트")
	public void 등록_테스트1(){
		
		//Given 
		TaskDTO givenDTO = TaskFactory.makeTaskDTOWithSubTaskDTO();
		System.out.println(givenDTO.getSubTaskDTOList().size());
		
		//Mocking
		BDDMockito
			.given(repository.save(any())).
			willReturn(TaskDTO.dtoToEntity(givenDTO));
		BDDMockito
			.given(subRepository.save(any())).
			willReturn(TaskDTO.dtoToEntity(givenDTO).getSubTaskList().get(0));
				
		//When
		Long result = service.register(givenDTO);
		
		//Then
		//결과값
		Assertions.assertThat(result).isEqualTo(givenDTO.getTid());
		//호출횟수
		BDDMockito.verify(repository,times(1)).save(any());
		BDDMockito.verify(subRepository,times(TaskDTO.dtoToEntity(givenDTO).getSubTaskList().size())).save(any());
	}
	
	@Test
	@DisplayName("getTaskOne 기능 동작 테스트")
	public void getTaskOne_테스트(){
		
		//Given 
		Long givenId = 1L;
		Task givenTask = TaskFactory.makeTaskEntity();
		
		//Mocking
		BDDMockito
			.given(repository.findById(anyLong())).
			willReturn(Optional.of(givenTask));

		//When
		TaskDTO result = service.getTaskOne(givenId);
		System.out.println(result);
		
		//Then
		//결과값
		Assertions.assertThat(result).isEqualTo(result.entityToDTO(givenTask));
		//호출횟수
		BDDMockito.verify(repository,times(1)).findById(givenId);
	}
	
	@Test
	@DisplayName("modify 기능 동작 테스트")
	public void modify_테스트(){
		
		//Given 
		Task givenEntity = TaskFactory.makeTaskEntity();
		TaskDTO givenDTO = TaskFactory.makeTaskDTO();
		
		//Mocking
		BDDMockito
			.given(repository.findById(givenEntity.getTid())).
			willReturn(Optional.of(givenEntity));
		BDDMockito
			.given(repository.save(givenEntity)).
			willReturn(givenEntity);

		//When
		long result = service.modify(givenDTO);
		
		//Then
		Assertions.assertThat(result).isEqualTo(givenEntity.getTid());
		BDDMockito.verify(repository,times(1)).save(givenEntity);
	}
	
	@Test
	@DisplayName("getStatusBeforeList 기능 동작 테스트")
	public void 상태가_진행전인_Task_리스트_가져와() {
		
		//Given
		Task givenEntity = TaskFactory.makeTaskEntity();
		
		List<Task> givenArr = Arrays.asList(givenEntity,givenEntity,givenEntity);
		
		//Mocking
		BDDMockito
		.given(repository.getNotStartList())
		.willReturn(givenArr);
		
		//When
		List<TaskDTO>expected = service.getStatusBeforeList();
		
		//Then
		Assertions.assertThat(expected.get(0).getStatusCode())
					.isEqualTo(givenArr.get(0).getStatusCode());
		BDDMockito.verify(repository,times(1)).getNotStartList();
		
	}
	
	@Test
	@DisplayName("getStatusIngList 기능 동작 테스트")
	@Transactional
	public void 상태가_진행중인_Task_리스트_가져와() {
		
		//Given
		Task givenEntity = TaskFactory.makeTaskEntity();
		
		List<Task> givenArr = Arrays.asList(givenEntity,givenEntity,givenEntity);
		
		//Mocking
		BDDMockito
		.given(repository.getOnGoingList())
		.willReturn(givenArr);
		
		//When
		List<TaskDTO>expected = service.getStatusIngList();
		
		//Then
		Assertions.assertThat(expected.get(0).getStatusCode())
					.isEqualTo(givenArr.get(0).getStatusCode());
		BDDMockito.verify(repository,times(1)).getOnGoingList();
		
	}
	
	@Test
	@Transactional
	@DisplayName("getStatusEndList 기능 동작 테스트")
	public void 상태가_완료된_Task_리스트_가져와() {
		
		//Given
		Task givenEntity = TaskFactory.makeTaskEntity();
		
		List<Task> givenArr = Arrays.asList(givenEntity,givenEntity,givenEntity);
		
		//Mocking
		BDDMockito
		.given(repository.getEndedList())
		.willReturn(givenArr);
		
		//When
		List<TaskDTO>expected = service.getStatusEndList();
		
		//Then
		Assertions.assertThat(expected.get(0).getStatusCode())
					.isEqualTo(givenArr.get(0).getStatusCode());
		BDDMockito.verify(repository,times(1)).getEndedList();
	}
	
	@Test
	@Transactional
	@DisplayName("상위작업 수정시 하위작업도 같이 수정된다.")
	public void 상위작업과_하위작업은_같이_수정된다() {
		//GIVEN
		SubTaskDTO dto = SubTaskDTO.builder()
				.subTitle("테스트")
				.subStartDate("2022-03-06T10:10")
				.subEndDate("2022-03-08T10:10")
				.build();
		
		List<SubTaskDTO> list = new ArrayList<SubTaskDTO>();
		list.add(dto);
		
		TaskDTO target = TaskDTO.builder()
				.tid(13L)
				.taskTitle("테스트")
				.taskContents("테스트")
				.taskStartDate("2022-03-07T10:10")
				.taskEndDate("2022-03-07T10:10")
				.subTaskDTOList(list)
				.build();
		
		//Mocking
		BDDMockito
		.given(repository.getById(anyLong()))
		.willReturn(TaskDTO.dtoToEntity(target));
		
		BDDMockito
		.given(repository.save(any()))
		.willReturn(TaskDTO.dtoToEntity(target));
		
		//WHEN
		Long expectedId = service.modify(target);
		
		//THEN
		assertThat(expectedId).isEqualTo(target.getTid());
		BDDMockito.verify(repository,times(1)).getById(anyLong());
		BDDMockito.verify(repository,times(1)).save(any());
	}
	
	
	
}
