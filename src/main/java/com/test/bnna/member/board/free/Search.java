package com.test.bnna.member.board.free;



public class Search extends paging{

	

	public Search(String page, int totalCount) {
		super(page, totalCount);
		
	}



	private String searchType;
	private String keyword;	

			

	public String getSearchType() {

		return searchType;

	}

	

	public void setSearchType(String searchType) {

		this.searchType = searchType;

	}

	

	public String getKeyword() {

		return keyword;

	}



	public void setKeyword(String keyword) {

		this.keyword = keyword;

	}

}
