package com.yoon.pms.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.yoon.pms.dto.TaskDTO;
import com.yoon.pms.entity.Project;
import com.yoon.pms.entity.Task;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


public interface TaskService {
	
	default Map<String,Object> dtoToEntity(TaskDTO taskDTO){
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

	Task register(TaskDTO taskDTO);
	
	List<Task> getStatusBeforeList();
	List<Task> getStatusIngList();
	List<Task> getStatusEndList();
	
}
