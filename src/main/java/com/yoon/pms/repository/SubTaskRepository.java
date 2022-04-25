package com.yoon.pms.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yoon.pms.entity.SubTask;

@Repository
public interface SubTaskRepository extends  JpaRepository<SubTask, Long>{



}
