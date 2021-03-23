package com.test.bnna.member.dibs;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 관심숙소 관련 메서드들을 담은 DAO입니다.
 * @author 김다은
 *
 */
@Repository("dibsDibsDAO")
public class DibsDAO implements IDibsDAO {
	
	@Autowired
	private SqlSessionTemplate template;
	

	@Override
	public int getTotalCount() {
		
		return template.selectOne("dibs.gettotalcount");
	}
	
	
	@Override
	public List<DibsDTO> list(HashMap<String, String> map) {

		return template.selectList("dibs.list", map);
	}
	
	
	@Override
	public int del(String seq) {

		return template.delete("dibs.del", seq);
	}

}
