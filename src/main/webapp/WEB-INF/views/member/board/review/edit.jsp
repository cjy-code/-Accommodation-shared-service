<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="/bnna/resources/css/member/board/review.css">
<!-- menu path - 메뉴 경로 표기 -->
<section class="contentsection page-start">
	<div id="menupath">
	   	리뷰게시판
	   	<span class="glyphicon glyphicon-menu-right" style="position:static; z-index: -1;"></span>
	   	수정하기
	</div>
	<hr style="margin-top: -5px;">
	<!-- 여기까지 menu path -->
	<h3>수정하기</h3>
	<form method="POST" action="/bnna/member/board/review/editok.action" enctype="multipart/form-data">
		<table class="table table-bordered" id="addtbl">
			<tr>
				<th>제목</th>
				<td colspan="3"><input type="text" class="form-control" id="title" name="title" value=${dto.title}></td>
			</tr>
			<tr>
				<th>예약번호</th>
				<td>
					<select class="form-control" aria-label="Default select example" id="seqbook" name="seqbook">
						<option value="${dto.seqbook}">${dto.seqbook}</option>
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
				<td colspan="3"><textarea class="form-control" id="content" name="content">${dto.content}</textarea></td>
			</tr>
			<c:if test="${plist.size()>0}">
			<tr>
				<th rowspan="2">리뷰사진</th>
				<td colspan="3">
					<div><span class="glyphicon glyphicon-bullhorn"></span>&nbsp;삭제하기 버튼을 누르면 바로 삭제됩니다!</div>
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<c:forEach items="${plist}" var="pdto">
						<div>${pdto.orgimage} <span id="delbtnbox"><button type="button" class="btn btn-default btn-xs" id="delfilebtn" data-seq=${pdto.seq}>삭제하기</button></span></div>
					</c:forEach>
				</td>
			</tr>
			</c:if>
		</table>
		<input type="hidden" value=${dto.seq} id="seq" name="seq">
		<div id="btns">
			<input type="submit" class="btn-general" value="수정하기">
			<button type="button" class="btn-general" onclick="location.href='/bnna/member/board/review/list.action'">뒤로가기</button>
		</div>
	</form>
</section>

<script>
	/* 첨부파일 삭제하기 */
	$("#delbtnbox > #delfilebtn").on('click', function(){
		
		$.ajax({
			type: "GET",
			url: "/bnna/member/board/review/delfile.action",
			data: "seq=" + $(this).data("seq"),
			success: function(result) {
				console.log("filedeletesuccess");
			},
			error: function() {
				console.log("error");
			}
		});
		
		$(this).hide();
		$(this).parent().html("&nbsp;<span class='glyphicon glyphicon-trash'></span>&nbsp;삭제되었습니다.");
		
	});
</script>