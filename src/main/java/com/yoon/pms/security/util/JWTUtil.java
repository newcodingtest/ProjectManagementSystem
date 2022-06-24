package com.yoon.pms.security.util;

import java.nio.charset.StandardCharsets;

import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JWTUtil {
	
	private String secretKey = "yoon1234";

	private long expire = 60*24*30;
	
	//토큰 검증 로직
	public String validateAndExtract(String token) {
		String contentValue = null;
		
		try {
			String result = Jwts.parser()
					//공용키를 해석하는 비밀키 대입
					.setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8))
					.parseClaimsJws(token)
					.getBody()
					.getSubject();
			
			contentValue = result;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
		
		return contentValue;
	}		
}
