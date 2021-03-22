package com.test.bnna.member.board.blackboard;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 블랙리스트 신고게시판 댓글과 관련된 DB 작업 담당 DAO
 * @author 김주혁
 *
 */
@Repository
public class BlackBoardCmtDAO implements IBlackBoardCmtDAO {

	@Autowired
	private SqlSessionTemplate template;
	
	@Override
	public int add(BlackBoardCmtDTO dto) {

		return template.insert("blackboardcmt.add", dto);
	}
	
	@Override
	public BlackBoardCmtDTO getAddCmt() {
		
		return template.selectOne("blackboardcmt.getAddCmt");
	}
	
	@Override
	public int del(String seqBlackBoardCmt) {
		
		return template.delete("blackboardcmt.del", seqBlackBoardCmt);
	}
	
	@Override
	public BlackBoardCmtDTO get(String seq) {
		
		return template.selectOne("blackboardcmt.get", seq);
	}
	
}
