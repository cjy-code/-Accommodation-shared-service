package com.test.bnna.admin.board.tripinfo;

import lombok.Data;


/**
 * 여행정보 게시판 댓글 DTO입니다.
 * @author 김다은
 *
 */
@Data
public class TripInfoCmtDTO {
	
	private String seq;			//댓글번호
	private String seqMember;	//회원번호
	private String seqTripInfo;	//글번호
	private String ccontent;	//댓글내용
	private String regdate;		//댓글 작성일
	
	//회원 정보 관련 추가
	private String pic;			//회원사진
	private String id;			//회원아이디
	
	
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getSeqMember() {
		return seqMember;
	}
	public void setSeqMember(String seqMember) {
		this.seqMember = seqMember;
	}
	public String getSeqTripInfo() {
		return seqTripInfo;
	}
	public void setSeqTripInfo(String seqTripInfo) {
		this.seqTripInfo = seqTripInfo;
	}
	public String getCcontent() {
		return ccontent;
	}
	public void setCcontent(String ccontent) {
		this.ccontent = ccontent;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	
}
