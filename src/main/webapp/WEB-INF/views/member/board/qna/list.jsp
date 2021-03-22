<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<section class="contentsection page-start">

	<!-- menu path - 메뉴 경로 표기 -->
	<div id="menupath">
    	게시판
    	<span class="glyphicon glyphicon-menu-right" style="position:static; z-index: -1;"></span>
    	Q&A게시판
	</div>
	<hr style="margin-top: -5px;">
	<!-- 여기까지 menu path -->
                
	<h1>Q&A 게시판</h1>
		<!-- //http://localhost:8090/bnna/member/board/qna/bnna/member/board/qna/list.action?id=air0001&seqmember=1 -->
		<button type="button" class="btn-general" onclick="location.href='/bnna/member/board/qna/add.action'">작성하기</button>
		<div style="float:right; margin-left:10px;"><a href="/bnna/member/board/qna/login.action?id=air0001&seqmember=1" class="btn-general">로그인</a></div>
		<div style="float:right; margin-right:10px;"><a href="/bnna/member/board/qna/logout.action" class="btn-general">로그아웃</a></div>
		<%-- <div>${empty id ? "익명" : id}</div>		 --%>
		<%-- <div>${empty seqmember ? "익명" : seqmember}</div>		 --%>
		
		<table class="table table-bordered">
			<tr>
				<th>번호</th>
				<th>질문유형</th>
				<th>작성자</th>
				<th>제목</th>
				<th>작성일</th>
			</tr>
			<c:forEach items="${list}" var="dto">
			<tr>
			<td>${dto.num}</td>
			<td>${dto.category}</td>
			<td>${dto.id}</td>
			<td><a href="/bnna/member/board/qna/view.action?seq=${dto.seq}">${dto.title}</a></td>
			<td>${dto.regdate.substring(0, 10)}</td>
			</tr>
			</c:forEach>
		</table>
	
		<div class=pagearea>
		    <div class="pagination">
		        <a href="#">&laquo;</a>
		        <a href="#">1</a>
		        <a href="#" class="active">2</a>
		        <a href="#">3</a>
		        <a href="#">4</a>
		        <a href="#">5</a>
		        <a href="#">6</a>
		        <a href="#">&raquo;</a>
		    </div>
		</div>
		
		<%-- <div style="display: block; text-align: center;">		
		<c:if test="${paging.startPage != 1 }">
			<a href="/bnna/member/board/qna/list?nowPage=${paging.startPage - 1 }&cntPerPage=${paging.cntPerPage}">&lt;</a>
		</c:if>
		<c:forEach begin="${paging.startPage }" end="${paging.endPage }" var="p">
			<c:choose>
				<c:when test="${p == paging.nowPage }">
					<b>${p }</b>
				</c:when>
				<c:when test="${p != paging.nowPage }">
					<a href="/bnna/member/board/qna/list?nowPage=${p }&cntPerPage=${paging.cntPerPage}">${p }</a>
				</c:when>
			</c:choose>
		</c:forEach>
		<c:if test="${paging.endPage != paging.lastPage}">
			<a href="/bnna/member/board/qna/list?nowPage=${paging.endPage+1 }&cntPerPage=${paging.cntPerPage}">&gt;</a>
		</c:if>
	</div> --%>

</section class="contentsection">