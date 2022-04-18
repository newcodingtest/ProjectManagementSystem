package com.yoon.pms.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.yoon.pms.TaskFactory;
import com.yoon.pms.dto.SubTaskDTO;
import com.yoon.pms.entity.SubTask;
import com.yoon.pms.repository.SubTaskRepository;

@ExtendWith(MockitoExtension.class)
public class SubTaskServiceTests {

	@InjectMocks
	SubTaskServiceImpl service;
	
	@Mock
	SubTaskRepository repository;
	
	@Test
	@DisplayName("test")
	void 서브테스트_등록테스트() {
		//Given
		SubTask givenEntity = TaskFactory.makeSubTaskEntity();
		SubTaskDTO givenDTO = TaskFactory.makeSubTaskDTO();
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
		
		//Mocking
		BDDMockito
			.given(repository.save(any()))
			.willReturn(givenEntity);
			
		//when
		service.register(request);
		
		//Then
		BDDMockito.verify(repository, times(2)).save(any());
		
	}
	
}
