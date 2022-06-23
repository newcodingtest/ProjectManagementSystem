package com.yoon.pms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yoon.pms.entity.LoginMember;

public interface MemberRepository extends JpaRepository<LoginMember, Long> {
	
	Optional<LoginMember> findByEmail(String email);

}
