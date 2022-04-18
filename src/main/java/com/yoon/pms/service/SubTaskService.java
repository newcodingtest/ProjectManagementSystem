package com.yoon.pms.service;

import java.util.Map;

import com.yoon.pms.dto.SubTaskDTO;

public interface SubTaskService {
	
	/**
	 * 하위 작업 등록
	 * */
	void register(Map<String, String[]> map);
	

}
