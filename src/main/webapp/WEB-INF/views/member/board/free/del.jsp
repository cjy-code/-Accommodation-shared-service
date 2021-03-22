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
	
	<form method="POST" action="/bnna/member/board/free/delok.action">
		<div class="btns">
			<button type="submit" class="btn btn-warning">삭제</button>
			<button type="button" class="btn btn-default" onclick="location.href='/bnna/member/board/free/list.action';">목록</button>
			<span>${seq}</span>
		</div>
		<input type="hidden" name="seq" value="${seq}">
	</form>
	
</section>