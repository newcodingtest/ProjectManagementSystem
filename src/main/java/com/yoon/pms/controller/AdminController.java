package com.yoon.pms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yoon.pms.dto.ProjectDTO;
import com.yoon.pms.dto.ProjectResponseDTO;
import com.yoon.pms.security.filter.ApiCheckFilter;
import com.yoon.pms.service.ProjectService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;

/**
 * 권한이 있는 관리자/어드민 접근 클래스
 * @author 윤주영
 * */
@Controller
@Log4j2
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
	
	@GetMapping("/project/wbs")
	@ResponseBody
	public String wbs(Model model, Long projectId) {
		
		//프로젝트 가져오기
		//프로젝트 해당 멤버 가져오기
		
		ProjectDTO projectDto =  projectService.getProjectOneById(projectId);
		return null;
	}
	

	

}
