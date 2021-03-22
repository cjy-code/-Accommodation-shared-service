<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- menu path - 메뉴 경로 표기 -->
<section class="contentsection page-start">
	<div id="menupath">
	   	Q&A게시판
	   	<span class="glyphicon glyphicon-menu-right" style="position:static; z-index: -1;"></span>
	   	작성하기
	</div>
	<hr style="margin-top: -5px;">
	<!-- 여기까지 menu path -->
	<form method="POST" action="/bnna/member/board/qna/addok.action">
		<table class="table table-bordered">
			<tr>
				<th>제목</th>
				<td colspan="3"><input type="text" class="form-control" id="title" name="title"></td>
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
				<td colspan="3"><textarea class="form-control" id="content" name="content"></textarea></td>
			</tr>
		</table>
		<input type="submit" class="btn-general" value="작성하기">
	</form>
</section>