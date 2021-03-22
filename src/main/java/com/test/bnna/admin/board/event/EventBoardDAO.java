package com.test.bnna.admin.board.event;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EventBoardDAO implements IEventBoardDAO {
	
	@Autowired
	private SqlSessionTemplate template;

	@Override
	public List<EventBoardDTO> list(HashMap<String, String> map) {
		
		return template.selectList("eventboard.list", map);
	}

	@Override
	public EventBoardDTO get(String seq) {
		
		return template.selectOne("eventboard.get", seq);
		
	}

	@Override
	public List<EventCmtDTO> clist(String seq) {
		
		return template.selectList("eventboard.clist", seq);
	}

	@Override
	public int add(EventBoardDTO dto) {
		
		return template.insert("eventboard.add",dto);
	}

	@Override
	public int edit(EventBoardDTO dto) {
		
		return template.update("eventboard.edit",dto);
	}

	@Override
	public int del(String seq) {
		
		return template.delete("eventboard.del",seq);
	}

	@Override
	public int delcomment(String seq) {
		return template.delete("eventboard.delcomment",seq);
	}

	
}
