package com.yoon.pms.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yoon.pms.dto.PageRequestDTO;
import com.yoon.pms.service.TaskService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController("/task")
@Slf4j
@RequiredArgsConstructor
public class TaskController {

	private final TaskService taskService;
	
	@GetMapping("list")
	public ResponseEntity<?> taskList(PageRequestDTO dto, Model model) {
		String test = "hi";
		log.info("test");
		return new ResponseEntity<>(test,HttpStatus.OK);
	}

}
