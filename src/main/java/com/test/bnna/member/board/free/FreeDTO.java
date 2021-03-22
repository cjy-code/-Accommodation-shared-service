package com.test.bnna.member.board.free;

import lombok.Data;

/**
 * 자유 게시판 DTO
 * @author 최진영
 *
 */
@Data
public class FreeDTO {
	
	int seq;			//번호
	int seqMember;		//회원번호
	String title;		//주제
	String content;		//내용
	String regdate;		//등록날짜
	int thread;			//
	int depth;			//
	
	String id;			//회원 아이디
	String name;		//회원 이름
	int cnt;			//총 개시물 개수
}
