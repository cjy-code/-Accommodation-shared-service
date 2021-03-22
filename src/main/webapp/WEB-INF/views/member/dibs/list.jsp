<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	

<link rel="stylesheet" href="/bnna/resources/css/myinfo/dibs.css">

		<!-- 주요내용! 여기에 작성한 코드 넣을 것! -->
		<section class="contentsection page-start">
		

            <!-- menu path - 메뉴 경로 표기 -->
            <div id="menupath">
                마이페이지
                <span class="glyphicon glyphicon-menu-right" style="position:static; z-index: -1;"></span>
                찜한 숙소
            </div>
            <hr style="margin-top: -5px;">
            <!-- 여기까지 menu path -->

            <article>
                
                <h1>찜한 숙소</h1>

                <ul class="rule">
                    <li>상단의 하트를 누르시면 관심 숙소가 해제됩니다.</li>
                    <li>숙소를 클릭하시면 해당 숙소 페이지로 이동합니다.</li>
                </ul>

                <div class="listarea">
                
                	<c:if test="${empty list}">
                		<div>찜한 숙소가 없습니다.</div>
                	</c:if>
                    
                    <c:forEach items="${list}" var="dto">
	                    <div class="bnb" onclick="location.href='/bnna/member/bnbsearch/view.action?seq=${dto.seqBnb}';">
	                        <div class="crop">
	                            <img src="/bnna/resources/image/bnb/${dto.image}" alt="">
	                        </div>
<%--
 	                        <form method="POST" action="/bnna/member/dibs/delok.action">
		                        <button type="submit" style="all:unset;">
		                        <span class="glyphicon glyphicon-heart"></span>
		                        </button>
		                        <input type="hidden" name="seq" value="${dto.seqBnb}">
	                        </form> 
	                        --%>
	                        <div onclick="event.cancelBubble = true;">
	                        	<span class="glyphicon glyphicon-heart" onclick="location.href='/bnna/member/dibs/delok.action?seq=${dto.seq}';"></span>
	                        </div>
	                        <div class="bnbinfo">
	                            <span class="bnbtype">${dto.bnbtype}</span>
	                            <span class="room">/ 침실 ${dto.bedroom}개 · 욕실 ${dto.bathroom}개</span>
	                            <div class="name">${dto.name}</div>
	                            <span class="price">${dto.price}원 <span>/박</span></span>
	                        </div>
	                    </div>
                    </c:forEach>
                    
                </div>
                
                <div class=pagearea>
					<div class="pagination">
						${pagebar}
					</div>
				</div>

            </article>
		
		</section>