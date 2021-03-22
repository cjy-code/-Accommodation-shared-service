<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="/bnna/resources/css/member/search.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
<section class="contentsection page-start">
	<!-- 이미지가 들어갈 테이블 -->
	<div class="bxslider">
		<c:forEach items="${plist}" var="pdto">
		  <div><img src="/bnna/resources/image/bnb/${pdto.image}"></div>
		</c:forEach>
	</div>
	<!-- 화면 왼쪽에 뜨는 숙소소개 및 정보 -->
	<div id="left">
		<h2>${dto.bnbName}<span class="glyphicon glyphicon-star"></span><span>${dto.star}점</span></h2>
		<div><span class="glyphicon glyphicon-map-marker"> ${dto.zipcode} ${dto.loadname} ${dto.detail}</span></div>
		<div><span class="glyphicon glyphicon-phone-alt"> ${dto.tel}</span></div>
		<img src="/bnna/resources/image/mempic/${dto.hostImage}" id="profile">
		<span class="glyphicon glyphicon-home"> 호스트 ${dto.hostName}</span>
		<div class="desc">${dto.hostIntro}</div>
		<hr>
		<div class="desc">${dto.bnbIntro}</div>
		<hr>
		<h4>이용규칙</h4>
		<span id="detail">
			<c:if test="${dto.pet eq 1}">
			<span>반려동물동반가능&nbsp;&nbsp;<span class="glyphicon glyphicon-ok"></span></span>
			</c:if>
			<c:if test="${dto.smoke eq 1 }">
			<span>흡연가능&nbsp;&nbsp;<span class="glyphicon glyphicon-ok"></span></span>
			</c:if>
			<c:if test="${dto.evt eq 1 }">
			<span>이벤트가능&nbsp;&nbsp;<span class="glyphicon glyphicon-ok"></span></span>
			</c:if>
			<c:if test="${dto.selfcheck eq 1 }">
			<span>셀프체크인가능&nbsp;&nbsp;<span class="glyphicon glyphicon-ok"></span></span>
			</c:if>
		</span>
		<hr>
		<div class="desc">
		<h4>편의시설</h4>
			<c:if test="${dto.amenity eq 1}}">
			<span>세면도구&nbsp;&nbsp;<span class="glyphicon glyphicon-tint"></span></span>
			</c:if>
			<c:if test="${dto.kitchen eq 1 }">
			<span>주방사용가능&nbsp;&nbsp;<span class="glyphicon glyphicon-glass"></span></span>
			</c:if>
			<c:if test="${dto.heating eq 1 }">
			<span>난방사용가능&nbsp;&nbsp;<span class="glyphicon glyphicon-fire"></span></span>
			</c:if>
			<c:if test="${dto.washing eq 1 }">
			<span>세탁기&nbsp;&nbsp;<span class="glyphicon glyphicon-cloud"></span></span>
			</c:if>
			<c:if test="${dto.wifi eq 1 }">
			<span>무선인터넷&nbsp;&nbsp;<span class="glyphicon glyphicon-globe"></span></span>
			</c:if>
			<c:if test="${dto.workroom eq 1 }">
			<span>업무전용공간&nbsp;&nbsp;<span class="glyphicon glyphicon-print"></span></span>
			</c:if>
			<c:if test="${dto.babybed eq 1 }">
			<span>아기침대&nbsp;&nbsp;<span class="glyphicon glyphicon-eye-open"></span></span>
			</c:if>
			<c:if test="${dto.dryer eq 1 }">
			<span>건조기&nbsp;&nbsp;<span class="glyphicon glyphicon-leaf"></span></span>
			</c:if>
			<c:if test="${dto.breakfast eq 1 }">
			<span>조식제공&nbsp;&nbsp;<span class="glyphicon glyphicon-cutlery"></span></span>
			</c:if>
			<c:if test="${dto.tv eq 1 }">
			<span>TV&nbsp;&nbsp;<span class="glyphicon glyphicon-blackboard"></span></span>
			</c:if>
			<c:if test="${dto.ac eq 1 }">
			<span>에어컨&nbsp;&nbsp;<span class="glyphicon glyphicon-transfer"></span></span>
			</c:if>
			<c:if test="${dto.hairdryer eq 1 }">
			<span>헤어드라이기&nbsp;&nbsp;<span class="glyphicon glyphicon-flash"></span></span>
			</c:if>
			<c:if test="${dto.parking eq 1 }">
			<span>주차가능&nbsp;&nbsp;<span class="glyphicon glyphicon-road"></span></span>
			</c:if>
			<c:if test="${dto.disabled eq 1 }">
			<span>장애인편의시설&nbsp;&nbsp;<span class="glyphicon glyphicon-user"></span></span>
			</c:if>
		</div>
	</div>
	
	<!-- 화면 오른쪽에 뜨는 예약하기박스 -->
	<div id="right">
		<table id="reservetbl">
			<tr>
				<th>체크인</th>
				<td><input type="date" id="checkIn" name="checkIn" required value="${condition.checkIn}" min="1900-01-01" max="2999-01-01"></td>
			</tr>
			<tr>
				<th>체크아웃</th>
				<td><input type="date" id="checkOut" name="checkOut" required value="${condition.checkOut}" min="1900-01-02" max="2999-01-01" readonly></td>
			</tr>
			<tr>
				<th>인원</th>
				<td><input type="number" id="guest" name="guest" min=1 max=10 value=${condition.guest} placeholder="인원 추가" class="last" required> 명</td>
			</tr>
			<tr>
				<td colspan="2">
				<div id="price">
					<h6 class="inline"><span id="total">총 &#8361; ${String.format('%,d', dto.price * condition.gap * condition.guest)}</span></h6>
				</div>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<button type="submit" id="total" class="btn btn-general">
		        	<span class="glyphicon glyphicon-credit-card"> 예약하기</span>
		        	</button>
				</td>
			</tr>
        </table>
        	<input type="hidden" id="gap" name="gap" value=${condition.gap}>
	</div>
	
	<div id="map"></div>
	<div style="clear:both;"></div>
	
	<!-- 리뷰 테이블 -->
	
	<table class="table" id="reviewtbl">
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>별점</th>
			<th>예약일</th>
			<th>추천수</th>
			<th>조회수</th>
		</tr>
		<c:if test="${rlist.size()!=0}">
			<c:forEach items="${rlist}" var="rdto">
			<tr>
				<td>${rdto.seq}</td>
				<td id="todetail">
					<a data-toggle="modal" data-target="#detailModal" data-seq="${rdto.seq}" data-content="${rdto.content}">${rdto.title}</a>
					<c:set var="loop_flag" value="false" />
					<c:forEach items="${rplist}" var="rpdto">
						<c:if test="${rpdto.seqreview eq rdto.seq}">
						<c:set var="loop_flag" value="true" />
						</c:if>
					</c:forEach>
					<c:if test="${loop_flag eq true}">
						<span class="glyphicon glyphicon-picture"></span>
					</c:if>
				</td>
				<td>${rdto.id}</td>
				<td>${rdto.star}</td>
				<td>${rdto.bookdate.substring(0, 10)}</td>
				<td>${rdto.recommendcnt}</td>
				<td>${rdto.readcnt}</td>
			</tr>
			</c:forEach>
		</c:if>
		<c:if test="${rlist.size()==0}">
			<tr>
				<td colspan="7">작성된 리뷰가 없습니다.</td>
			</tr>
		</c:if>
	</table>
	
	<c:if test="${rlist.size()!=0}">
	${pagebar}
	</c:if>
	
</section>

<div class="modal fade" id="detailModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
			</div>
			<div class="modal-body" style="text-align:center;">
				<p id="modalcontent"></p>
				<div id="reviewimgbox"></div>
			</div>
			<div class="modal-footer">
          		<button type="button" class="btn btn-general" id="recommend">추천</button>
          		<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
        	</div>
		</div>
	</div>
</div>


<script>

	/* 숙소 이미지 슬라이드 */
	$('.bxslider').bxSlider({
		auto: true,
		autoControls: true,
		stopAutoOnClick: true,
		pager: true,
		slideWidth: 600
	});
	
	/* 모달 열기 + 조회수 올리기 */
	$("#todetail > a").on('click', function(){
		// 1. 조회수 올린다
		$.ajax({
			type: "GET",
			url: "/bnna/member/bnbsearch/readcntup.action",
			data: "seq=" + $(this).data("seq"),
			success: function() {
				console.log("readcnt++");
			},
			error: function(a,b,c) {
				console.log(a,b,c);
			}
		});
		
		var seq=$(this).data("seq");
		
		// 내용 모달로 옮긴다.
		$('#modalcontent').text($(this).data("content"));
		
 		// 리뷰번호에 따른 이미지도 모달로 넣는다.
 		// 아래는 리뷰번호에 따라 그 리뷰번호의 이미지들 가져오는 ajax
		$.ajax({
			url: "/bnna/member/bnbsearch/getreviewpic.action",
			data: {seq: $(this).data("seq")},
			dataType:"json",
			success: function(data) {
				var content="";
				for(var key in data) {
					content+='<div><img src="/bnna/resources/image/board/review/'+data[key]+'" style="max-width:350px; margin:10px;"></div>';
				}
				$(".modal-body > #reviewimgbox").html(content);
				console.log("complete");
			},
			error: function() {
				console.log("error!");
			}
		});
		
		// 추천버튼을 누르면 추천수를 올릴 수 있도록 리뷰번호 설정해준다.
		$("#recommend").val($(this).data("seq"));
		
	});
	
	/* 추천수 올리기 */
	$("#recommend").on('click', function(){
		
		$.ajax({
			type: "GET",
			url: "/bnna/member/bnbsearch/recommendcntup.action",
			data: "seq=" + $(this).val(),
			success: function(result) {
				console.log("recommendcnt++");
			},
			error: function(a,b,c) {
				console.log(a,b,c);
			}
		});
		
	});

</script>

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=69bfe67931ff11ac5d3aa813e9eb15dd"></script>

<script>

	var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
	var options = { //지도를 생성할 때 필요한 기본 옵션
		center: new kakao.maps.LatLng(${dto.mapx}, ${dto.mapy}), //지도의 중심좌표.
		level: 3 //지도의 레벨(확대, 축소 정도)
	};
	
	var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
	
	// 마커가 표시될 위치입니다 
	var markerPosition  = new kakao.maps.LatLng(${dto.mapx}, ${dto.mapy}); 

	// 마커를 생성합니다
	var marker = new kakao.maps.Marker({
	    position: markerPosition
	});

	// 마커가 지도 위에 표시되도록 설정합니다
	marker.setMap(map);
	
</script>