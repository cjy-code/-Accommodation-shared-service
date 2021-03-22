<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="/bnna/resources/css/member/board/review.css">
<!-- menu path - 메뉴 경로 표기 -->
<section class="contentsection page-start">
	<div id="menupath">
	   	리뷰게시판
	   	<span class="glyphicon glyphicon-menu-right" style="position:static; z-index: -1;"></span>
	   	작성하기
	</div>
	<hr style="margin-top: -5px;">
	<!-- 여기까지 menu path -->
	<h3>작성하기</h3>
	<form method="POST" action="/bnna/member/board/review/addok.action" enctype="multipart/form-data">
		<table class="table table-bordered" id="addtbl">
			<tr>
				<th>제목</th>
				<td colspan="3"><input type="text" class="form-control" id="title" name="title" required></td>
			</tr>
			<tr>
				<th>예약번호</th>
				<td>
					<select class="form-control" aria-label="Default select example" id="seqbook" name="seqbook">
					<c:forEach items="${blist}" var="bdto">
					  	<option value="${bdto.seq}">${bdto.seq}</option>
					</c:forEach>
					</select>
				</td>
				<th>별점</th>
				<td>
					<select class="form-control" aria-label="Default select example" id="star" name="star">
					  	<option value="1">1</option>
					  	<option value="2">2</option>
					  	<option value="3">3</option>
					  	<option value="4">4</option>
					  	<option value="5">5</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>내용</th>
				<td colspan="3"><textarea class="form-control" id="content" name="content" required></textarea></td>
			</tr>
			<tr>
				<th>리뷰사진</th>
				<td colspan="3"><input type="file" class="form-control" id="reviewpic" name="reviewpic" multiple="multiple"></td>
			</tr>
		</table>
		<div id="btns">
			<input type="submit" class="btn-general" value="작성하기">
					<button type="button" class="btn-general" onclick="location.href='/bnna/member/board/review/list.action'">뒤로가기</button>
		</div>
	</form>
</section>