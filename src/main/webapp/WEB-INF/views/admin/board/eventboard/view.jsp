<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="/bnna/resources/css/admin/event.css">
<section class="evtsection content">
	<h2 onclick="location.href='/bnna/admin/board/eventboard/list.action';" style="cursor: pointer;">이벤트 게시판</h2>
	<div>
		<table class="table table-bordered view">
			<tr>
				<td><span class="title">${dto.title}</span> <span
					class="readcnt">읽음(${dto.readcnt})</span> <span class="regdate">${dto.regdate}</span>
					<span class="id">${dto.id}</span>
				</td>
			</tr>
			<tr>
				<td class="content"><img alt="${dto.image}" src="/bnna/resources/image/board/eventboard/${dto.image}"> <br> ${dto.content}</td>
			</tr>
		</table>

		<div class="btns">
			<button type="submit" class="btn btn-default"
				onclick="location.href='/bnna/admin/board/eventboard/list.action';">목록</button>
			<button type="submit" class="btn btn-default" onclick="location.href='/bnna/admin/board/eventboard/edit.action?seq=${dto.seq}';">수정</button>
			<button type="submit" class="btn btn-default" onclick="del(${dto.seq})">삭제</button>
		</div>
	</div>

	<!-- 댓글 목록 -->
	<table class="table comment">
		<c:if test="${empty clist}">
			<tr>
				<td>댓글이 없습니다.</td>
			</tr>
		</c:if>
		<c:if test="${not empty clist}">
		<c:forEach items="${clist}" var="cdto">
			<tr>
				<td><span class="comment">${cdto.content}</span> <span
					class="regdate">${cdto.regdate}</span> <span class="id">${cdto.id}</span>

					<span class="delete"
							onclick="location.href='/bnna/admin/board/eventboard/delcommentok.action?seq=${cdto.seq}&bseq=${dto.seq}';">[삭제]</span>
				</td>
			</tr>
		</c:forEach>
		</c:if>
	</table>
	
	
</section>

<script>

	//삭제 alert
	function del(seq) {
		var chk = confirm("정말 삭제하시겠습니까?");
		if (chk){
			location.href = '/bnna/admin/board/eventboard/delok.action?seq='+seq;
		}zja
	}
</script>