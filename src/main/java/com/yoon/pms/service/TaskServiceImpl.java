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
	public Long register(TaskDTO dto) {
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
	
	// readOnly = true : 트랜잭션 범위는 유지하면서 조회 기능만 남겨두어 조회 속도가 개선되어, 등록/수정/삭제 기능이 없는 메소드에 사용하는 것을 추천되어진다.
	@Transactional(readOnly = true) 
	@Override
	public List<TaskDTO> getStatusBeforeList() {
		
		List<Task> target = taskRepository.getNotStartList();
		
		return TaskResponseDTO.ListEntityToDto(target);
	}
	
	
	// readOnly = true : 트랜잭션 범위는 유지하면서 조회 기능만 남겨두어 조회 속도가 개선되어, 등록/수정/삭제 기능이 없는 메소드에 사용하는 것을 추천되어진다.
	@Transactional(readOnly = true) 
	@Override
	public List<TaskDTO> getStatusIngList() {
		
		List<Task> target = taskRepository.getOnGoingList();
		
		return TaskResponseDTO.ListEntityToDto(target);
	}
	
	// readOnly = true : 트랜잭션 범위는 유지하면서 조회 기능만 남겨두어 조회 속도가 개선되어, 등록/수정/삭제 기능이 없는 메소드에 사용하는 것을 추천되어진다.
	@Transactional(readOnly = true) 
	@Override
	public List<TaskDTO> getStatusEndList() {
		
		List<Task> target = taskRepository.getEndedList();
		
		return TaskResponseDTO.ListEntityToDto(target);
	}

	// readOnly = true : 트랜잭션 범위는 유지하면서 조회 기능만 남겨두어 조회 속도가 개선되어, 등록/수정/삭제 기능이 없는 메소드에 사용하는 것을 추천되어진다.
	@Transactional(readOnly = true) 
	@Override
	public TaskDTO getTaskOne(Long id) {
		Optional<Task> target = taskRepository.findById(id);
		
		return TaskDTO.entityToDTO(target.get());
	}

	@Override
	@Transactional
	public Long modify(TaskDTO dto) {

		Task target = taskRepository.getById(dto.getTid());
		
		target.getSubTaskList().clear();
		
		Task updated = TaskDTO.dtoToEntity(dto);
		
		taskRepository.save(updated);
		
		return dto.getTid();
	}

	@Override
	@Transactional
	public void remove(TaskDTO dto) {
		Optional<Task> origin =taskRepository.findById(dto.getTid()); 
		origin.orElseThrow(NoSuchElementException::new);
		
		Task target =  TaskDTO.dtoToEntity(dto);
		taskRepository.delete(target);
		
	}

	@Override
	@Transactional
	public Long modifyTaskWithSub(TaskDTO requestDto) {
		Task target = taskRepository.getById(requestDto.getTid());
		
		target.getSubTaskList().clear();
		
		Task updated = TaskDTO.dtoToEntity(requestDto);
		
		taskRepository.save(updated);
		
		return requestDto.getTid();
	}


	
}
