package com.yoon.pms.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Member {

	@Id
	@Column(name="MEMBER_ID")
	private String id;
	
	private String username;
	
	private String city;
	private String street;
	private String zipcode;
	
	
	@OneToMany(mappedBy = "member")
	private List<Order>orders = new ArrayList<>();

}
