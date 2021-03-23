package com.test.bnna.member.bnbsearch;

import java.util.List;

public interface IBnBDAO {

	BnBDTO getInfo(String seq);

	List<BnBPicDTO> getPic(String seq);

	int getTotalCount(String seq);

}
