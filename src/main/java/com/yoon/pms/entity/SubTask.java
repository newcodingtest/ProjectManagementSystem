package com.yoon.pms.entity;

import java.time.LocalDateTime; 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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
@ToString(exclude = "task")
@DynamicUpdate
public class SubTask extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="SUB_ID")
	private Long sid;
	
	@Column
	private String subTitle;
	@Column
	@Nullable
	private String subContents;
	@Column
	private LocalDateTime subStartDate;
	@Column
	private LocalDateTime subEndDate;
	@Column
	private float subRealProgress; // 실제 진행률 --> 1%,50%,100%
	@Column
	private String subReportRegistFlag; //--> 보고서 등록 여부
	
	@ManyToOne
	private Task task;
	
	public void setTask(Task task) {
		this.task = task;
	}
}
