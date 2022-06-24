package com.yoon.pms.controller;

import java.util.Optional; 

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.yoon.pms.dto.WeekReportDTO;
import com.yoon.pms.security.filter.ApiCheckFilter;
import com.yoon.pms.service.WeekReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;

/**
 * 주간보고서 클래스
 * @author 윤주영
 * */
@RestController
@Log4j2
@RequestMapping("report")
@RequiredArgsConstructor
public class ReportController {
	
	private final WeekReportService weekReportService;
	
	@GetMapping("/departmentList/{selectedDate}")
	public ResponseEntity<?>showMyDepartmentsReport(@PathVariable Optional<String> selectedDate){
	
		WeekReportDTO dto = new WeekReportDTO();
		
		return new ResponseEntity<>(selectedDate,HttpStatus.OK);
	}
	
	
	@GetMapping("/departmentList")
	public void moveDepartmentReportList(){
	
	}
	
	
	

}
