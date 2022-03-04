package com.yoon.pms.service;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.yoon.pms.dto.TaskDTO;
import com.yoon.pms.entity.Task;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


public interface TaskService {
	
	default Map<String,Object> dtoToEntity(TaskDTO taskDTO){
		 Map<String,Object> entityMap = new HashMap();

	        Task task = Task.builder()
	              .progressState(taskDTO.getProgressState())
	              .parent(taskDTO.getParent())
	              .projectId(taskDTO.getProjectId())
	              .realProgress(taskDTO.getRealProgress())
	              .remarks(taskDTO.getRemarks())
	              .reportRegistFlag(taskDTO.getReportRegistFlag())
	              .taskType(taskDTO.getTaskType())
	              .taskTitle(taskDTO.getTaskTitle())
	              .taskStartDate(taskDTO.getTaskStartDate())
	              .taskEndDate(taskDTO.getTaskEndDate())
	              .detailedTaskType(taskDTO.getDetailedTaskType())
	              .divisionOfTask(taskDTO.getDivisionOfTask())
	              .writer(taskDTO.getWriter())
	              .build();
	             
	        entityMap.put("task",task);

	 
	        return entityMap;
	}

	void register(TaskDTO taskDTO);
	
}
