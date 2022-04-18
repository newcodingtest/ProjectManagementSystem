package com.yoon.pms.repository;

import java.util.List;

import com.yoon.pms.entity.Project;

public interface ProjectRepositoryCustom{
	
	List<Project> getProjectListByStatusCode();

}
