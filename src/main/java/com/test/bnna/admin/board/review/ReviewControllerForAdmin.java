package com.test.bnna.admin.board.review;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.test.bnna.admin.board.review.IReviewForAdminDAO;
import com.test.bnna.admin.board.review.ReviewForAdminDTO;
import com.test.bnna.member.board.review.ReviewPicDTO;

/**
 * 관리자용으로 리뷰에 관련된 비즈니스로직을 처리하는 컨트롤러입니다.
 * @author 조아라
 *
 */
@Controller
public class ReviewControllerForAdmin {
	
	@Autowired
	IReviewForAdminDAO dao;
	
	@Autowired
	IReviewPicForAdminDAO pdao;
	
	/**
	 * 전체리뷰목록을 가져오는 메서드입니다.
	 * @param req
	 * @param resp
	 * @param session
	 * @param page
	 * @return admin/board/review/list.jsp를 호출합니다.
	 */
	@RequestMapping(value="/admin/board/review/list.action", method={RequestMethod.GET})
	public String listForAdmin(HttpServletRequest req, HttpServletResponse resp, HttpSession session, String page) {
		
		// 전체리뷰목록 찾아오기(pagination 추가)
		
		HashMap<String,String> map = new HashMap<String,String>();
		
		//페이징
		int nowPage = 0;		//현재 페이지 번호
		int totalCount = 0;		//총 게시물 수
		int pageSize = 6;		//한페이지 당 출력 개수
		int totalPage = 0;		//총 페이지 수
		int begin = 0;			//rnum 시작 번호
		int end = 0;			//rnum 끝 번호
		int n = 0;				//페이지바 관련 변수
		int loop = 0;			//페이지바 관련 변수
		int blockSize = 10;		//페이지바 관련 변수
		
		if (page == null || page == "") {
			//기본 -> page = 1
			nowPage = 1;
		} else {
			nowPage = Integer.parseInt(page);
		}
		
		begin = ((nowPage - 1) * pageSize) + 1;
		end = begin + pageSize - 1;
		
		
		System.out.println(begin); // 1
		System.out.println(end); // 6
		
		map.put("begin", begin + "");
		map.put("end", end + "");
		
		// 리뷰를 전부 가져온다.
		List<ReviewForAdminDTO> list=dao.list(map);
		
		totalCount = dao.getTotalCount(); //총 게시물 수
		
		totalPage = (int)Math.ceil((double)totalCount / pageSize); //총 페이지 수
		
		String pagebar = "";
		loop=1;
		n = ((nowPage - 1) / blockSize) * blockSize + 1;
		
		if (n == 1) {
			pagebar += String.format("<div class='pagearea'>\n" + 
					"		    <div class=\"pagination\">\n" + 
					"		        <a>&laquo;</a>");
		} else {
			pagebar += String.format("<div class=pagearea>\n" + 
					"		    <div class=\"pagination\">\n" + 
					"		        <a href=\"/bnna/admin/board/review/list.action?page=%d\">&laquo;</a>", n - 1);
		}
		
		while (!(loop > blockSize || n > totalPage)) {
			
			if (nowPage == n) {
				pagebar += String.format("<a href=\'/bnna/admin/board/review/list.action?page=%d\' class=\'active\'>%d</a>", n , n);
			} else {
				pagebar += String.format("<a href=\'/bnna/admin/board/review/list.action?page=%d\'>%d</a>", n, n);
			}
			
			loop++;
			n++;
		}
		
		//다음 10페이지로 이동
		if (n > totalPage) {
			pagebar += String.format("<a>&raquo;</a>\n" + 
					"		    </div>\n" + 
					"		</div>");
		} else {
			pagebar += String.format("<a href=\'/bnna/admin/board/review/list.action?page=%d\'>&raquo;</a>\n" + 
					"		    </div>\n" + 
					"		</div>", n);
		}
		
		req.setAttribute("list", list);
		req.setAttribute("pagebar", pagebar);
		req.setAttribute("nowPage", nowPage);
		
		return "admin.board.review.list";
	}
	
	/**
	 * 관리자용으로 리뷰 한 개의 상세정보를 가져오는 메서드입니다.
	 * @param req
	 * @param resp
	 * @param session
	 * @param seq
	 * @return admin/board/review/view.jsp를 호출합니다.
	 */
	@RequestMapping(value="/admin/board/review/view.action", method={RequestMethod.GET})
	public String viewForAdmin(HttpServletRequest req, HttpServletResponse resp, HttpSession session, String seq) {
		
		// 선택한 글번호를 가져와서 그 번호로 리뷰글정보를 찾는다.
		ReviewForAdminDTO dto=dao.info(seq);
		// 그 글번호로 리뷰이미지도 찾는다.
		List<ReviewPicForAdminDTO> plist=pdao.list(seq);
		
		req.setAttribute("dto", dto);
		req.setAttribute("plist", plist);
		
		return "admin.board.review.view";
	}
	
	/**
	 * 리뷰 및 리뷰에 첨부된 이미지파일 삭제, DB 삭제를 진행하는 메서드입니다.
	 * @param req
	 * @param resp
	 * @param session
	 * @param seq
	 */
	@RequestMapping(value="/admin/board/review/delok.action", method={RequestMethod.GET})
	public void delokForAdmin(HttpServletRequest req, HttpServletResponse resp, HttpSession session, String seq) {
		
		// 1. 데이터 가져오기
		// 2. DB 위임 -> delete
		// 3. 결과 처리
		
		// 2.
		
		String path=req.getRealPath("/resources/image/board/review/");
		
		// dao 내부에서 원본이미지파일삭제, DB의 이미지파일정보 삭제, DB의 리뷰글 삭제가 모두 일어난다.
		int result=dao.del(path, seq);
		
		// 3.
		
		try {
			
			if (result==1) {
				resp.sendRedirect("/bnna/admin/board/review/list.action");
			} else {
				resp.sendRedirect("/bnna/admin/board/review/view.action?seq="+seq);
			}
			
		} catch (Exception e) {
			System.out.println("ReviewControllerForAdmin.delok()");
			e.printStackTrace();
		}
	}

}
