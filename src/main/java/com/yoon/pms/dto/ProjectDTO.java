package com.yoon.pms.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
public class ProjectDTO {
	
	private Long pid;

	private String manager;
	
	private String projectNickname;
	
	private String projectTitle;
	
	private String content;
	
	private String remarks;
	
	private String statusCode; //진행 상태 --> 진핸전/진행중/완료/중단
	
	private String startDate;
	
	private String endDate;
	
	public static LocalDateTime stringToLocalDateTime(String inputDate) {
		LocalDateTime dateTime = LocalDateTime.parse(inputDate,DateTimeFormatter.ISO_DATE_TIME);
		return dateTime;
	}
	
	public static Project dtoToEntity(ProjectDTO dto){

		 LocalDateTime startDate = ProjectDTO.stringToLocalDateTime(dto.getStartDate());
		 LocalDateTime endDate = ProjectDTO.stringToLocalDateTime(dto.getEndDate());
		 
	     return Project.builder()
	    		 .id(dto.getPid())
	    		 .projectTitle(dto.getProjectTitle())
	    		 .projectNickname(dto.getProjectNickname())
	    		 .contents(dto.content)
	    		 .startDate(startDate)
	    		 .endDate(endDate)
	    		 .statusCode(dto.getStatusCode())
	    		 .manager(dto.getManager())
	    		 .build();
	}
	
	public static ProjectDTO entityToDTO(Project entity) {
		return ProjectDTO.builder()
				 .pid(entity.getId())
	    		 .projectTitle(entity.getProjectTitle())
	    		 .projectNickname(entity.getProjectNickname())
	    		 .content(entity.getContents())
	    		 .startDate(entity.getStartDate().toString())
	    		 .endDate(entity.getEndDate().toString())
	    		 .statusCode(entity.getStatusCode())
	    		 .manager(entity.getManager())
	    		 .build();
	}
	
}
