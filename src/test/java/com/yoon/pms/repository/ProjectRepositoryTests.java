package com.yoon.pms.repository;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.yoon.pms.dto.ProjectDTO;
import com.yoon.pms.entity.Project;
import com.yoon.pms.entity.Task;

@SpringBootTest
public class ProjectRepositoryTests {

	@Autowired
	ProjectRepository repository;

	@Test
	void 읽기_테스트() {
		Optional<Project> createEntity = repository.findById(1L);
		
		createEntity.ifPresent(expectedEntity->{
			
			Assertions.assertThat(expectedEntity.getId()).
				isEqualTo(createEntity.get().getId());
			Assertions.assertThat(expectedEntity.getProjectTitle())
				.isEqualTo(createEntity.get().getProjectTitle());
			Assertions.assertThat(expectedEntity.getProjectNickname())
				.isEqualTo(createEntity.get().getProjectNickname());
			Assertions.assertThat(expectedEntity.getStartDate())
				.isEqualTo(createEntity.get().getStartDate());
			Assertions.assertThat(expectedEntity.getEndDate())
				.isEqualTo(createEntity.get().getEndDate());
		});
	}
	
	@Test
	void 저장_테스트() {
		//given
		Project createEntity = makeEntity();
		
		//when
		Project savedEntity= repository.save(createEntity);
		
		//then
		Assertions.assertThat(savedEntity.getId())
			.isEqualTo(createEntity.getId());
		Assertions.assertThat(savedEntity.getProjectTitle())
			.isEqualTo(createEntity.getProjectTitle());
		Assertions.assertThat(savedEntity.getProjectNickname())
			.isEqualTo(createEntity.getProjectNickname());
		Assertions.assertThat(savedEntity.getStartDate())
			.isEqualTo(createEntity.getStartDate());
		Assertions.assertThat(savedEntity.getEndDate())
			.isEqualTo(createEntity.getEndDate());
		Assertions.assertThat(savedEntity.getManager())
			.isEqualTo(createEntity.getManager());
	}
	
	@Test
	void 수정_테스트() {
		//given. when
		Optional<Project>createEntity = repository.findById(1L);
		
		//then
		createEntity.ifPresent(updateEntity->{
			updateEntity.changeMananger("윤주영수정");
			Project updatedEntity=repository.save(updateEntity);
			
			Assertions.assertThat(updatedEntity.getManager()).isEqualTo(createEntity.get().getManager());
		});	
	}
	
	@Test
	void 삭제_테스트() {
		//given. when
		Optional<Project>entity = repository.findById(2L);
		
		//then
		entity.ifPresent(selectResult->{
			repository.deleteById(entity.get().getId());
			
			Assertions.assertThat(entity.get()).isNull();
		});	
	}
	
	private Project makeEntity() {
		
		ProjectDTO dto = ProjectDTO.builder()
				.projectTitle("테스트")
				.manger("윤주영")
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
