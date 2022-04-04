package com.yoon.pms.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.yoon.pms.entity.SubTask;
import com.yoon.pms.entity.Task;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor; 

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubTaskDTO {

	private Long sid;

	private Long tid;
	
	private String subTitle;
	
	private String subContents;
	
	private String subStartDate;
	
	private String subEndDate;
	
	private float subRealProgress; // 실제 진행률 --> 1%,50%,100%
	
	private String subReportRegistFlag; //--> 보고서 등록 여부
	
	public static LocalDateTime stringToLocalDateTime(String inputDate) {
		LocalDateTime dateTime = LocalDateTime.parse(inputDate,DateTimeFormatter.ISO_DATE_TIME);
		return dateTime;
	}
	
	public static SubTask dtoToEntity(SubTaskDTO dto) {
		return SubTask.builder()
				.sid(dto.getSid())
				.task(Task.builder().tid(dto.getTid()).build())
				.subTitle(dto.getSubTitle())
				.subContents(dto.getSubContents())
				.subStartDate(SubTaskDTO.stringToLocalDateTime(dto.getSubStartDate()))
				.subEndDate(SubTaskDTO.stringToLocalDateTime(dto.getSubStartDate()))
				.subReportRegistFlag(dto.getSubReportRegistFlag())
				.subRealProgress(dto.getSubRealProgress())
				.build();
	}
	
	public static SubTaskDTO entityToDto(SubTask entity) {
		return SubTaskDTO.builder()
				.sid(entity.getSid())
				.tid(entity.getTask().getTid())
				.subTitle(entity.getSubTitle())
				.subContents(entity.getSubContents())
				.subStartDate(entity.getSubStartDate().toString())
				.subEndDate(entity.getSubEndDate().toString())
				.subReportRegistFlag(entity.getSubReportRegistFlag())
				.subRealProgress(entity.getSubRealProgress())
				.build();
	}
	
}
