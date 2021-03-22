<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="/bnna/resources/css/member/board/review.css">
<section class="contentsection page-start">

	<!-- menu path - 메뉴 경로 표기 -->
	<div id="menupath">
    	게시판
    	<span class="glyphicon glyphicon-menu-right" style="position:static; z-index: -1;"></span>
    	리뷰게시판
	</div>
	<hr style="margin-top: -5px;">
	<!-- 여기까지 menu path -->
                
	<h3>상세조회</h3>

		<table class="table table-bordered" id="viewtbl">
			<tr>
				<th>제목</th>
				<td colspan="3">${dto.title}</td>
			</tr>
			<tr>
				<th>예약일</th>
				<td>${dto.bookdate.substring(0, 10)}</td>
				<th>별점</th>
				<td>${dto.star}</td>
			</tr>
			<tr>
				<th>조회수</th>
				<td>${dto.readcnt}</td>
				<th>추천수</th>
				<td>${dto.recommendcnt}</td>
			</tr>
			<tr>
				<th>내용</th>
				<td colspan="5">
					${dto.content}
					<c:forEach items="${plist}" var="pdto">
						<img src="/bnna/resources/image/board/review/${pdto.image}" id="reviewPic">
					</c:forEach>
				</td>
			</tr>
		</table>
		
	<div id="btns">
		<button type="button" class="btn-general" onclick="location.href='/bnna/member/board/review/edit.action?seq=${dto.seq}'">수정하기</button>
		<button type="button" class="btn-general" data-toggle="modal" data-target="#delModal">삭제하기</button>
		<button type="button" class="btn-general" onclick="location.href='/bnna/member/board/review/list.action'">뒤로가기</button>
	</div>
		
</section>

<div class="modal fade" id="delModal" role="dialog">
    <div class="modal-dialog modal-md">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title"></h4>
        </div>
        <div class="modal-body">
         <p>정말 삭제하시겠습니까?</p>
        </div>
        <div id="btns">
           	<button type="button" class="btn btn-general inline" id="modaldel" onclick="location.href='/bnna/member/board/review/delok.action?seq=${dto.seq}'">삭제하기</button>
        </div>
    </div>
    </div>
</div>