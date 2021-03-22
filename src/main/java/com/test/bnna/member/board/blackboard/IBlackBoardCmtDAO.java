package com.test.bnna.member.board.blackboard;

/**
 * 블랙리스트 신고게시판 댓글과 관련된 인터페이스
 * @author 김주혁
 *
 */
public interface IBlackBoardCmtDAO {

	int add(BlackBoardCmtDTO dto);

	BlackBoardCmtDTO getAddCmt();

	int del(String seqBlackBoardCmt);

	BlackBoardCmtDTO get(String seq);

}
