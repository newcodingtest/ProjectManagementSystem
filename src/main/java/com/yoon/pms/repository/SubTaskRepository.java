package com.yoon.pms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yoon.pms.entity.SubTask;
import com.yoon.pms.entity.Task;

@Repository
public interface SubTaskRepository extends  JpaRepository<SubTask, Long>{



}