<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<section class="contentsection">

	<div id="menupath">
    	게시판
    	<span class="glyphicon glyphicon-menu-right" style="position:static; z-index: -1;"></span>
    	자유게시판
    	<span class="glyphicon glyphicon-menu-right" style="position:static; z-index: -1;"></span>
    	수정
	</div>
	<hr style="margin-top: -5px;">
	
	<h1>자유게시판</h1>

	<form method="POST" action="/bnna/member/board/free/editok.action">
	<table class="table table-bordered" style="100%;">
		<tr>
			<th style="width: 120px;">번호</th>
			<td style="width: auto;">${dto.seq}</td>
		</tr>
		<tr>
			<th>아이디</th>
			<td>${dto.id}</td>
		</tr>
		<tr>
			<th>제목</th>
			<td><input type="text" name="title" class="form-control" value="${dto.title}"></td>
		</tr>
		<tr>
			<th style="height: 350px;">내용</th>
			<td><textarea name="content" class="form-control" style="height: 300px;">${dto.content}</textarea></td>
		</tr>
	</table>
	<div class="btns">
		<button type="submit" class="btn btn-primary">확인</button>
		<button type="button" class="btn btn-default" onclick="location.href='/bnna/member/board/free/list.action';">취소</button>
	</div>	
	<input type="hidden" name="seq" value="${dto.seq}">
	</form>

</section class="contentsection">