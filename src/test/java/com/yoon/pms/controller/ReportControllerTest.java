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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.yoon.pms.service.WeekReportService;


@RunWith(SpringRunner.class)
@WebMvcTest(controllers = {ReportController.class})
public class ReportControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private WeekReportService weekReportService;
	
	
	  @Test
	  @DisplayName("첫번째 HI 컨트롤러 테스트")
	  public void hi_문자열_반환_테스트() throws Exception{
		  
		  //given
	      String hi = "hi";
	        
	      //andExpect
	      mvc.perform(get("/test"))
	         .andExpect(status().isOk())
	         .andExpect(content().string(hi));
	   }
	  
	  @Test
	  @DisplayName("주간보고서 날짜 가져오기 테스트")
	  public void 날짜_반환_테스트() throws Exception {
		  
		  //given
		  String testDate = "20220202";
		  
		  //andExpect
		  mvc.perform(get("/report/team/"+testDate))
		  	.andExpect(status().isOk())
		  	.andDo(print());
	  }
}
