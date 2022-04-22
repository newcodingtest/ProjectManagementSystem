package com.yoon.pms.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.yoon.pms.entity.Task;

@Repository
public interface TaskRepository extends  JpaRepository<Task, Long>, TaskRepositoryCustom{


	@Query("select distinct t from Task t join fetch t.subTaskList where t.tid=:tid ")
	Task getTaskListWithAll(@Param(value = "tid") Long tid);
	
	
}
