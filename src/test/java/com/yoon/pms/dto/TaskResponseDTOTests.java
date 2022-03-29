package com.yoon.pms.dto;

import java.util.Arrays; 
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.yoon.pms.TaskFactory;
import com.yoon.pms.entity.Task;

public class TaskResponseDTOTests {
	
	@Test
	@DisplayName("List<Entity타입>를 List<DTO타입>로 변환")
	void entity리스트를_DTO리스트로_변환하자() {
		Task givenEntity = TaskFactory.makeTaskEntity();
		
		List<Task> arr = Arrays.asList(givenEntity,givenEntity,givenEntity);
		
		List<TaskDTO> expected = TaskResponseDTO.ListEntityToDto(arr);
		
		Assertions.assertThat(expected.get(0)).isEqualTo(TaskDTO.entityToDTO(givenEntity));
		
	}

}
