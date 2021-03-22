package com.test.bnna.admin.board.tripinfo;

import lombok.Data;

/**
 * 여행정보 게시판 관련 DTO입니다.
 * @author 김다은
 *
 */
@Data
public class TripInfoDTO {
	
	private String seq;			//글번호
	private String seqAdmin;	//관리자번호
	private String subject;		//글제목
	private String content;		//내용
	private String regdate;		//등록일
	private int readcnt;		//조회수
	private int cmtcnt;			//댓글수
	private int thread;			//thread
	private int depth;			//depth
	
	
	
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getSeqAdmin() {
		return seqAdmin;
	}
	public void setSeqAdmin(String seqAdmin) {
		this.seqAdmin = seqAdmin;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public int getReadcnt() {
		return readcnt;
	}
	public void setReadcnt(int readcnt) {
		this.readcnt = readcnt;
	}
	public int getCmtcnt() {
		return cmtcnt;
	}
	public void setCmtcnt(int cmtcnt) {
		this.cmtcnt = cmtcnt;
	}
	public int getThread() {
		return thread;
	}
	public void setThread(int thread) {
		this.thread = thread;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	
	

}
