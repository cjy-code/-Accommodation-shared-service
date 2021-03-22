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
		상세
	</div>
	<hr style="margin-top: -5px;">
	<!-- 여기까지 menu path -->

	<!-- 본인 작업 -->
	<h1 class="board-title">신고게시판 <small>Blacklist Board</small></h1>
		
		<!-- 글 상세 정보 -->
		<table class="table table-bordered" id="tblView">
			<tr>
				<th>제목</th>
				<td>
					${dto.title}
				</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>
					${dto.name} (${dto.id})
				</td>
			</tr>
			<tr>
				<th>작성일</th>
				<td>
					${dto.regdate}
				</td>
			</tr>
			<tr>
				<th>조회수</th>
				<td>
					${dto.readcnt}
				</td>
			</tr>
			<tr>
				<th>이미지</th>
				<td>
					<c:if test="${empty ilist}">
						이미지가 없습니다.
					</c:if>
					<c:forEach items="${ilist}" var="image">
						<a href="/bnna/resources/image/board/blackboard/${image.image}" title="다운로드" download>${image.orgimage}</a>
						<br>					
					</c:forEach>
					
				</td>
			</tr>
			<c:if test="${reply.equals('n')}">
				<tr>
					<th>
						신고대상회원				
					</th>
					<td>
						${dto.issueMemberName} (${dto.issueMemberId})
					</td>
				</tr>			
			</c:if>
			<tr>
				<th>내용</th>
				<td>
					<c:if test="${not empty ilist}">
						<div class="images">
							<c:forEach items="${ilist}" var="image">
								<img src="/bnna/resources/image/board/blackboard/${image.image}" class="insertimg">					
							</c:forEach>					
						</div>					
					</c:if>
					${dto.content}
				</td>
			</tr>
		</table>
	
		<div class="btns">
			<c:if test="${not empty seqMember}">
				<c:if test="${dto.seqMember.equals(seqMember)}">
					<button type="button" class="btn btn-success" onclick="location.href='/bnna/member/board/blackboard/edit.action?seq=${seq}&page=${nowPage}&reply=${reply}';">수정</button>
					<button type="button" id="btnDel" class="btn btn-danger">삭제</button>
				</c:if>
					<button type="button" class="btn btn-warning" onclick="location.href='/bnna/member/board/blackboard/add.action?page=${nowPage}&reply=y&thread=${dto.thread}&depth=${dto.depth}&seqParent=${seq}';">답글</button>
			</c:if>
			<button type="button" class="btn btn-default" onclick="location.href='/bnna/member/board/blackboard/list.action?page=${nowPage}';">목록</button>
		</div>
		
		<!-- 댓글 -->
		<h3 class="comment-title">댓글 <small>Comment</small></h3>
		<table class="table table-bordered" id="tblComment">
			<thead>
				<tr>
					<th>작성자</th>
					<th>내용</th>
					<th>작성일</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${empty clist}">
					<tr><td colspan="3" style="text-align: center;">댓글이 없습니다.</td></tr>
				</c:if>
				<c:forEach items="${clist}" var="cmt">
					<tr>
						<td>
							${cmt.name} (${cmt.id})
							<input type="hidden" id="seqBlackBoardCmt" value="${cmt.seq}">
							<input type="hidden" id="seqMember" value="${cmt.seqMember}">
						</td>
						<td>
							<span class="cmtContent">${cmt.content}</span>
							<c:if test="${seqMember.equals(cmt.seqMember)}">
								<button type="button" class="btn btn-danger btn-sm btnCmtDel">삭제</button>
							</c:if>
						</td>
						<td>${cmt.regdate}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<c:if test="${not empty seqMember}">
			<h4 class="comment-title">댓글 작성 <small>Write Comment</small></h4>
			<div class="write-comment">
				<textarea class="form-control" id="commentContent" name="commentContent"></textarea>
				<button type="button" class="btn btn-default btn-lg" id="addcmt">작성</button>
			</div>
			
			<input type="hidden" id="seq" name="seq" value="${seq}">	
		</c:if>

		<!-- delModal -->
		<div class="modal fade" id="delModal" tabindex="-1" role="dialog" aria-labelledby="delModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-sm">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title" id="delModalLabel">글 삭제</h4>
					</div>
					<div class="modal-body">
						삭제하시겠습니까?
						<div class="delModalBtns">
							<button type="button" id="btnDelOk" class="btn btn-danger">삭제</button>
							<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>						
						</div>
						<input type="hidden" id="seqBlackBoard" value="${seq}">
						<input type="hidden" id="seqParent" value="${dto.seqParent}">
					</div>
				</div>
			</div>
		</div>

		<!-- cmtDelModal -->
		<div class="modal fade" id="cmtDelModal" tabindex="-1" role="dialog" aria-labelledby="cmtDelModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-sm">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title" id="cmtDelModalLabel">댓글 삭제</h4>
					</div>
					<div class="modal-body">
						삭제하시겠습니까?
						<div class="delModalBtns">
							<button type="button" id="btnCmtDel" class="btn btn-danger">삭제</button>
							<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>						
						</div>
						<%--
						글 삭제 모달에서 가져옴
						<input type="hidden" id="seqBlackBoard" value="${seq}">
						--%>
						<input type="hidden" id="nowPage" value="${nowPage}">
						<input type="hidden" id="reply" value="${reply}">
					</div>
				</div>
			</div>
		</div>

</section>

<script src="/bnna/resources/js/blackboard.js"></script>

