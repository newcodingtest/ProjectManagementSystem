package com.yoon.pms.repository.impl;

import java.util.List;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.yoon.pms.entity.QTask;
import com.yoon.pms.entity.Task;
import com.yoon.pms.repository.TaskRepositoryCustom;
import lombok.RequiredArgsConstructor;


public class TaskRepositoryImpl implements TaskRepositoryCustom {

	private final JPAQueryFactory queryFactory;
	
	public TaskRepositoryImpl(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}
	
	QTask task = QTask.task;
	
	@Override
	public long getTaskList() {
	
		return queryFactory.select(task.count())
				.from(task).fetchCount();
	}

	@Override
	public List<Task> getNotStartList() {
		return queryFactory.select(task)
				.from(task).where(task.statusCode.eq("진행전"))
				.fetch();
	}

	@Override
	public List<Task> getOnGoingList() {
		return queryFactory.select(task)
				.from(task).where(task.statusCode.eq("진행중"))
				.fetch();
	}

	@Override
	public List<Task> getEndedList() {
		return queryFactory.select(task)
				.from(task).where(task.statusCode.eq("완료"))
				.fetch();
	}
	

}
