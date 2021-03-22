package com.test.bnna.member.board.blackboard;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 회원과 관련된 DB 작업 담당 DAO
 * @author 김주혁
 *
 */
@Repository
public class MemberDAO implements IMemberDAO {

	@Autowired
	private SqlSessionTemplate template;
	
	@Override
	public List<MemberDTO> list(String condition, String keyword) {

		HashMap<String,String> map = new HashMap<String,String>();
		
		if (condition.equals("이름")) {
			condition = "name";
		} else {
			condition = "id";
		}
		
		map.put("condition", condition);
		map.put("keyword", keyword);
		
		return template.selectList("blackboardMember.list", map);
	}
	
}
