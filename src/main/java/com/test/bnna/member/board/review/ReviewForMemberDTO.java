package com.test.bnna.member.board.review;

import lombok.Data;

/**
 * 회원이 본인의 리뷰에 접근할 때 사용하는 DTO입니다.
 * @author 조아라
 *
 */
@Data
public class ReviewForMemberDTO {
	
	private String seq;		 // 리뷰번호
	private String seqbook;	 // 예약번호
	private String title;    // 리뷰제목
	private String content;  // 리뷰내용
	private String star;	 // 별점
	private String bookdate; // 예약일
	private int readcnt;	 // 조회수
	private int recommendcnt;// 추천수
	private int thread;	     // 계층형 게시판을 위한 thread
	private int depth;		 // 계층형 게시판을 위한 depth

}
