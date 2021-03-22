package com.test.bnna.admin.board.event;

import java.util.HashMap;
import java.util.List;

/**
 * 관리자 이벤트 게시판 인터페이스
 * @author 오수경
 *
 */
public interface IEventBoardDAO {

	List<EventBoardDTO> list(HashMap<String, String> map);

	EventBoardDTO get(String seq);

	List<EventCmtDTO> clist(String seq);

	int add(EventBoardDTO dto);

	int edit(EventBoardDTO dto);

	int del(String seq);

	int delcomment(String seq);

}
