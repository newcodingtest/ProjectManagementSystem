package com.yoon.pms.repository;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.yoon.pms.dto.TaskDTO;
import com.yoon.pms.entity.Task;


@SpringBootTest
public class TaskRepositoryTests{
	
	@Autowired
	TaskRepository taskRepository;
	
	@Test
	@DisplayName("task 등록 테스트")
	public void task_등록_테스트() {
		  //given
		  Task task = Task.builder()
				  .taskTitle("테스트 제목")
				  .progressState((int)4)
				  .realProgress(3)
				  .reportRegistFlag("2")
				  .projectId((long) 1.0)
				  .taskStartDate(LocalDateTime.now())
				  .taskEndDate(LocalDateTime.now())
				  .taskType("종류")
				  .detailedTaskType("상세")
				  .divisionOfTask("분류")
				  .remarks("비고")
				  .build();
		  //when
		  Task savedTask =  taskRepository.save(task);
		  
		  //then
		  Assertions.assertThat(savedTask.getTaskTitle())
		  .isEqualTo(task.getTaskTitle());
	}
	
}
