package com.yoon.pms.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.yoon.pms.entity.Task;

@Repository
public interface TaskRepository extends  JpaRepository<Task, Long>, TaskRepositoryCustom{

	/**
	 * 내작업 자세히 보기-->상위작업과 그 상위작업에 대한 하위작업의 리스트가 출력
	 * */
	@Query("select distinct t from Task t join fetch t.subTaskList where t.tid=:tid ")
	Task getTaskListWithAll(@Param(value = "tid") Long tid);
	
	
}
