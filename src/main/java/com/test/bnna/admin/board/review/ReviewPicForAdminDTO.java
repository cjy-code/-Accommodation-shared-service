package com.test.bnna.admin.board.review;

import lombok.Data;

/**
 * 리뷰이미지정보를 담는 DTO입니다.
 * @author 조아라
 *
 */
@Data
public class ReviewPicForAdminDTO {
	
	private String seq;			// 리뷰이미지번호
	private int seqreview;		// 예약번호
	private String image;		// DB에 저장된 이미지파일명
	private String orgimage;	// 업로드시파일명

}
