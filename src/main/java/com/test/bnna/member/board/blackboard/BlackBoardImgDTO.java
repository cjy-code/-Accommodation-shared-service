package com.test.bnna.member.board.blackboard;

import lombok.Data;

/**
 * 블랙리스트 신고게시판 사진 DTO
 * @author 김주혁
 *
 */
@Data
public class BlackBoardImgDTO {

	private String seq;
	private String seqBlackBoard;
	private String image;
	private String orgimage;
	
}
