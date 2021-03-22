package com.test.bnna.admin.board.tripinfo;

import java.util.HashMap;
import java.util.List;

public interface ITripInfoDAO {

	List<TripInfoDTO> list(HashMap<String, String> map);

	TripInfoDTO view(String seq);

	List<TripInfoCmtDTO> cmtlist(String seq);

	int write(TripInfoDTO dto);

	String getTripInfoSeq();

	TripInfoDTO get(String seq);

	int edit(TripInfoDTO dto);

	int del(String path, String seq);

	int getThread();

	void updateThread(HashMap<String, Integer> map);

	List<TripInfoDTO> rlist(int thread);

	int getTotalCount(HashMap<String, String> map);

	void updateReadcount(String seq);

}
