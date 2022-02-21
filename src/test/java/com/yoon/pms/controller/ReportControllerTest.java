package com.yoon.pms.controller;

import static org.junit.jupiter.api.Assertions.*; 
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.apache.catalina.security.SecurityConfig;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


@RunWith(SpringRunner.class)
@WebMvcTest(controllers = {ReportController.class})
public class ReportControllerTest {

	@Autowired
	private MockMvc mvc;
	
	  @Test
	  public void hi_∏Æ≈œ() throws Exception{
	        String hi = "hi";
	        mvc.perform(get("/test"))
	                .andExpect(status().isOk())
	                .andExpect(content().string(hi));
	   }
}
