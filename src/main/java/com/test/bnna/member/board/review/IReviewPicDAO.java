package com.test.bnna.member.board.review;

import java.util.ArrayList;
import java.util.List;

public interface IReviewPicDAO {

	List<ReviewPicDTO> list(String seq);

	int addReviewPic(ArrayList<ReviewPicDTO> plist);

	void del(String path, String seq);

	List<ReviewPicDTO> listByBnB(String seq);

}
