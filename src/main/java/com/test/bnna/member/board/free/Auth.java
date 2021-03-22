package com.test.bnna.member.board.free;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class Auth {

	@Autowired
	private IFreeDAO dao;
	
	//주업무 지정(Point cut) -> 보조업무 결합(Adivce -> Weaving)
	
	
	//인증 받은 사람만 접근 가능 체크
	@Pointcut("execution(public * com.test.bnna.member.board.free.FreeController.member_*(..))")
	public void forMember() {
		
	}	
	
	//글쓴이만 접근 가능 체크 -> 4개
	@Pointcut("execution(public * com.test.bnna.member.board.free.FreeController.member_owner_*(..))")
	public void forWriter() {
		
	}
	
	
	@Before("forMember()")
	public void member(JoinPoint joinPoint) {
		
		HttpServletResponse response = (HttpServletResponse) joinPoint.getArgs()[1];
		HttpSession session = (HttpSession)joinPoint.getArgs()[2];
		
		System.out.println(session.getAttribute("id"));
		
		if(session.getAttribute("id") == null) {
			//로그인 안한 사람 -> 내보내기
			
			try {
				response.setCharacterEncoding("UTF-8");
				
				PrintWriter writer = response.getWriter();
				writer.print("<html><body>");
				writer.print("<script>");
				writer.print("alert('failed'); history.back()");
				writer.print("</script>");
				writer.print("</body></html>");
				
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		
	}
	
	
	
	
	@Before("forWriter()")
	public void owner(JoinPoint joinPoint) {
		
		
		HttpServletResponse response = (HttpServletResponse) joinPoint.getArgs()[1];
		HttpSession session = (HttpSession)joinPoint.getArgs()[2];
		//String seq = (String)joinPoint.getArgs()[3];
		
		
		
		int seq = 0;
		
		if(joinPoint.getArgs()[3] instanceof FreeDTO) {
			seq=((FreeDTO)joinPoint.getArgs()[3]).getSeq();
		} else {
			seq=(Integer)joinPoint.getArgs()[3];
		}
		
		
		FreeDTO dto = dao.get(seq);
				
		System.out.println("게시판 번호:  "+ seq);
		System.out.println("세션 아이디: " + session.getAttribute("id"));
		System.out.println("게시판 아이디: "+dto.getId());
		
		if(!session.getAttribute("id").equals(dto.getId())) {
			
			try {
				
				response.setCharacterEncoding("UTF-8");
				
				PrintWriter writer = response.getWriter();
				writer.print("<html><head><meta charset='utf-8'></head><body>");
				writer.print("<script>");
				writer.print("alert('아이디가 일치하지 않습니다.'); history.back()");
				writer.print("</script>");
				writer.print("</body></html>");
				
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}	
}
