package com.yoon.pms;

import java.time.LocalDateTime; 
import com.yoon.pms.dto.TaskDTO;
import com.yoon.pms.entity.Task;
 
public class TaskFactory {
	public static Task makeTaskEntity() {
		TaskDTO dto = makeTaskDTO();
		
		return dtoToEntity(dto);
		
	}
	
	public static TaskDTO makeTaskDTO() {
		 return TaskDTO.builder()
				  .tid((long) 1)
				  .taskTitle("서비스단 테스트 제목")
				  .taskContents("서비스단 테스트 내용")
				  .statusCode("진행전")
				  .realProgress(3)
				  .reportRegistFlag("2")
				  .pid((long)1)
				  .taskStartDate("2022-03-08T10:10")
				  .taskEndDate("2022-03-08T10:10")
				  .taskType("종류")
				  .detailedTaskType("상세")
				  .divisionOfTask("분류")
				  .remarks("비고")
				  .build();
		
	}
	
	static Task dtoToEntity(TaskDTO taskDTO){

		 LocalDateTime startDate = TaskDTO.stringToLocalDateTime(taskDTO.getTaskStartDate());
		 LocalDateTime endDate = TaskDTO.stringToLocalDateTime(taskDTO.getTaskEndDate());
		 
	        Task task = Task.builder()
	        	  .projectId((long)1)
	              .statusCode(taskDTO.getStatusCode())
	              .parent(taskDTO.getParent())
	              .projectId(taskDTO.getPid())
	              .realProgress(taskDTO.getRealProgress())
	              .remarks(taskDTO.getRemarks())
	              .reportRegistFlag(taskDTO.getReportRegistFlag())
	              .taskType(taskDTO.getTaskType())
	              .taskTitle(taskDTO.getTaskTitle())
	              .taskContents(taskDTO.getTaskContents())
	              .taskStartDate(startDate)
	              .taskEndDate(endDate)
	              .detailedTaskType(taskDTO.getDetailedTaskType())
	              .divisionOfTask(taskDTO.getDivisionOfTask())
	              .writer(taskDTO.getWriter())
	              .build();
	 
	        return task;
	}

}
