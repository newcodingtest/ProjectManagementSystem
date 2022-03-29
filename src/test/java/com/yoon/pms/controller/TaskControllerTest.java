package com.yoon.pms.controller;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;  
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yoon.pms.TaskFactory;
import com.yoon.pms.config.QuerydslConfig;
import com.yoon.pms.dto.TaskDTO;
import com.yoon.pms.repository.TaskRepository;
import com.yoon.pms.service.TaskService;

@RunWith(SpringRunner.class) 
@WebMvcTest(controllers = {TaskController.class}) 
@MockBean(JpaMetamodelMappingContext.class)
public class TaskControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@MockBean //spring-boot-test 에서 제공하는 어노테이션, 개발자가 직접 반환값을 컨트롤이 가능
	private TaskService taskService;
	
	@MockBean
	private TaskRepository taskRepository;
	
	@MockBean
	private QuerydslConfig config;
	
	@Autowired
	protected ObjectMapper objectMapper;
	
	private ModelAndView mv;
	
	  @Test
	  @DisplayName("list 페이지 테스트")
	  public void list_페이지_이동() throws Exception{
	      //andExpect
	      mvc.perform(get("/task/list").content("application/json"))
	         .andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()))
	      	 .andExpect(MockMvcResultMatchers.model().attributeExists("beforeList"))
	      	 .andExpect(MockMvcResultMatchers.model().attributeExists("ingList"))
	      	 .andExpect(MockMvcResultMatchers.model().attributeExists("endList"))
	      	 .andExpect(MockMvcResultMatchers.model().attributeExists("beforeCnt"))
	      	 .andExpect(MockMvcResultMatchers.model().attributeExists("ingCnt"))
	      	 .andExpect(MockMvcResultMatchers.model().attributeExists("endCnt"));
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
		//Given
		String test = "2022-03-08";
		
		 TaskDTO givenDTO = TaskDTO.builder()
				  .taskTitle("테스트 제목")
				  .statusCode("진행전")
				  .realProgress(3)
				  .reportRegistFlag("2")
				  .pid((long) 1.0)
				  .taskStartDate(test)
				  .taskEndDate(test)
				  .taskType("종류")
				  .detailedTaskType("상세")
				  .divisionOfTask("분류")
				  .remarks("비고")
				  .build();
		  
		  //When
		  	final ResultActions result=	mvc.perform(post("/task/register")
		  				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				  .param("taskTitle", givenDTO.getTaskTitle())
				  .param("statusCode",String.valueOf(givenDTO.getStatusCode()))
				  .param("realProgress", String.valueOf(givenDTO.getRealProgress()))
				  .param("reportRegistFlag", String.valueOf(givenDTO.getReportRegistFlag()))
				  .param("projectId", String.valueOf(givenDTO.getPid()) )
				  .param("taskStartDate", givenDTO.getTaskStartDate())
				  .param("taskEndDate",givenDTO.getTaskEndDate())
				  .param("taskType", String.valueOf(givenDTO.getTaskType()))
				  .param("detailedTaskType", String.valueOf(givenDTO.getDetailedTaskType()))
				  .param("divisionOfTask", String.valueOf(givenDTO.getDivisionOfTask()))
				  .param("remarks", String.valueOf(givenDTO.getRemarks())))
		  		.andDo(print());
		  	
		  	
		  //Then		
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
		
		  //Given
		  TaskDTO givenDTO = TaskDTO.builder()
				  .taskTitle("테스트 제목")
				  .statusCode("진행전")
				  .realProgress(3)
				  .reportRegistFlag("2")
				  .pid((long)1)
				  .taskStartDate(test)
				  .taskEndDate(test)
				  .taskType("종류")
				  .detailedTaskType("상세")
				  .divisionOfTask("분류")
				  .remarks("비고")
				  .build();
		  
		  //When
		  	final ResultActions result=	mvc.perform(post("/task/register")
		  				.contentType(MediaType.APPLICATION_JSON)
		  				.accept(MediaType.APPLICATION_FORM_URLENCODED)
		  				.content(objectMapper.writeValueAsString(givenDTO)))
		  			.andDo(print());
		  	
		  //Then		
		  result.andExpect(redirectedUrl("/task/list"));
		  result.andExpect(view().name("redirect:/task/list"));
		  //모델 확인 테스트
		  //result.andExpect(model().attributeExists("test"));
	  }  
	  
	  @Test
	  @DisplayName("수정 테스트")
	  public void modify_수정된다() throws Exception {
		//Given
		  TaskDTO givenDTO = TaskFactory.makeTaskDTO();
		  
		//When
		  final ResultActions result = mvc.perform(post("/task/modify")
				  .accept(MediaType.APPLICATION_FORM_URLENCODED)
				  .param("tid", String.valueOf(givenDTO.getTid()))
				  .param("taskTitle", givenDTO.getTaskTitle())
				  .param("statusCode",givenDTO.getStatusCode())
				  .param("realProgress", String.valueOf(givenDTO.getRealProgress()))
				  .param("reportRegistFlag", givenDTO.getReportRegistFlag())
				  .param("projectId", String.valueOf(givenDTO.getPid())) 
				  .param("taskStartDate", givenDTO.getTaskStartDate())
				  .param("taskEndDate",givenDTO.getTaskEndDate())
				  .param("taskType", givenDTO.getTaskType())
				  .param("detailedTaskType", givenDTO.getDetailedTaskType())
				  .param("divisionOfTask", givenDTO.getDivisionOfTask())
				  .param("remarks", givenDTO.getRemarks())
				  ).andDo(print()); 
		//then
		result.andExpect(redirectedUrl("/task/list"));
		  result.andExpect(view().name("redirect:/task/list"));
		
	  }
	  
	  @Test
	  @DisplayName("삭제 테스트")
	  public void remove_삭제된다() throws Exception {
		//Given
		  TaskDTO givenDTO = TaskFactory.makeTaskDTO();
		  
		//When
		  final ResultActions result = mvc.perform(post("/task/remove")
				  .accept(MediaType.APPLICATION_FORM_URLENCODED)
				  .param("tid", String.valueOf(givenDTO.getTid()))
				  .param("taskTitle", givenDTO.getTaskTitle())
				  .param("statusCode",givenDTO.getStatusCode())
				  .param("realProgress", String.valueOf(givenDTO.getRealProgress()))
				  .param("reportRegistFlag", givenDTO.getReportRegistFlag())
				  .param("projectId", String.valueOf(givenDTO.getPid())) 
				  .param("taskStartDate", givenDTO.getTaskStartDate())
				  .param("taskEndDate",givenDTO.getTaskEndDate())
				  .param("taskType", givenDTO.getTaskType())
				  .param("detailedTaskType", givenDTO.getDetailedTaskType())
				  .param("divisionOfTask", givenDTO.getDivisionOfTask())
				  .param("remarks", givenDTO.getRemarks())
				  ).andDo(print()); 
		//Then
		  result.andExpect(redirectedUrl("/task/list"));
		  result.andExpect(view().name("redirect:/task/list"));
	  }
	  

}
