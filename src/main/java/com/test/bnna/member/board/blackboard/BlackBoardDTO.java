package com.test.bnna.member.board.blackboard;

import lombok.Data;

/**
 * 블랙리스트 신고게시판 DTO
 * @author 김주혁
 *
 */
@Data
public class BlackBoardDTO {

	private String seq;
	private String seqMember;
	private String seqIssueMember;
	private String title;
	private String content;
	private String readcnt;
	private String regdate;
	private String thread;
	private String depth;
	
	private String id;
	private String name;
	
	private String issueMemberId;
	private String issueMemberName;
	
	private String reply;
	
	private boolean hasImage;
	
	private String seqParent;
	
}
