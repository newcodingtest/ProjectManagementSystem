package com.yoon.pms.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class TaskDTOTests {


	@Test
	@DisplayName("TaskDTO 생성 테스트")
	public void DTO_생성_테스트() {
		
		//given
		TaskDTO dto = TaskDTO.builder()
				.taskTitle("테스트제목")
				.build();
		
		//andExpect
		assertEquals(dto.getTaskTitle(), "테스트제목");
	}
	
	
}
