package com.yoon.pms.service;

import java.util.List;  
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yoon.pms.dto.TaskDTO;
import com.yoon.pms.dto.TaskResponseDTO;
import com.yoon.pms.entity.SubTask;
import com.yoon.pms.entity.Task;
import com.yoon.pms.repository.SubTaskRepository;
import com.yoon.pms.repository.TaskRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

	private final TaskRepository taskRepository;
	private final SubTaskRepository subTaskRepository;
    
	@Transactional
	@Override
	public long register(TaskDTO dto) {
		
		Task parentTarget = TaskDTO.dtoToEntity(dto);
		List<SubTask> subTarget = parentTarget.getSubTaskList();
		
		Task savedTask = taskRepository.save(parentTarget);
		
		  subTarget.forEach(subTask -> {
			  subTaskRepository.save(subTask);
		  });
		 
		
		return savedTask.getTid();
	}

	@Override
	public List<TaskDTO> getStatusBeforeList() {
		
		List<Task> target = taskRepository.getNotStartList();
		
		return TaskResponseDTO.ListEntityToDto(target);
	}
	
	

	@Override
	public List<TaskDTO> getStatusIngList() {
		
		List<Task> target = taskRepository.getOnGoingList();
		
		return TaskResponseDTO.ListEntityToDto(target);
	}

	@Override
	public List<TaskDTO> getStatusEndList() {
		
		List<Task> target = taskRepository.getEndedList();
		
		return TaskResponseDTO.ListEntityToDto(target);
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
		
		Task target = TaskDTO.dtoToEntity(dto);
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
