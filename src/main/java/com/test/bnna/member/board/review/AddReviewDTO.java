package com.test.bnna.member.board.review;

import lombok.Data;

/**
 * 리뷰 추가시 사용하는 DTO입니다.
 * @author 조아라
 *
 */
@Data
public class AddReviewDTO {
	
	private String seqbook; // 예약번호
	private String title;	// 리뷰제목
	private String content; // 리뷰내용
	private String star;	// 별점

}
