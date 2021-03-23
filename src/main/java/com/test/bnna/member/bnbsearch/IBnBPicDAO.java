package com.test.bnna.member.bnbsearch;

import java.util.List;

public interface IBnBPicDAO {

	List<BnBPicDTO> picList(List<BnBSearchResultDTO> list);

	List<BnBPicDTO> pic(int parseInt);

}
