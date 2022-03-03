package com.yoon.pms.service;

import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Service;

import com.yoon.pms.dto.WeekReportDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Service
public class WeekReportServiceImpl implements WeekReportService {

	@Override
	public List<WeekReportDTO> getMyDeptReportList(Map<String, Object> needs) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
