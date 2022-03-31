package com.yoon.pms.repository;

import java.util.List;
import java.util.Optional; 
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.yoon.pms.ProjectFactory;
import com.yoon.pms.dto.ProjectDTO;
import com.yoon.pms.entity.Project;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class ProjectRepositoryTests {

	@Autowired
	ProjectRepository repository;

	@Test
	void 읽기_테스트() {
		//When
		Optional<Project> target = repository.findById(1L);
		
		//Then
		target.ifPresent(expectedEntity->{
			
			Assertions.assertThat(expectedEntity.getId()).
				isEqualTo(target.get().getId());
			Assertions.assertThat(expectedEntity.getProjectTitle())
				.isEqualTo(target.get().getProjectTitle());
			Assertions.assertThat(expectedEntity.getProjectNickname())
				.isEqualTo(target.get().getProjectNickname());
			Assertions.assertThat(expectedEntity.getStartDate())
				.isEqualTo(target.get().getStartDate());
			Assertions.assertThat(expectedEntity.getEndDate())
				.isEqualTo(target.get().getEndDate());
		});
	}
	
	
	@Test
	void 프로젝트_수정한다() {
		//given. when
		Optional<Project>target = repository.findById(1L);
		
		//then
		target.ifPresent(updateEntity->{
			updateEntity.changeMananger("윤주영수정");
			Project updatedEntity=repository.save(updateEntity);
			
			Assertions.assertThat(updatedEntity.getManager()).isEqualTo(target.get().getManager());
			
		});	
	}
	

	@Test
	void 모든_프로젝트_리스트_가져오기() {
		//Given, When
		List<Project> target = repository.getProjectListByStatusCode();
		
		//Then
		target.forEach(x->{
			Project result = x;
			log.info("result = {}", target);
			
		});
	}
	
	@Test
	void 프로젝트_등록된다() {
		//Given
		Project givenEntity = ProjectFactory.makeProjectEntity();
		
		//When
		Project savedEntity = repository.save(givenEntity);
		
		//Then
		Assertions.assertThat(savedEntity.getId()).isEqualTo(givenEntity.getId());
		Assertions.assertThat(savedEntity.getProjectTitle()).isEqualTo(givenEntity.getProjectTitle());
		Assertions.assertThat(savedEntity.getProjectNickname()).isEqualTo(givenEntity.getProjectNickname());
		Assertions.assertThat(savedEntity.getStatusCode()).isEqualTo(givenEntity.getStatusCode());
		Assertions.assertThat(savedEntity.getStartDate()).isEqualTo(givenEntity.getStartDate());
		Assertions.assertThat(savedEntity.getEndDate()).isEqualTo(givenEntity.getEndDate());
		Assertions.assertThat(savedEntity.getRemarks()).isEqualTo(givenEntity.getRemarks());
		Assertions.assertThat(savedEntity.getManager()).isEqualTo(givenEntity.getManager());
	}
	
	@Test
	void 프로젝트_수정된다() {
		//Given
		Project givenEntity =Project.builder() 
				.id((long)2)
				 .projectTitle("프로젝트 제목 수정")
	    		 .projectNickname("프로젝트 닉네임 수정")
	    		 .startDate(ProjectDTO.stringToLocalDateTime("2022-12-03T10:15"))
	    		 .endDate(ProjectDTO.stringToLocalDateTime("2022-12-03T10:15"))
	    		 .statusCode("진행전")
	    		 .manager("윤주영 수정")
              .build();
		
		//When
		Project savedEntity = repository.save(givenEntity);
		
		//Then
		Assertions.assertThat(savedEntity.getId()).isEqualTo(givenEntity.getId());
		Assertions.assertThat(savedEntity.getProjectTitle()).isEqualTo(givenEntity.getProjectTitle());
		Assertions.assertThat(savedEntity.getProjectNickname()).isEqualTo(givenEntity.getProjectNickname());
		Assertions.assertThat(savedEntity.getStatusCode()).isEqualTo(givenEntity.getStatusCode());
		Assertions.assertThat(savedEntity.getStartDate()).isEqualTo(givenEntity.getStartDate());
		Assertions.assertThat(savedEntity.getEndDate()).isEqualTo(givenEntity.getEndDate());
		Assertions.assertThat(savedEntity.getRemarks()).isEqualTo(givenEntity.getRemarks());
		Assertions.assertThat(savedEntity.getManager()).isEqualTo(givenEntity.getManager());
	}
	
	@Test
	void 삭제_테스트() {
		//given. when
		Optional<Project>target = repository.findById(2L);
		
		//then
		target.ifPresent(selectResult->{
			repository.deleteById(target.get().getId());
			
			Assertions.assertThat(target.get()).isNull();
		});	
	}
	
	
	
	private Project makeEntity() {
		ProjectDTO dto = ProjectDTO.builder()
				.projectTitle("테스트")
				.manager("윤주영")
				.projectNickname("test")
				.startDate("2022-12-03T10:15")
				.endDate("2022-12-03T10:15")
				.build();
		
		Project entity = Project.builder()
				.id(dto.getPid())
				.projectTitle(dto.getProjectTitle())
				.projectNickname(dto.getProjectNickname())
				.startDate(dto.stringToLocalDateTime(dto.getStartDate()))
				.endDate(dto.stringToLocalDateTime(dto.getEndDate()))
				.build();	
		
		return entity;
	}
	
}
