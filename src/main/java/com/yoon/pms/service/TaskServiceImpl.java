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
		//1.파라미터로 들어온 상위작업을 갖고있는 하위작업들을 찾는다.
		//2.1번 조건에 만족하는 하위작업들을 삭제한다.
		//3.파라미터로 들어온 상위작업을 등록 또는 수정한다.
		//4.파라미터로 들어온 새로운 하위작업들을 추가한다.
		Task parentTarget = TaskDTO.dtoToEntity(dto);
		List<SubTask> subTarget = parentTarget.getSubTaskList();
		
		Task result = taskRepository.getTaskListWithAll(dto.getTid());
		
		//(1)
		if(!result.getSubTaskList().isEmpty()) {
			result.getSubTaskList().forEach(subtask->{
				//(2)
				subTaskRepository.deleteById(subtask.getSid());
			});
		}
		//(3)
		Task savedParentTask = taskRepository.save(parentTarget);
		
		//(4)
		parentTarget.getSubTaskList().forEach(subtask->{
			  subTaskRepository.save(subtask);
		  });
		return savedParentTask.getTid();
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
	

	@Transactional
	@Override
	public long modify(TaskDTO dto) {
		
		Optional<Task> origin =taskRepository.findById(dto.getTid()); //Eager
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
