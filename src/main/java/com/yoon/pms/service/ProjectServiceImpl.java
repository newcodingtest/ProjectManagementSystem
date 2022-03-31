package com.yoon.pms.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.yoon.pms.dto.ProjectDTO;
import com.yoon.pms.dto.ProjectResponseDTO;
import com.yoon.pms.entity.Project;
import com.yoon.pms.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
	
	private final ProjectRepository projectRepository;
	
	@Override
	public long register(ProjectDTO dto) {
		
		Project target = ProjectDTO.dtoToEntity(dto);
		
		Project savedProject = projectRepository.save(target);
		
		return savedProject.getId();
	}

	@Override
	public void modify(ProjectDTO dto) {
		Optional<Project> origin =projectRepository.findById(dto.getPid());
		
		origin.ifPresent(target ->{
			Project updated = projectRepository.save(target);
		});

	}

	@Override
	public void remove(Long id) {
		Optional<Project> origin =projectRepository.findById(id);
		origin.orElseThrow(NoSuchElementException::new);
		
		projectRepository.deleteById(id);
		
	}

	@Override
	public ProjectResponseDTO getProjectListByStatusCode() {
		
		ProjectResponseDTO dto = new ProjectResponseDTO();
		List<Project> target = projectRepository.getProjectListByStatusCode();
		
		List<ProjectDTO> result =  dto.ListEntityToDto(target);
		dto.setData(result);
		
		return dto;
		
	}

}
