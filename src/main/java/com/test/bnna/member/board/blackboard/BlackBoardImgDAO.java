package com.test.bnna.member.board.blackboard;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 블랙리스트 신고게시판 사진과 관련된 DB 작업 담당 DAO
 * @author 김주혁
 *
 */
@Repository
public class BlackBoardImgDAO implements IBlackBoardImgDAO {

	@Autowired
	private SqlSessionTemplate template;
	
	@Override
	public int add(List<BlackBoardImgDTO> ilist) {
		
		int result = 0;
		
		for (BlackBoardImgDTO idto : ilist) {
			result += template.insert("blackboardimg.add", idto);
		}
		
		return result;
	}
	
	@Override
	public void del(String seq) {

		template.delete("blackboardimg.del", seq);		
	}
	
	@Override
	public boolean hasImage(String seq) {
		
		if ((int)template.selectOne("blackboardimg.hasImage", seq) != 0) {
			return true;
		} else {
			return false;			
		}
		
	}
	
}
