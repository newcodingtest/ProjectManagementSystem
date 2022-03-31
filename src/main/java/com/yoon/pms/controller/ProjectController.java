package com.yoon.pms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yoon.pms.dto.ProjectResponseDTO;
import com.yoon.pms.service.ProjectService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/project")
public class ProjectController {
	
	private final ProjectService service;
	
	@GetMapping("/list")
	public void moveList() {
		log.info("----project->list------");
	}
	
	   @PostMapping(value = "/sheet/list")
	   @ResponseBody
	   public ProjectResponseDTO data(){
		 
		   return service.getProjectListByStatusCode();
	   }
	   
	   @GetMapping(value = "/register")
	   public void register(){
		   log.info("----project->register------");
	   }

}
