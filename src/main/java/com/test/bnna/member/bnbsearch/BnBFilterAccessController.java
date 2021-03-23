package com.test.bnna.member.bnbsearch;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * AOP를 활용하여, 검색을 거치지 않고 바로 필터검색URL로 접근하는 경우를 차단하는 컨트롤러입니다.
 * @author 조아라
 *
 */
@Aspect
@Component
public class BnBFilterAccessController {
	
	@Pointcut("execution(public String com.test.bnna.member.bnbsearch.BnBSearchController.filter*(..))")
	public void toFilter() {
		
	}
	
	/**
	 * 필터를 적용하기 전 검색했던 결과가 있는지 확인하고, 검색결과가 없으면 검색화면으로 이동시킵니다.
	 * @param joinPoint
	 */
	@Before("toFilter()")
	public void isExist(JoinPoint joinPoint) {
		
		Object[] args=joinPoint.getArgs();
		HttpSession session=(HttpSession) args[2];
		HttpServletResponse response=(HttpServletResponse) args[1];
		
		if (session.getAttribute("searchResultList")==null) {
			try {
				
				response.setCharacterEncoding("UTF-8");
				
				PrintWriter writer=response.getWriter();
				writer.print("<html><head><meta charset='utf-8'></head><body>");
				writer.print("<script>alert('필터를 적용할 검색결과가 존재하지 않습니다. 첫 화면으로 이동하겠습니다.'); location.href='/bnna/member/bnbsearch/list.action';</script>");
				
				writer.close();
				
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		
	}

}
