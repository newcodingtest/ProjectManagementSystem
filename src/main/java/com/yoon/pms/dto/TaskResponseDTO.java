package com.yoon.pms.dto;

import java.util.List; 
import java.util.stream.Collectors;

import com.yoon.pms.entity.Task;

import lombok.Data;

@Data
public class TaskResponseDTO {

	private List<Task> task;
	
	public static List<TaskDTO> ListEntityToDto(List<Task> entity){
		return entity.stream()
				.map(TaskDTO::entityToDTO)
				.collect(Collectors.toList());
	}
}
