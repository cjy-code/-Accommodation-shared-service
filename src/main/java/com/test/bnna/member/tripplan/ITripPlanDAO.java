package com.test.bnna.member.tripplan;

import java.util.HashMap;
import java.util.List;

public interface ITripPlanDAO {

	List<TripPlanDTO> list(HashMap<String, String> map);

	int getTotalCount(HashMap<String, String> map);

}
