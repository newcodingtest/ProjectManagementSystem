package com.yoon.pms.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.lang.Nullable;

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
@ToString
@Getter
@DynamicUpdate
@DynamicInsert 
public class Project extends BaseEntity{
	
	@Id
	@Column(name="PROJECT_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String projectTitle;
	
	@Column
	private String projectNickname;
	
	@Column
	private LocalDateTime startDate;
	
	@Column
	private LocalDateTime endDate;
	
	@Column
	private String contents;
	
	@Column
	private String remarks;
	
	@Column(nullable = false)
	@Convert(converter = GenderAttributeConverter.class)
	private String statusCode; //진행 상태 --> 진행전/진행중/완료/중단
	
	@Column
	private String manager;
	
	
	/*
	 * @OneToMany(mappedBy = "projects") private List<Task> task = new ArrayList();
	 */
	
	public void changeMananger(String name) {
		this.manager = name;
	}
	
	
		
}
