package com.test.bnna.member.board.review;

import lombok.Data;

/**
 * 리뷰의 수정정보를 담고 있는 DTO입니다.
 * @author 조아라
 *
 */
@Data
public class EditReviewDTO {
	
	private String seq;		// 리뷰번호
	private String title;	// 리뷰제목
	private String seqbook;	// 예약번호
	private String star;	// 별점
	private String content;	// 내용

}
