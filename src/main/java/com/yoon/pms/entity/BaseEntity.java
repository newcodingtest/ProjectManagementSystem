package com.yoon.pms.entity;

import java.time.LocalDateTime;  
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import lombok.Getter;

/**
 * 엔티티 등록일, 수정일 자동등록 엔티티
 * @author 윤주영
 * */
@MappedSuperclass
@EntityListeners(value= {AuditingEntityListener.class})
@Getter
public class BaseEntity {

	@CreatedDate
	@Column(name="regdate", updatable = false, nullable = false)
	private LocalDateTime regDate;
	
	@LastModifiedDate
	@Column(name="moddate", nullable = false)
	private LocalDateTime modDate;
	
}
