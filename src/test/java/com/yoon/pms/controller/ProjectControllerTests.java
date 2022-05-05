package com.yoon.pms.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yoon.pms.TaskFactory;
import com.yoon.pms.config.QuerydslConfig;
import com.yoon.pms.config.TestConfig;
import com.yoon.pms.dto.ProjectDTO;
import com.yoon.pms.dto.SubTaskDTO;
import com.yoon.pms.repository.ProjectRepository;
import com.yoon.pms.service.ProjectService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = {ProjectController.class})
@MockBean(JpaMetamodelMappingContext.class) //JPA 테스트 추가
public class ProjectControllerTests {

	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean //spring-boot-test 에서 제공하는 어노테이션, 개발자가 직접 반환값을 컨트롤이 가능
	private ProjectService projectService;
	
	@MockBean
	private ProjectRepository projectRepository;
	
	@MockBean
	private TestConfig config;
	
	
	  @Test
	  public void 프로젝트_list_page로_이동한다() throws Exception{
	        
	      //andExpect
	      mvc.perform(get("/project/list"))
	         .andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()));
	   }
	  
	  @Test
	  @DisplayName("api가 요구하는 스펙대로 리턴만 해주면 알아서 json으로 받는다.")
	  public void 외부api에_JSON을_던진다() throws Exception{
		  //예상 json
		  String expected = "{\"data\":[{\"pid\":2,\"manager\":\"윤주영 수정\",\"projectNickname\":\"프로젝트 닉네임 수정\",\"projectTitle\":\"프로젝트 제목 수정\",\"content\":null,\"remarks\":null,\"statusCode\":\"진행전\",\"startDate\":\"2022-12-03T10:15\",\"endDate\":\"2022-12-03T10:15\"}]}";
		  
		  ProjectDTO dto = ProjectDTO.builder()
				  .pid(1L)
				  .projectTitle("테스트")
				  .projectNickname("테스트")
				  .contents("테스트")
				  .startDate("2022-03-08T10:10")
				  .endDate("2022-03-08T10:10")
				  .remarks("테스트")
				  .statusCode("진행전")
				  .build();
				  
		  List<ProjectDTO> data = new ArrayList<>();
			data.add(dto);	  
			data.add(dto);	  
				  
	      //andExpect
		   mvc.perform(post("/project/sheet/list")
				  //전달된 콘텐츠 타입 검증
				  .accept(MediaType.APPLICATION_FORM_URLENCODED)
				  .contentType(MediaType.APPLICATION_JSON)
				  .content(objectMapper.writeValueAsString(data)))
				  .andExpect(status().isOk())
				  .andDo(print());

   }
	  
	  @Test
	  public void 프로젝트_등록한다() throws Exception {
		  List<SubTaskDTO>list = TaskFactory.makeSubTaskDTOList();
		  
		  final ResultActions result = mvc.perform(post("/project/register")
				  .param("manager", "윤주영")
				  .param("projectTitle", "테스트 제목")
				  .param("projectNickname", "TEST")
				  .param("content", "테스트 내용")
				  .param("remarks", "테스트 비고")
				  .param("statusCode", "진행중")
				  .param("startDate", "2022-03-08T10:10")
				  .param("endDate", "2022-03-08T10:10")
				  .param("subTaskDTOList",list.toString())
				  .accept(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
				  ).andDo(print());

		  result.andExpect(redirectedUrl("/project/list"));
		  result.andExpect(view().name("redirect:/project/list"));
				  
	  }
	  
	  @Test
	  public void 프로젝트_수정한다() throws Exception {
		  final ResultActions result = mvc.perform(post("/project/modify")
				  .param("pid", "2")
				  .param("manager", "윤주영")
				  .param("projectTitle", "테스트 제목")
				  .param("projectNickname", "TEST")
				  .param("contents", "테스트 내용")
				  .param("remarks", "테스트 비고")
				  .param("statusCode", "진행중")
				  .param("startDate", "2022-03-08T10:10")
				  .param("endDate", "2022-03-08T10:10")
				  .accept(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
				  ).andDo(print());

		  result.andExpect(redirectedUrl("/project/list"));
		  result.andExpect(view().name("redirect:/project/list"));
	  }
	  
	  @Test
	  public void 프로젝트_삭제한다() throws Exception {
		  
		  final ResultActions result = mvc.perform(post("/project/remove")
				  .param("pid", "2")
				  .accept(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
				  ).andDo(print());

		  result.andExpect(redirectedUrl("/project/list"));
		  result.andExpect(view().name("redirect:/project/list"));
	  }
	  
}
