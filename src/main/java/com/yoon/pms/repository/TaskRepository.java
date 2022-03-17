package com.yoon.pms.repository;

import java.util.List;

import javax.persistence.Column;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.yoon.pms.entity.Task;
import com.yoon.pms.entity.WeekReport;


@Repository
public interface TaskRepository extends  JpaRepository<Task, Long> {
	/*
	 * @Query("select t from Task t join fetch t.project where t.statusCode= :statusCode "
	 * ) List<Task>getListByStatusCode(@Param("statusCode") int statusCode);
	 */

}
