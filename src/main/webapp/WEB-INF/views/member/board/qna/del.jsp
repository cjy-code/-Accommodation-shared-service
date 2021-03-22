<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<section class="contentsection page-start">
	
	<h3>글삭제</h3>
	
	<form method="POST" action="/bnna/member/board/qna/delok.action">
	<div class="btns">
		<button type="submit" class="btn btn-warning">삭제</button>
		<button type="button" class="btn btn-default" onclick="location.href='/bnna/member/board/qna/list.action';">목록</button>
	</div>
	<input type="hidden" name="seq" value="${seq}">
	</form>
	
	

</section>    



