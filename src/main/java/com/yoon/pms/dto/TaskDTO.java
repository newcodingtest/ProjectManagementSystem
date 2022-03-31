package com.yoon.pms.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import com.yoon.pms.entity.Project;
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
	 
	private Long pid;  //프로젝트 아이디
	 
	private String taskType; //업무 종류 --> 개발/업무제안/관리/기타
	 
	private String detailedTaskType; //상세 업무 -->개발/버그수정/산출물/테스트/휴가/기타 등등
	 
	private String divisionOfTask; //업무 구분 --> 주/지원
	private String taskContents; // 업무 내용
	private String taskTitle; //업무 제목
	 
	private String writer; //작성자 아이디
	 
	private String remarks; //비고
	
    //@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") //get
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul") //post
	//private LocalDateTime taskStartDate; //업무 시작일
	private String taskStartDate;
    
    //@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") //get
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul") //post
	//private LocalDateTime taskEndDate;  //업무 종료일
	private String taskEndDate;
	
	private float realProgress; // 실제 진행률 --> 1%,50%,100%
	 
	private String savedWeekDate; // 배치가 돌아서 저장된 날짜
	 
	private String statusCode; //진행 상태 --> 진핸전/진행중/완료/중단
	 
	private Long parent; // --> 부모 작업 /자식작업 구분 키
	 
	private String reportRegistFlag; //--> 보고서 등록 여부
	
	private LocalDateTime regDate;

	private LocalDateTime modDate;
	
	//20220317T10:30
	public static LocalDateTime stringToLocalDateTime(String inputDate) {
		LocalDateTime dateTime = LocalDateTime.parse(inputDate,DateTimeFormatter.ISO_DATE_TIME);
		return dateTime;
	}
	

	public static Task dtoToEntity(TaskDTO dto){

		 LocalDateTime startDate = dto.stringToLocalDateTime(dto.getTaskStartDate());
		 LocalDateTime endDate = dto.stringToLocalDateTime(dto.getTaskEndDate());
		 
	     return Task.builder()
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
				.build();
	}
	

}
