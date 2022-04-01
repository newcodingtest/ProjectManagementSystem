package com.yoon.pms;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import com.yoon.pms.dto.ProjectDTO;
import com.yoon.pms.dto.TaskDTO;
import com.yoon.pms.entity.Project;
import com.yoon.pms.entity.Task;
 
public class ProjectFactory {
	
	public static Project makeProjectEntity() {
		ProjectDTO dto = makeProjectkDTO();
		
		return dtoToEntity(dto);
		
	}
	
	
	public static ProjectDTO makeProjectkDTO() {
		 return ProjectDTO.builder()
				 .pid(2L)
				 .projectTitle("프로젝트 제목 생성")
	    		 .projectNickname("프로젝트 닉네임 생성")
	    		 .startDate("2022-03-08T10:10")
	    		 .endDate("2022-03-08T10:10")
	    		 .statusCode("진행전")
	    		 .manager("윤주영")
				  .build();
		
	}
	
	public static Project dtoToEntity(ProjectDTO dto){

		 LocalDateTime startDate = ProjectDTO.stringToLocalDateTime(dto.getStartDate());
		 LocalDateTime endDate = ProjectDTO.stringToLocalDateTime(dto.getEndDate());
		 
	     return Project.builder()
	    		 .id(dto.getPid())
	    		 .projectTitle(dto.getProjectTitle())
	    		 .projectNickname(dto.getProjectNickname())
	    		 .startDate(startDate)
	    		 .endDate(endDate)
	    		 .statusCode(dto.getStatusCode())
	    		 .manager(dto.getManager())
	    		 .build();
	}

}
