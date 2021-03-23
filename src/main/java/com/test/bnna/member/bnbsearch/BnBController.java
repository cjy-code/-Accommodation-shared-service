package com.test.bnna.member.bnbsearch;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;
import com.test.bnna.member.board.review.IReviewDAO;
import com.test.bnna.member.board.review.IReviewPicDAO;
import com.test.bnna.member.board.review.ReviewDTO;
import com.test.bnna.member.board.review.ReviewPicDTO;

/**
 * 숙소와 관련된 비즈니스로직을 처리하는 컨트롤러입니다.
 * @author 조아라
 *
 */
@Controller
public class BnBController {
	
	@Autowired
	private IBnBDAO dao;
	
	@Autowired
	private IBnBPicDAO pdao;
	
	@Autowired
	private IReviewDAO rdao;
	
	@Autowired
	private IReviewPicDAO rpdao;
	
	/**
	 * 하나의 숙소의 상세정보를 가져오는 메서드입니다.
	 * @param req
	 * @param resp
	 * @param session
	 * @param seq 숙소번호입니다.
	 * @return "member/bnbsearch/view"를 호출합니다.
	 */
	@RequestMapping(value="/member/bnbsearch/view.action", method={RequestMethod.GET})
	public String view(HttpServletRequest req, HttpServletResponse resp, HttpSession session, String seq, String page) {
		
		// 받아온 숙소번호로 정보를 찾는다.
		BnBDTO dto=dao.getInfo(seq);
		
		// 받아온 숙소번호로 숙소이미지를 찾는다.
		List<BnBPicDTO> plist=pdao.pic(Integer.parseInt(seq));
		
		// 받아온 숙소번호로 그 숙소의 리뷰를 찾는다. (pagination 추가)
		
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
		
		map.put("begin", begin + "");
		map.put("end", end + "");
		map.put("seq", seq); // 숙소번호도 넣어준다.
		
		// 받아온 숙소번호로 그 숙소의 리뷰를 찾는다.
		List<ReviewDTO> rlist=rdao.list(map);
		
		totalCount = dao.getTotalCount(seq); //총 게시물 수
		
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
					"		        <a href=\"/bnna/member/bnbsearch/view.action?seq=%s&page=%d\">&laquo;</a>", seq, n - 1);
		}
		
		while (!(loop > blockSize || n > totalPage)) {
			
			if (nowPage == n) {
				pagebar += String.format("<a href=\'/bnna/member/bnbsearch/view.action?seq=%s&page=%d\' class=\'active\'>%d</a>", seq, n , n);
			} else {
				pagebar += String.format("<a href=\'/bnna/member/bnbsearch/view.action?seq=%s&page=%d\'>%d</a>", seq, n, n);
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
			pagebar += String.format("<a href=\'/bnna/member/bnbsearch/view.action?seq=%s&page=%d\'>&raquo;</a>\n" + 
					"		    </div>\n" + 
					"		</div>", seq, n);
		}
		
		// 그 숙소의 리뷰의 이미지들을 찾는다.
		List<ReviewPicDTO> rplist=rpdao.listByBnB(seq);
		
		req.setAttribute("dto", dto);
		req.setAttribute("plist", plist);
		req.setAttribute("rlist", rlist);
		req.setAttribute("rplist", rplist);
		req.setAttribute("pagebar", pagebar);
		req.setAttribute("nowPage", nowPage);
		
		return "member.nosidebar.bnbsearch.view";
	}
	
	/**
	 * ajax로 호출되는, 특정 리뷰번호로 이미지를 가져오는 메서드입니다.
	 * @param req
	 * @param resp
	 * @param session
	 * @param seq
	 */
	@RequestMapping(value="/member/bnbsearch/getreviewpic.action", method={RequestMethod.GET})
	public void getReviewPic(HttpServletRequest req, HttpServletResponse resp, HttpSession session, String seq) {
		
		Gson gson=new Gson();
		
		// 리뷰 이미지들의 리스트를 넣어줄 HashMap을 만든다.
		Map<String, Object> data=new HashMap<String, Object>();
		
		// 받아온 리뷰번호로 그 리뷰의 이미지들을 찾는다.
		List<ReviewPicDTO> rplist=rpdao.list(seq);
		
		// 리뷰이미지 리스트들을 풀어서 HashMap에 넣는다.
		for (int i=0;i<rplist.size();i++) {
			data.put(i+"", rplist.get(i).getImage());
		}
		
		try {
			resp.getWriter().print(gson.toJson(data));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
