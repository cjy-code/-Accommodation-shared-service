package com.test.bnna.member.board.free;

import java.util.HashMap;

/**
 * 자유 게시판 페이징
 * @author 최진영
 *
 */
public class paging {
	
	
	private static int nowPage;	//현재 페이지 번호
	private int pageSize = 10;	//한페이지 당 출력 개수
	private static int totalPage;		//총 페이지 수
	private static int begin;	//rnum 시작 번호
	private static int end;		//rnum 끝 번호
	private static int num;			//페이지바 관련 변수
	private static int loop;			//페이지바 관련 변수
	private static int blockSize = 5;	//페이지바 관련 변수
	
	private static String pagebar = "";
	
	public paging(String page, int totalCount) {
		
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
	
	
	public static String getPagebar(HashMap<String, String> map) {
		
		pagebar = "";
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
								"		    <a href=\"/bnna/member/board/free/list.action?page=%d&condition=%s&keyword=%s\" aria-label=\"Previous\">" + 
								"		        <span aria-hidden=\"true\">&laquo;</span>" + 
								"		    </a>" + 
								"		</li>"
								, num - 1
								, map.get("condition")
								, map.get("keyword"));
					} else {
						//검색어 없는 경우
						pagebar += String.format("<li>" + 
								"		    <a href=\"/bnna/member/board/free/list.action?page=%d\" aria-label=\"Previous\">" + 
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
						pagebar += String.format("<a href=\"/bnna/member/board/free/list.action?page=%d&condition=%s&keyword=%s\">%d</a></li> "
								, num
								, map.get("condition")
								, map.get("keyword")
								, num);
					} else {
						pagebar += String.format("<a href=\"/bnna/member/board/free/list.action?page=%d\">%d</a></li> ", num, num);
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
								"		    <a href=\"/bnna/member/board/free/list.action?page=%d&condition=%s&keyword=%s\" aria-label=\"Next\">" + 
								"		        <span aria-hidden=\"true\">&raquo;</span>" + 
								"		    </a>" + 
								"		</li>"
								, num
								, map.get("condition")
								, map.get("keyword"));			
						
					} else {
						pagebar += String.format("<li>" + 
								"		    <a href=\"/bnna/member/board/free/list.action?page=%d\" aria-label=\"Next\">" + 
								"		        <span aria-hidden=\"true\">&raquo;</span>" + 
								"		    </a>" + 
								"		</li>", num);			
						
					}
				}	
		return pagebar;
	} 

	
//	public static String getPagebar() {
//		return pagebar;
//	}

	public static int getBegin() {
		return begin;
	}

	public static int getEnd() {
		return end;
	}
	
	public static int getNowPage() {
		return nowPage;
	}

	



	
	
}
