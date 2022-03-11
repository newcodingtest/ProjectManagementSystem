package com.yoon.pms.controller;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get; 
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.hibernate.boot.jaxb.mapping.spi.JaxbNamedAttributeNode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.yoon.pms.dto.TaskDTO;
import com.yoon.pms.repository.TaskRepository;
import com.yoon.pms.service.TaskServiceImpl;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = {TaskController.class})
public class TaskControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private TaskServiceImpl taskService;
	
	@MockBean
	private TaskRepository taskRepository;
	
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
	  @DisplayName("formData post 테스트")
	  public void Posts_등록된다() throws Exception {
		 
		String test = "2022-03-08";
		//LocalDateTime dateTime = LocalDateTime.parse(test,DateTimeFormatter.ISO_DATE);
		
		  //given
		  TaskDTO dto = TaskDTO.builder()
				  .taskTitle("테스트 제목")
				  .progressState((int)4)
				  .realProgress(3)
				  .reportRegistFlag("2")
				  .projectId((long) 1.0)
				  .taskStartDate(test)
				  .taskEndDate(test)
				  .taskType("종류")
				  .detailedTaskType("상세")
				  .divisionOfTask("분류")
				  .remarks("비고")
				  .build();
		  
		  //when
		  	final ResultActions result=	mvc.perform(post("/task/register")
		  				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				  .param("taskTitle", dto.getTaskTitle())
				  .param("progressState",String.valueOf(dto.getProgressState()))
				  .param("realProgress", String.valueOf(dto.getRealProgress()))
				  .param("reportRegistFlag", String.valueOf(dto.getReportRegistFlag()))
				  .param("projectId", String.valueOf(dto.getProjectId()) )
				  .param("taskStartDate", dto.getTaskStartDate())
				  .param("taskEndDate",dto.getTaskEndDate())
				  .param("taskType", String.valueOf(dto.getTaskType()))
				  .param("detailedTaskType", String.valueOf(dto.getDetailedTaskType()))
				  .param("divisionOfTask", String.valueOf(dto.getDivisionOfTask()))
				  .param("remarks", String.valueOf(dto.getRemarks())))
		  		.andDo(print());
		  	
		  	
		  //then		
		  result.andExpect(redirectedUrl("/task/list"));
		  result.andExpect(view().name("redirect:/task/list"));
		  //모델 확인 테스트
		  //result.andExpect(model().attributeExists("test"));
	  }
	  
	  @Test
	  @DisplayName("JSON post 테스트")
	  public void Posts_등록된다1() throws Exception {
		 
		String test = "2022-03-08";
		//LocalDateTime dateTime = LocalDateTime.parse(test,DateTimeFormatter.ISO_DATE);
		
		  //given
		  TaskDTO dto = TaskDTO.builder()
				  .taskTitle("테스트 제목")
				  .progressState((int)4)
				  .realProgress(3)
				  .reportRegistFlag("2")
				  .projectId((long)1)
				  .taskStartDate(test)
				  .taskEndDate(test)
				  .taskType("종류")
				  .detailedTaskType("상세")
				  .divisionOfTask("분류")
				  .remarks("비고")
				  .build();
		  

		  
		  //when
		  	final ResultActions result=	mvc.perform(post("/task/register")
		  				.contentType(MediaType.APPLICATION_JSON)
		  				.accept(MediaType.APPLICATION_FORM_URLENCODED)
		  				.content(objectMapper.writeValueAsString(dto)))
		  			.andDo(print());
		  	
		  //then		
		  result.andExpect(redirectedUrl("/task/list"));
		  result.andExpect(view().name("redirect:/task/list"));
		  //모델 확인 테스트
		  //result.andExpect(model().attributeExists("test"));
	  }
	  
	  @Test
	  public void 변환() {
		  LocalDateTime date = LocalDateTime.parse("2014-02-18T12:10", DateTimeFormatter.ISO_DATE_TIME);
	        System.out.println(date);
	  }
	
	  
		  

}
