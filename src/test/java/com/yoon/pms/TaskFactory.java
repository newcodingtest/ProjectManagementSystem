package com.yoon.pms;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.yoon.pms.dto.SubTaskDTO;
import com.yoon.pms.dto.TaskDTO;
import com.yoon.pms.entity.SubTask;
import com.yoon.pms.entity.Task;
 
public class TaskFactory {
	
	public static Task makeTaskEntity() {
		TaskDTO dto = makeTaskDTO();
		
		return changeTaskEntity(dto);
		
	}
	
	public static SubTask makeSubTaskEntity() {
		SubTaskDTO dto = makeSubTaskDTO();
		
		return changeSubTaskEntity(dto);
		
	}
	
	public static SubTaskDTO makeSubTaskDTO() {
		 return SubTaskDTO.builder()
					.subTitle("테스트용 제목")
					.subContents("테스트용 내용")
					.subStartDate("2022-03-08T10:10")
					.subEndDate("2022-03-08T10:10")
					.subReportRegistFlag("1")
					.subRealProgress(10)
					.build();
	}
	
	public static List<SubTaskDTO> makeSubTaskDTOList() {
		List<SubTaskDTO> list = new ArrayList<SubTaskDTO>();
		list.add(makeSubTaskDTO());
		return list;
	}
	
	
	public static SubTask changeSubTaskEntity(SubTaskDTO dto) {
		return SubTask.builder()
				.sid(dto.getSid())
				.task(Task.builder().tid(dto.getTid()).build())
				.subTitle(dto.getSubTitle())
				.subContents(dto.getSubContents())
				.subStartDate(SubTaskDTO.stringToLocalDateTime(dto.getSubStartDate()))
				.subEndDate(SubTaskDTO.stringToLocalDateTime(dto.getSubStartDate()))
				.subReportRegistFlag(dto.getSubReportRegistFlag())
				.subRealProgress(dto.getSubRealProgress())
				.build();
	}
	
	public static TaskDTO makeTaskDTO() {
		 return TaskDTO.builder()
				  .taskTitle("서비스단 테스트 제목")
				  .taskContents("서비스단 테스트 내용")
				  .statusCode("진행전")
				  .realProgress(3)
				  .reportRegistFlag("2")
				  .pid(1L)
				  .taskStartDate("2022-03-08T10:10")
				  .taskEndDate("2022-03-08T10:10")
				  .taskType("종류")
				  .detailedTaskType("상세")
				  .divisionOfTask("분류")
				  .remarks("비고")
				  .build();
	}
	
	public static TaskDTO makeTaskDTOWithSubTaskDTO() {
		List<SubTaskDTO> subList = makeSubTaskDTOList();
		
		 return TaskDTO.builder()
				  .tid(1L)
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
				  .subTaskDTOList(subList)
				  .build();
	}
	
	static Task changeTaskEntity(TaskDTO taskDTO){

		 LocalDateTime startDate = TaskDTO.stringToLocalDateTime(taskDTO.getTaskStartDate());
		 LocalDateTime endDate = TaskDTO.stringToLocalDateTime(taskDTO.getTaskEndDate());
		 
	        Task task = Task.builder()
	        	  .projectId(taskDTO.getPid())
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
