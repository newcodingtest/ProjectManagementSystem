package com.yoon.pms.repository;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.yoon.pms.dto.TaskDTO;
import com.yoon.pms.entity.Project;
import com.yoon.pms.entity.Task;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class TaskRepositoryTests{
	
	@Autowired
	private TaskRepository repository;
	
	@Test
	@DisplayName("@ManyToONE 적용 전")
	void task_등록_테스트() {
		  //given
		  Task task = Task.builder()
				  .taskTitle("테스트 제목")
				  .statusCode((int)4)
				  .realProgress(3)
				  .reportRegistFlag("2")
				  .projects((long)1)
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
				  .statusCode((int)4)
				  .realProgress(3)
				  .reportRegistFlag("2")
				  .projects(Project.builder().id((long)1).build())
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
	@DisplayName("task 수정 테스트")
	@Transactional
	void task_수정_테스트() {
		//when
		Optional<Task>createEntity = repository.findById(2L);
		
		//then
		createEntity.ifPresent(expectedEntity->{
			 expectedEntity.changeContents("제목을 수정","내용을 수정했어요");
			 Task updatedEntity = repository.save(expectedEntity);
			 
			 Assertions.assertThat(updatedEntity.getContents())
			 	.isEqualTo(createEntity.get().getContents());
			 Assertions.assertThat(updatedEntity.getTaskTitle())
			 	.isEqualTo(createEntity.get().getTaskTitle());
		});
	
	}
	
	@Test
	@DisplayName("task 삭제 테스트")
	void task_삭제_테스트() {
		//given. when
		Optional<Task>entity = repository.findById(3L);
		
		//then
		entity.ifPresent(selectResult->{
			repository.deleteById(entity.get().getTid());
			
			Assertions.assertThat(entity.get().getTid()).isNull();
		});	
		
	}
	
	@Test
	@DisplayName("상태코드에 따라서 Task 그리고 관련된 프로젝트 가져오기")
	void Query_어노테이션_테스트() {
		//given (110: 진행전 111: 진행중 112: 완료)
		final int statusCode = 1;
		
		repository.getListByStatusCode(statusCode).forEach(test ->{
			System.out.println(test.toString());
		});
	}
	
	
}
