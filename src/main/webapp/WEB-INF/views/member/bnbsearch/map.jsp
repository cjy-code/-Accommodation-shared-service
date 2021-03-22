<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="/bnna/resources/css/member/search.css">
<section class="mainsection page-start">
	
	<c:if test="${empty list}">
		<h4 id="noresult">원하는 조건의 숙소가 없습니다.</h4>
	</c:if>
	
	<c:if test="${not empty list}">
	<!-- 목록으로 보기, 필터 추가 버튼 -->
	<div id="btns">
		<button id="tomap" class="btn btn-default inline" onclick="location.href='/bnna/member/bnbsearch/result.action?location=${condition.location}&checkIn=${condition.checkIn}&checkOut=${condition.checkOut}&guest=${condition.guest}&gap=${condition.gap}'">목록으로 보기</button>
		<button id="addfilter" class="btn btn-default inline" data-toggle="modal" data-target="#filterModal">필터 추가</button>
	</div>
	
	<h5>검색결과 ${list.size()} 건입니다.</h5>
	
	<div id="map" style="width:100%; height:600px;"></div>

	<c:forEach items="${list}" var="dto">
	<!-- 개별 숙소 정보 -->
	<div id="bnbbox">
		<div id="imgbox">
			<c:forEach items="${plist}" var="pdto">
				<c:if test="${dto.seq eq pdto.seqbnb }">
				<img src="/bnna/resources/image/bnb/${pdto.image}" id="bnb">
				</c:if>
			</c:forEach>
			<!-- 관심숙소 버튼 -->
			<c:set var="loop_flag" value="false" />
			<c:forEach items="${dlist}" var="ddto">
				<c:if test="${dto.seq eq ddto.seqbnb}">
					<c:set var="loop_flag" value="true" />
				</c:if>
			</c:forEach>
			<c:if test="${loop_flag eq true}">
				<button class="btn-search" id="heart"><span class="glyphicon glyphicon-heart"></span></button>
			</c:if>
			<c:if test="${loop_flag eq false}">
				<button class="btn-search" id="heart" style="background-color:#ccc;" ><span class="glyphicon glyphicon-heart"></span></button>
			</c:if>
		</div>
		<div id="intro">
			<a href="/bnna/member/bnbsearch/view.action?seq=${dto.seq}"><h4 id="title">${dto.bnbName}</h4></a>
			<p><span class="glyphicon glyphicon-home"> 호스트 ${dto.hostName}</span></p>
			<div>${dto.intro}</div>
			<span id="detail">
				<span class="glyphicon glyphicon-star"><span>${dto.star}점</span></span>
				<c:if test="${dto.pet eq 1}">
				<span>반려동물동반가능<span class="glyphicon glyphicon-ok"></span></span>
				</c:if>
				<c:if test="${dto.smoke eq 1 }">
				<span>흡연가능<span class="glyphicon glyphicon-ok"></span></span>
				</c:if>
				<c:if test="${dto.evt eq 1 }">
				<span>이벤트가능<span class="glyphicon glyphicon-ok"></span></span>
				</c:if>
				<c:if test="${dto.selfcheck} eq 1 }">
				<span>셀프체크인가능<span class="glyphicon glyphicon-ok"></span></span>
				</c:if>
				<c:if test="${dto.babybed eq 1 }">
				<span>아기침대가능<span class="glyphicon glyphicon-ok"></span></span>
				</c:if>
				<c:if test="${dto.workroom eq 1 }">
				<span>업무공간가능<span class="glyphicon glyphicon-ok"></span></span>
				</c:if>
			</span>
			<div id="price">
				<h6 class="inline"><span id="total">총 &#8361; ${String.format('%,d', dto.price * condition.gap * condition.guest)}</span></h6>
			</div>
		</div>
	</div>
	<div style="clear:both;"></div>
	</c:forEach>
	</c:if>

</section>

<div class="modal fade" id="filterModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
				</div>
				<form method="GET" action="/bnna/member/bnbsearch/filtermap.action">
				<div class="modal-body">
					<table>
						<tr>
							<th>침실수</th>
							<td><input type="number" id="bedroom" name="bedroom" value=1 min=1 max=10></td>
							<th>침대수</th>
							<td><input type="number" id="bed" name="bed" value=1 min=1 max=10></td></td>
							<th>욕실수</th>
							<td><input type="number" id="bathroom" name="bathroom" value=1 min=1 max=10></td></td>
						</tr>
					</table>
					<hr>
					<table>
						<tr>
							<td class="cb"><input type="checkbox" id="amenity" name="amenity" value=1><label for="amenity">&nbsp;&nbsp;세면도구</label></td>
							<td class="cb"><input type="checkbox" id="dryer" name="dryer" value=1><label for="dryer">&nbsp;&nbsp;건조기</label></td>
							<td class="cb"><input type="checkbox" id="kitchen" name="kitchen" value=1><label for="kitchen">&nbsp;&nbsp;주방이용가능</label></td>
						</tr>
						<tr>
							<td class="cb"><input type="checkbox" id="heating" name="heating" value=1><label for="heating">&nbsp;&nbsp;난방가능</label></td>
							<td class="cb"><input type="checkbox" id="washing" name="washing" value=1><label for="washing">&nbsp;&nbsp;세탁기이용가능</label></td>
							<td class="cb"><input type="checkbox" id="wifi" name="wifi" value=1><label for="wifi">&nbsp;&nbsp;무선인터넷</label></td>
						</tr>
						<tr>
							<td class="cb"><input type="checkbox" id="workroom" name="workroom" value=1><label for="workroom">&nbsp;&nbsp;업무전용공간</label></td>
							<td class="cb"><input type="checkbox" id="babybed" name="babybed" value=1><label for="babybed">&nbsp;&nbsp;아기침대이용가능</label></td>
							<td class="cb"><input type="checkbox" id="breakfast" name="breakfast" value=1><label for="breakfast">&nbsp;&nbsp;조식제공</label></td>
						</tr>
						<tr>
							<td class="cb"><input type="checkbox" id="tv" name="tv" value=1><label for="tv">&nbsp;&nbsp;TV</label></td>
							<td class="cb"><input type="checkbox" id="ac" name="ac" value=1><label for="ac">&nbsp;&nbsp;에어컨</label></td>
							<td class="cb"><input type="checkbox" id="hairdryer" name="hairdryer" value=1><label for="hairdryer">&nbsp;&nbsp;헤어드라이기</label></td>
						</tr>
						<tr>
							<td class="cb"><input type="checkbox" id="parking" name="parking" value=1><label for="parking">&nbsp;&nbsp;주차가능</label></td>
							<td class="cb"><input type="checkbox" id="disabled" name="disabled" value=1><label for="disabled">&nbsp;&nbsp;장애인편의시설</label></td>
						</tr>
					</table>
					<hr>
					<table>
						<tr>
							<td class="cb"><input type="checkbox" id="pet" name="pet" value=1><label for="pet">&nbsp;&nbsp;반려동물동반가능</label></td>
							<td class="cb"><input type="checkbox" id="smoke" name="smoke" value=1><label for="smoke">&nbsp;&nbsp;흡연가능</label></td>
						</tr>
						<tr>
							<td class="cb"><input type="checkbox" id="evt" name="evt" value=1><label for="evt">&nbsp;&nbsp;이벤트가능</label></td>
							<td class="cb"><input type="checkbox" id="selfcheck" name="selfcheck" value=1><label for="selfcheck">&nbsp;&nbsp;셀프체크인가능</label></td>
						</tr>
					</table>
				</div>
				<div class="modal-footer">
					<button type="submit" class="btn btn-general">적용</button>
					<button type="button" class="btn btn-default" data-dismiss="modal" value=0>취소</button>
				</div>
			</form>
		</div>
	</div>
</div>

<c:if test="${not empty list}">

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=69bfe67931ff11ac5d3aa813e9eb15dd"></script>

<script>

	var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
	var options = { //지도를 생성할 때 필요한 기본 옵션
		center: new kakao.maps.LatLng(${list.get(0).mapx}, ${list.get(0).mapy}), //지도의 중심좌표.
		level: 10 //지도의 레벨(확대, 축소 정도)
	};
	
	var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴

	var positions = [
	
	    <c:forEach items="${list}" var="dto">     
	
	   {
	
	       title: '${dto.bnbName}', 
	
	       latlng: new daum.maps.LatLng(${dto.mapx} , ${dto.mapy}),
	       
	       content: '${String.format("%,d", dto.price * condition.gap * condition.guest)}'
	
	   },
	
	   </c:forEach>
	
	];
	
	for (var i = 0; i < positions.length; i ++) {
	
	    // 마커를 생성합니다
	
	    var marker = new daum.maps.Marker({
	
	        map: map, // 마커를 표시할 지도
	
	        position: positions[i].latlng, // 마커를 표시할 위치
	
	    });
	    
	    var iwContent = '<div style="padding:5px; width:250px; height:100px;"><h4 style="text-align:center;">'+positions[i].title+'</h4><div style="text-align:center;">총'+positions[i].content+'원</div>'+'</div>', iwRemoveable = true;
	    
	    var infowindow = new kakao.maps.InfoWindow({
	        /* content: positions[i].content // 인포윈도우에 표시할 내용 */
	        content:iwContent
	    });
	    
	    infowindow.open(map, marker);
		
	    marker.setMap(map);
	
	}
	
	function makeOverListener(map, marker, infowindow) {
	    return function() {
	        infowindow.open(map, marker);
	    };
	}
	
	function makeOutListener(infowindow) {
	    return function() {
	        infowindow.close();
	    };
	}
	
</script>

</c:if>