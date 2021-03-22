<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- <link href="/resources/css/free.css" rel="stylesheet"> -->  

<link rel="stylesheet" href="/bnna/resources/css/member/board/free.css">


<section class="contentsection page-start">

	<div id="menupath">
    	게시판
    	<span class="glyphicon glyphicon-menu-right" style="position:static; z-index: -1;"></span>
    	자유게시판
	</div>
	<hr style="margin-top: -5px;">
	
	
	<h1>자유게시판</h1>
	
	<c:set var="cnt" />
	
	<c:if test="${not empty keyword}">
		<span>검색하신 '<b>${keyword}</b>'의 결과는 '${totalboard}'개 입니다.</span> 
	</c:if> 
	<table class="table table" >	
		<tr>
			<th style="width:50px;">번호</th>
			<th>주제</th>
			<th>글쓴이</th>
			<th>등록일</th>
			<th>조회수</th>
		</tr> 
	 	<c:forEach items="${list}" var="dto">
		<tr>
			<td>${dto.seq}</td>
			<td><a href="/bnna/member/board/free/view.action?seq=${dto.seq}">${dto.title}</a></td>
			<td>${dto.id}</td>
			<td>${dto.regdate.substring(0, 10)}</td>
			<td>${dto.cnt}</td><!-- 조회수자리 -->
		</tr>
		<c:set var="cnt" value="${dto.cnt}" />
		</c:forEach> 
	</table>
		
		<div class="btns">
			<select nema="searchType" id="searchType">
				<option value="title" <c:out value="${searchCriteria.searchType eq 'title' ? 'selected' : 'null'}"/>>제목</option>
				<option value="content" <c:out value="${searchCriteria.searchType eq 'content' ? 'selected' : 'null'}"/>>내용</option>
				<option value="id" <c:out value="${searchCriteria.searchType eq 'id' ? 'selected' : 'null'}"/>>작성자</option>
		   <%-- <option value="tc" <c:out value="${searchCriteria.searchType eq 't' ? 'selected' : ''}"/>>주제+내용</option>
				<option value="cw" <c:out value="${searchCriteria.searchType eq 't' ? 'selected' : ''}"/>>내용+작성자</option> --%>
			</select>
			<input  type="text" id="keyword">
			<button type="button" id="btnSearch" class="btn btn-warning">검색</button>
			<button type="button" id="btnWrite" class="btn btn-success" onclick="location.href='/bnna/member/board/free/add.action?cnt=${cnt}';">쓰기</button>
		</div>
		<c:if test="${not empty list}">
			<div class="pagebox">
					<nav class="pagebar">
						<ul class="pagination">${pagebar}</ul>
					</nav>
			</div>
		</c:if>		
				
				
		
	
		
</section class="contentsection">

<script src="/bnna/resources/js/free.js"></script>