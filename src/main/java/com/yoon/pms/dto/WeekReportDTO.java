package com.yoon.pms.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
public class WeekReportDTO {
	
	private int Dayoftheweek;
	
	private List<TaskDTO> getMyDeptReportList;
	
	private LocalDate lastMonday;
	private LocalDate lastSaturday;
	private LocalDate lastSunday;
	
	private LocalDate thistMonday;
	private LocalDate thisSaturday;
	private LocalDate thisSunday;
	
	private LocalDate nexttMonday;
	private LocalDate nextSaturday;
	private LocalDate nextSunday;
	
	public WeekReportDTO() {
		Dayoftheweek = LocalDate.now().getDayOfWeek().getValue();
		createDate();
	}
	
	private void createDate() {
	
		switch (getDayoftheweek()) {
		   case 1: //���� ��¥�� ������ �̶��
			   lastSaturday = LocalDate.now().minusDays(2); //������ �����
			   lastSunday = LocalDate.now().minusDays(1);
			   thistMonday = LocalDate.now();               //���� ������    
			   thisSaturday = LocalDate.now().plusDays(5); //���� �����
			   nexttMonday = LocalDate.now().plusDays(7);   //���� ������
	           nextSaturday = LocalDate.now().plusDays(12); //���� �����	 
				break;
			case 2: //���� ��¥�� ȭ���� �̶��
				lastSaturday = LocalDate.now().minusDays(3);
				lastSunday = LocalDate.now().minusDays(2);
				thistMonday = LocalDate.now().minusDays(1);
				thisSaturday = LocalDate.now().plusDays(4);
				nexttMonday = LocalDate.now().plusDays(6);
				nextSaturday = LocalDate.now().plusDays(11);	
				break;
			case 3: //���� ��¥�� ������ �̶��
				lastSaturday = LocalDate.now().minusDays(4);
				lastSunday = LocalDate.now().minusDays(3);
				thistMonday = LocalDate.now().minusDays(2);
				thisSaturday = LocalDate.now().plusDays(3);
				nexttMonday = LocalDate.now().plusDays(5);
				nextSaturday = LocalDate.now().plusDays(10);		
				break;
			case 4: //���� ��¥�� ����� �̶��
				lastSaturday = LocalDate.now().minusDays(5);
				lastSunday = LocalDate.now().minusDays(4);
				thistMonday = LocalDate.now().minusDays(3);
				thisSaturday = LocalDate.now().plusDays(2);
				nexttMonday = LocalDate.now().plusDays(4);
				nextSaturday = LocalDate.now().plusDays(9);
				break;
			case 5: //���� ��¥�� ������ �̶��
				lastSaturday = LocalDate.now().minusDays(6);
				lastSunday = LocalDate.now().minusDays(5);
				thistMonday = LocalDate.now().minusDays(4);
				thisSaturday = LocalDate.now().plusDays(1);
				nexttMonday = LocalDate.now().plusDays(3);
				nextSaturday = LocalDate.now().plusDays(8);
				break;
			case 6: //���� ��¥�� ����� �̶��
				lastSaturday = LocalDate.now().minusDays(7);
				lastSunday = LocalDate.now().minusDays(6);
				thistMonday = LocalDate.now().minusDays(5);
				thisSaturday = LocalDate.now();
				nexttMonday = LocalDate.now().plusDays(2);
				nextSaturday = LocalDate.now().plusDays(7);
				break;
			case 7: //���� ��¥�� �Ͽ��� �̶��
				lastSaturday = LocalDate.now().minusDays(8);
				lastSunday = LocalDate.now().minusDays(7);
				thistMonday = LocalDate.now().minusDays(6);
				thisSaturday = LocalDate.now().minusDays(1);
				nexttMonday = LocalDate.now().plusDays(1);
				nextSaturday = LocalDate.now().plusDays(6);
				break;

		default:
			break;
		}
	};
	
}
