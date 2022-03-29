package com.yoon.pms.service;

import java.util.List; 
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.yoon.pms.dto.TaskDTO;
import com.yoon.pms.dto.TaskResponseDto;
import com.yoon.pms.entity.Task;
import com.yoon.pms.repository.TaskRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskServiceImpl implements TaskService {

	private final TaskRepository taskRepository;
	
	@Override
	public long register(TaskDTO dto) {
		
		Task target = dto.dtoToEntity(dto);
		
		Task savedTask = taskRepository.save(target);
		
		return savedTask.getTid();
	}

	@Override
	public List<TaskDTO> getStatusBeforeList() {
		
		List<Task> target = taskRepository.getNotStartList();
		
		return TaskResponseDto.ListEntityToDto(target);
	}
	
	

	@Override
	public List<TaskDTO> getStatusIngList() {
		
		List<Task> target = taskRepository.getOnGoingList();
		
		return TaskResponseDto.ListEntityToDto(target);
	}

	@Override
	public List<TaskDTO> getStatusEndList() {
		
		List<Task> target = taskRepository.getEndedList();
		
		return TaskResponseDto.ListEntityToDto(target);
	}

	@Override
	public TaskDTO getTaskOne(Long id) {
		Optional<Task> target = taskRepository.findById(id);
		
		return TaskDTO.entityToDTO(target.get());
	}
	

	@Override
	public long modify(TaskDTO dto) {
		
		Optional<Task> origin =taskRepository.findById(dto.getTid());
		origin.orElseThrow(NoSuchElementException::new);
		
		Task target = dto.dtoToEntity(dto);
		Task updated = taskRepository.save(target);
		
		return updated.getTid();
	}

	@Override
	public void remove(TaskDTO dto) {
		Optional<Task> origin =taskRepository.findById(dto.getTid());
		origin.orElseThrow(NoSuchElementException::new);
		
		Task target =  TaskDTO.dtoToEntity(dto);
		taskRepository.delete(target);
		
	}


	
}
