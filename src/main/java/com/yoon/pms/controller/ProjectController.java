package com.yoon.pms.controller;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yoon.pms.dto.ProjectDTO;
import com.yoon.pms.dto.ProjectResponseDTO;
import com.yoon.pms.service.ProjectService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 프로젝트 관련 클래스
 * @author 윤주영
 * */
@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/project")
public class ProjectController {
	
	private final ProjectService service;
	
	@GetMapping("/list")
	public void moveListPage() {
		log.info("----project->list------");
	}
	
	 @PostMapping(value = "/sheet/list")
	 @ResponseBody
	 public ProjectResponseDTO sendData(@RequestParam(value = "status", defaultValue = "진행중")String status){
		 log.info("상태코드: " +status);
		 
		 return service.getProjectListByStatusCode(status);
	 }
	   
	   @GetMapping(value = "/register")
	   public void moveRegisterPage(){
		   log.info("----project->moveRegisterPage------");
	   }
	   
	   @PostMapping(value = "/register")
	   public String doRegister(ProjectDTO dto, RedirectAttributes rttr){
		   log.info("----project->doRegister------");
		   Long pid = service.register(dto);
		   
		   rttr.addFlashAttribute("msg", pid);
			
			return "redirect:/project/list";
	   }
	   
	   @PostMapping(value="modify")
	   public String doModify(ProjectDTO dto, RedirectAttributes rttr) {
		   log.info("----project->doModify------");
		   
		   service.modify(dto);
		   
		   return "redirect:/project/list";
	   }
	   
	   @GetMapping(value="/read")
	   public void moveReadPage(@Param(value="pid")long pid, Model model) {
		   log.info("----project->moveReadPage------");
		   
		   ProjectDTO dto = service.getProjectOneById(pid);
		   model.addAttribute("dto",dto);
	   }
	   
	   @PostMapping(value="remove")
	   public String doRemove(@Param(value="pid")long pid) {
		   log.info("----project->doRemove------");
		   
		   service.remove(pid);
		   
		   return "redirect:/project/list";
	   }

}
