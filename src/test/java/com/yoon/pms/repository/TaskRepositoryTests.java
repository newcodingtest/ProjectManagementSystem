package com.yoon.pms.repository;

import java.util.stream.IntStream;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.yoon.pms.entity.Task;


@SpringBootTest
public class TaskRepositoryTests{
	
	@Autowired
	TaskRepository taskRepository;
	
	@Test
	@DisplayName("task 등록 테스트")
	public void task_등록_테스트() {
		IntStream.rangeClosed(1, 10).mapToObj(i -> Task.builder().taskTitle("Task.."+i).build())
		.forEach(task -> {System.out.println("----------------------");
		taskRepository.save(task);
		
		System.out.println("===================");
		});
	}
	
	
	
}
