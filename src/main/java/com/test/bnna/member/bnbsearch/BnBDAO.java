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
public class BnBDAO implements IBnBDAO {
	
	@Autowired
	private SqlSessionTemplate template;

	/**
	 * 개별 숙소정보를 가져오는 메서드입니다.
	 */
	@Override
	public BnBDTO getInfo(String seq) {
		
		return template.selectOne("search.info", seq);
	}

	/**
	 * 숙소번호에 따른 숙소이미지를 가져오는 메서드입니다.
	 */
	@Override
	public List<BnBPicDTO> getPic(String seq) {
		
		return template.selectList("search.image", seq);
	}

	/**
	 * 전체 숙소 개수를 반환하는 메서드입니다.
	 */
	@Override
	public int getTotalCount(String seq) {
		
		return template.selectOne("review.getTotalCount", seq);
	}

}
