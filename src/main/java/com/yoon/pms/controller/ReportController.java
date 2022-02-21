package com.yoon.pms.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ReportController {

	@GetMapping("test")
	public ResponseEntity<?> test() {
		String test = "hi";
		log.info("test");
		return new ResponseEntity<>(test,HttpStatus.OK);
	}

}
