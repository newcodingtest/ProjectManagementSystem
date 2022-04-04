package com.yoon.pms.controller;

import static org.assertj.core.api.Assertions.assertThat;

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
import org.springframework.test.web.servlet.MockMvc;

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
		
		//When
		ResponseEntity<Long>responseEntity = this.restTemplate.postForEntity(url, dto, Long.class);
		//then
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(responseEntity.getBody()).isEqualTo(dto.getSid());
	}
	
}
