<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet" href="/bnna/resources/css/tripinfo/admin-tripinfo.css">

<section class="mainsection">

    <div id="menupath">
        게시판 <span class="glyphicon glyphicon-menu-right" style="position: static; z-index: -1;"></span> 여행정보 게시판
        <span class="glyphicon glyphicon-menu-right" style="position: static; z-index: -1;"></span> 전체보기
    </div>
    <hr style="margin-top: -5px">
    <!-- 여기까지 menu path -->

    <article>

        <h1>여행정보 게시판</h1>

        <div class="boardhead">
        
        	<!-- 검색은 주로 GET방식 이용 : 상태유지를 위해 -->
            <div class="searchform searcharea">
            	<form method="GET" action="/bnna/admin/board/tripinfo/list.action">
                <input type="text" class="form-control boardsearch" id="search" name="search" required value="${search}">
                <button id="searchBtn" class="btn" type="submit"><span class="glyphicon glyphicon-search"></span></button>
                </form>
            </div>

            <div class="btns">
                <button class="btn btn-thumbnail">썸네일보기</button>
                <button class="btn btn-write" onclick="location.href='/bnna/admin/board/tripinfo/write.action?reply=n'">새글쓰기</button>
            </div>
        </div>
        
		<c:if test="${not empty search}">
		<div class="message well well-sm" >
		   '${search}'(으)로 ${list.size()}건의 게시물을 검색했습니다.
		</div>
		</c:if>

        <div class="admin-listarea">
            <table class="tbl-tripinfo table table-bordered">
                <tr>
                    <th>번호</th>
                    <th>제목</th>
                    <th>조회수</th>
                    <th>작성일</th>
                </tr>
                
                <c:forEach items="${list}" var="dto">
                <tr>
                    <td>${dto.seq}</td>
                    <td>
              	 	<a href="/bnna/admin/board/tripinfo/view.action?seq=${dto.seq}&search=${search}&page=${nowPage}" 
          	style="margin-left: ${dto.depth * 30}px;">
          			
          			<c:if test="${dto.depth > 0}">
          			<span class="glyphicon glyphicon-share-alt"></span>
          			</c:if>
          			
                    ${dto.subject}
                    
                    <!-- 댓글 수 -->
                   	<c:if test="${dto.cmtcnt > 0}">
                   	<span class="badge">${dto.cmtcnt}</span>
                   	</c:if>
                   	
                    </a>
                    </td>
                    <td>${dto.readcnt}</td>
                    <td>${dto.regdate.substring(0, 10)}</td>
                </tr>
                </c:forEach>
                
            </table>

            <div class=pagearea>
                <div class="pagination">
					${pagebar}
				</div>
            </div>
        </div>

    </article>

</section>