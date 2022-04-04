package com.yoon.pms.service;

import org.springframework.stereotype.Service;

import com.yoon.pms.dto.SubTaskDTO;
import com.yoon.pms.entity.SubTask;
import com.yoon.pms.repository.SubTaskRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class SubTaskServiceImpl implements SubTaskService {

	private final SubTaskRepository repository;
	
	
	@Override
	public long register(SubTaskDTO dto) {
		
		SubTask entity = SubTaskDTO.dtoToEntity(dto);
		return repository.save(entity).getSid();
	
	}
	

}
