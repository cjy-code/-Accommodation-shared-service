<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	

<link rel="stylesheet" href="/bnna/resources/css/myinfo/mileage.css">

		<!-- 주요내용! 여기에 작성한 코드 넣을 것! -->
		<section class="contentsection page-start">
		

			<!-- menu path - 메뉴 경로 표기 -->
			<div id="menupath">
				마이페이지 <span class="glyphicon glyphicon-menu-right"
					style="position: static; z-index: -1;"></span> 마일리지
			</div>
			<hr style="margin-top: -5px;">
			<!-- 여기까지 menu path -->
		
			<article>
		
				<h1>마일리지</h1>
		
				<div class="infobox">
					<%--
 					<div class="crop">
						<img src="/bnna/resources/image/mempic/${dto.pic}" alt="${dto.pic}">
					</div>
		
					<div class="basicinfo">
						<h4>
							${dto.name}<span> &nbsp;${dto.id}</span>
						</h4>
						가입일 : ${dto.regdate.substring(0, 10)}
					</div> 
					--%>
					<div class="crop">
						<img src="/bnna/resources/image/mempic/f1.png" alt="${dto.pic}">
					</div>
		
					<div class="basicinfo">
						<h4>
							이한나<span> &nbsp;hannah123</span>
						</h4>
						가입일 : 2019-02-19
					</div>

		
					<div class="numbered">
						<div class="dibs">
							<a href=""> <span class="glyphicon glyphicon-heart"></span> 찜한
								숙소 <span class="glyphicon glyphicon-menu-right"></span>
								<p class="num">10</p></a>
						</div>
						<div class="plan">
							<a href=""> <span class="glyphicon glyphicon-plane"></span> 여행
								일정 <span class="glyphicon glyphicon-menu-right"></span>
								<p class="num">2</p></a>
						</div>
						<div class="review">
							<a href=""> <span class="glyphicon glyphicon-pencil"></span> 리뷰
								작성 <span class="glyphicon glyphicon-menu-right"></span>
								<p class="num">6</p></a>
						</div>
						<div class="qna">
							<a href=""> <span class="glyphicon glyphicon-comment"></span>
								문의 내역 <span class="glyphicon glyphicon-menu-right"></span>
								<p class="num">8</p></a>
						</div>
						<div style="clear: both;"></div>
					</div>
				</div>
		
				<hr>
		
				<div class="mbox">
					<div class="now inline-block">
						현재 마일리지
						<div>203,000 M</div>
					</div>
					<div class="thismonth inline-block">
						이번달 마일리지
						<div>9,000 M</div>
					</div>
		
					<div class="minfo">
						<ul>
							<li>마일리지는 1M당 1원처럼 사용이 가능합니다.</li>
							<li>결제건으로 적립된 마일리지는 다음 결제건부터 바로 이용이 가능합니다.</li>
							<li>마일리지 사용은 결제 시 100M 단위로 사용이 가능합니다.</li>
						</ul>
					</div>
				</div>
		
				<hr>
		
				<div class="btns">
					<button class="btn btn-general">전체</button>
					<button class="btn btn-default">적립</button>
					<button class="btn btn-default">사용</button>
				</div>
		
				<div class="listarea">
					<table class="table tbl-md">
						<tr>
							<th>마일리지</th>
							<th>적립구분</th>
							<th>누적마일리지</th>
							<th>적립일자</th>
						</tr>
						
						<c:if test="${empty list}">
							<td colspan="4">내역이 없습니다.</td>
						</c:if>
						
						<c:forEach items="${list}" var="dto">
						<tr>
							<c:if test="${dto.saveM != 0}">
							<td>+ ${dto.saveM} M</td>
							<td>예약 적립</td>
							</c:if>
							
							<c:if test="${dto.useM != 0}">
							<td style="color: rgb(228, 55, 55);">- ${dto.useM} M</td>
							<td>예약 사용</td>
							</c:if>
							
							<td>${dto.totalM} M</td>
							<td>${dto.regdate.substring(0, 10)}</td>
						</tr>
						</c:forEach>

					</table>
				</div>
		
				<div class=pagearea>
					<div class="pagination">
						<a href="#">&laquo;</a> <a href="#">1</a> <a href="#" class="active">2</a>
						<a href="#">3</a> <a href="#">4</a> <a href="#">5</a> <a href="#">6</a>
						<a href="#">&raquo;</a>
					</div>
				</div>
		
			</article>
		
		</section>