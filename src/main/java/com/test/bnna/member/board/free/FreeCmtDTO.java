package com.test.bnna.member.board.free;

import lombok.Data;

/**
 * 자유 게시판 DTO
 * @author 최진영
 *
 */
@Data
public class FreeCmtDTO {

	int seq;
	int seqMember;
	int seqFree;
	String content;
	String regdate; 
}
