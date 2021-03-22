package com.test.bnna.admin.board.tripinfo;

import lombok.Data;
/**
 * 여행정보 게시판 사진 테이블 DTO입니다.
 * @author 김다은
 *
 */
@Data
public class TripInfoImgDTO {
	
	private String seq;				//여행정보게시판 사진 번호
	private String seqTripInfo;		//여행정보게시판 번호
	private String image;			//사진이름 (DB 저장되는 이름)
	private String orgimage;		//원본사진이름	(폴더에 저장되는 이름)
	
	
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
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getOrgimage() {
		return orgimage;
	}
	public void setOrgimage(String orgimage) {
		this.orgimage = orgimage;
	}
	
	
	

}
