package com.test.bnna.member.bnbsearch;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 숙소DB에 접근하는 DAO입니다.
 * @author 조아라
 *
 */
@Repository
public class BnBSearchDAO implements IBnBSearchDAO {
	
	@Autowired
	private SqlSessionTemplate template;

	/**
	 * 숙소정보를 리스트형식으로 가져옵니다.
	 */
	@Override
	public List<BnBSearchResultDTO> list(SearchConditionDTO dto) {
		
		return template.selectList("search.list", dto);
	}

}
