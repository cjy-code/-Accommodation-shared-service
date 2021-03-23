package com.test.bnna.member.bnbsearch;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 관심숙소DB에 접근하는 DAO입니다.
 * @author 조아라
 *
 */
@Repository("bnbsearchDibsDAO")
public class DibsDAO implements IDibsDAO {
	
	@Autowired
	private SqlSessionTemplate template;
	
	/**
	 * 관심숙소를 리스트형식으로 가져옵니다.
	 */
	@Override
	public List<DibsDTO> list(String seqMember) {
		
		return template.selectList("search.dibsList", seqMember);
	}

}
