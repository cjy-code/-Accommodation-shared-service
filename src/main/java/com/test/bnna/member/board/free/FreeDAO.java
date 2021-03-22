package com.test.bnna.member.board.free;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 자유 게시판 DAO
 * @author 최진영 
 *
 */
@Repository
public class FreeDAO implements IFreeDAO {

	@Autowired
	private SqlSessionTemplate template;
	
	
	@Override
	public List<FreeDTO> list(HashMap<String, String> map) {
		
		return template.selectList("free.list",map);
	}

	
//	/**
//	 * 게시판 리스트 불러오기
//	 */
//	@Override
//	public List<FreeDTO> list() {
//		
//		return template.selectList("free.list");
//	}
	
	/**
	 * 게시물 상세보기
	 */
	@Override
	public FreeDTO get(int seq) {
	
		return template.selectOne("free.get", seq);
	}
	
	/**
	 * 게시물 추가하기
	 */
	@Override
	public int add(FreeDTO dto) {
		
		return template.insert("free.add", dto);
	}
	
	/**
	 * 게시물 수정하기
	 */
	@Override
	public int edit(FreeDTO dto) {
		
		return template.update("free.edit", dto);
	}
	
	/**
	 * 게시물 삭제하기
	 */
	@Override
	public int del(int seq) {
		
		return template.delete("free.del", seq);
	}
	
	/**
	 * 전체 게시물 개수 조회
	 */
	@Override
	public int total(HashMap<String, String> map) {
		
		return template.selectOne("free.total", map);
	}
	
//	@Override
//	public int total() {
//		
//		return template.selectOne("free.total");
//	}
}
