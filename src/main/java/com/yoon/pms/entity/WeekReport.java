package com.yoon.pms.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Table
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Getter
public class WeekReport {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long wid;
	@Column
	private Long projectId;
	@Column
	private Long taskId;
	
	@Column
	private String taskType; //업무 종류 --> 개발/업무제안/관리/기타
	@Column
	private String detailedTaskType; //상세 업무 -->개발/버그수정/산출물/테스트/휴가/기타 등등
	@Column
	private String divisionOfTask; //업무 구분 --> 주/지원
	@Column
	private String contents; // 업무 내용
	@Column
	private String taskTitle; //업무 제목
	@Column
	private String writer; //작성자 아이디
	@Column
	private String remarks; //비고
	
	@Column
	private LocalDateTime taskStartDate; //업무 시작일
	@Column
	private LocalDateTime taskEndDate;  //업무 종료일
	@Column
	private LocalDateTime savedWeekDate; // 배치가 돌아서 저장된 날짜
	
	@Column
	private int progressState; //진행 상태 --> 진핸전/진행중/완료/중단
	@Column
	private Long parent; // --> 부모 작업 /자식작업 구분 키
	@Column
	private int reportRegistFlag; //--> 보고서 등록 여부
	
}
