package com.test.bnna.member.board.review;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 리뷰이미지DB에 접근하는 DAO입니다.
 * @author 조아라
 *
 */
@Repository
public class ReviewPicDAO implements IReviewPicDAO {
	
	@Autowired
	private SqlSessionTemplate template;

	/**
	 * 리뷰번호에 따라 그 리뷰에 첨부된 이미지들을 가져오는 메서드입니다.
	 */
	@Override
	public List<ReviewPicDTO> list(String seq) {
		
		return template.selectList("review.plist", seq);
	}

	/**
	 * 리뷰이미지정보를 DB에 추가하는 메서드입니다.
	 */
	@Override
	public int addReviewPic(ArrayList<ReviewPicDTO> plist) {
		
		int result=0;
		for (int i=0; i<plist.size(); i++) {
			template.insert("review.addReviewPic", plist.get(i));
			result++;
		}
		return result;
	}

	/**
	 * 리뷰이미지를 삭제하는 메서드입니다.
	 */
	@Override
	public void del(String path, String seq) {
		
		// 파일을 삭제해야하는데...
		// 1. 먼저 DB에서 삭제한다.
		// 2. 이미지파일 삭제한다.
		
		try {
			
			// 삭제할 파일이름을 가져온다.
			String filename=template.selectOne("review.getDeleteFileName", seq);
			// 1.
			template.delete("review.delSpecificPic", seq);
			
			File file=new File(path+filename);
			file.delete();
			
		} catch (Exception e) {
			System.out.println("ReviewPicDAO.del()");
			e.printStackTrace();
		}
		
	}

	/**
	 * 숙소번호로 그 숙소의 리뷰의 이미지들을 가져오는 메서드입니다.
	 */
	@Override
	public List<ReviewPicDTO> listByBnB(String seq) {
		
		return template.selectList("review.listByBnB", seq);
	}

}
