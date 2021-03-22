package com.test.bnna.admin.board.event;

import lombok.Data;

@Data
/**
 * 이벤트 게시판 DTO
 * @author 오수경
 *
 */
public class EventBoardDTO {
	
	private String seq;
	private String seqAdmin;
	private String content;
	private String title;
	private String regdate;
	private String id;
	private String pw;
	private String readcnt;
	private String image;
	private String orgImage;
	private int thread;
	private int depth;
}
