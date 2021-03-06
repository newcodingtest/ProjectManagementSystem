package com.yoon.pms.repository;

import static org.assertj.core.api.Assertions.assertThat;  
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import com.yoon.pms.aop.AspectAdvice;
import com.yoon.pms.dto.SubTaskDTO;
import com.yoon.pms.dto.TaskDTO;
import com.yoon.pms.entity.SubTask;
import com.yoon.pms.entity.Task;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
@Import(AspectAdvice.class)
//@Transactional
public class TaskRepositoryTests{
	
	@Autowired
	private TaskRepository repository;
	
	@Test
	@DisplayName("단일 읽기 테스트")
	void findById_테스트() {
		//given
		Long givenId = 2L;
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
	@DisplayName("@DynamicInsert null값은 스스로 제외된다.")
	void insertNullTest() {
		TaskDTO given = TaskDTO.builder()
				.taskTitle("테스트")
				.taskContents(null)
				.taskStartDate("2022-03-08T10:10")
				.taskEndDate("2022-03-08T10:10")
				.build();
		
		Task target = TaskDTO.dtoToEntity(given);
		
		 Task result = repository.save(target);
		 
		 assertThat(result.getTaskContents()).isEqualTo(null);
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
		
		log.info("result={}, size={}", result.toString(), result.size());
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
	
	@Test
	@DisplayName("작업은 하위작업이랑 같이 불러온다.")
	void getTaskListWithAll_메서드테스트() {
		//When
		Task result = repository.getTaskListWithAll(1L);
		
		log.info("result={}",result);
		//Then
		//assertThat(result.getSubTask().size()).isGreaterThanOrEqualTo(0);
		
		assertThat(result.getSubTaskList().size()).isGreaterThanOrEqualTo(0);
		assertThat(result.getSubTaskList().get(0).getTask().getTid()).isEqualTo(1L);
	}
	
	@Test
	@DisplayName(" CascadeType.ALL 부모엔티티(Task)가 삭제되면 자식엔티티(SubTask)도 삭제된다.")
	@Transactional
	void deleteTaskWithSubTask() {
		//GIVEN
		Optional<Task> given = repository.findById(3L);
		
		//WHEN
		given.ifPresent(target ->{
			repository.deleteById(given.get().getTid());
		});
	}
	
	@Test
	@DisplayName(" CascadeType.ALL 부모엔티티(Task)와 자식엔티티(SubTask) 등록")
	@Transactional
	void insertTaskWithSubTask() {
		//GIVEN
		TaskDTO task = TaskDTO.builder()
				.taskTitle("테스트")
				.taskContents("테스트")
				.taskStartDate("2022-03-08T10:10")
				.taskEndDate("2022-03-08T10:10")
				.build();
		SubTaskDTO subTask1 = SubTaskDTO.builder()
				.subTitle("테스트")
				.subStartDate("2022-03-08T10:10")
				.subEndDate("2022-03-08T10:10")
				.build();
		
		SubTaskDTO subTask2 = SubTaskDTO.builder()
				.subTitle("테스트")
				.subStartDate("2022-03-08T10:10")
				.subEndDate("2022-03-08T10:10")
				.build();
		
		Task entity = TaskDTO.dtoToEntity(task);
		SubTask subEntity1 = SubTaskDTO.dtoToEntity(subTask1);
		SubTask subEntity2 = SubTaskDTO.dtoToEntity(subTask2);
		
		entity.addSubTaskList(subEntity1);
		entity.addSubTaskList(subEntity2);
		
		//WHEN
		Task result = repository.save(entity);
		
		//THEN
		assertThat(result.getSubTaskList()).hasSize(2);
	}
	
	@Test
	@DisplayName(" CascadeType.ALL 부모엔티티(Task)에서 연관관계 삭제시 고아가된 SubTask는 삭제된다.")
	@Transactional
	void 고아객체는_삭제된다() {
		//GIVEN
		Optional<Task> target = repository.findById(13L);
		
		//WHEN - THEN
		target.ifPresent(task->{
			assertThat(task.getSubTaskList()).hasSize(2);
			
			task.getSubTaskList().clear();
			assertThat(task.getSubTaskList()).hasSize(0);
		});
	}
	
	@Test
	@DisplayName(" CascadeType.ALL 자식엔티티만 수정되는 경우 clear를 사용하자")
	@Transactional
	void 자식엔티티만_수정하는방법() {
		//GIVEN
		SubTaskDTO subTask1 = SubTaskDTO.builder()
				.subTitle("테스트")
				.subStartDate("2022-03-08T10:10")
				.subEndDate("2022-03-08T10:10")
				.build();
		
		SubTask subEntity1 = SubTaskDTO.dtoToEntity(subTask1);
		
		Task target = repository.getById(13L);
		
		int beforeSize = target.getSubTaskList().size();
		
		//WHEN
		target.getSubTaskList().clear();
		int ingSize = target.getSubTaskList().size();
		
		target.getSubTaskList().add(subEntity1);
		int EndSize =  target.getSubTaskList().size();
		
		//THEN
		assertThat(beforeSize).isEqualTo(2);
		assertThat(ingSize).isEqualTo(0);
		assertThat(EndSize).isEqualTo(1);
	}
	
	
}
