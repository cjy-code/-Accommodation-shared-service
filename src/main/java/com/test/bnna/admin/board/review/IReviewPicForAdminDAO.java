package com.test.bnna.admin.board.review;

import java.util.List;

import com.test.bnna.member.board.review.ReviewPicDTO;

public interface IReviewPicForAdminDAO {

	List<ReviewPicForAdminDTO> list(String seq);

}