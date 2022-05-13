package com.yoon.pms.entity;

import java.time.LocalDateTime; 
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.lang.Nullable;

import com.yoon.pms.dto.SubTaskDTO;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Table
@Entity
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@ToString
@DynamicUpdate
@DynamicInsert 
public class Task extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="TASK_ID")
	private Long tid;
	
	@Column
	@Comment("업무 종류 --> 개발/업무제안/관리/기타")
	private String taskType; 
	
	@Column
	@Comment("상세 업무 -->개발/버그수정/산출물/테스트/휴가/기타 등등")
	private String detailedTaskType; 
	
	@Column
	@Comment("업무 구분 --> 주/지원")
	private String divisionOfTask; 
	
	@Column
	@Nullable
	@Comment("업무 내용")
	private String taskContents; 
	
	@Column
	@Comment("업무 제목")
	private String taskTitle; 
	
	@Column
	@Comment("작성자 아이디")
	private String writer; 
	
	@Column
	@Comment("비고")
	private String remarks; //비고
	
	@Column
	@Comment("업무 시작일")
	private LocalDateTime taskStartDate; 
	
	@Column
	@Comment("업무 종료일")
	private LocalDateTime taskEndDate;  
	
	@Column
	@Comment(" 실제 진행률 --> 1%,50%,100%짜")
	private float realProgress; // 
	
	@Column
	@Comment(" 배치가 돌아서 저장된 날짜")
	private LocalDateTime savedWeekDate; 
	
	@Column(nullable = false)
	@Convert(converter = GenderAttributeConverter.class)
	@Comment("진행 상태 --> 진핸전/진행중/완료/중단")
	private String statusCode; 
	
	@Column
	private Long parent; // --> 부모 작업 /자식작업 구분 키
	
	@Column
	@Comment("보고서 등록 여부")
	private String reportRegistFlag;
	
	/*
		자식 엔티티의 변경이 있다면, JPA에서 자식엔티티 수정은 
		insert -> update -> delete 순으로 이어지는데,  변경된 자식을 먼저 insert하고, 기존 자식을 NULL로 update 한다.
		여기서 , orphanRemoval = true로 설정하면 NULL로 처리된 자식을 DELETE 한다.
	 */
	@OneToMany(mappedBy = "task", fetch = FetchType.LAZY,  cascade = {CascadeType.ALL}, orphanRemoval = true)
	private List<SubTask> subTaskList = new ArrayList<SubTask>();
	
	///@ManyToOne(fetch = FetchType.LAZY)
	//@JoinColumn(name="PROJECT_ID")
	@Column
	private Long projectId; 

	public void deleteSubTask() {
		this.getSubTaskList().clear();
	}
	
	public void addSubTaskList(SubTask subTask) {
		subTaskList.add(subTask);
		subTask.setTask(this);
	}
	
	public void changeStartDate(LocalDateTime startDate) {
		this.taskStartDate = startDate;
	}
	
	public void changeEndDate(LocalDateTime endDate) {
		this.taskEndDate = endDate;
	}
	
	public void changeRealProgress(float realProgress) {
		this.realProgress = realProgress;
	}
	/*
	 * public void setProject(Project projects) { if(this.projects!=null) {
	 * this.projects.getTask().remove(this); } this.projects = projects;
	 * projects.getTask().add(this); }
	 */


}



