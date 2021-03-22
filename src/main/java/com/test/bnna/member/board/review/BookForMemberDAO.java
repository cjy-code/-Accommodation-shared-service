package com.test.bnna.member.board.review;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 예약DB에 접근하는 DAO입니다.
 * @author 조아라
 *
 */
@Repository
public class BookForMemberDAO implements IBookForMemberDAO {

	@Autowired
	private SqlSessionTemplate template;

	/**
	 * 회원번호로 그 회원의 예약정보를 가져오는 메서드입니다.
	 */
	@Override
	public List<BookForMemberDTO> list(String seqMember) {
		
		return template.selectList("review.blist", seqMember);
	}

}
