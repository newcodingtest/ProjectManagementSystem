package com.yoon.pms.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PageRequestDTO {

	private String title;

	public PageRequestDTO() {
		this.title = title;
	}
	
	public int createTitle() {
		if(getTitle()=="empty") {
			return 0;
		}else if(getTitle()=="all") {
			return 1;
		}
		return 1;
	}
	
	
	
}
