package com.yoon.pms.exception.handler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.yoon.pms.exception.ApiException;
import com.yoon.pms.exception.UserNotExistException;

@ControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler(value = {UserNotExistException.class})
	public ResponseEntity<Object> handleUserNotExistException(UserNotExistException e){
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		
		ApiException apiException = new ApiException("존재하지 않는 회원 정보입니다.",
				httpStatus,
				ZonedDateTime.now(ZoneId.of("Z"))
				);
		
		return new ResponseEntity<>(apiException, httpStatus);
	}
}
