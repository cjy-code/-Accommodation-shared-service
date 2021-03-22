<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="/bnna/resources/css/admin/event.css">

<section>
	<h2>이벤트 게시판 <small>글 수정</small></h2>

	<form method="POST" action="/bnna/admin/board/eventboard/editok.action">
		<table class="table table-bordered evtboard">
			<tr>
				<th style="width: 120px">제목</th>
				<td style="width: auto;"><input type="text" name="title"
					class="form-control" value="${dto.title}"></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea name="content" class="form-control"
						style="height: 300px">${dto.content}</textarea></td>
			</tr>
		</table>
		<div class="btns">
			<button type="submit" class="btn btn-general">쓰기</button>
			<button type="button" class="btn btn-default"
				onclick="location.href='/bnna/admin/board/eventboard/list.action';">목록</button>
		</div>
		<input type="hidden" name="seq" value="${dto.seq}">
	</form>

</section>