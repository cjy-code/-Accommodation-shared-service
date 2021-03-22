package com.test.bnna.admin.board.review;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 관리자용으로 리뷰DB에 접근하는 DAO입니다.
 * @author 조아라
 *
 */
@Repository
public class ReviewForAdminDAO implements IReviewForAdminDAO {
	
	@Autowired
	private SqlSessionTemplate template;

	/**
	 *  관리자용으로 전체 리뷰를 가져오는 메서드입니다.
	 */
	@Override
	public List<ReviewForAdminDTO> list(HashMap<String, String> map) {
		
		return template.selectList("review.allList", map);
	}

	/**
	 * 관리자용으로 개별 리뷰정보를 가져오는 메서드입니다.
	 */
	@Override
	public ReviewForAdminDTO info(String seq) {
		
		return template.selectOne("review.infoForAdmin", seq);
	}

	/**
	 * 리뷰번호로 리뷰글을 삭제하는 메서드입니다.
	 */
	@Override
	public int del(String path, String seq) {
		
		int result=delFiles(path, seq);
			
		template.delete("review.delPicForAdmin", seq);
		return template.delete("review.delForAdmin", seq);
	}
	
	/**
	 * 리뷰번호로 리뷰글에 딸린 이미지들을 서버에서 삭제하는 메서드입니다.
  	 * @param path 파일들이 존재하는 경로입니다.
	 * @param seq 리뷰번호입니다.
	 * @return 성공하면 0이 아닌 값, 실패하면 0을 반환합니다.
	 */
	public int delFiles(String path, String seq) {
		
		int deleteCnt=0;
		
		// 파일명들을 가져온다.
		List<ReviewPicForAdminDTO> list=template.selectList("review.plistForAdmin", seq);
		
		// 삭제한다.
		for(ReviewPicForAdminDTO dto:list) {
			File file=new File(path+dto.getImage());
			file.delete();
			deleteCnt++;
		}
		
		return deleteCnt;
	}

	/**
	 * 리뷰 전체 글수를 가져오는 메서드입니다.
	 */
	@Override
	public int getTotalCount() {
		
		return template.selectOne("review.getTotalCountForAdmin");
	}
}
