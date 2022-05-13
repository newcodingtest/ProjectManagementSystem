package com.yoon.pms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yoon.pms.dto.ProjectResponseDTO;
import com.yoon.pms.service.ProjectService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
	
	private final ProjectService projectService;
	
	
	@GetMapping("/projectManageList")
	public void projectManageList() {
		log.info("--- move projectManageList ---");
	}
	
	@PostMapping(value = "/sheet/list")
	public ProjectResponseDTO sheetApi(@RequestParam(value = "status", defaultValue = "전체")String status){
		log.info("상태코드: " +status);
		 
		 return projectService.getProjectListByStatusCode(status);
	}
	
	@GetMapping(value="/project/main")
	public void projectMain() {
		
	}
	

	

}
