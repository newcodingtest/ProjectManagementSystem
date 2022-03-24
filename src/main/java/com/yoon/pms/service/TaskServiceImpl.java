package com.yoon.pms.service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
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
	public long register(TaskDTO taskDTO) {
		
		Task target = dtoToEntity(taskDTO);
		
		Task savedTask = taskRepository.save(target);
		
		return savedTask.getTid();
	}

	@Override
	public List<Task> getStatusBeforeList() {
		// TODO Auto-generated method stub
		return taskRepository.findAll();
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
	

	@Override
	public TaskDTO getTaskOne(Long id) {
		
		Optional<Task> target = taskRepository.findById(id);
		return entityToDTO(target.get());
	}

	@Override
	public long modify(TaskDTO dto) {
		
		Optional<Task> origin =taskRepository.findById(dto.getTid());
		origin.orElseThrow(NoSuchElementException::new);
		
		Task target = dtoToEntity(dto);
		Task updated = taskRepository.save(target);
		
		return updated.getTid();
	}

	@Override
	public void remove(TaskDTO dto) {
		Optional<Task> origin =taskRepository.findById(dto.getTid());
		origin.orElseThrow(NoSuchElementException::new);
		
		Task target = dtoToEntity(dto);
		taskRepository.delete(target);
		
	}
	
}
