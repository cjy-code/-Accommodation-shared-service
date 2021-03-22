<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet" href="/bnna/resources/css/blackboard.css">

<section class="contentsection page-start">

	<!-- menu path - 메뉴 경로 표기 -->
	<div id="menupath">
		게시판
		<span class="glyphicon glyphicon-menu-right" style="position: static; z-index: -1;"></span>
		신고게시판
		<span class="glyphicon glyphicon-menu-right" style="position: static; z-index: -1;"></span>
		작성
	</div>
	<hr style="margin-top: -5px;">
	<!-- 여기까지 menu path -->

	<!-- 본인 작업 -->
	<h1 class="board-title">신고게시판 <small>Blacklist Board</small></h1>

	<form id="add" method="POST" action="/bnna/member/board/blackboard/addok.action" enctype="multipart/form-data">
	
		<table class="table table-bordered" id="tblAdd">
			<tr>
				<th>제목</th>
				<td>
					<input type="text" class="form-control" id="title" name="title" placeholder="제목을 입력하세요." maxlength=100 required>
				</td>
			</tr>
			<c:if test="${reply.equals('n')}">
				<tr>
					<th>
						신고대상회원
						<button type="button" class="btn btn-default btn-sm" id="btnSearch"><span class="glyphicon glyphicon-search"></span></button>				
					</th>
					<td>
						<span id="issueMemberInfo"></span>
					</td>
				</tr>			
			</c:if>
			<tr>
				<th>내용</th>
				<td>
					<textarea class="form-control" id="content" name="content" placeholder="내용을 입력하세요." maxlength=1000 required></textarea>
				</td>
			</tr>
			<tr>
				<th>이미지</th>
				<td>
					<button type="button" class="btn btn-warning" onclick="$('#image').click();">파일 선택</button>
					<span class="upload-help">*** 다중 이미지 선택이 가능합니다.</span>
					<input type="file" id="image" name="image" multiple="multiple">
					<div id="imagename"></div>
				</td>
			</tr>
		</table>
	
		<div class="btns">
			<button type="submit" class="btn btn-primary">작성</button>
			<button type="button" class="btn btn-default" onclick="location.href='/bnna/member/board/blackboard/list.action?page=${nowPage}';">취소</button>
		</div>
		
		<input type="hidden" id="seqIssueMember" name="seqIssueMember">		
		<input type="hidden" id="page" name="page" value="${nowPage}">
		<input type="hidden" id="reply" name="reply" value="${reply}">
		<input type="hidden" id="thread" name="thread" value="${thread}">
		<input type="hidden" id="depth" name="depth" value="${depth}">
		<input type="hidden" id="seqParent" name="seqParent" value="${seqParent}">
		
	</form>

	<!-- searchModal -->
	<div class="modal fade" id="searchModal" tabindex="-1" role="dialog"
		aria-labelledby="searchModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">신고대상회원 검색</h4>
				</div>
				<div class="modal-body">
				
					<div class="form-inline" style="text-align: right;">
						<select class="form-control" id="condition" name="condition">
							<option>이름</option>
							<option>아이디</option>
						</select>
						<input type="text" class="form-control" id="keyword" name="keyword">
						<button type="button" class="btn btn-default" id="btnSearchResult"><span class="glyphicon glyphicon-search"></span></button>
					</div>
					
					<table class="table table-hover" id="tblResult">
						<thead>
							<tr>
								<th>선택</th>
								<th>이름</th>
								<th>아이디</th>
							</tr>						
						</thead>
						<tbody>
							
						</tbody>
					</table>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" id="choice">선택</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
				</div>
			</div>
		</div>
	</div>

</section>

<script src="/bnna/resources/js/blackboard.js"></script>


