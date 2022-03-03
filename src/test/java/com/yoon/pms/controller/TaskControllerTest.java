package com.yoon.pms.controller;


import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yoon.pms.dto.TaskDTO;
import com.yoon.pms.service.TaskService;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = {TaskController.class})
public class TaskControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private TaskService taskService;
	
	@Autowired
	protected ObjectMapper objectMapper;
	
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
	  
	  @Test
	  @DisplayName("register taskDTO 생성테스트")
	  public void register_taskDTO_생성테스트() throws Exception {
		  
		  LocalDateTime test = LocalDateTime.now();
		  
		  //given
		  TaskDTO dto = TaskDTO.builder()
				  .taskTitle("테스트 제목")
				  .progressState(4)
				  .realProgress(3)
				  .reportRegistFlag(2)
				  .projectId((long) 1.0)
				  .taskStartDate(test)
				  .taskEndDate(test)
				  .taskType("종류")
				  .detailedTaskType("상세")
				  .divisionOfTask("분류")
				  .Contents("테스트 내용")
				  .remarks("비고")
				  .build();

		  //when
		  final ResultActions resultActions =  mvc.perform(post("/task/register")
				  .contentType("application/x-www-form-urlencoded")
				  .accept("application/x-www-form-urlencoded")
		  .content(objectMapper.writeValueAsString(dto)))
		  .andDo(print());
	  }
	 
	  

}
