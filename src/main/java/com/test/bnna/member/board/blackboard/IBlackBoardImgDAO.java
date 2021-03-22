package com.test.bnna.member.board.blackboard;

import java.util.List;

/**
 * 블랙리스트 신고게시판 사진과 관련된 인터페이스
 * @author 김주혁
 *
 */
public interface IBlackBoardImgDAO {

	int add(List<BlackBoardImgDTO> ilist);

	void del(String seq);

	boolean hasImage(String seq);

}
