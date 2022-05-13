package com.yoon.pms.aop;

import org.aspectj.lang.JoinPoint; 
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * 스프링 AOP 사용
 * Request, Reponse 파악 용도
 * @author 윤주영
 * */
@Slf4j
@Aspect
@Component
public class AspectAdvice {  
	
	
    /*
    //hello.aop.order 패키지와 하위 패키지 이면서 클래스 이름 패턴이 *Service
    @Around("com.yoon.pms.aop.Pointcuts.orderAndService()")
    public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable{
        try {
            //@Before
            log.info("[트랜잭션 시작] {}", joinPoint.getSignature());
            Object result = joinPoint.proceed();
            //@AfterReturning
            log.info("[트랜잭션 커밋] {}", joinPoint.getSignature());
            return result;
        } catch (Exception e){
            //@AfterThrowing
            log.info("[트랜잭션 롤백] {}", joinPoint.getSignature());
            throw e;
        }finally {
            //@After
            log.info("[리소스 릴리즈] {}", joinPoint.getSignature());
        }
    }
     */
	
    @Pointcut("execution(* com.yoon.pms..*(..))")
    public void allOrder(){} //pointcut signature

    //클래스 이름 패턴이 *Service
    @Pointcut("execution(* *..*Service.*(..))")
    public void allService(){}

    //allOrder && allService
    @Pointcut("allOrder() && allService()")
    public void orderAndService(){}
	
	
	   @Before("orderAndService()")
	    public void doBefore(JoinPoint joinPoint) throws Throwable{

	        log.info("[before 시작] {}", joinPoint.getSignature());
	    }

	    @AfterReturning(pointcut = "orderAndService()", returning = "result")
	    public void doReturn(JoinPoint joinPoint, Object result) throws Throwable{

	        log.info("[return] {} return={}", joinPoint.getSignature(), result);
	    }

	    @AfterReturning(pointcut = "orderAndService()", returning = "result")
	    public void doReturn2(JoinPoint joinPoint, String result) throws Throwable{

	        log.info("[return2] {} return2={}", joinPoint.getSignature(), result);
	    }

	    @AfterThrowing(pointcut = "orderAndService()", throwing = "ex")
	    public void doTrowing(JoinPoint joinPoint, Exception ex) throws Throwable{

	        log.info("[ex] {} message={}", ex);
	    }

	    @After(value = "orderAndService()")
	    public void doAfter(JoinPoint joinPoint) throws Throwable{
	        //@Before
	        log.info("[after] {}", joinPoint.getSignature());
	    }

}
