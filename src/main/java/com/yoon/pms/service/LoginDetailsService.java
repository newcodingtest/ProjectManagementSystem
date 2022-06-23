package com.yoon.pms.service;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.yoon.pms.dto.LoginMemberDTO;
import com.yoon.pms.entity.LoginMember;
import com.yoon.pms.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
@RequiredArgsConstructor
public class LoginDetailsService implements UserDetailsService {

	private final MemberRepository memberRepository;
	
	/**
	 * 유저 존재 여부 확인
	 * */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("loadUserByUsername: {}",username);
		
		Optional<LoginMember> result = memberRepository.findByEmail(username);
		
		if(!result.isPresent()) {
			throw new UsernameNotFoundException("해당 회원의 정보를 찾을 수 없습니다. "+ username);
		}
		
		LoginMember loginMember = result.get();
		
		LoginMemberDTO loginMemberDTO = new LoginMemberDTO(
				loginMember.getEmail(),
				loginMember.getPassword(),
				loginMember.isFromSocial(),
				loginMember.getRoleSet().stream().map(role->new SimpleGrantedAuthority("ROLE_"+role.name())).collect(Collectors.toSet())
		);
		
		loginMemberDTO.setName(loginMember.getName());
		loginMemberDTO.setFromSocial(loginMember.isFromSocial());
		
		//User-->LoginMemberDTO
		return loginMemberDTO;
	}

}
