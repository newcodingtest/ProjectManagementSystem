package com.yoon.pms.dto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;  

import java.time.LocalDateTime;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.NotEmpty;

import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.yoon.pms.entity.Task;

@ExtendWith(MockitoExtension.class)
public class TaskDTOTests {
	
	private static Validator validator;
	
	@BeforeAll
	public static void setupValidatorInstance() {
		  validator = Validation.buildDefaultValidatorFactory().getValidator();
	}
	
	
	
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
	
	
	@Test
	@DisplayName("제목을 NULL 기입할때 NotNull 체크")
	public void 제목_DTO_NotNull_체크() {
		//given
		TaskDTO dto = TaskDTO.builder()
				.tid(1L)
				.taskTitle(null)
				.taskContents("test")
				.taskStartDate("2022-03-08T10:10")
				.taskEndDate("2022-03-08T10:10")
				.build();
		
		//when
		Set<ConstraintViolation<TaskDTO>> violations = validator.validate(dto);
		
		//then
		assertThat(violations.size()).isEqualTo(3);
	}
	
	@Test
	@DisplayName("날짜를 NULL 기입할때 NotNull 체크")
	public void 날짜_DTO_NotNull_체크() {
		//given
		TaskDTO dto = TaskDTO.builder()
				.taskTitle("test")
				.taskContents("test")
				.taskStartDate(null)
				.taskEndDate(null)
				.build();
		
		//when
		Set<ConstraintViolation<TaskDTO>> violations = validator.validate(dto);
		
		//then
		assertThat(violations.size()).isEqualTo(6);
	}
	
	@Test
	@DisplayName("날짜를 단순 띠어쓰기 기입할때 NotEmpty 체크")
	public void 날짜_DTO_NotEmpty_체크() {
		//given
		TaskDTO dto = TaskDTO.builder()
				.taskTitle("test")
				.taskContents("test")
				.taskStartDate(" ")
				.taskEndDate(" ")
				.build();
		
		//when
		Set<ConstraintViolation<TaskDTO>> violations = validator.validate(dto);
		
		//then
		assertThat(violations.size()).isEqualTo(2);
	}
	
	
	
	
}
