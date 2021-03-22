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
	
	<form method="POST" action="/bnna/member/board/free/addok.action">
	<table class="table-bordered" style="width: 100%; margin-top: 30px;">
		<tr>
			<th>제목</th>
			<td><input type="text" style="width: 100%;" name="title"></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea style="width: 100%; height: 500px" name="content"></textarea></td>
		</tr>
	</table>
	<div class="btns">
		<button type="submit" class="btn btn-warning">쓰기</button>
		<button type="button" class="btn btn-default" onclick="location.href='/bnna/member/board/free/list.action';">목록</button>
	</div>
		<input type="hidden" name="cnt" value=${cnt}>
	</form>
</section class="contentsection">