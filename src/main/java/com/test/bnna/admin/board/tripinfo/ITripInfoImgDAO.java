package com.test.bnna.admin.board.tripinfo;

import java.util.ArrayList;
import java.util.List;

public interface ITripInfoImgDAO {

	List<TripInfoImgDTO> list(String seq);

	int addTripInfoImg(ArrayList<TripInfoImgDTO> ilist);


}
