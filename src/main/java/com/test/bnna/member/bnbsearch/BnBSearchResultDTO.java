package com.test.bnna.member.bnbsearch;

import lombok.Data;

/**
 * 숙소의 편의시설정보를 담고 있는 DTO입니다.
 * @author 조아라
 *
 */
@Data
public class BnBSearchResultDTO {
	
	private int seq;		// 숙소번호
	private String bnbName;	// 숙소명
	private String intro;	// 숙소소개
	private int cap;		// 수용가능인원
	private int price;		// 1박 가격
	private String hostName;// 호스트 이름
	private double star;	// 평균별점
	private int bedroom;	// 침실수
	private int bed;		// 침대수
	private int bathroom;	// 욕실수
	
	// 이후의 변수들은 0은 제공안함, 1은 제공함.
	private int amenity;	// 세면도구 여부
	private int dryer;		// 건조기 여부
	private int kitchen;	// 주방사용가능 여부
	private int heating;	// 난방사용가능 여부
	private int washing;	// 세탁기사용가능 여부
	private int wifi;		// 무선인터넷사용가능 여부
	private int workroom;	// 업무전용공간 여부
	private int babybed;	// 아기침대사용가능여부
	private int breakfast;	// 조식제공 여부
	private int tv;			// tv가능 여부
	private int ac;			// 에어컨가능 여부
	private int hairdryer;	// 헤어드라이기제공 여부
	private int parking;	// 주차가능 여부
	private int disabled;	// 장애인편의시설 여부
	private int pet;		// 반려동물 동반가능 여부
	private int smoke;		// 흡연가능 여부
	private int evt;		// 이벤트가능 여부
	private int selfcheck;	// 셀프체크인가능 여부
	
	private String mapx;	// 지도에서의 x좌표
	private String mapy;	// 지도에서의 y좌표

}
