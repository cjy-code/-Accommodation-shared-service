package com.test.bnna.member.bnbsearch;

import lombok.Data;

/**
 * 고객이 숙소검색 중 선택한 필터정보를 담고 있는 DTO입니다.
 * @author 조아라
 *
 */
@Data
public class FilterDTO {
	
	private int bedroom;	// 침실수
	private int bed;		// 침대수
	private int bathroom;	// 욕실수
	
	// 이후의 변수들은 0은 제공안함, 1은 제공함.
	private int amenity;	// 세면도구
	private int dryer;		// 건조기
	private int kitchen;	// 주방사용가능
	private int heating;	// 난방사용가능
	private int washing;	// 세탁기
	private int wifi;		// 무선인터넷
	private int workroom;	// 업무전용공간
	private int babybed;	// 아기침대
	private int breakfast;	// 조식제공
	private int tv;			// tv
	private int ac;			// 에어컨
	private int hairdryer;	// 헤어드라이기
	private int parking;	// 주차
	private int disabled;	// 장애인편의시설
	private int pet;		// 반려동물동반
	private int smoke;		// 흡연가능
	private int evt;		// 이벤트
	private int selfcheck;	// 셀프체크인

}
