package com.yoon.pms.repository;

import javax.persistence.Column;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yoon.pms.entity.Task;
import com.yoon.pms.entity.WeekReport;


public interface TaskRepository extends  JpaRepository<Task, Long> {
	
	
	
}
