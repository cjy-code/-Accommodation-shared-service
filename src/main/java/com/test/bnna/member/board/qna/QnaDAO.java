package com.test.bnna.member.board.qna;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 
 * Q&A 게시판 DAO
 *
 */
@Repository
public class QnaDAO implements IQnaDAO {
	
	@Autowired
	private SqlSessionTemplate template;
	
	@Override
	public List<QnaDTO> list() {
		
		return template.selectList("qna.list");
	}

	@Override
	public int add(QnaDTO dto) {
		
		return template.insert("qna.add", dto);
	}

	@Override
	public QnaDTO get(String seq) {
		
		return template.selectOne("qna.get", seq);
	}

	@Override
	public int del(String seq) {
		
		return template.delete("qna.del", seq);
	}

	@Override
	public int edit(QnaDTO dto) {
		
		return template.update("qna.edit", dto);
	}

	/*
	@Override
	public int countBoard() {
		return template.selectOne("qna.countBoard");
	}

	@Override
	public List<QnaDTO> selectBoard(PagingVO vo) {
		
		return template.selectList("qna.list", vo);
	}
	*/
	
	
	

}
