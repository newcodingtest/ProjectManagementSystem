package com.yoon.pms.controller;


import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;   
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yoon.pms.TaskFactory;
import com.yoon.pms.config.TestConfig;
import com.yoon.pms.dto.SubTaskDTO;
import com.yoon.pms.dto.TaskDTO;
import com.yoon.pms.entity.Task;
import com.yoon.pms.repository.SubTaskRepository;
import com.yoon.pms.repository.TaskRepository;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureMockMvc // https://we1cometomeanings.tistory.com/65
public class TaskControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;
	
    @LocalServerPort
    private int port;
    
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private SubTaskRepository subTaskRepository;
	
	@MockBean
	private TestConfig config;
	
	@Autowired
	protected ObjectMapper objectMapper;
	
	@Test
	@DisplayName("list ????????? ?????????")
	@Transactional
	public void list_?????????_??????() throws Exception{
	      //andExpect
	      mvc.perform(get("/task/list"))
	      .andDo(print())
	         .andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()));

	}

	  @Test
	  @DisplayName("??? ????????? DTO?????? ???????????? @valid??? NULL ????????????.")
	  public void ????????????_??????_?????????() throws Exception {
		  //GIVEN
		  String taskTitle = null;
		  String taskStartDate = null;
		  String taskEndDate = null;
		  
		  //WHEN
		  final ResultActions result =  mvc.perform(post("/task/register").contentType(MediaType.APPLICATION_FORM_URLENCODED)
					 .param("taskTitle", taskTitle)
					 .param("taskStartDate", taskStartDate)
					 .param("taskEndDate", taskEndDate));
			
		  //THEN
		  result.andExpect(status().isBadRequest());
			
	  }
	  
	  @Test
	  @DisplayName("register ????????? ?????????")
	  public void register_?????????_??????() throws Exception{
	        
	      //andExpect
	      mvc.perform(get("/task/register"))
	         .andExpect(status().isOk());    
	   }
	  
	  @Test
	  @DisplayName("?????? v1.0 formData post ?????????")
	  public void Posts_????????????() throws Exception {
		//Given
		String test = "2022-03-08";
		
		 TaskDTO givenDTO = TaskDTO.builder()
				  .taskTitle("????????? ??????")
				  .statusCode("?????????")
				  .realProgress(3)
				  .reportRegistFlag("2")
				  .pid((long) 1.0)
				  .taskStartDate(test)
				  .taskEndDate(test)
				  .taskType("??????")
				  .detailedTaskType("??????")
				  .divisionOfTask("??????")
				  .remarks("??????")
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
		  //?????? ?????? ?????????
		  //result.andExpect(model().attributeExists("test"));
	  }
	  
	  @Test
	  @DisplayName("?????? v1.1 JSON post ?????????")
	  public void Posts_????????????1() throws Exception {
		 
		String test = "2022-03-08";
		//LocalDateTime dateTime = LocalDateTime.parse(test,DateTimeFormatter.ISO_DATE);
		
		  //Given
		  TaskDTO givenDTO = TaskDTO.builder()
				  .taskTitle("????????? ??????")
				  .statusCode("?????????")
				  .realProgress(3)
				  .reportRegistFlag("2")
				  .pid((long)1)
				  .taskStartDate(test)
				  .taskEndDate(test)
				  .taskType("??????")
				  .detailedTaskType("??????")
				  .divisionOfTask("??????")
				  .remarks("??????")
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
		  //?????? ?????? ?????????
		  //result.andExpect(model().attributeExists("test"));
	  }  
	  
	  @Test
	  @DisplayName("?????? v1.0 ????????????_????????????_?????????_????????????.")
	  public void modify_????????????() throws Exception {
		  //Given
		  List<SubTaskDTO>subList = new ArrayList<SubTaskDTO>();
		  
		  TaskDTO givenDTO = TaskFactory.makeTaskDTO();
		  SubTaskDTO givenSubDto = TaskFactory.makeSubTaskDTO();
		  
		  subList.add(givenSubDto);
		  subList.add(givenSubDto);
		  subList.add(givenSubDto);
		  
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
				  .param("subTaskDTOList", subList.toString())
				  ).andDo(print()); 
		//then
		  result.andExpect(redirectedUrl("/task/list"));
		  result.andExpect(view().name("redirect:/task/list"));
	  }
	  
	   @Test
	   @DisplayName("?????? v1.1 ????????????_????????????_?????????_????????????.")
		public void ???????????????_???????????????_Rest_api?????????_????????????() throws Exception {
			//Given
			List<SubTaskDTO>requestSubList = new ArrayList<SubTaskDTO>();
			SubTaskDTO requestSubDto = TaskFactory.makeSubTaskDTO();
			
			
			Task saveTask = taskRepository.save(TaskFactory.makeTaskEntity());
			
			Long updateId = saveTask.getTid();
			String expectedTitle = "title ??????";
			String expectedContent = "content ??????";
			
			String url = "http://localhost:"+this.port+"/task/"+updateId;
		
			
			TaskDTO requestTaskDto = 
					TaskDTO.builder()
						.tid(updateId)
						.taskTitle(expectedTitle)
						.taskContents(expectedContent)
						.taskStartDate("2022-03-08T10:10")
						.taskEndDate("2022-03-08T10:10")
						.realProgress(10f)
						.subTaskDTOList(requestSubList)
						.build();
			
			System.out.println(new ObjectMapper().writeValueAsString(requestTaskDto));
			
			HttpEntity<TaskDTO>requestEntity = new HttpEntity<>(requestTaskDto);
			//When
			ResponseEntity<Long>responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Long.class);
			

			responseEntity.status(HttpStatus.OK);
				//then
			  Optional<Task> result = taskRepository.findById(updateId);
			  result.ifPresent(resultTask->{
			  assertThat(resultTask.getTaskTitle()).isEqualTo(expectedTitle);
			  
			  });
			  assertThat(responseEntity.getBody()).isEqualTo(updateId);
		}
	  
	  @Test
	  @DisplayName("?????? ?????????")
	  public void remove_????????????() throws Exception {
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
