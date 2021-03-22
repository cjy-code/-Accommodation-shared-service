package com.test.bnna.member.tripplan;

public class PlanDetailDTO {

	private String seq; 			//여행 세부일정번호
	private String seqTripInfo;		//여행 일정번호
	private String seqMember;		//회원 번호
	private String title;			//장소이름
	private String addr;			//주소
	private String img;				//사진
	private String mapX;			//주소 x값
	private String mapY;			//주소 y값
	private String planDay;			//여행 몇째날인지?
	private String planNo;			//일정순서
	private String info;			//장소설명
	
	
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getSeqTripInfo() {
		return seqTripInfo;
	}
	public void setSeqTripInfo(String seqTripInfo) {
		this.seqTripInfo = seqTripInfo;
	}
	public String getSeqMember() {
		return seqMember;
	}
	public void setSeqMember(String seqMember) {
		this.seqMember = seqMember;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getMapX() {
		return mapX;
	}
	public void setMapX(String mapX) {
		this.mapX = mapX;
	}
	public String getMapY() {
		return mapY;
	}
	public void setMapY(String mapY) {
		this.mapY = mapY;
	}
	public String getPlanDay() {
		return planDay;
	}
	public void setPlanDay(String planDay) {
		this.planDay = planDay;
	}
	public String getPlanNo() {
		return planNo;
	}
	public void setPlanNo(String planNo) {
		this.planNo = planNo;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	
	
	
}
