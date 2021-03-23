package com.test.bnna.member.bnbsearch;

import lombok.Data;

/**
 * 숙소정보를 담고 있는 DTO입니다.
 * @author 조아라
 *
 */
@Data
public class BnBDTO {
	
	private int seq;			// 숙소번호
	private String hostName;	// 호스트이름
	private String hostImage;	// 호스트사진
	private String bnbType;		// 숙소유형
	private String bnbName;		// 숙소명
	private String bnbIntro;	// 숙소소개
	private int cap;			// 수용가능인원
	private int price;			// 1박가격
	private int bedroom;		// 침실수
	private int bathroom;		// 욕실수
	private String tel;			// 연락처
	private int amenity;		// 세면도구
	private int kitchen;		// 주방사용가능
	private int heating;		// 난방사용가능
	private int washing;		// 세탁기
	private int wifi;			// 무선인터넷
	private int workroom;		// 업무전용공간
	private int babybed;		// 아기침대
	private int dryer;			// 건조기
	private int breakfast;		// 조식제공
	private int tv;				// tv
	private int ac;				// 에어컨
	private int hairdryer;		// 헤어드라이기
	private int parking;		// 주차
	private int disabled;		// 장애인편의시설
	private int bed;			// 침대수
	private int pet;			// 반려동물동반가능
	private int smoke;			// 흡연가능
	private int evt;			// 이벤트가능
	private int selfcheck;		// 셀프체크인가능
	private String checkin;		// 체크인시간
	private String checkout;	// 체크아웃시간
	private String loadname;	// 도로명주소
	private String detail;		// 상세주소
	private String zipcode;		// 우편번호
	private String mapx;		// x좌표
	private String mapy;		// y좌표
	private String hostIntro;	// 호스트소개
	private String hostRegdate;	// 호스트등록일
	private int star;			// 평균별점

}
