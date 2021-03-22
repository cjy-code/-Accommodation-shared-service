package com.test.bnna.member.dibs;

public class DibsDTO {
	
	private String seq;			//관심숙소 번호
	private String seqMember;	//회원번호
	private String seqBnb;		//숙소번호
	
	private String name;		//숙소이름
	private String bathroom;	//욕실수
	private String bedroom;		//침실수
	private String price;		//가격
	private String bnbtype;		//숙소타입
	private String image;		//숙소이미지이름
	
	
	
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
	public String getSeqBnb() {
		return seqBnb;
	}
	public void setSeqBnb(String seqBnb) {
		this.seqBnb = seqBnb;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBathroom() {
		return bathroom;
	}
	public void setBathroom(String bathroom) {
		this.bathroom = bathroom;
	}
	public String getBedroom() {
		return bedroom;
	}
	public void setBedroom(String bedroom) {
		this.bedroom = bedroom;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getBnbtype() {
		return bnbtype;
	}
	public void setBnbtype(String bnbtype) {
		this.bnbtype = bnbtype;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	

}

