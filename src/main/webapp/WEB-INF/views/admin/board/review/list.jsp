<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="/bnna/resources/css/admin/board/review.css">

<section class="mainsection">

    <div id="menupath">
        게시판 <span class="glyphicon glyphicon-menu-right" style="position: static; z-index: -1;"></span> 리뷰 게시판
        <span class="glyphicon glyphicon-menu-right" style="position: static; z-index: -1;"></span> 전체보기
    </div>
    <hr style="margin-top: -5px">
    <!-- 여기까지 menu path -->

	<h3>목록조회</h3>
	
		<table class="table table-bordered" id="listtbl">
			<tr>
				<th class="col-md-1">번호</th>
				<th class="col-md-1">예약번호</th>
				<th class="col-md-3">제목</th>
				<th class="col-md-2">아이디</th>
				<th class="col-md-2">예약일</th>
				<th class="col-md-1">별점</th>
				<th class="col-md-1">조회수</th>
				<th class="col-md-1">추천수</th>
			</tr>
			<c:if test="${list.size()!=0}">
			<c:forEach items="${list}" var="dto">
			<tr>
				<td>${dto.seq}</td>
				<td>${dto.seqbook}</td>
				<td><a href="/bnna/admin/board/review/view.action?seq=${dto.seq}">${dto.title}</a></td>
				<td>${dto.id}</td>
				<td>${dto.bookdate.substring(0, 10)}</td>
				<td>${dto.star}</td>
				<td>${dto.readcnt}</td>
				<td>${dto.recommendcnt}</td>
			</tr>
			</c:forEach>
			</c:if>
			<c:if test="${list.size()==0}">
				<tr>
					<td colspan="8">작성된 리뷰가 없습니다.</td>
				</tr>
			</c:if>
		</table>

		<c:if test="${list.size()!=0}">
			${pagebar}
		</c:if>

</section>