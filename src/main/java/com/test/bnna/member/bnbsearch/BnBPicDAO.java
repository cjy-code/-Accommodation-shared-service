package com.test.bnna.member.bnbsearch;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 숙소이미지DB에 접근하는 DAO입니다.
 * @author 조아라
 *
 */
@Repository
public class BnBPicDAO implements IBnBPicDAO{
	
	@Autowired
	private SqlSessionTemplate template;

	/**
	 * 숙소사진리스트를 가져오는 메서드입니다.
	 * 검색결과에 보여줄 숙소대표사진 1개만을 가져옵니다.
	 */
	@Override
	public List<BnBPicDTO> picList(List<BnBSearchResultDTO> list) {
		
		// 1. 가져온 리스트에서 번호를 하나씩 꺼내서
		// 2. 그 번호에 맞는 사진을 가져오고
		// 3. 리스트에 추가해서 리턴
		List<BnBPicDTO> plist=new ArrayList<BnBPicDTO>();
		List<BnBPicDTO> all=new ArrayList<BnBPicDTO>();
		
		for (BnBSearchResultDTO dto:list) {
			all=template.selectList("search.plist", dto.getSeq()); // 가져온 사진 다 담아주고
			plist.add(all.get(0)); // 첫번째만 넣어준다.
		}
		
		return plist;
	}

	/**
	 * 숙소 한 개의 사진 여러 개를 가져오는 메서드입니다.
	 * @param seq 숙소번호입니다.
	 * @return 숙소이미지 정보를 담고 있는 DTO들의 리스트를 반환합니다.
	 */
	public List<BnBPicDTO> pic(int seq) {
		
		return template.selectList("search.plist", seq);
	}

}
