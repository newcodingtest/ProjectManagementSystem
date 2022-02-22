package com.yoon.pms.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yoon.pms.dto.WeekReportDTO;
import com.yoon.pms.service.WeekReportService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("report")
@RequiredArgsConstructor
public class ReportController {
	
	private final WeekReportService weekReportService;
	
	@GetMapping("/team/{selectedDate}")
	public ResponseEntity<?>showMyDepartmentsReport(@PathVariable Optional<String> selectedDate){
	
		WeekReportDTO dto = new WeekReportDTO();
		
		return new ResponseEntity<>(selectedDate,HttpStatus.OK);
	}
	

}
