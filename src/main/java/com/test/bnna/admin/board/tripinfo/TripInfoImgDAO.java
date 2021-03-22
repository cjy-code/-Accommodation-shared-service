package com.test.bnna.admin.board.tripinfo;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TripInfoImgDAO implements ITripInfoImgDAO {
	
	@Autowired
	private SqlSessionTemplate template;
	
	@Override
	public List<TripInfoImgDTO> list(String seq) {
		
		return template.selectList("tripinfo.ilist", seq);
	}
	
	
	@Override
	public int addTripInfoImg(ArrayList<TripInfoImgDTO> ilist) {
	
		int result = 0;
		
		for (int i = 0; i < ilist.size(); i++) {
			
			template.insert("tripinfo.addimage", ilist.get(i));
			
			result++;
		}
		
		return result;
	}



}
