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

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.lang.Nullable;
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
	private String taskType; //업무 종류 --> 개발/업무제안/관리/기타
	@Column
	private String detailedTaskType; //상세 업무 -->개발/버그수정/산출물/테스트/휴가/기타 등등
	@Column
	private String divisionOfTask; //업무 구분 --> 주/지원
	@Column
	@Nullable
	private String taskContents; // 업무 내용
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
	private float realProgress; // 실제 진행률 --> 1%,50%,100%
	@Column
	private LocalDateTime savedWeekDate; // 배치가 돌아서 저장된 날짜
	
	@Column(nullable = false)
	@Convert(converter = GenderAttributeConverter.class)
	private String statusCode; //진행 상태 --> 진핸전/진행중/완료/중단
	@Column
	private Long parent; // --> 부모 작업 /자식작업 구분 키
	@Column
	private String reportRegistFlag; //--> 보고서 등록 여부
	
	
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

	
	public void addSubTaskList(SubTask subTask) {
		subTaskList.add(subTask);
		subTask.setTask(this);
	}
	/*
	 * public void setProject(Project projects) { if(this.projects!=null) {
	 * this.projects.getTask().remove(this); } this.projects = projects;
	 * projects.getTask().add(this); }
	 */


}



