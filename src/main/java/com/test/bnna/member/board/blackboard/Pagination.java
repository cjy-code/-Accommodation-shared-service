package com.test.bnna.member.board.blackboard;

import java.util.HashMap;

/**
 * 페이징 작업 클래스
 * @author 김주혁
 *
 */
public class Pagination {

	private int nowPage;		//현재 페이지 번호
	private int pageSize = 15;		//한페이지 당 출력 개수
	private int totalPage;		//총 페이지 수
	private int begin;			//rnum 시작 번호
	private int end;			//rnum 끝 번호
	private int num;				//페이지바 관련 변수
	private int loop;			//페이지바 관련 변수
	private int blockSize = 5;		//페이지바 관련 변수
	
	private String pagebar = "";
	
	/**
	 * Pagination Constructor
	 * @param page 페이지번호
	 * @param totalCount 총 게시글 수
	 */
	public Pagination(String page, int totalCount) {
		
		if (page == null || page == "") {
			nowPage = 1;
		} else {
			nowPage = Integer.parseInt(page);
		}
		
		begin = ((nowPage - 1) * pageSize) + 1;
		end = begin + pageSize - 1;
		
		totalPage = (int)Math.ceil((double)totalCount / pageSize);
		
		loop = 1;
		num = ((nowPage - 1) / blockSize) * blockSize + 1;
		
	} //생성자

	/**
	 * 페이지바 만드는 메서드
	 * @param map 검색조건과 검색어를 담은 객체
	 * @return 동적으로 생성된 페이지바
	 */
	public String getPagebar(HashMap<String, String> map) {
		
		//이전 10페이지
		if (num == 1) {
			pagebar += String.format("<li class='disabled'>" + 
					"		    <a href=\"#!\" aria-label=\"Previous\">" + 
					"		        <span aria-hidden=\"true\">&laquo;</span>" + 
					"		    </a>" + 
					"		</li>");
		} else {
			if (map.get("keyword") != null) {
				//검색어 있는 경우
				pagebar += String.format("<li>" + 
						"		    <a href=\"/bnna/member/board/blackboard/list.action?page=%d&condition=%s&keyword=%s\" aria-label=\"Previous\">" + 
						"		        <span aria-hidden=\"true\">&laquo;</span>" + 
						"		    </a>" + 
						"		</li>"
						, num - 1
						, map.get("condition")
						, map.get("keyword"));
			} else {
				//검색어 없는 경우
				pagebar += String.format("<li>" + 
						"		    <a href=\"/bnna/member/board/blackboard/list.action?page=%d\" aria-label=\"Previous\">" + 
						"		        <span aria-hidden=\"true\">&laquo;</span>" + 
						"		    </a>" + 
						"		</li>", num - 1);				
			}
		}
		
		while (!(loop > blockSize || num > totalPage)) {
			
			if (nowPage == num) {
				pagebar += "<li class='active'>";
			} else {
				pagebar += "<li>";
				
			}
			if (map.get("keyword") != null) {
				pagebar += String.format("<a href=\"/bnna/member/board/blackboard/list.action?page=%d&condition=%s&keyword=%s\">%d</a></li> "
						, num
						, map.get("condition")
						, map.get("keyword")
						, num);
			} else {
				pagebar += String.format("<a href=\"/bnna/member/board/blackboard/list.action?page=%d\">%d</a></li> ", num, num);
			}
			
			loop++;
			num++;
		}
		
		//다음 10페이지로 이동
		if (num > totalPage) {
			pagebar += String.format("<li class='disabled'>" + 
					"		    <a href=\"#!\" aria-label=\"Next\">" + 
					"		        <span aria-hidden=\"true\">&raquo;</span>" + 
					"		    </a>" + 
					"		</li>");			
		} else {
			if (map.get("keyword") != null) {
				pagebar += String.format("<li>" + 
						"		    <a href=\"/bnna/member/board/blackboard/list.action?page=%d&condition=%s&keyword=%s\" aria-label=\"Next\">" + 
						"		        <span aria-hidden=\"true\">&raquo;</span>" + 
						"		    </a>" + 
						"		</li>"
						, num
						, map.get("condition")
						, map.get("keyword"));			
				
			} else {
				pagebar += String.format("<li>" + 
						"		    <a href=\"/bnna/member/board/blackboard/list.action?page=%d\" aria-label=\"Next\">" + 
						"		        <span aria-hidden=\"true\">&raquo;</span>" + 
						"		    </a>" + 
						"		</li>", num);			
				
			}
		}	
		
		return pagebar;
	}

	public int getBegin() {
		return begin;
	}

	public int getEnd() {
		return end;
	}
	
	public int getNowPage() {
		return nowPage;
	}
	
}
