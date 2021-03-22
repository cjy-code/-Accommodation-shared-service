package com.test.bnna.member.mileage;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MileageDAO implements IMileageDAO {
	
	@Autowired
	private SqlSessionTemplate template;
		
	@Override
	public List<MileageDTO> list() {

		return template.selectList("mileage.list");
	}
}
