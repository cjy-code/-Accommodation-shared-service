package com.test.bnna.member.board.blackboard;

import lombok.Data;

/**
 * 블랙리스트 신고게시판 댓글 DTO
 * @author 김주혁
 *
 */
@Data
public class BlackBoardCmtDTO {

	private String seq;
	private String seqBlackBoard;
	private String seqMember;
	private String content;
	private String regdate;
	
	private String id;
	private String name;
	
}
