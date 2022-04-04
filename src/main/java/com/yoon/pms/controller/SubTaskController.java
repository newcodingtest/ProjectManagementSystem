package com.yoon.pms.controller;

import org.springframework.http.HttpStatus; 
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yoon.pms.dto.SubTaskDTO;
import com.yoon.pms.service.SubTaskService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/subtask")
public class SubTaskController {
	
	private final SubTaskService service;

	@PostMapping("/{tid}")
	public ResponseEntity<Long>addSubTask(@RequestBody SubTaskDTO subTaskDTO){
		log.info("--------------- addSubTask ---------------");
		log.info("subTaskDTO: " + subTaskDTO);
		
		Long savedSubTaskId = service.register(subTaskDTO);
		
		return new ResponseEntity<>(savedSubTaskId,HttpStatus.OK);
	}
}
