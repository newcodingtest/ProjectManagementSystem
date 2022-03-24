package com.yoon.pms.controller;

import java.security.Provider.Service;

import org.springframework.http.HttpStatus;  
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.yoon.pms.dto.PageRequestDTO;
import com.yoon.pms.dto.TaskDTO;
import com.yoon.pms.service.ProjectService;
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
		log.info("----------list----------");
		//내가 속한 프로젝트 이름=>뷰 단 selectBox에 들어갈 값
		//model.addAttribute("",);
		//model.addAttribute("",);
		//진행전 리스트+개수
		model.addAttribute("beforeList",taskService.getStatusBeforeList());
		//model.addAttribute("beforeCnt",);
		//진행중 리스트+개수
		//model.addAttribute("ingList",);
		//model.addAttribute("ingCnt",);
		//완료 리스트+개수
		//model.addAttribute("endList",);
		//model.addAttribute("endCnd",);
	}
	
	@GetMapping("/register")
	public void moveRegister(Model model) {
		log.info("----------register----------");
	}
	
	
	
	@PostMapping("/register")
	public String register(TaskDTO dto,RedirectAttributes redirectAttributes) {
		log.info("----------register----------");
		log.info("TaskDTO: "+dto);
		Long tid = taskService.register(dto);
		
		redirectAttributes.addFlashAttribute("msg", tid);
		
		return "redirect:/task/list";
	}
	
	@GetMapping("/read")
	public void read(long id, Model model) {
		log.info("----------read----------");
		log.info("tid: "+id);
		TaskDTO dto = taskService.getTaskOne(id);
		log.info("dto: "+dto);
		
		model.addAttribute("dto",dto);
	}
	
	@PostMapping("/modify")
	public String modify(TaskDTO dto,RedirectAttributes redirectAttributes) {
		log.info("----------modify----------");
		log.info("tid: "+dto.getTid());
		
		taskService.modify(dto);
		
		return "redirect:/task/list";
		
	}
	
	@PostMapping("/remove")
	public String remove(TaskDTO dto,RedirectAttributes redirectAttributes) {
		log.info("----------remove----------");
		log.info("tid: "+dto.getTid());
		
		taskService.remove(dto);
		
		return "redirect:/task/list";
	}
	
	

}
