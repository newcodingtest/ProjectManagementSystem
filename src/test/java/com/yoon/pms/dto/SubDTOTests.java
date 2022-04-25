package com.yoon.pms.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SubDTOTests {

	@Test
	@DisplayName("SubTaskDTO 생성 테스트")
	public void DTO_생성_테스트() {
		
		//Given
		SubTaskDTO dto = SubTaskDTO.builder()
				.sid(1L)
				.tid(1L)
				.subTitle("테스트 제목")
				.subContents("테스트 내용")
				.subStartDate("2022-03-08T10:10")
				.subEndDate("2022-03-08T10:10")
				.subRealProgress(50f)
				.subReportRegistFlag("1")
				.build();
		
		assertThat(dto.getSubTitle())
				.isEqualTo("테스트 제목");
		assertThat(dto.getSubContents())
				.isEqualTo("테스트 내용");
		assertThat(dto.getSubStartDate())
				.isEqualTo("2022-03-08T10:10");
		assertThat(dto.getSubReportRegistFlag())
				.isEqualTo("1");
		
		//Then
	}
}
