package com.test.bnna.admin.board.review;

import java.util.HashMap;
import java.util.List;

public interface IReviewForAdminDAO {

	List<ReviewForAdminDTO> list(HashMap<String, String> map);

	ReviewForAdminDTO info(String seq);

	int del(String path, String seq);

	int getTotalCount();

}
