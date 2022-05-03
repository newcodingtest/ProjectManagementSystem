package com.yoon.pms.dto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;  

import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.yoon.pms.entity.Task;

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
				.taskStartDate("2022-03-08T10:10")
				.build();
		
		//when
		LocalDateTime expectedDate = TaskDTO.stringToLocalDateTime(dto.getTaskStartDate());
		System.out.println(expectedDate);
		//then
	}
	
	@Test
	@DisplayName("nullable한_DTO값도_엔티티로변환된다.")
	public void nullable한_DTO값을_엔티티로변환하자() {
		TaskDTO dto = TaskDTO.builder()
				.tid(1L)
				.taskTitle("TEST")
				.taskContents(null)
				.taskStartDate("2022-03-08T10:10")
				.taskEndDate("2022-03-08T10:10")
				.build();
		
		Task result = TaskDTO.dtoToEntity(dto);
		System.out.println(result);
		assertThat(result.getTaskContents()).isNull();
	}
	
	
	
}
