package com.yoon.pms.dto;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
@ToString
//LoginDetailsService -> User -> UserDetails -> Authentication
public class LoginMemberDTO extends User{

	/**
	 * ID
	 * */
	private String email;
	
	private String name;
	
	private String password;
	
	private boolean fromSocial;
	
	//OAuth2
	private Map<String, Object> attr;
	
	
	public LoginMemberDTO(
			String username,
			String password,
			boolean fromSocial,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password,authorities);
		this.email = username;
		this.password = password;
		this.fromSocial = fromSocial;
	}
	
	//OAuth2 전용
    public LoginMemberDTO(
            String username,
            String password,
            boolean fromSocial,
            Collection<? extends GrantedAuthority> authorities, Map<String,Object> attr) {
        this(username, password, fromSocial, authorities);
        this.attr = attr;
    }

}
