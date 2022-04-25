package com.yoon.pms.controller;


import javax.validation.Valid;

import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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
		//log.info("----------list----------");
		//내가 속한 프로젝트 이름=>뷰 단 selectBox에 들어갈 값
		//model.addAttribute("",);
		//model.addAttribute("",);
		//진행전 리스트+개수
		model.addAttribute("beforeList",taskService.getStatusBeforeList());
		model.addAttribute("beforeCnt",taskService.getStatusBeforeList().size());
		//진행중 리스트+개수
		model.addAttribute("ingList",taskService.getStatusIngList());
		model.addAttribute("ingCnt",taskService.getStatusIngList().size());
		//완료 리스트+개수
		model.addAttribute("endList",taskService.getStatusEndList());
		model.addAttribute("endCnt",taskService.getStatusEndList().size());
	}
	
	@GetMapping("/register")
	public void moveRegister(Model model) {
		log.info("----------moveRegister----------");
	}
	
	
	
	@PostMapping("/register")
	public String doRegister(@Valid TaskDTO dto,RedirectAttributes redirectAttributes) {
		log.info("----------doRegister----------");
		Long tid = taskService.register(dto);
		
		redirectAttributes.addFlashAttribute("msg", tid);
		
		return "redirect:/task/list";
	}
	
	@GetMapping("/read")
	public void moveReadPage(long id, Model model) {
		log.info("----------moveReadPage----------");
		TaskDTO dto = taskService.getTaskOne(id);
		
		model.addAttribute("dto",dto);
	}
	
	@PostMapping("/modify")
	public String doModify(TaskDTO dto,RedirectAttributes redirectAttributes) {
		log.info("----------doModify----------");
		
		taskService.modify(dto);
		
		return "redirect:/task/list";
		
	}
	
	@PostMapping("/remove")
	public String doRemove(TaskDTO dto,RedirectAttributes redirectAttributes) {
		log.info("----------doRemove----------");
		
		taskService.remove(dto);
		
		return "redirect:/task/list";
	}
	
	

}
