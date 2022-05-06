package com.yoon.pms.dto;

import java.time.LocalDateTime;  
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import com.yoon.pms.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {

	private Long tid;
	 
	private Long pid; 
	 
	private String taskType; 
	 
	private String detailedTaskType; 
	 
	private String divisionOfTask; 
	private String taskContents; 
	
	@NotEmpty(message = "제목은 빈 값이 될수 없습니다.")
	@NotNull(message = "제목은 NULL 값이 될수 없습니다.")
	private String taskTitle; 
	 
	private String writer; 
	
	private String remarks; //비고 
	
	@NotEmpty(message = "시작일은 빈 값이 될수 없습니다.")
	@NotNull(message = "시작일은 NULL 값이 될수 없습니다.")
    //@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") //get
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul") //post
	//private LocalDateTime taskStartDate; //
	private String taskStartDate;
	
	@NotEmpty(message = "종료일은 빈 값이 될수 없습니다.")
	@NotNull(message = "종료일은 NULL 값이 될수 없습니다.")
    //@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") //get
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul") //post
	//private LocalDateTime taskEndDate;  /
	private String taskEndDate;
	
	@Builder.Default
	private float realProgress=0; // 
	 
	private String savedWeekDate; //
	
	@Builder.Default
	private String statusCode="진행전"; //
	 
	private Long parent; // -->
	
	private String reportRegistFlag; //-->
	
	private LocalDateTime regDate;

	private LocalDateTime modDate;
	
	@Builder.Default
	private List<SubTaskDTO> subTaskDTOList = new ArrayList<SubTaskDTO>();
	
	
	//20220317T10:30
	public static LocalDateTime stringToLocalDateTime(String inputDate) {
		LocalDateTime dateTime = LocalDateTime.parse(inputDate,DateTimeFormatter.ISO_DATE_TIME);
		return dateTime;
	}
	

	public static Task dtoToEntity(TaskDTO dto){

		 LocalDateTime startDate = TaskDTO.stringToLocalDateTime(dto.getTaskStartDate());
		 LocalDateTime endDate = TaskDTO.stringToLocalDateTime(dto.getTaskEndDate());
		 
	     return 
	    		 Task.builder()
	        	  .tid(dto.getTid())
	        	  .projectId(dto.getPid())
	              .statusCode(dto.getStatusCode())
	              .parent(dto.getParent())
	              .realProgress(dto.getRealProgress())
	              .remarks(dto.getRemarks())
	              .reportRegistFlag(dto.getReportRegistFlag())
	              .taskType(dto.getTaskType())
	              .taskTitle(dto.getTaskTitle())
	              .taskContents(dto.getTaskContents())
	              .taskStartDate(startDate)
	              .taskEndDate(endDate)
	              .detailedTaskType(dto.getDetailedTaskType())
	              .divisionOfTask(dto.getDivisionOfTask())
	              .writer(dto.getWriter())
	              .subTaskList(SubTaskDTO.ListDtoToEntity(dto.getSubTaskDTOList()))
	              .build();
	             
	}

	
	public static TaskDTO entityToDTO(Task entity) {
		return TaskDTO.builder()
		      	  .tid(entity.getTid())
	        	  .pid(entity.getProjectId())
	              .statusCode(entity.getStatusCode())
	              .parent(entity.getParent())
	              .realProgress(entity.getRealProgress())
	              .remarks(entity.getRemarks())
	              .reportRegistFlag(entity.getReportRegistFlag())
	              .taskType(entity.getTaskType())
	              .taskTitle(entity.getTaskTitle())
	              .taskContents(entity.getTaskContents())
	              .taskStartDate(entity.getTaskStartDate().toString())
	              .taskEndDate(entity.getTaskEndDate().toString())
	              .detailedTaskType(entity.getDetailedTaskType())
	              .divisionOfTask(entity.getDivisionOfTask())
	              .writer(entity.getWriter())
	              .subTaskDTOList(SubTaskDTO.ListEntityToDto(entity.getSubTaskList()))
				.build();
	}
	

}
