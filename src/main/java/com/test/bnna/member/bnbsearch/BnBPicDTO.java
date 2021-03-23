package com.test.bnna.member.bnbsearch;

import lombok.Data;

/**
 * 숙소사진정보를 담고 있는 DTO입니다.
 * @author 조아라
 *
 */
@Data
public class BnBPicDTO {
	
	private int seqbnb;		// 숙소번호
	private String image;	// 이미지파일명

}
