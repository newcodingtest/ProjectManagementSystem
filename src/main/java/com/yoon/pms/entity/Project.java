package com.yoon.pms.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Getter
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
	private String remarks;
	
	@Column(nullable = false)
	private int status;
	
	@Column
	private String manager;
	
	
	/*
	 * @OneToMany(mappedBy = "projects") private List<Task> task = new ArrayList();
	 */
	
	public void changeMananger(String name) {
		this.manager = name;
	}
	
	
		
}
