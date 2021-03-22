package com.test.bnna.member.mileage;

import lombok.Data;

@Data
public class MileageDTO {
	
	private String seq;
	private String seqMember;
	private String seqPay;
	private int totalM;
	private int useM;
	private int saveM;
	private String regdate;
	
	
	//Member 정보 관련 추가
	private String id;
	private String name;
	private String memdate;
	private String pic;
	
	
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
	public String getSeqPay() {
		return seqPay;
	}
	public void setSeqPay(String seqPay) {
		this.seqPay = seqPay;
	}
	public int getTotalM() {
		return totalM;
	}
	public void setTotalM(int totalM) {
		this.totalM = totalM;
	}
	public int getUseM() {
		return useM;
	}
	public void setUseM(int useM) {
		this.useM = useM;
	}
	public int getSaveM() {
		return saveM;
	}
	public void setSaveM(int saveM) {
		this.saveM = saveM;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMemdate() {
		return memdate;
	}
	public void setMemdate(String memdate) {
		this.memdate = memdate;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}

	
	
}
