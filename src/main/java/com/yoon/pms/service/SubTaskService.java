package com.yoon.pms.service;

import com.yoon.pms.dto.SubTaskDTO;

public interface SubTaskService {
	
	/**
	 * 하위 작업 등록
	 * */
	long register(SubTaskDTO dto);
	

}
