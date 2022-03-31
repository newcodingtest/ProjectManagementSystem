package com.yoon.pms.service;

import com.yoon.pms.dto.ProjectDTO;
import com.yoon.pms.dto.ProjectResponseDTO;

public interface ProjectService {
	
	/**
	 * 프로젝트 등록
	 * */
	long register(ProjectDTO dto);
	
	/**
	 * 프로젝트 수정
	 * */
	void modify(ProjectDTO dto);
	
	/**
	 * 프로젝트 삭제
	 * */
	void remove(Long id);
	
	/**
	 * 상태에 따른 프로젝트 리스트 출력
	 * */
	ProjectResponseDTO getProjectListByStatusCode();

}
