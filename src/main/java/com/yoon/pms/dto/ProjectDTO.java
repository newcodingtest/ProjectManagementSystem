package com.yoon.pms.dto;

import java.time.LocalDateTime;  
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.yoon.pms.entity.Project;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class ProjectDTO {
	
	private Long pid;

	private String manager;
	
	@NotEmpty(message = "제목은 NULL, \"\" 값이 될수 없습니다.")
	@NotNull(message = "제목은 NULL 값이 될수 없습니다.")
	@NotBlank(message ="제목은 빈값, NULL, 단순 공백으로 들어올수 없습니다.")
	private String projectTitle;
	
	@NotEmpty(message = "별칭은 NULL, \"\" 값이 될수 없습니다.")
	@NotNull(message = "별칭은 NULL 값이 될수 없습니다.")
	@NotBlank(message ="별칭은 빈값, NULL, 단순 공백으로 들어올수 없습니다.")
	private String projectNickname;
	
	private String contents;
	
	private String remarks;
	
	private String statusCode; //진행 상태 --> 진핸전/진행중/완료/중단
	
	@NotEmpty(message = "시작일은 NULL, \"\" 될수 없습니다.")
	@NotNull(message = "시작일은 NULL 값이 될수 없습니다.")
	@NotBlank(message = "시작일은 빈값, NULL, 단순 공백으로 들어올수 없습니다.")
	private String startDate;
	
	@NotEmpty(message = "종료일은 NULL, \"\" 값이 될수 없습니다.")
	@NotNull(message = "종료일은 NULL 값이 될수 없습니다.")
	@NotBlank(message ="종료일은 빈값, NULL, 단순 공백으로 들어올수 없습니다.")
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
	    		 .contents(dto.contents)
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
	    		 .contents(entity.getContents())
	    		 .startDate(entity.getStartDate().toString())
	    		 .endDate(entity.getEndDate().toString())
	    		 .statusCode(entity.getStatusCode())
	    		 .manager(entity.getManager())
	    		 .build();
	}
	
}
