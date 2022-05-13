package com.yoon.pms.service;

import java.time.LocalDateTime;
import java.util.Iterator;
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
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskServiceImpl implements TaskService {

	private final TaskRepository taskRepository;
	private final SubTaskRepository subTaskRepository;
    
	@Transactional
	@Override
	public Long register(TaskDTO dto) {

		Task target = TaskDTO.dtoToEntity(dto);
	
		Task saved = taskRepository.save(target);

		return saved.getTid();
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
		target.orElseThrow(NoSuchElementException::new);
		
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
	public void remove(Long tid) {
		Optional<Task> origin =taskRepository.findById(tid); 
		origin.orElseThrow(NoSuchElementException::new);
		
		taskRepository.deleteById(tid);
	}

	@Override
	@Transactional
	public Long modifyTaskWithSub(TaskDTO requestDto) {
		System.out.println("받은dto: "+requestDto);
		Task target = taskRepository.getById(requestDto.getTid());
		
		//target.getSubTaskList().clear();
		target.deleteSubTask();
		
		//(1) DTO 에서 하위 정보를 가져온다.
		//(2) 하위작업들의 최소 시작일 최대 종료일을 상위 작업의 시작,종료일로 반영하자
		//(3) 하위작업들의 진행률의 평균값을 계산하여 상위작업 진행률에 반영하자
	
		Task updated = TaskDTO.dtoToEntity(requestDto);
		System.out.println("변환후: "+updated);
		//(1)
		List<SubTask>subTask = updated.getSubTaskList();
		
		int length = subTask.size();
		LocalDateTime minStartDate = subTask.get(0).getSubStartDate();
		LocalDateTime maxEndDate = subTask.get(0).getSubEndDate();
		float sum = 0.0f;
		
		//(2)
		for (int i = 1; i < length; i++) {
		
			
			if(minStartDate.compareTo(subTask.get(i).getSubStartDate())>0) {
				minStartDate = subTask.get(i).getSubStartDate();
			}
			if(maxEndDate.compareTo(subTask.get(i).getSubEndDate())<0) {
				maxEndDate = subTask.get(i).getSubEndDate();
			}
			//(3)
			sum+=subTask.get(i).getSubRealProgress();
		}
		
		updated.changeStartDate(minStartDate);
		updated.changeEndDate(maxEndDate);
		updated.changeRealProgress(sum/length);
	    
		taskRepository.save(updated);
		
		return requestDto.getTid();
	}


	
}
