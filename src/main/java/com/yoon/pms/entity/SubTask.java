package com.yoon.pms.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Comment;
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
@ToString(exclude = "task")
@DynamicUpdate
public class SubTask extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="SUB_ID")
	private Long sid;
	
	@Column
	@Comment("하위 작업 제목")
	private String subTitle;
	
	@Column
	@Nullable
	@Comment("하위 작업 내용")
	private String subContents;
	
	@Column
	@Comment("하위 작업 시작일")
	private LocalDateTime subStartDate;
	
	@Column
	@Comment("하위 작업 종료일")
	private LocalDateTime subEndDate;
	
	@Column
	@Comment("실제 진행률 --> 1%,50%,100%")
	private float subRealProgress; 
	
	@Column
	@Comment("보고서 등록 여부")
	private String subReportRegistFlag;
	
	@ManyToOne
	private Task task;
	
	public void setTask(Task task) {
		this.task = task;
	}
}
