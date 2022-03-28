package com.yoon.pms.repository;

import java.util.List;

import com.yoon.pms.entity.Task;

public interface TaskRepositoryCustom {

	public long getTaskList();
	
	public List<Task> getNotStartList();
	
	public List<Task> getOnGoingList();
	
	public List<Task> getEndedList();
}
