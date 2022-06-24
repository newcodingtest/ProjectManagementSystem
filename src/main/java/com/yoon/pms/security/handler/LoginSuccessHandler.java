package com.yoon.pms.security.handler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.yoon.pms.dto.LoginMemberDTO;
import com.yoon.pms.security.filter.ApiCheckFilter;

import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;


/**
 *  로그인 성공시 처리하는 핸들러
 * */
@Log4j2
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

	//페이지 분기처리 클래스
	private RedirectStrategy redirect = new DefaultRedirectStrategy();
	
	private PasswordEncoder passwordEncoder;
	
	public LoginSuccessHandler(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
	
	/**
	 * 로그인시 소셜 로그인 여부를 판별하여 비밀번호 수정 유도
	 * */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		log.info("onAuthenticationSuccess");
		
		LoginMemberDTO loginMemberDTO = (LoginMemberDTO)authentication.getPrincipal();
		
		boolean isSocial = loginMemberDTO.isFromSocial();
		
		//소셜 로그인으로 로그인 연동시 기본 비밀번호는 1111로 지정
		boolean isPassword = passwordEncoder.matches("1111", passwordEncoder.encode(loginMemberDTO.getPassword()));
		
		if(isSocial && isPassword) {
			redirect.sendRedirect(request, response, "member/modify?from=socials");
		}
		
	}

}
