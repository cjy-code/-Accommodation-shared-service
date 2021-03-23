package com.test.bnna.member.bnbsearch;

import lombok.Data;

/**
 * 검색조건을 담고 있는 DTO입니다.
 * @author 조아라
 *
 */
@Data
public class SearchConditionDTO {
	
	private String location;	// 위치
	private String checkIn;		// 체크인날짜
	private String checkOut;	// 체크아웃날짜
	private int guest;			// 인원수
	private int gap;			// 몇박인지

}
