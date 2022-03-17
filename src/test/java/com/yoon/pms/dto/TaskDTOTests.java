package com.yoon.pms.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit4.SpringRunner;

@ExtendWith(MockitoExtension.class)
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
	
	@Test
	@DisplayName("StringToLocalDateTime 테스트")
	public void 날짜변환테스트() {
		//given
		TaskDTO dto = TaskDTO.builder()
				.taskStartDate("2022-03-08")
				.build();
		
		//when
		LocalDateTime expectedDate = dto.stringToLocalDateTime(dto.getTaskStartDate());
		System.out.println(expectedDate);
		//then
	
	}
	
	
}
