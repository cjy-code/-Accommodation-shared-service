package com.test.bnna.member.board.blackboard;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 회원 여부 및 본인 여부 체크
 * @author 김주혁
 *
 */
@Aspect
@Component
public class AuthCheck {

	@Autowired
	private IBlackBoardDAO dao;
	
	@Autowired
	private IBlackBoardCmtDAO cdao;
	
	@Pointcut("execution(public * com.test.bnna.member.board.blackboard.BlackBoardController.member_*(..))")
	public void pcMember() {
		
	}
	
	@Pointcut("execution(public * com.test.bnna.member.board.blackboard.BlackBoardController.member_owner_*(..))")
	public void pcOwner() {
		
	}
	
	@Pointcut("execution(public * com.test.bnna.member.board.blackboard.BlackBoardController.member_cmt_owner_*(..))")
	public void pcCmtOwner() {
		
	}
	
	/**
	 * 회원 여부 체크 메서드
	 * @param joinPoint
	 */
	@Before("pcMember()")
	public void authCheck(JoinPoint joinPoint) {
		
		//req, resp, session
		
		HttpServletResponse resp = (HttpServletResponse)joinPoint.getArgs()[1];
		HttpSession session = (HttpSession)joinPoint.getArgs()[2];
		
		if (session.getAttribute("seqMember") == null) {
			//로그인 안한사람 내보내기
			try {
				
				resp.setCharacterEncoding("UTF-8");
				
				PrintWriter writer = resp.getWriter();
				
				writer.print("<html><head><meta charset='utf-8' /></head><body>");
				writer.print("<script>");
				writer.print("alert('회원만 접근할 수 있습니다.');");
				writer.print("history.back();");
				writer.print("</script>");
				writer.print("</body></html>");
				
				writer.close();				
				
			} catch (Exception e) {
				System.out.println("AuthCheck.authCheck()");
				e.printStackTrace();
			}

		}
		
	} //authCheck
	
	/**
	 * 본인 글 여부 체크 메서드
	 * @param joinPoint
	 */
	@Before("pcOwner()")
	public void ownerCheck(JoinPoint joinPoint) {
		
		//req, resp, session, seq(글번호)
		HttpServletResponse resp = (HttpServletResponse)joinPoint.getArgs()[1];
		HttpSession session = (HttpSession)joinPoint.getArgs()[2];
		
		String seq = "";
		
		if (joinPoint.getArgs()[3] instanceof BlackBoardDTO) {
			seq = ((BlackBoardDTO)joinPoint.getArgs()[3]).getSeq();
		} else {
			seq = ((String)joinPoint.getArgs()[3]);
		}
		
		BlackBoardDTO dto = dao.get(seq);
		
		if (!session.getAttribute("seqMember").equals(dto.getSeqMember())) {
			//본인 아닌경우 내보내기
			try {
				
				resp.setCharacterEncoding("UTF-8");
				
				PrintWriter writer = resp.getWriter();
				
				writer.print("<html><head><meta charset='utf-8' /></head><body>");
				writer.print("<script>");
				writer.print("alert('본인만 접근할 수 있습니다.');");
				writer.print("history.back();");
				writer.print("</script>");
				writer.print("</body></html>");
				
				writer.close();				
				
			} catch (Exception e) {
				System.out.println("AuthCheck.ownerCheck()");
				e.printStackTrace();
			}
			
		}
		
	} //ownerCheck
	
	/**
	 * 본인 댓글 여부 체크 메서드
	 * @param joinPoint
	 */
	@Before("pcCmtOwner()")
	public void cmtOwnerCheck(JoinPoint joinPoint) {
		
		//req, resp, session, seq(댓글번호)
		HttpServletResponse resp = (HttpServletResponse)joinPoint.getArgs()[1];
		HttpSession session = (HttpSession)joinPoint.getArgs()[2];
		
		String seq = "";
		
		if (joinPoint.getArgs()[3] instanceof BlackBoardCmtDTO) {
			seq = ((BlackBoardCmtDTO)joinPoint.getArgs()[3]).getSeq();
		} else {
			seq = ((String)joinPoint.getArgs()[3]);
		}
		
		BlackBoardCmtDTO cdto = cdao.get(seq);
		
		if (!session.getAttribute("seqMember").equals(cdto.getSeqMember())) {
			//본인 아닌경우 내보내기
			try {
				
				resp.setCharacterEncoding("UTF-8");
				
				PrintWriter writer = resp.getWriter();
				
				writer.print("<html><head><meta charset='utf-8' /></head><body>");
				writer.print("<script>");
				writer.print("alert('본인만 접근할 수 있습니다.');");
				writer.print("history.back();");
				writer.print("</script>");
				writer.print("</body></html>");
				
				writer.close();				
				
			} catch (Exception e) {
				System.out.println("AuthCheck.cmtOwnerCheck()");
				e.printStackTrace();
			}
			
		}
		
	} //cmtOwnerCheck
	
}
