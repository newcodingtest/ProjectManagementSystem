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
	
	List<TaskDTO> getStatusBeforeList();
	List<TaskDTO> getStatusIngList();
	List<TaskDTO> getStatusEndList();
	
}
