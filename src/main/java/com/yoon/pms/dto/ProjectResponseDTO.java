package com.yoon.pms.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.yoon.pms.entity.Project;
import com.yoon.pms.entity.Task;

import lombok.Data;

@Data
public class ProjectResponseDTO {
	private List<ProjectDTO> data;
	
	public static List<ProjectDTO> ListEntityToDto(List<Project> entity){
		return entity.stream()
				.map(ProjectDTO::entityToDTO)
				.collect(Collectors.toList());
	}
}
