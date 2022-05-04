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
import com.yoon.pms.service.SubTaskService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/subtask")
public class SubTaskController {
	
	private final SubTaskService service;

	/*
	 * @PostMapping("/{tid}") public ResponseEntity<Long>addSubTask(@RequestBody
	 * Map<String, String[]> subTaskMap){
	 * log.info("--------------- addSubTask ---------------");
	 * log.info("subTaskDTO: " + subTaskMap);
	 * 
	 * service.register(subTaskMap);
	 * 
	 * return new ResponseEntity<>(HttpStatus.OK); }
	 */
	
	@PostMapping("/{tid}")
	public ResponseEntity<Long>addSubTask(@RequestBody TaskDTO TaskWithSubTaskDto){
		log.info("--------------- addSubTask ---------------");
		log.info("subTaskDTO: " + TaskWithSubTaskDto);
		
		//service.register(subTaskMap);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
