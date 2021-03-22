package com.test.bnna.admin.board.review;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 관리자용으로 리뷰이미지DB에 접근하는 DAO입니다.
 * @author 조아라
 *
 */
@Repository
public class ReviewPicForAdminDAO implements IReviewPicForAdminDAO {

	@Autowired
	private SqlSessionTemplate template;
	
	/**
	 * 하나의 리뷰에 첨부된 리뷰이미지들을 가져오는 메서드입니다.
	 */
	@Override
	public List<ReviewPicForAdminDTO> list(String seq) {
		return template.selectList("review.plistForAdmin", seq);
	}

}
