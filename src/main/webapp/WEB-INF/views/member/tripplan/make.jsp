<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" href="/bnna/resources/css/tripplan/tpmake.css">
<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=55ab69bc871b766267ae1612c1e51daf&libraries=services"></script>

<section class="mainsection">

	<div id="dayarea">
		<!-- 일정 간단 정보 -->
		<div id="dayInfo">
			<p>일정작성</p>
			<p>
				${city} <span class="day">${dateDiff}</span>일
			</p>
			<div class="day-period">
				<span>${startDate2}</span> <span> - </span> <span>${endDate2}</span>
			</div>
		</div>

		<div id="dayContent">
			<!-- 총 며칠간의 여행? -->
			<div id="dayList">

				<!-- 
				<div class="dayitem selectAble selected">
					<div class="day-num">
						DAY <span class="num">1</span>
					</div>
					<span class="day-date">03.14</span> <span class="day-day">(일)</span>
				</div>

				<div class="dayitem selectAble">
					<div class="day-num">
						DAY <span class="num">2</span>
					</div>
					<span class="day-date">03.15</span> <span class="day-day">(월)</span>
				</div> 
				-->
				
			</div>

			<div class="savePlan">일정 저장</div>
		</div>


	</div>
	
	<script>
	

	
		//dayitem 동적으로 생성하기
		let startDate = new Date('<c:out value="${startDate}"/>');
		let totalDate = ${dateDiff};
		console.log(totalDate);
		
		let temp = new Date(startDate);
		
		for (let i=1; i<=totalDate; i++) {
			
			let tempDate = startDate;
			
			let temp = '';
			
			temp += '<div class="dayitem selectAble">';
			temp += '<div class="day-num">';
			temp += 'DAY <span class="num">' + i + '</span>';
			temp += '</div>';
			temp += '<span class="day-date">'+ (tempDate.getMonth() + 1) + '.' + tempDate.getDate()  +'</span> <span class="day-day">('+ getDayName(tempDate) +')</span>';
			temp += '</div>';
			
			$("#dayList").append(temp);
			
			tempDate.setDate(tempDate.getDate() + 1);
			
		}
		
		
		//요일을 구하는 함수
		function getDayName(date) {
			
			let weekDay = new Array('일', '월','화', '수','목', '금', '토');
			
			return weekDay[date.getDay()];
			
		}
	
		
		//
		$('.dayitem').click(function() {
			let day = $(this).children('.day-num').children('.num').text();
			let year = $(this).children('.day-date').text();
			let weekday = $(this).children('.day-day').text();
			
			
			$('#planHead .headDay').text(day);
			$('#planHead .headDate').text('2021.' + year);
			$('#planHead .headWeekDay').text(weekday);
			
		});
		
		
		//dayitem 첫째날 고정하기
		//document.getElementById("dayList")[1].click();
		$('#dayList').children().first('.dayitem').trigger('click');
		$('#dayList').children().first('.dayitem').addClass('selected');

	</script>
	
	<div id="planarea">
		<div id="planHead">
			<span>DAY <span class="headDay">1</span></span>
			<div><span class="headDate">2021.${startDate2}</span><span class="headWeekDay">(일)</span></div>
		</div>

		<div id="planBody">
			<div id="planDetail">
					<!--                         
                     <div class="scheduleItem">
                         <div class="imgbox">
                             <div class="item_number">1</div>
                             <img src="/image/tripplan/1.jpg" alt="">
                         </div>

                         <div class="contentbox">
                             <p>장소이름1</p>
                             <p>장소종류1</p>
                         </div>

                         <button class="btn btn_del">
                             <span class="glyphicon glyphicon-remove"></span>
                         </button>
                         
                         <div class="btn_box" style="display: none;">
                             <input type="button" class="btn btn-default" data-toggle="modal" data-target="#detailCommonInfo" value="정보보기">
                             <input type="button" class="btn btn-default addScheduleItem" value="일정추가">
                         </div>
                     </div> 
                     -->
			</div>
		</div>
	</div>


	<!-- 지도파트 -->
	<div id="maparea">
		<div id="map"></div>
	</div>

	<!-- 검색창 파트 -->
	<div id="searcharea">
		<div id="searchBody">
			<!--  searchBody -->
			<div id="searchTheme">
				<div class="titleTheme">테마 선택</div>
				<div class="theme_area">
					<button class="nature btn" data-cat1="A01" data-theme="자연">자연</button>
					<button class="tour btn" data-cat1="A02" data-theme="관광">관광</button>
					<button class="activity btn" data-cat1="A03" data-theme="레포츠">레포츠</button>
					<button class="shopping btn" data-cat1="A04" data-theme="쇼핑">쇼핑</button>
					<button class="food btn" data-cat1="A05" data-theme="음식">음식</button>
				</div>
			</div>

			<div id="searchKeyword" class="searchRow">
				<input type="text" placeholder="검색어를 입력하세요."
					class="form-control inline-block" id="searchText" name="keyword">
				<button id="searchBtn" class="inline-block btn" onclick="getData();">
					<span class="glyphicon glyphicon-search"></span>
				</button>
			</div>

		</div>

		<div class="resultInfo">
			<div class="inline-block">
				선택 테마 : <span class="theme">전체</span>
			</div>
			<div class="inline-block">
				검색결과 (<span class="resultnum">0</span>건)
			</div>
			<div style="clear: both;"></div>
		</div>

		<div id="searchResult" class="searchRow">
			<img src="/bnna/resources/image/sorry.png" style="width: 220px;">
			<!-- resultItem -->
			<!-- <div class="resultItem">
				<div class="img_box">
					<img src="/image/tripplan/1.jpg" alt="">
					<div class="item_number" style="display: none;"></div>
				</div>
			    <div class="content_box">
					<p class="content_name">장소이름</p>
					<p class="content_type">자연</p>
				</div>
				<button class="btn_del btn" style="display: none;">
					<span class="glyphicon glyphicon-remove deleteScheduleItem"></span>
				</button>
				<div class="btn_box">
					<input type="button" class="btn btn-default" data-toggle="modal"
						data-target="#detailCommonInfo" value="정보보기"> <input
						type="button" class="btn btn-default addScheduleItem" value="일정추가">
				</div>
			</div> -->

		</div>
		<!-- paging -->
		<div id="paging">
<!-- 
			<nav aria-label="Page navigation">
				<ul id="pagination" class="pagination"></ul>
			</nav> 
			-->
			<div class="pagination" id="pagination">
			
			</div>
		</div>
	</div>




    <!-- 장소 정보보기 Modal -->
	<div class="modal fade" id="detailCommonInfo" tabindex="-1"
	   role="dialog" aria-labelledby="myModalLabel">
	   <div class="modal-dialog" role="document">
	      <div class="modal-content">
	         <div class="modal-header">
	            <button type="button" class="close" data-dismiss="modal"
	               aria-label="Close">
	               <span aria-hidden="true">&times;</span>
	            </button>
	            <h4 class="modal-title" id="commonInfoTitle">장소제목</h4>
	         </div>
	         <div class="modal-body" id="commonInfoBody">
	            <div class="image">
	               <img src="/bnna/resources/image/tripplan/noimage.jpg" alt="">
	            </div>
	            <div class="content">
	               <div>주소 : <span class="category"></span></div>
	               <div class="makeLine">전화번호 : <span class="tel"></span></div>
	               <hr style="margin: 4px 0px;">
	               <div class="pdtop"><span class="overview"></span></div>
	            </div>
	               
	         </div>
	         <div class="modal-footer">
	            <button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
	         </div>
	      </div>
	   </div>
	</div>

	<script src="/bnna/resources/js/tripplan/tpmake.js"></script>



</section>

