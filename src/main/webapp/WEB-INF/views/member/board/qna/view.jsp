<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<section class="contentsection page-start">
<section>
	
	<h3>글보기</h3>
	
	<table class="table table-bordered">
		<tr>
			<th style="width: 120px;">번호</th>
			<td style="width: auto;">${dto.seq}</td>
		</tr>
		<tr>
			<th>날짜</th>
			<td>${dto.regdate.substring(0, 10)}</td>
		</tr>
		<tr>
			<th>유형</th>
			<td>${dto.category}</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${dto.id}</td>
		</tr>
		<tr>
			<th>제목</th>
			<td>${dto.title}</td>
		</tr>
		<tr>
			<th>내용</th>
			<td>${dto.content}</td>
		</tr>
	</table>	
	<div class="btns">
		
		<button type="button" class="btn btn-default" onclick="location.href='/bnna/member/board/qna/list.action';">목록</button>
		
		<button type="button" class="btn btn-default" onclick="location.href='/bnna/member/board/qna/edit.action?seq=${dto.seq}';">수정</button>
		
		<button type="button" class="btn btn-default" onclick="location.href='/bnna/member/board/qna/del.action?seq=${dto.seq}';">삭제</button>
		
	</div>
	

</section>
</section>










    