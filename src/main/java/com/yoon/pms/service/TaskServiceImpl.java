package com.yoon.pms.service;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import com.yoon.pms.dto.TaskDTO;
import com.yoon.pms.entity.Task;
import com.yoon.pms.repository.TaskRepository;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskServiceImpl implements TaskService {

	private final TaskRepository taskRepository;
	
	@Override
	public Task register(TaskDTO taskDTO) {
		Map<String, Object> entityMap = dtoToEntity(taskDTO);
		Task target = (Task) entityMap.get("task");
		
		Task savedTask = taskRepository.save(target);
		
		return savedTask;
	}

	@Override
	public List<Task> getStatusBeforeList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Task> getStatusIngList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Task> getStatusEndList() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public int test() {
		return 2;
	}
}
