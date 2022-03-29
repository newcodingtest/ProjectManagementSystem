package com.yoon.pms.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.yoon.pms.entity.Task;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class TaskRepositoryTests{
	
	@Autowired
	private TaskRepository repository;
	
	@Test
	@DisplayName("단일 읽기 테스트")
	void findById_테스트() {
		//given
		Long givenId = 1L;
		//when
		Optional<Task> result = repository.findById(givenId);
		
		//then
		result.ifPresent(x->{
			Assertions.assertThat(x).isNotNull();
			log.info("findById={}",x);
		});
	}
	
	@Test
	@DisplayName("리스트 읽기 테스트")
	void findByAll_테스트() {
		//when
		List<Task> result = repository.findAll();
		
		//then
		result.forEach(x->{
			log.info("findByAll={}",x);
		});
	}
	
	
	
	@Test
	@DisplayName("@ManyToONE 적용 전")
	void task_등록_테스트() {
		  //given
		  Task task = Task.builder()
				  .taskTitle("테스트 제목")
				  .statusCode("진행전")
				  .realProgress(3)
				  .reportRegistFlag("2")
				  .projectId((long)1)
				  .taskStartDate(LocalDateTime.now())
				  .taskEndDate(LocalDateTime.now())
				  .taskType("종류")
				  .detailedTaskType("상세")
				  .divisionOfTask("분류")
				  .remarks("비고")
				  .build();
		  //when
		  Task savedTask =  repository.save(task);
		  
		  //then
		  Assertions.assertThat(savedTask.getTaskTitle())
		  .isEqualTo(task.getTaskTitle());
	}
	
	@Test
	@DisplayName("@ManyToONE 적용 후")
	void task_등록_테스트1() {
		  //given
		  Task task = Task.builder()
				  .taskTitle("테스트 제목")
				  .statusCode("진행전")
				  .realProgress(3)
				  .reportRegistFlag("2")
				  .projectId((long) 1)
				  .taskStartDate(LocalDateTime.now())
				  .taskEndDate(LocalDateTime.now())
				  .taskType("종류")
				  .detailedTaskType("상세")
				  .divisionOfTask("분류")
				  .remarks("비고")
				  .build();
		  //when
		  Task savedTask =  repository.save(task);
		  
		  //then
		  Assertions.assertThat(savedTask.getTaskTitle())
		  .isEqualTo(task.getTaskTitle());
		  Assertions.assertThat(savedTask.getStatusCode())
		  .isEqualTo(task.getStatusCode());
	}
	
	
	@Test
	@DisplayName("task 수정 테스트")
	@Transactional
	void task_수정_테스트() {
		//given
		Long givenId = 2L;
		//when
		Optional<Task>createEntity = repository.findById(givenId);
		
		//then
		createEntity.ifPresent(expectedEntity->{
			//change 메서드 존재시
			//expectedEntity.changeContents("변경");
			 Task updatedEntity = repository.save(expectedEntity);
			 
			 Assertions.assertThat(updatedEntity.getTaskContents())
			 	.isEqualTo(createEntity.get().getTaskContents());
			 Assertions.assertThat(updatedEntity.getTaskTitle())
			 	.isEqualTo(createEntity.get().getTaskTitle());
		});
	
	}
	
	@Test
	@DisplayName("task 삭제 테스트")
	void task_삭제_테스트() {
		//given
		Long givenId = 3L;
		
		//when
		Optional<Task>entity = repository.findById(givenId);
		
		//then
		entity.ifPresent(selectResult->{
			repository.deleteById(entity.get().getTid());
			
			Assertions.assertThat(entity.get().getTid()).isNull();
		});	
		
	}
	
	@Test
	@DisplayName("Qdsl Task 전체count 테스트")
	void count_테스트() {
		//when
		long result = repository.getTaskList();
		
		log.info("result={}", result);
		//then
		Assertions.assertThat(result).isNotNull();
	}
	
	@Test
	@DisplayName("Qdsl 진행전 리스트 출력 테스트")
	void 진행전_리스트_가져오기_테스트() {
		//when
		List<Task>result = repository.getNotStartList();
		
		log.info("result={}, size={}", result, result.size());
		//then
		Assertions.assertThat(result.size()).isGreaterThanOrEqualTo(0);
	}
	
	@Test
	@DisplayName("Qdsl 진행중 리스트 출력 테스트")
	void 진행중_리스트_가져오기_테스트() {
		//when
		List<Task>result = repository.getOnGoingList();
		
		log.info("result={}, size={}", result, result.size());
		//then
		Assertions.assertThat(result.size()).isGreaterThanOrEqualTo(0);
	}
	
	@Test
	@DisplayName("Qdsl 완료 리스트 출력 테스트")
	void 완료_리스트_가져오기_테스트() {
		//when
		List<Task>result = repository.getEndedList();
		
		log.info("result={}, size={}", result, result.size());
		//then
		Assertions.assertThat(result.size()).isGreaterThanOrEqualTo(0);
	}
	
	
	
}
