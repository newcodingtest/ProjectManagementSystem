package com.yoon.pms.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.yoon.pms.entity.SubTask;

@Repository
public interface SubTaskRepository extends  JpaRepository<SubTask, Long>{
	
	@Modifying 
	@Query("DELETE FROM SubTask st WHERE st.task.tid= :tid")
	void deleteByTid(@Param(value = "tid") Long tid);


}
