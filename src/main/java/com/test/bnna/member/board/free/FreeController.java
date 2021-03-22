package com.test.bnna.member.board.free;


import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;




/**
 * 자유게시판 Controller
 * @author 최진영
 *
 */
@Controller
public class FreeController {

	@Autowired
	private IFreeDAO dao;
	
	
	
	
	/**
	 * 자유게시판 리스트 불러오기
	 * @param req
	 * @param resp
	 * @param session
	 * @param page
	 * @param searchType
	 * @param keyword
	 * @return /member/board/free/list.action 호출
	 */
	@RequestMapping(value="/member/board/free/list.action", method= {RequestMethod.GET})
	public String list(HttpServletRequest req, HttpServletResponse resp, HttpSession session, String page, String searchType, String keyword) {
		
		//TODO 1번 회원 세션으로 고정
		session.setAttribute("seq", 1);
		session.setAttribute("id", "air0001");
		
		HashMap<String, String> map = new HashMap<String, String>();
		
		//검색
		System.out.println(searchType);
		System.out.println(keyword);
		
		map.put("searchType", searchType);
		map.put("keyword", keyword);
		
		//전체 게시글 수
//		int totalboard = dao.total();
		int totalboard= dao.total(map);
		
		
		//페이지바 setting
		paging nowpage = new paging(page, totalboard);
		
		//페이지바 생성
		String pagebar = paging.getPagebar();
		
		//페이지 번호 가져오기
		int nowPage = paging.getNowPage();
		
		System.out.println("현제 페이지: "+nowPage);
		
		map.put("begin", paging.getBegin() + "");
		map.put("end", paging.getEnd() + "");
		
		
		
		List<FreeDTO> list = dao.list(map);
		
		//회원 리스트
		req.setAttribute("list", list);
		req.setAttribute("pagebar", pagebar);
		req.setAttribute("nowpage", nowPage);
		
		//검색 결과
		req.setAttribute("totalboard", totalboard);
		req.setAttribute("keyword", keyword);
		
		return "member.board.free.list";
	}
		
		
		
	
	/**
	 * 게시글 상세보기
	 * @param req
	 * @param resp
	 * @param session
	 * @param seq
	 * @return /member/board/free/view.action 호출
	 */
	@RequestMapping(value="/member/board/free/view.action", method= {RequestMethod.GET})
	public String view(HttpServletRequest req, HttpServletResponse resp, HttpSession session, int seq) {
		
		FreeDTO dto = dao.get(seq);
		
		req.setAttribute("dto", dto);
		
		
		
		
		return "member.board.free.view";
	}
	
	
	/**
	 * 게시글 추가하기 화면
	 * @param req
	 * @param resp
	 * @param session
	 * @param cnt 전체 게시글 수
	 * @return /member/board/free/add.action
	 */
	@RequestMapping(value="/member/board/free/add.action", method= {RequestMethod.GET})
	public String member_add(HttpServletRequest req, HttpServletResponse resp, HttpSession session, int cnt) {
		
		req.setAttribute("cnt", cnt);
		
		
		return "member.board.free.add";
	}

	
	/**
	 * 게시글 추가하기 메서드
	 * @param req
	 * @param resp
	 * @param session
	 * @param dto 자유게시판 DTO
	 */
	@RequestMapping(value="/member/board/free/addok.action", method= {RequestMethod.POST})
	public void member_addok(HttpServletRequest req, HttpServletResponse resp, HttpSession session, FreeDTO dto) {
		
		dto.setId((String)session.getAttribute("id"));
		
		int result = dao.add(dto);
						
		try {
			if(result == 1) {
				resp.sendRedirect("/bnna/member/board/free/list.action");
			}else {
				resp.sendRedirect("/bnna/member/board/free/add.ation");
			}
		}catch (Exception e) {
			System.out.println(e);
		}
	}

	
	/**
	 * 게시글 수정하기 화면
	 * @param req
	 * @param resp
	 * @param session
	 * @param seq 회원 번호
	 * @return /member/board/free/edit.action 호출
	 */
	@RequestMapping(value="/member/board/free/edit.action", method={RequestMethod.GET})
	public String member_owner_edit(HttpServletRequest req, HttpServletResponse resp, HttpSession session, int seq) { //1.
				
		FreeDTO dto = dao.get(seq);
		
		req.setAttribute("dto", dto);		
		
		return "member.board.free.edit";		
	}
	
	
	/**
	 * 게시글 수정하기 메서드
	 * @param req
	 * @param resp
	 * @param session
	 * @param dto 자유게시판 DTO
	 */
	@RequestMapping(value="/member/board/free/editok.action", method={RequestMethod.POST})
	public void member_owner_editok(HttpServletRequest req, HttpServletResponse resp, HttpSession session, FreeDTO dto) { //1.
				
		
		dto.setId((String)session.getAttribute("id"));
		
		int result = dao.edit(dto);
		
		//3.
		try {
			if (result == 1) {
				resp.sendRedirect("/bnna/member/board/free/view.action?seq=" + dto.getSeq());
			} else {
				resp.sendRedirect("/bnna/member/board/free/edit.action?seq=" + dto.getSeq());
			}
		} catch(Exception e) {
			System.out.println(e);
		}	
		
	}
	
	/**
	 * 게시글 삭제하기 화면
	 * @param req
	 * @param resp
	 * @param session
	 * @param seq 회원 번호
	 * @return /member/board/free/del.action
	 */
	@RequestMapping(value="/member/board/free/del.action", method = {RequestMethod.GET})
	public String member_owner_del(HttpServletRequest req, HttpServletResponse resp, HttpSession session, int seq) {
		
		req.setAttribute("seq", seq);
		
		return "member.board.free.del";
	}
	
	/**
	 * 삭제하기 메서드
	 * @param req
	 * @param resp
	 * @param session
	 * @param seq 회원 번호
	 */
	@RequestMapping(value="/member/board/free/delok.action", method = {RequestMethod.POST})
	public void member_owner_delok(HttpServletRequest req, HttpServletResponse resp, HttpSession session, int seq) {
		
		int result = dao.del(seq);
		
		try {
			if(result==1) {
				resp.sendRedirect("/bnna/member/board/free/list.action");
			}
				resp.sendRedirect("/bnna/member/board/free/view.action?seq=" + seq);
		} catch (Exception e) {
				System.out.println(e);
		}
	}
	
	
	
}
