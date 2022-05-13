package com.yoon.pms.service;

import java.util.List;
import com.yoon.pms.dto.TaskDTO;

public interface TaskService {
	
	/**
	 * 내 작업 등록
	 * */
	Long register(TaskDTO dto);
	
	/**
	 * 내 작업 1개 출력
	 * */
	TaskDTO getTaskOne(Long id);
	
	/**
	 * 내 작업 수정(하위작업만)-->하위 작업으로 인해 현재 사용하지 않음
	 * */
	Long modify(TaskDTO dto);
	
	/**
	 * 내 작업 수정(하위작업과 함께)
	 * */
	Long modifyTaskWithSub(TaskDTO requestDto);
	
	/**
	 * 내 작업 삭제(상위작업 삭제시, 하위작업도 삭제됨)
	 * */
	void remove(Long tid);
	
	/**
	 * 진행도가 "진행전"인 내작업 리스트 출력
	 * */
	List<TaskDTO> getStatusBeforeList();
	
	/**
	 * 진행도가 "진행중"인 내작업 리스트 출력
	 * */
	List<TaskDTO> getStatusIngList();
	
	/**
	 * 진행도가 "완료"인 내작업 리스트 출력
	 * */
	List<TaskDTO> getStatusEndList();
	

	
}
