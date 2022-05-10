package com.yoon.pms.service;

import java.util.List;
import com.yoon.pms.dto.TaskDTO;

public interface TaskService {
	Long register(TaskDTO dto);
	TaskDTO getTaskOne(Long id);
	Long modify(TaskDTO dto);
	void remove(Long tid);
	
	List<TaskDTO> getStatusBeforeList();
	
	List<TaskDTO> getStatusIngList();
	
	List<TaskDTO> getStatusEndList();
	
	Long modifyTaskWithSub(TaskDTO requestDto);
	
}
