package com.yoon.pms;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.yoon.pms.dto.TaskDTO;
import com.yoon.pms.entity.Project;
import com.yoon.pms.entity.Task;

public class TaskFactory {
	public static Task makeTaskEntity() {
		 return Task.builder()
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
		
	}
	
	public static TaskDTO makeTaskDTO() {
		 return TaskDTO.builder()
				  .taskTitle("테스트 제목")
				  .Contents("테스트 내용")
				  .progressState((int)4)
				  .realProgress(3)
				  .reportRegistFlag("2")
				  .pid((long)1)
				  .taskStartDate("2022-03-08")
				  .taskEndDate("2022-03-08")
				  .taskType("종류")
				  .detailedTaskType("상세")
				  .divisionOfTask("분류")
				  .remarks("비고")
				  .build();
		
	}

}
