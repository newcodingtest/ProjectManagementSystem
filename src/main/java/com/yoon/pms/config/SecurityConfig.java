package com.yoon.pms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.yoon.pms.service.LoginDetailsService;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)//어노테이션 기반의 접근제한 설정, securedEnabled: 예전버전의 @Secure 사용가능여부
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	private LoginDetailsService loginDetailsService; 
	
	public SecurityConfig(LoginDetailsService loginDetailsService) {
		this.loginDetailsService = loginDetailsService;
	}
	
	@Override
	protected void configure(HttpSecurity http)throws Exception {
		http.formLogin();
		http.csrf().disable();
		http.logout();
		
		http.oauth2Login().successHandler(successHandler());
		http.rememberMe().tokenValiditySeconds(60*60*27*7)
				.userDetailsService(userDetailsService);
				
		http.addFilterBefore(apiCheckFilter(), UsernamePasswordAuthenticationFilter.class);
	}
	
		/**
		 *  로그인 성공시 핸들러
		 * */
		@Bean
	    public LoginSuccessHandler successHandler(){
	        return new LoginSuccessHandler(passwordEncoder());
	    }

	    @Bean
	    public ApiCheckFilter apiCheckFilter(){
	        return new ApiCheckFilter("/notes/**/*",jwtUtil());
	    }

	    @Bean
	    public ApiLoginFilter apiLoginFilter()throws Exception{
	        ApiLoginFilter apiLoginFilter = new ApiLoginFilter("/api/login",jwtUtil());
	        apiLoginFilter.setAuthenticationManager(authenticationManager());

	        apiLoginFilter.setAuthenticationFailureHandler(new ApiLoginFailHandler());

	        return apiLoginFilter;
	    }
}
