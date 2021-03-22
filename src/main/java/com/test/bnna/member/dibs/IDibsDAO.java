package com.test.bnna.member.dibs;

import java.util.HashMap;
import java.util.List;

public interface IDibsDAO {

	int getTotalCount();

	List<DibsDTO> list(HashMap<String, String> map);

	int del(String seq);


}
