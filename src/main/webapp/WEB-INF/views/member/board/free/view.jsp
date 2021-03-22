<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<section class="contentsection">

	<div id="menupath">
    	게시판
    	<span class="glyphicon glyphicon-menu-right" style="position:static; z-index: -1;"></span>
    	자유게시판
	</div>
	<hr style="margin-top: -5px;">
	
	<h1>자유게시판</h1>
	
	
	<table class="table table-bordered" style="width:880px;">
		<tr>
			<th style="width: 120px;">번호</th>
			<td style="width: auto;">${dto.seq}</td>
			<th style="width: 120px;">날짜</th>
			<td>${dto.regdate.substring(0, 10)}</td>
		</tr>
		<tr>
			<th>아이디</th>
			<td colspan="3">${dto.id}</td>
		</tr>
		<tr>
			<th>제목</th>
			<td colspan="3">${dto.title}</td>
		</tr>
		<tr>
			<th style="height: 300px;">내용</th>
			<td colspan="3">${dto.content}</td>
		</tr>
	</table>
	<div class="btns">
		<button type="button" class="btn btn-warning" onclick="location.href='/bnna/member/board/free/edit.action?seq=${dto.seq}';">수정</button>
		<button type="button" class="btn btn-primary" onclick="location.href='/bnna/member/board/free/del.action?seq=${dto.seq}';">삭제</button>
		<button type="button" class="btn btn-default" onclick="location.href='/bnna/member/board/free/list.action';">목록</button>
	</div>	

</section class="contentsection">