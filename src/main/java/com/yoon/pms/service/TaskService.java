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
	long register(TaskDTO taskDTO);
	TaskDTO getTaskOne(Long id);
	long modify(TaskDTO dto);
	void remove(TaskDTO dto);
	
	List<Task> getStatusBeforeList();
	List<Task> getStatusIngList();
	List<Task> getStatusEndList();
	
	default TaskDTO entityToDTO(Task entity) {
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
	              .taskStartDate(entity.getTaskStartDate().toString())
	              .taskEndDate(entity.getTaskEndDate().toString())
	              .detailedTaskType(entity.getDetailedTaskType())
	              .divisionOfTask(entity.getDivisionOfTask())
	              .writer(entity.getWriter())
				.build();
	}
	
	default Task dtoToEntity(TaskDTO dto){

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
	              .taskStartDate(startDate)
	              .taskEndDate(endDate)
	              .detailedTaskType(dto.getDetailedTaskType())
	              .divisionOfTask(dto.getDivisionOfTask())
	              .writer(dto.getWriter())
	              .build();
	}
	
}
