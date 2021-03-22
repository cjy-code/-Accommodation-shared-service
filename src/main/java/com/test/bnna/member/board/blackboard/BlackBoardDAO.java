package com.test.bnna.member.board.blackboard;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 블랙리스트 신고게시판과 관련된 DB 작업 담당 DAO
 * @author 김주혁
 *
 */
@Repository
public class BlackBoardDAO implements IBlackBoardDAO {

	@Autowired
	private SqlSessionTemplate template;
	
	@Override
	public BlackBoardDTO get(String seq) {
		
		return template.selectOne("blackboard.get", seq);
	}
	
	@Override
	public List<BlackBoardImgDTO> getImages(String seq) {
		
		return template.selectList("blackboard.getImages", seq);
	}
	
	@Override
	public List<BlackBoardCmtDTO> getComments(String seq) {
		
		return template.selectList("blackboard.getComments", seq);
	}
	
	@Override
	public int getThread() {

		return template.selectOne("blackboard.getThread");
	}
	
	@Override
	public void updateThread(HashMap<String, Integer> map) {
		
		template.update("blackboard.updateThread", map);
		
	}
	
	@Override
	public int addok(BlackBoardDTO dto) {

		return template.insert("blackboard.addok", dto);
	}
	
	@Override
	public String getAddSeq() {

		return template.selectOne("blackboard.getAddSeq");
	}
	
	@Override
	public int del(String addSeqBlackBoard) {
		
		return template.delete("blackboard.del", addSeqBlackBoard);
		
	}
	
	@Override
	public List<BlackBoardDTO> list(HashMap<String, String> map) {

		return template.selectList("blackboard.list", map);
	}
	
	@Override
	public int getCount(HashMap<String, String> map) {

		return template.selectOne("blackboard.getCount", map);
	}
	
	@Override
	public List<String> getSeqHasImage() {

		return template.selectList("blackboard.getSeqHasImage");
	}
	
	@Override
	public void updateReadCnt(String seq) {
		
		template.update("blackboard.updateReadCnt", seq);
	}
	
	@Override
	public boolean hasReply(String seq) {
		
		if ((int)template.selectOne("blackboard.hasReply", seq) != 0) {
			return true;
		} else {
			return false;
		}
		
	}
	
	@Override
	public boolean hasComment(String seq) {
		
		if ((int)template.selectOne("blackboard.hasComment", seq) != 0) {
			return true;
		} else {
			return false;
		}
		
	}
	
	@Override
	public int editok(BlackBoardDTO dto) {
		
		return template.update("blackboard.editok", dto);
	}
	
}
