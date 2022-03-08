package com.yoon.pms.controller;

import org.springframework.http.HttpStatus; 
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yoon.pms.dto.PageRequestDTO;
import com.yoon.pms.dto.TaskDTO;
import com.yoon.pms.service.TaskService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/task")
public class TaskController {

	private final TaskService taskService;
	
	@GetMapping("/list")
	public void moveList(Model model) {
		log.info("TaskController-moveList()");
	}
	
	@GetMapping("/register")
	public void moveRegister(Model model) {
		log.info("TaskController-moveRegister()");
	}
	
	@PostMapping("/register")
	public String register(TaskDTO dto,RedirectAttributes redirectAttributes) {
		log.info("TaskController-register()");
		log.info("TaskDTO: "+dto);
		
		taskService.register(dto);
		
		return "redirect:/task/list";
		
	}
	
	

}
