package com.test.bnna.member.board.blackboard;

import java.util.List;

/**
 * 회원과 관련된 인터페이스
 * @author 김주혁
 *
 */
public interface IMemberDAO {

	List<MemberDTO> list(String condition, String keyword);

}
