package com.test.bnna.member.board.qna;

import lombok.Data;

/**
 * 
 * QNA 게시판 DTO
 *
 */
@Data
public class QnaDTO {
	
	private String seq;
	private String seqmember;
	private String title;
	private String content;
	private String date;
	private String id;
	private String category;
	private String regdate;
	private String num;
	private String rownum;
	
	
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getSeqmember() {
		return seqmember;
	}
	public void setSeqmember(String seqmember) {
		this.seqmember = seqmember;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	@Override
	public String toString() {
		return "QnaDTO [seq=" + seq + ", seqmember=" + seqmember + ", title=" + title + ", content=" + content
				+ ", date=" + date + ", id=" + id + ", category=" + category + ", regdate=" + regdate + "]";
	}
	
	
	

}
