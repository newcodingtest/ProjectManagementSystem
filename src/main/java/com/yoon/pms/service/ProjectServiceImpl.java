package com.yoon.pms.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	@Transactional
	public void modify(ProjectDTO dto) {
		Optional<Project> origin =projectRepository.findById(dto.getPid());
		
		origin.ifPresent(target ->{
			Project updated = ProjectDTO.dtoToEntity(dto);
			
			projectRepository.save(updated);
		});

	}

	@Override
	@Transactional
	public void remove(Long id) {
		Optional<Project> origin =projectRepository.findById(id);
		origin.orElseThrow(NoSuchElementException::new);
		
		projectRepository.deleteById(id);
		
	}

	@Override
	public ProjectResponseDTO getProjectListByStatusCode(String status) {
		
		ProjectResponseDTO dto = new ProjectResponseDTO();
		List<Project> target = null;
		
		if(status.equals("전체")) {
			target = projectRepository.getProjectListAll();
		}else {
			target = projectRepository.getProjectListByStatusCode(status);
		}
		
		List<ProjectDTO> result =  ProjectResponseDTO.ListEntityToDto(target);
		dto.setData(result);
		
		return dto;
		
	}

	@Override
	public ProjectDTO getProjectOneById(Long id) {
		
		 Optional<Project> target = projectRepository.findById(id);
		 
		return ProjectDTO.entityToDTO(target.get());
	}

}
