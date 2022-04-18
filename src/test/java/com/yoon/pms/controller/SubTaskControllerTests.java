package com.yoon.pms.controller;

import static org.assertj.core.api.Assertions.assertThat; 

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yoon.pms.TaskFactory;
import com.yoon.pms.config.QuerydslConfig;
import com.yoon.pms.dto.SubTaskDTO;
import com.yoon.pms.service.SubTaskService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@MockBean(JpaMetamodelMappingContext.class)
public class SubTaskControllerTests {
	
    @LocalServerPort
    private int port;

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private SubTaskService service;
	
	@Autowired
	private QuerydslConfig config;
	
	@Autowired
	protected ObjectMapper objectMapper;
	
	@Test
	public void 서브테스트_post가_된다() throws Exception {
		//Given
		String url = "http://localhost:"+this.port+"/subtask/1";
		
		SubTaskDTO dto = TaskFactory.makeSubTaskDTO();
		
		String[] tid = {"1","1"};
		String[] title = {"첫번째제목","두번째제목"};
		String[] contents = {"첫번째내용","두번째내용"};
		String[] startDate = {"2022-03-08T10:10","2022-03-08T10:10"};
		String[] endDate = {"2022-03-08T10:10","2022-03-08T10:10"};
		String[] realProgress = {"0","0"};
		String[] ReportRegistFlag = {"0","1"};
		
		Map<String, String[]> request = new HashMap();
		
		request.put("tid", tid);
		request.put("subTitle", title);
		request.put("subContents", contents);
		request.put("subStartDate", startDate);
		request.put("subEndDate", endDate);
		request.put("subRealProgress", realProgress);
		request.put("subReportRegistFlag", ReportRegistFlag);
		
		
		//When
		ResponseEntity<Long>responseEntity = this.restTemplate.postForEntity(url, request,null);
		//then
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
	}
	
}
