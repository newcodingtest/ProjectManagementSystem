package com.yoon.pms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/project")
public class ProjectController {
	
	
	@GetMapping("/list")
	public void moveList() {
		log.info("----project->list------");
	}

}
