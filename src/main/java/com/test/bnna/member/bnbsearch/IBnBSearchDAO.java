package com.test.bnna.member.bnbsearch;

import java.util.List;

public interface IBnBSearchDAO {
	
	List<BnBSearchResultDTO> list(SearchConditionDTO dto);

}
