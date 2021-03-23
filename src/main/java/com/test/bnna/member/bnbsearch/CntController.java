package com.test.bnna.member.bnbsearch;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.test.bnna.member.board.review.IReviewDAO;

/**
 * ajax로 호출되어 조회수, 추천수에 관한 로직을 처리하는 컨트롤러입니다.
 * @author 조아라
 *
 */
@Controller
public class CntController {
	
	@Autowired
	IReviewDAO dao;
	
	/**
	 * ajax로 호출된, 조회수를 올리는 메서드입니다.
	 * @param req
	 * @param resp
	 * @param seq 리뷰번호입니다.
	 */
	@RequestMapping(value="/member/bnbsearch/readcntup.action", method={RequestMethod.GET})
	public void readCountUp(HttpServletRequest req, HttpServletResponse resp, String seq) {
		
		dao.readCountUp(seq);
	
	}
	
	/**
	 * ajax로 호출된, 추천수를 올리는 메서드입니다.
	 * @param req
	 * @param resp
	 * @param seq 리뷰번호입니다.
	 */
	@RequestMapping(value="/member/bnbsearch/recommendcntup.action", method={RequestMethod.GET})
	public void recommendCountUp(HttpServletRequest req, HttpServletResponse resp, String seq) {
		
		dao.recommendCountUp(seq);
	
	}

}
