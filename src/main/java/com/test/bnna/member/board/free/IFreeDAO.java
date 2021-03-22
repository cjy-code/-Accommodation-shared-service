package com.test.bnna.member.board.free;

import java.util.HashMap;
import java.util.List;

public interface IFreeDAO {

	List<FreeDTO> list(HashMap<String, String> map);

	FreeDTO get(int seq);

	int add(FreeDTO dto);

	int edit(FreeDTO dto);

	int del(int seq);

//	int total();

	int total(HashMap<String, String> map);
	
}
