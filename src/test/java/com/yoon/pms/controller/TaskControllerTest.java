package com.yoon.pms.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.yoon.pms.service.TaskService;
import com.yoon.pms.service.WeekReportService;


@RunWith(SpringRunner.class)
@WebMvcTest(controllers = {TaskController.class})
public class TaskControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private TaskService taskService;
	
	  @Test
	  @DisplayName("list 페이지 테스트")
	  public void list_페이지_이동() throws Exception{
	        
	      //andExpect
	      mvc.perform(get("/task/list").content("application/json"))
	         .andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()));    
	   }
	  
	  @Test
	  @DisplayName("list 페이지 테스트1")
	  public void list_페이지_이동1() throws Exception{
	        
	      //andExpect
	      mvc.perform(get("/task/list"))
	         .andExpect(status().isOk());    
	   }
	  
	  @Test
	  @DisplayName("register 페이지 테스트")
	  public void register_페이지_이동() throws Exception{
	        
	      //andExpect
	      mvc.perform(get("/task/register"))
	         .andExpect(status().isOk());    
	   }
	  

}
