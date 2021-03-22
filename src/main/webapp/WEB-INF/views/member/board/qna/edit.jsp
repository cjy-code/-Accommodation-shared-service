<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<section class="contentsection page-start">
	
	<h3>글수정</h3>
	
	<form method="POST" action="/bnna/member/board/qna/editok.action">
	
	<%-- <table class="table table-bordered">
		<tr>
			<th style="width: 120px;">제목</th>
			<td style="width: auto;"><input type="text" name="title" class="form-control" value="${dto.title}"></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea name="content" class="form-control" style="height: 300px;">${dto.content}</textarea></td>
		</tr>
	</table> --%>
	<table class="table table-bordered">
			<tr>
				<th>제목</th>
				<td colspan="3"><input type="text" class="form-control" id="title" name="title" value="${dto.title}"></td>
			</tr>
			<tr>
				<th>질문유형</th>
				<td>
					<select class="form-control" aria-label="Default select example" name="category" id="category">
					  	<option value="1">예약</option>
					  	<option value="2">결제</option>
					  	<option value="3">환불</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>내용</th>
				<td colspan="3"><textarea class="form-control" id="content" name="content">${dto.content}</textarea></td>
			</tr>
		</table>	
	<div class="btns">
		<button type="submit" class="btn btn-warning">수정하기</button>
		<button type="button" class="btn btn-default" onclick="location.href='/bnna/member/board/qna/list.action';">목록</button>
	</div>
	<input type="hidden" name="seq" value="${dto.seq}">
	</form>
	
	

</section>    



