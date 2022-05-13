package com.yoon.pms.repository.impl;

import java.util.List;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.yoon.pms.entity.Project;
import com.yoon.pms.entity.QProject;
import com.yoon.pms.entity.QTask;
import com.yoon.pms.entity.Task;
import com.yoon.pms.repository.ProjectRepositoryCustom;
import com.yoon.pms.repository.TaskRepositoryCustom;
import lombok.RequiredArgsConstructor;


public class ProjectRepositoryImpl implements ProjectRepositoryCustom {

	private final JPAQueryFactory queryFactory;
	
	public ProjectRepositoryImpl(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}
	
	QProject project = QProject.project;

	@Override
	public List<Project> getProjectListByStatusCode(String status) {
		return queryFactory.select(project)
				.from(project)
				.where(project.statusCode.eq(status))
				.fetch();
	}
	
	@Override
	public List<Project> getProjectListAll() {
	
		return queryFactory.select(project)
				.from(project)
				.fetch();
	}

}
