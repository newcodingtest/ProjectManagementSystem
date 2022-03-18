package com.yoon.pms;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import com.yoon.pms.dto.TaskDTO;
import com.yoon.pms.entity.Project;
import com.yoon.pms.entity.Task;

public class TaskFactory {
	public static Task makeTaskEntity() {
		TaskDTO dto = makeTaskDTO();
		
		return (Task)dtoToEntity(dto).get("task"); 
		
	}
	
	public static TaskDTO makeTaskDTO() {
		 return TaskDTO.builder()
				 .tid((long)100)
				  .taskTitle("서비스단 테스트 제목")
				  .Contents("서비스단 테스트 내용")
				  .progressState((int)4)
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
	
	static Map<String,Object> dtoToEntity(TaskDTO taskDTO){
		 Map<String,Object> entityMap = new HashMap();

		 LocalDateTime startDate = taskDTO.stringToLocalDateTime(taskDTO.getTaskStartDate());
		 LocalDateTime endDate = taskDTO.stringToLocalDateTime(taskDTO.getTaskEndDate());
		 
	        Task task = Task.builder()
	        		.tid(taskDTO.getTid())
	              .statusCode(taskDTO.getProgressState())
	              .parent(taskDTO.getParent())
	              .projects(Project.builder().id(taskDTO.getPid()).build())
	              .realProgress(taskDTO.getRealProgress())
	              .remarks(taskDTO.getRemarks())
	              .reportRegistFlag(taskDTO.getReportRegistFlag())
	              .taskType(taskDTO.getTaskType())
	              .taskTitle(taskDTO.getTaskTitle())
	              .taskStartDate(startDate)
	              .taskEndDate(endDate)
	              .detailedTaskType(taskDTO.getDetailedTaskType())
	              .divisionOfTask(taskDTO.getDivisionOfTask())
	              .writer(taskDTO.getWriter())
	              .build();
	             
	        entityMap.put("task",task);

	 
	        return entityMap;
	}

}
