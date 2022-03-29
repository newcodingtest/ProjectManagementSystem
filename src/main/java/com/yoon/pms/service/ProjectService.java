package com.yoon.pms.service;

import java.time.LocalDateTime; 

import java.util.HashMap;
import java.util.Map;
import com.yoon.pms.dto.ProjectDTO;
import com.yoon.pms.entity.Project;


public interface ProjectService {

	default Map<String,Object>dtoToEntity(ProjectDTO projectDTO){
		Map<String, Object> entityMap = new HashMap();
		
		 LocalDateTime startDate = projectDTO.stringToLocalDateTime(projectDTO.getStartDate());
		 LocalDateTime endDate = projectDTO.stringToLocalDateTime(projectDTO.getEndDate());
		 
		
		Project project = Project.builder()
				.id(projectDTO.getPid())
				.projectTitle(projectDTO.getProjectTitle())
				.projectNickname(projectDTO.getProjectNickname())
				.startDate(startDate)
				.endDate(endDate)
				.build();
		
		entityMap.put("project", project);
		
		return entityMap;
	}
}
