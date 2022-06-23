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

import lombok.extern.slf4j.Slf4j;


/**
 *  로그인 성공시 처리하는 핸들러
 * */
@Slf4j
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

	//페이지 분기처리 클래스
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	private PasswordEncoder passwordEncoder;
	
	public LoginSuccessHandler(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		log.info("onAuthenticationSuccess");
		
	}

}
