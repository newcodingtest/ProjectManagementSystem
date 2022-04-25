package com.yoon.pms.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NotFound;

import com.yoon.pms.entity.Project;
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

	@NotEmpty(message = "제목은 빈값 일 수 없습니다.")
	@NotNull(message = "제목는 Null 일 수 없습니다.")
	private String subTitle;
	
	private String subContents;
	
	@NotEmpty(message = "시작일은 빈값 일 수 없습니다.")
	@NotNull(message = "시작일은 Null 일 수 없습니다.")
	private String subStartDate;
	
	@NotEmpty(message = "종료일은 빈값 일 수 없습니다.")
	@NotNull(message = "종료일은 Null 일 수 없습니다.")
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
	
	public static SubTaskDTO entityToDTO(SubTask entity) {
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
	
	public static List<SubTaskDTO> ListEntityToDto(List<SubTask> entity){
		return entity.stream()
				.map(SubTaskDTO::entityToDTO)
				.collect(Collectors.toList());
	}
	
	public static List<SubTask> ListDtoToEntity(List<SubTaskDTO> dto){
		return dto.stream()
				.map(SubTaskDTO::dtoToEntity)
				.collect(Collectors.toList());
	}
	
}
