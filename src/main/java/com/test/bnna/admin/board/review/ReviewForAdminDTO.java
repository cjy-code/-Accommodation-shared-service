package com.test.bnna.admin.board.review;

import lombok.Data;

/**
 * 리뷰정보를 담는 DTO입니다.
 * @author 조아라
 *
 */
@Data
public class ReviewForAdminDTO {
	
	private String seq;		 // 리뷰번호
	private String seqbook;	 // 예약번호
	private String title;    // 리뷰제목
	private String content;  // 리뷰내용
	private String star;	 // 별점
	private String id;	 	 // 작성자 ID
	private String bookdate; // 예약일
	private int readcnt;	 // 조회수
	private int recommendcnt;// 추천수
	private int thread;	     // 계층형 게시판을 위한 thread
	private int depth;		 // 계층형 게시판을 위한 depth

}
