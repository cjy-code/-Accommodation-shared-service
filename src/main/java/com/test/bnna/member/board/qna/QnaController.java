package com.test.bnna.member.board.qna;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;




/**
 * 
 * Q&A 게시판 컨트롤러
 *
 */
@Controller
public class QnaController {
	
	@Autowired
	private IQnaDAO dao;
	
	@RequestMapping(value="/member/board/qna/auth.action", method={RequestMethod.GET})
	public String auth(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		
		
		return "member.board.qna.list";		
	}
	//http://localhost:8090/bnna/member/board/qna/list.action?id=air0001&seqmember=1
	//http://localhost:8090/bnna/member/board/qna/list.action
	
	@RequestMapping(value="/member/board/qna/login.action", method={RequestMethod.GET})
	public void login(HttpServletRequest request, HttpServletResponse response, HttpSession session, String id, String seqmember) { //1.
		
		//1. 데이터 가져오기(id)
		//2. 인증 티켓 발급
		//3. 페이지 이동
		
		session.setAttribute("id", id); //2.
		session.setAttribute("seqmember", seqmember);
		
		
		
		//3.
		try {
			response.sendRedirect("/bnna/member/board/qna/list.action");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@RequestMapping(value="/member/board/qna/logout.action", method={RequestMethod.GET})
	public void logout(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		
		//1. 인증 티켓 폐기
		//2. 페이지 이동
		
		session.removeAttribute("id");
		session.removeAttribute("seqmember");
		
		//2.
		try {
			response.sendRedirect("/bnna/member/board/qna/list.action");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@RequestMapping(value="/member/board/qna/list.action", method={RequestMethod.GET})
	public String list(HttpServletRequest request, HttpServletResponse response) {
		
		//1. DB 위임 -> select
		//2. JSP 호출하기
		
		System.out.println(111);
		
		List<QnaDTO> list = dao.list();
		
		request.setAttribute("list", list);
		
		return "member.board.qna.list";		
	}
	
	@RequestMapping(value="/member/board/qna/add.action", method={RequestMethod.GET})
	public String member_add(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		
		
//		request.setAttribute("category", category);
//		System.out.println(category);
		return "member.board.qna.add";		
	}
	
	@RequestMapping(value="/member/board/qna/addok.action", method={RequestMethod.POST})
	public void member_addok(HttpServletRequest request, HttpServletResponse response, HttpSession session, QnaDTO dto) { //1.
		
		//1. 데이터 가져오기
		//2. DAO 위임 -> insert
		//3. 결과 처리
		

		dto.setId((String)session.getAttribute("id"));
		dto.setSeqmember((String)session.getAttribute("seqmember"));
		

		System.out.println(dto);
		
		//dto.setCategory(((String)request.getAttribute("category")));
		
		int result = dao.add(dto);
		
		//3.
		try {
			if (result == 1) {
				response.sendRedirect("/bnna/member/board/qna/list.action");
			} else {
				response.sendRedirect("/bnna/member/board/qna/add.action");
			}
		} catch(Exception e) {
			System.out.println(e);
		}
		
	}
	
	@RequestMapping(value="/member/board/qna/del.action", method= {RequestMethod.GET})
	public String member_owner_del(HttpServletRequest request, HttpServletResponse resopnse, HttpSession session, String seq) {
		
		
		request.setAttribute("seq", seq);
		
		return "member.board.qna.del";
	}
	
	@RequestMapping(value="/member/board/qna/delok.action", method= {RequestMethod.POST})
	public void member_owner_delok(HttpServletRequest request, HttpServletResponse response, HttpSession session, String seq) {
		
		int result = dao.del(seq);
		
		try {
			if (result ==1) {
				response.sendRedirect("/bnna/member/board/qna/list.action");
			} else {
				response.sendRedirect("/bnna/member/board/qna/view.action?seq=" + seq);
			}
			
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		
		
	}
	
	@RequestMapping(value="/member/board/qna/view.action", method= {RequestMethod.GET})
	public String view(HttpServletRequest request, HttpServletResponse response, HttpSession session, String seq) {
		
		QnaDTO dto = dao.get(seq);
		
		request.setAttribute("dto", dto);
		
		
		return "member.board.qna.view";
	}
	
	@RequestMapping(value="/member/board/qna/edit.action")
	public String member_owner_edit(HttpServletRequest request, HttpServletResponse response, HttpSession session, String seq ) {
		
		QnaDTO dto = dao.get(seq);
		
		request.setAttribute("dto", dto);
		
		return "member.board.qna.edit";
		
	}
	
	@RequestMapping(value="/member/board/qna/editok.action", method={RequestMethod.POST})
	public void member_owner_editok(HttpServletRequest request, HttpServletResponse response, HttpSession session, QnaDTO dto) { //1.
		
		//1. 데이터 가져오기
		//2. DB 위임 -> update
		//3. 결과 처리
		
		dto.setId((String)session.getAttribute("id"));
		
		int result = dao.edit(dto);
		
		//3.
		try {
			if (result == 1) {
				response.sendRedirect("/bnna/member/board/qna/view.action?seq=" + dto.getSeq());
			} else {
				response.sendRedirect("/bnna/member/board/qna/edit.action?seq=" + dto.getSeq());
			}
		} catch(Exception e) {
			System.out.println(e);
		}
		
		
				
	}
	/*
	@GetMapping("/member/board/qna/list.action")
	public String boardList(PagingVO vo, Model model
			, @RequestParam(value="nowPage", required=false)String nowPage
			, @RequestParam(value="cntPerPage", required=false)String cntPerPage) {
		
		int total = dao.countBoard();
		if (nowPage == null && cntPerPage == null) {
			nowPage = "1";
			cntPerPage = "5";
		} else if (nowPage == null) {
			nowPage = "1";
		} else if (cntPerPage == null) { 
			cntPerPage = "5";
		}
		vo = new PagingVO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
		model.addAttribute("paging", vo);
		model.addAttribute("viewAll", dao.selectBoard(vo));
		return "member.board.qna.list";
	}
	*/
	
	
	

}
