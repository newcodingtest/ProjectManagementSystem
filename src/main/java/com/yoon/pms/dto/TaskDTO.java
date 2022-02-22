package com.yoon.pms.dto;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class TaskDTO {

	private Long tid;
	 
	private Long projectId;  //������Ʈ ���̵�
	 
	private String taskType; //���� ���� --> ����/��������/����/��Ÿ
	 
	private String detailedTaskType; //�� ���� -->����/���׼���/���⹰/�׽�Ʈ/�ް�/��Ÿ ���
	 
	private String divisionOfTask; //���� ���� --> ��/����
	private String Contents; // ���� ����
	private String taskTitle; //���� ����
	 
	private String writer; //�ۼ��� ���̵�
	 
	private String remarks; //���
	
	 
	private LocalDateTime taskStartDate; //���� ������
	 
	private LocalDateTime taskEndDate;  //���� ������
	 
	private float realProgress; // ���� ����� --> 1%,50%,100%
	 
	private LocalDateTime savedWeekDate; // ��ġ�� ���Ƽ� ����� ��¥
	 
	private int progressState; //���� ���� --> ������/������/�Ϸ�/�ߴ�
	 
	private Long parent; // --> �θ� �۾� /�ڽ��۾� ���� Ű
	 
	private int reportRegistFlag; //--> ���� ��� ����
	
}
