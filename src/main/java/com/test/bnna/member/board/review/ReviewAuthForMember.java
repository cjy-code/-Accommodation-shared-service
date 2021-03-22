package com.test.bnna.member.board.review;

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
 * AOP를 활용하여 회원 리뷰게시판에 접근시 권한을 확인하는 클래스입니다.
 * @author 조아라
 *
 */
@Aspect
@Component
public class ReviewAuthForMember {
	
	@Autowired
	private IReviewDAO dao;
	
	@Pointcut("execution(public String com.test.bnna.member.board.review.ReviewController.*ForMember(..))")
	public void forMember() {
		
	}
	
	@Pointcut("execution(public String com.test.bnna.member.board.review.ReviewController.*ForSpecificMember(..))")
	public void forSpecificMember() {
		
	}
	
	/**
	 * 로그인한 상태인지 확인하는 메서드입니다.
	 * @param joinPoint
	 */
	@Before("forMember()")
	public void check(JoinPoint joinPoint) {
		
		Object[] args=joinPoint.getArgs();
		HttpSession session=(HttpSession) args[2];
		HttpServletResponse response=(HttpServletResponse) args[1];
		
		// TODO 우선 동작해야하므로 세션에 id값을 넣어준다.
		session.setAttribute("id", "air0001");
		
		
		if (session.getAttribute("id")==null) {
			try {
				
				response.setCharacterEncoding("UTF-8");
				
				PrintWriter writer=response.getWriter();
				writer.print("<html><head><meta charset='utf-8'></head><body>");
				writer.print("<script>alert('회원 전용입니다.'); location.href='/bnna/member/bnbsearch/list.action';</script>");
				
				writer.close();
				
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		
	}
	
	/**
	 * 특정회원인지 확인하는 메서드입니다.
	 * @param joinPoint
	 */
	@Before("forSpecificMember()")
	public void owner(JoinPoint joinPoint) {
		
		HttpServletResponse response=(HttpServletResponse)joinPoint.getArgs()[1];
		HttpSession session=(HttpSession)joinPoint.getArgs()[2];
		
		// TODO 나중에 회원가입 기능 만든다면 수정해야 함! 1번회원정보를 임의로 넣어둔다.
		session.setAttribute("id", "air0001");
		
		String seq="";
		
		if (joinPoint.getArgs()[3] instanceof EditReviewDTO) {
			seq=((EditReviewDTO)joinPoint.getArgs()[3]).getSeq();
		} else {
			seq=(String)joinPoint.getArgs()[3];
		}
		
		// 리뷰번호로 글쓴 사람 ID를 가져온다.
		String owner=dao.getOwner(seq);
		
		if (!session.getAttribute("id").equals(owner)) {
			// 작성자 아닌 사람 -> 내보내기
			
			try {
				
				response.setCharacterEncoding("UTF-8");
				
				PrintWriter writer=response.getWriter();
				writer.print("<html><head><meta charset='utf-8'></head><body>");
				writer.print("<script>alert('접근권한이 없습니다.'); location.href='/bnna/member/bnbsearch/list.action';</script>");
				writer.close();
				
			} catch (Exception e) {
				System.out.println("Auth.member()");
				e.printStackTrace();
			}
			
		}
	
	}

}
