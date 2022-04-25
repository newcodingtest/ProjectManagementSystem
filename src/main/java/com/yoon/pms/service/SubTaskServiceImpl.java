package com.yoon.pms.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
	public void register(Map<String, String[]> subTaskMap) {
		
		List<SubTaskDTO> list = new ArrayList();
		
		int existSubTaskCnt = subTaskMap.get("subTitle").length;
		
		for(int i=0; i<existSubTaskCnt; i++) {
			SubTaskDTO dto = SubTaskDTO.builder()
				.subTitle(subTaskMap.get("subTitle")[i])
				.tid(Long.valueOf(subTaskMap.get("tid")[i]))
				.subContents(Optional.ofNullable(subTaskMap.get("subContents")[i]).toString())
				.subStartDate(subTaskMap.get("subStartDate")[i])
				.subEndDate(subTaskMap.get("subEndDate")[i])
				.subRealProgress(Float.parseFloat(subTaskMap.get("subRealProgress")[i]))
				.subReportRegistFlag(subTaskMap.get("subReportRegistFlag")[i])
			
				.build();
			
			list.add(dto);
		}
		
		List<SubTask> target  = SubTaskDTO.ListDtoToEntity(list);
		
		target.forEach(targetOne->{
			repository.save(targetOne);
		});
	
	}
	

}
