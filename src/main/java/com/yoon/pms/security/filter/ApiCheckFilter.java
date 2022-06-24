package com.yoon.pms.security.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import com.yoon.pms.security.util.JWTUtil;
import lombok.extern.log4j.Log4j2;
import net.minidev.json.JSONObject;

/**
 * OncePerRequestFilter: 모든 서블릿에 일관된 요청을 처리하기 위해 만들어진 필터
 * https://minkukjo.github.io/framework/2020/12/18/Spring-142/
 * */
@Log4j2
public class ApiCheckFilter extends OncePerRequestFilter {
	
	private AntPathMatcher antPathMatcher; //특정 경로가 Ant 경로 패턴 경로와 일치하는지 여부를 확인하는 스프링 클래스
	private String pattern;
	private JWTUtil jwtUtil;
	
	public ApiCheckFilter(String pattern,JWTUtil jwtUtil) {
        this.antPathMatcher = new AntPathMatcher();
        this.pattern = pattern;
        this.jwtUtil = jwtUtil;

    }
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
	    log.info(request.getRequestURI());
        log.info(antPathMatcher.match(pattern, request.getRequestURI()));
        
        Boolean isCorrectPath = antPathMatcher.match(pattern, request.getRequestURI());
        
        if(isCorrectPath) {
        	Boolean checkHeader = checkAuthHeader(request);
	        if(checkHeader){
	        	filterChain.doFilter(request, response);
	        	return;
	        }else {
	        	 response.setStatus(HttpServletResponse.SC_FORBIDDEN);
	             //json 리턴 및 한글깨짐 수정
	             response.setContentType("application/json;charset=utf-8");
	             JSONObject json = new JSONObject();
	             String message = "FAIL CHECK API TOKEN";
	             json.put("code",403);
	             json.put("message", message);
	
	             PrintWriter out = response.getWriter();
	             out.print(json);
	             return;
	        }
        }
        //다음 필터로 넘어가는 역할 설정
        filterChain.doFilter(request,response);
	}
	
	//JwtUtil validateAndExtract 호출
    //Authorization 헤더를 추출해서 검증하는 역할
    private boolean checkAuthHeader(HttpServletRequest request){
        boolean checkResult = false;

        String authHeader = request.getHeader("Authorization");

        //Authorization 헤더 일반적인 인증 타입 Basic, JWT 이용시엔 Bearer 타입 사용
        if(StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer")){
            log.info("Authorization exist: "+ authHeader);

            try {
                String email = jwtUtil.validateAndExtract(authHeader.substring(7));
                log.info("validate result: "+email);
                checkResult = email.length() > 0;

            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return checkResult;
    }

}
