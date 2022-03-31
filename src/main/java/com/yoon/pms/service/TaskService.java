package com.yoon.pms.service;

import java.util.List;
import com.yoon.pms.dto.TaskDTO;

public interface TaskService {
	long register(TaskDTO dto);
	TaskDTO getTaskOne(Long id);
	long modify(TaskDTO dto);
	void remove(TaskDTO dto);
	
	List<TaskDTO> getStatusBeforeList();
	
	List<TaskDTO> getStatusIngList();
	
	List<TaskDTO> getStatusEndList();
	
}
