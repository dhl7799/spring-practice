package com.douzone.aoptest.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAspect {
	@Before("execution(public com.douzone.aoptest.vo.ProductVo com.douzone.aoptest.service.ProductService.find(String))")
	public void adviceBefore() {
		System.out.println("--- Before Advice ---");
	}
	
	@After("execution(* *..*.ProductService.find(..))")
	public void adviceAfter() {
		System.out.println("--- After Advice ---");
	}
	
	@AfterReturning("execution(* *..*.ProductService.*(..))")
	public void adviceAfterReturning() {
		System.out.println("--- After Returning Advice---");
	}
	
	//* *..controller.*(..)
	@AfterThrowing(value="execution(* *..*.ProductService.*(..))", throwing="ex")
	public void adviceAfterThrowing(Throwable ex) {
		System.out.println("--- After Throwing Advice: " + ex + " ---");
	}
	
	@Around("execution(* *..*.ProductService.*(..))")
	public Object adviceAround(ProceedingJoinPoint pjp) throws Throwable {
		/* Before */
		System.out.println("--- Around(Before) Advice ---");
		
		//Object[] params = {"Camera"};
		//Object result = pjp.proceed(params);
		
		/* Point Cut Method 실행 */
		Object result = pjp.proceed();
		
		/* After */
		System.out.println("--- Around(After) Advice ---");
		
		return result;
	}
	
}
