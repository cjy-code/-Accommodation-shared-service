<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="/bnna/resources/css/admin/event.css">
<section class="evtsection">
	<h2 onclick="location.href='/bnna/admin/board/eventboard/list.action';" style="cursor: pointer;">이벤트 게시판</h2>
<!-- 	<h2>이벤트 게시판</h2> -->
	
	<form id="searchForm" method="GET" action="/bnna/admin/board/eventboard/list.action">
    	<div class="input-group search" style="width: 180px; float: right;">
	        <input type="text" class="form-control" placeholder="" aria-describedby="basic-addon2" id="search" name="search" required value="${search}">
            <span class="input-group-addon" id="basic-addon2" style="cursor:pointer;" onclick="$('#searchForm').submit();"><span class="glyphicon glyphicon-search"></span></span>
        </div>
    </form>
    <div style="clear:both;"></div>
	
	
	<table class="table table-bordered evtboard">
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>아이디</th>
			<th>날짜</th>
		</tr>

		<c:if test="${list.size() == 0}">
			<tr>
				<td colspan="5" style="text-align: center;"><img src="/bnna/resources/image/sorry.png" alt="sorry" style="width:250px"></td>
			</tr>
		</c:if>

		<c:forEach items="${list}" var="dto">
		<tr>
			<td>${dto.seq}</td>
			<td><a href="/bnna/admin/board/eventboard/view.action?seq=${dto.seq}">${dto.title}</a></td>
			<td>${dto.id}</td>
			<td>${dto.regdate}</td>
		</tr>
		</c:forEach>
	</table>
	
	<div class="btns" style="display: inline;">
		<button type="button" class="btn btn-general" onclick="location.href='/bnna/admin/board/eventboard/add.action';">쓰기</button>
	</div>
	
	<div class=pagearea>
		    <div class="pagination">
		        <a href="#">&laquo;</a>
		        <a href="#">1</a>
		        <a href="#" class="active">2</a>
		        <a href="#">3</a>
		        <a href="#">4</a>
		        <a href="#">5</a>
		        <a href="#">6</a>
		        <a href="#">&raquo;</a>
		    </div>
		</div>
	
	
</section>