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
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long tid;
	@Column
	private Long projectId;  //������Ʈ ���̵�
	
	@Column
	private String taskType; //���� ���� --> ����/��������/����/��Ÿ
	@Column
	private String detailedTaskType; //�� ���� -->����/���׼���/���⹰/�׽�Ʈ/�ް�/��Ÿ ���
	@Column
	private String divisionOfTask; //���� ���� --> ��/����
	@Column
	private String Contents; // ���� ����
	@Column
	private String taskTitle; //���� ����
	@Column
	private String writer; //�ۼ��� ���̵�
	@Column
	private String remarks; //���
	
	@Column
	private LocalDateTime taskStartDate; //���� ������
	@Column
	private LocalDateTime taskEndDate;  //���� ������
	@Column
	private float realProgress; // ���� ����� --> 1%,50%,100%
	@Column
	private LocalDateTime savedWeekDate; // ��ġ�� ���Ƽ� ����� ��¥
	
	@Column
	private int progressState; //���� ���� --> ������/������/�Ϸ�/�ߴ�
	@Column
	private Long parent; // --> �θ� �۾� /�ڽ��۾� ���� Ű
	@Column
	private int reportRegistFlag; //--> ���� ��� ����
	
}
