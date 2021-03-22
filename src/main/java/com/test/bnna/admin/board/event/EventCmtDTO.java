package com.test.bnna.admin.board.event;

import lombok.Data;

@Data
/**
 * 이벤트 게시판 댓글 DTO
 * @author 오수경
 *
 */
public class EventCmtDTO {
	
	private String seq;
	private String seqMember;
	private String seqEvent;
	private String content;
	private String regdate;
	private String id;

}
