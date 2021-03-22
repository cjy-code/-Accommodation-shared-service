import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.test.bnna.member.board.qna.IQnaDAO;
import com.test.bnna.member.board.qna.QnaDTO;



@Aspect
@Component
public class Auth {
	
	@Autowired
	private IQnaDAO dao;

	//주업무 지정(Point Cut) -> 보조업무 결합(Advice -> Weaving)
	
	//권한 체크 -> add.action, addok.action
	//권한 체크 -> edit.action, editok.action, del.action, delok.action
	
	//로그인 유무
	// -> member_*
	
	//글쓴이 유무
	// -> member_owner_*
	
	//인증 받는 사람만 접근 가능 체크 -> 6개
	@Pointcut("execution(public * com.test.bnna.member.qna.QnaController.member_*(..))")
	public void pc1() {
		
	}
	
	//글쓴이만 접근 가능 체크 -> 4개
	@Pointcut("execution(public * com.test.bnna.member.qna.QnaController.member_owner_*(..))")
	public void pc2() {
		
	}
	
	@Before("pc1()")
	public void member(JoinPoint joinPoint) {
		
		HttpServletResponse response = (HttpServletResponse)joinPoint.getArgs()[1];
		HttpSession session = (HttpSession)joinPoint.getArgs()[2];
		
		if (session.getAttribute("id") == null) {
			//로그인 안함 사람 -> 내보내기
			try {
				
				PrintWriter writer = response.getWriter();
				writer.print("<html><body>");
				writer.print("<script>");
				writer.print("alert('failed'); history.back();");
				writer.print("</script>");
				writer.print("</body></html>");
				writer.close();
				
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		
	}
	
	
	@Before("pc2()")
	public void owner(JoinPoint joinPoint) {
		
		HttpServletResponse response = (HttpServletResponse)joinPoint.getArgs()[1];
		HttpSession session = (HttpSession)joinPoint.getArgs()[2];
		
		String seq = "";
		
		if (joinPoint.getArgs()[3] instanceof QnaDTO) {
			seq = ((QnaDTO)joinPoint.getArgs()[3]).getSeq();
		} else {
			seq = (String)joinPoint.getArgs()[3];
		}
		
		
		QnaDTO dto = dao.get(seq);		
		
		if (!session.getAttribute("id").equals(dto.getId())) {
			try {
				
				PrintWriter writer = response.getWriter();
				writer.print("<html><body>");
				writer.print("<script>");
				writer.print("alert('failed'); history.back();");
				writer.print("</script>");
				writer.print("</body></html>");
				writer.close();
				
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		
	}
	
}

