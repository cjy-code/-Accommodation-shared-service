package com.test.bnna.member.bnbsearch;

import lombok.Data;

/**
 * 관심숙소정보를 담고 있는 DTO입니다.
 * @author 조아라
 *
 */
@Data
public class DibsDTO {
	
	private String seq;			// 관심숙소번호
	private String seqmember;	// 회원번호
	private String seqbnb;		// 숙소번호

}
