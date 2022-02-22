package com.yoon.pms.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.yoon.pms.dto.WeekReportDTO;

public interface WeekReportService {

	List<WeekReportDTO>getMyDeptReportList(Map<String,Object>needs);
	
	default Map<String,Object>createMSday(){
		 Map<String,Object> need = new HashMap<String, Object>();
		 WeekReportDTO dto = new WeekReportDTO();
		 
		 need.put("thisMonday", dto.getThistMonday());
		 need.put("thisMonday", dto.getNextSaturday());
		 
		return need;
	}
	
	default int compareDate(LocalDate target) {
		WeekReportDTO dto = new WeekReportDTO();
		
		return target.compareTo(dto.getLastSunday());
	}
	
	default List<Map<String,Object>> execute(Map<String,Object>need) {
		
		//given
		String department = "130";
		return null;
	}
	
	default List<Map<String,Object>> makeRealTimeReport(Map<String,Object>need) {
		WeekReportDTO dto = new WeekReportDTO();
		need.entrySet().forEach(x -> { 
			LocalDate start;
			LocalDate end; 
			
			if(x.getKey()=="startDate") {
				start = (LocalDate) x.getValue();
			}else if(x.getKey()=="endDate") {
				end = (LocalDate) x.getValue();
			}
		
		});
		
		return null;
	}
	
    
   default int countHoliDays(LocalDate start, LocalDate end){
	   
	   LocalDate startDate = start;
	   LocalDate endDate= end;
	   
	   Period betweenResult = Period.between(startDate, endDate);
	   int result = betweenResult.getDays();
	   int cnt = 0;
	   
	   for(int i=0; i<=result; i++){
		   DayOfWeek week = startDate.getDayOfWeek();
		   int day = week.getValue();
		   if(day!=6 && day!=7){
			   cnt++;
		   }
		   startDate = startDate.plusDays(1);
	   }
	   return cnt;
   }
   
   default int makeOnlyHoliday(LocalDate start, LocalDate end) {
	   return countHoliDays(start,end);
   }
	
	
}
