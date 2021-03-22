package com.test.bnna.admin.board.review;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * AOP를 활용하여 관리자 리뷰 메뉴 접근시 권한을 확인하는 클래스입니다.
 * @author 조아라
 *
 */
@Aspect
@Component
public class ReviewAuthForAdmin {
	
	@Autowired
	private IReviewForAdminDAO dao;
	
	@Pointcut("execution(public String com.test.bnna.admin.board.review.ReviewControllerForAdmin.*ForAdmin(..))")
	public void forAdmin() {
		
	}
	
	/**
	 * 관리자로 로그인한 상태인지 확인하는 메서드입니다.
	 * @param joinPoint
	 */
	@Before("forAdmin()")
	public void check(JoinPoint joinPoint) {
		
		Object[] args=joinPoint.getArgs();
		HttpSession session=(HttpSession) args[2];
		HttpServletResponse response=(HttpServletResponse) args[1];
		
		// TODO 관리자 로그인 기능 만들면 없애야 함!
		session.setAttribute("id", "admin");
		if ((session.getAttribute("id")==null) || !session.getAttribute("id").equals("admin")) {
			try {
				
				response.setCharacterEncoding("UTF-8");
				
				PrintWriter writer=response.getWriter();
				writer.print("<html><head><meta charset='utf-8'></head><body>");
				writer.print("<script>alert('접근권한이 없습니다.'); location.href='/bnna/member/bnbsearch/list.action';</script>");
				
				writer.close();
				
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		
	}
	
}
