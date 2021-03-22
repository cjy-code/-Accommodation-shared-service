<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet" href="/bnna/resources/css/blackboard.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />


<section class="contentsection page-start">

	<!-- menu path - 메뉴 경로 표기 -->
	<div id="menupath">
		게시판
		<span class="glyphicon glyphicon-menu-right" style="position: static; z-index: -1;"></span>
		신고게시판
	</div>
	<hr style="margin-top: -5px;">
	<!-- 여기까지 menu path -->

	<!-- 본인 작업 -->
	<div class="board-title">
		<h1 style="float: left;">신고게시판 <small>Blacklist Board</small></h1>
		
		<!-- 로그인/로그아웃 임시 -->
		<c:if test="${empty seqMember}">
			<form method="GET" action="/bnna/member/board/blackboard/login.action">
				<button type="submit" class="btn btn-default" style="float: right; margin-top: 20px;">2번 로그인</button>
				<input type="hidden" id="seqMember" name="seqMember" value="2">
			</form>
			<form method="GET" action="/bnna/member/board/blackboard/login.action">
				<button type="submit" class="btn btn-default" style="float: right; margin-top: 20px; margin-right: 10px;">1번 로그인</button>
				<input type="hidden" id="seqMember" name="seqMember" value="1">
			</form>
		</c:if>
		<c:if test="${not empty seqMember}">
			<button type="button" class="btn btn-default" style="float: right; margin-top: 20px;" onclick="location.href='/bnna/member/board/blackboard/logout.action';">로그아웃</button>
		</c:if>
		<div style="clear: both;"></div>
	</div>	
	
	<c:if test="${empty keyword}">
		<h6>총 게시글 : ${totalCount}건</h6>
	</c:if>
	
	<c:if test="${not empty keyword}">
		<div class="searchResult">
			<h6>검색 결과 : ${totalCount}건</h6>
			<button type="button" class="btn btn-warning btn-sm" onclick="location.href='/bnna/member/board/blackboard/list.action';">초기화</button>
		</div>
	</c:if>
	
	<!-- list -->
	<table id="tblList" class="table table-bordered">
		<thead>
			<tr>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>조회수</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="dto">
				<tr>
					<td>
						<a href="/bnna/member/board/blackboard/view.action?seq=${dto.seq}&page=${nowPage}&reply=${dto.reply}" style="margin-left: ${dto.depth * 30}px;">
							<c:if test="${dto.depth > 0}">
								<i class="fab fa-replyd"></i>
								
							</c:if>
							${dto.title}
							<c:if test="${dto.hasImage}">
								<i class="far fa-file-image imgicon"></i>
							</c:if>
						</a>
					</td>
					<td>${dto.name}(${dto.id})</td>
					<td>${dto.regdate}</td>
					<td>${dto.readcnt}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	
	<!-- search -->
	<div id="searchbar" class="form-inline">
		<select class="form-control" id="searchCondition">
			<option>제목</option>
			<option>이름</option>
			<option>아이디</option>
		</select>
		<input type="text" class="form-control" id="searchKeyword">
		<button type="button" class="btn btn-default" id="btnSearchList"><i class="fas fa-search"></i></button>
			
		<c:if test="${not empty seqMember}">
			<button type="button" class="btn btn-primary btn-lg" id="btnAdd" onclick="location.href='/bnna/member/board/blackboard/add.action?page=${nowPage}&reply=n';">작성</button>
		</c:if>
	</div>
	
	
	<!-- paging -->
	<c:if test="${not empty list}">
		<div class="pagebox">
			<nav class="pagebar">
				<ul class="pagination">${pagebar}</ul>
			</nav>
		</div>
	</c:if>	
	
</section>

<script src="/bnna/resources/js/blackboard.js"></script>
