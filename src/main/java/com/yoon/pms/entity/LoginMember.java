package com.yoon.pms.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class LoginMember extends BaseEntity {
	@Id
	@Column(name="email", nullable=false)
	private String email;
	
	@Size(min=9)
	@Column
	private String password;
	
	@Column
	private String name;
	
	@Column
	private boolean fromSocial;
	
	@ElementCollection(fetch = FetchType.LAZY)
	@Builder.Default
	private Set<LoginMemberRole> roleSet = new HashSet<>();
	
	public void addMemberRole(LoginMemberRole member) {
		roleSet.add(member);
	}
}


