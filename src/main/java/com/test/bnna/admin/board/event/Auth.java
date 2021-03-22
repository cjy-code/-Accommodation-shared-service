//package com.test.bnna.admin.board.event;
//
//import javax.servlet.http.HttpServletResponse;
//
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//@Aspect
//@Component
//public class Auth {
//
//	@Autowired
//	private IEventBoardDAO dao;
//
//	// 주업무 지정(Point Cut) -> 보조 업무 결합(Advice -> Waving)
//
//	// 권한 체크 -> add.edit, addok.edit
//	// 권한 체크 -> edit.action, deitok.action, del.action, delok.action, delcommentok.action
//
//	// 로그인 유무
//	// -> admin_*
//
//	// 글쓴이 유무
//	// -> admin_owner_*
//	
//	// 인증 받은 사람만 접근 가능 체크 -> 7개
//	@Pointcut("execution(public * com.test.bnna.admin.board.event.eventController.admin_*(..))")
//	public void pc1() {
//		
//	}
//	
//	// 글쓴 사람만 접근 가능 체크 -> 5개
//	@Pointcut("execution(public * com.test.bnna.admin.board.event.eventController.admin_owner_*(..))")
//	public void pc2() {
//		
//	}
//	
//	@Before("pc1()")
//	public void admin(JoinPoint joinPoint) {
//		
////		HttpServletResponse response = (HttpServletResponse) joinPoint.getArgs()[]
//	}
//	
//}
