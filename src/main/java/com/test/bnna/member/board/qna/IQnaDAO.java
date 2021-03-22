package com.test.bnna.member.board.qna;

import java.util.List;

/**
 * 
 * Q&A 게시판 DAO 인터페이스
 *
 */
public interface IQnaDAO {

	List<QnaDTO> list();

	int add(QnaDTO dto);

	QnaDTO get(String seq);

	int del(String seq);

	int edit(QnaDTO dto);
	
	/*
	 * public int countBoard();
	 * 
	 * public List<QnaDTO> selectBoard(PagingVO vo);
	 */
	
	

}
