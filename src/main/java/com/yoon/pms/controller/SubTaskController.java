package com.yoon.pms.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus; 
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yoon.pms.dto.SubTaskDTO;
import com.yoon.pms.dto.TaskDTO;
import com.yoon.pms.security.filter.ApiCheckFilter;
import com.yoon.pms.service.SubTaskService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;

/**
 * 하위작업 클래스
 * @author 윤주영
 * */
@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/subtask")
public class SubTaskController {
	
	private final SubTaskService service;

	@PostMapping("/{tid}")
	public ResponseEntity<Long>addSubTask(@RequestBody TaskDTO TaskWithSubTaskDto){
		log.info("--------------- addSubTask ---------------");
		log.info("subTaskDTO: " + TaskWithSubTaskDto);
		
		//service.register(subTaskMap);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
