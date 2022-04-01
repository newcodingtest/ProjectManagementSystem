package com.yoon.pms.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.yoon.pms.ProjectFactory;
import com.yoon.pms.entity.Project;

@ExtendWith(MockitoExtension.class)
public class ProjectDTOTests {

	@Test
	void DTO_생성된다() {
		ProjectDTO dto = ProjectDTO.builder()
	    		 .projectTitle("프로젝트 제목 생성")
	    		 .projectNickname("프로젝트 닉네임 생성")
	    		 .startDate("2022-03-08T10:10")
	    		 .endDate("2022-03-08T10:10")
	    		 .statusCode("진행전")
	    		 .manager("윤주영")
	    		 .build();
				
		Assertions.assertEquals(dto.getProjectTitle(), "프로젝트 제목 생성");
		Assertions.assertEquals(dto.getProjectNickname(), "프로젝트 닉네임 생성");
		Assertions.assertEquals(dto.getStartDate(), "2022-03-08T10:10");
		Assertions.assertEquals(dto.getEndDate(), "2022-03-08T10:10");
		Assertions.assertEquals(dto.getStatusCode(), "진행전");
		Assertions.assertEquals(dto.getManager(), "윤주영");
	}
	
	@Test
	void DTO가_엔티티타입_으로_변경된다() {
		ProjectDTO givenDTO = ProjectFactory.makeProjectkDTO();
		
		Project expected = ProjectDTO.dtoToEntity(givenDTO);
		
		assertThat(expected).isExactlyInstanceOf(Project.class);
	}
	
}
