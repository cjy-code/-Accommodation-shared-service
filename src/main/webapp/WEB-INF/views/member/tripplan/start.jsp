<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

   <link rel="stylesheet" href="/bnna/resources/css/tripplan/tpstart.css">
    <!-- Slick 불러오기 -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.9.0/slick.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.9.0/slick.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.9.0/slick-theme.min.css">
    
    <!-- DateRangePicker -->
	<script type="text/javascript" src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
	<script type="text/javascript" src="/bnna/resources/js/moment-with-locales.js"></script> <!-- 언어파일 -->
	<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
	<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />
   
        <section class="mainsection">
        
        	<!-- home 메인 첫 페이지 -->
			<div id="tphome">

                <div id="txt">
                    <div>에어비앤나를 통해 숙소를 구하셨나요.</div>
                    <div style="font-size: 1.2em; margin-top: 5px;">이제 여행 일정을 짜러 가보실까요?</div>
                </div>
                
                <button id="btnStart" class="btn-general"><div>AirBnNa와 함께</div>여행 일정 만들기</button>

                <!-- slick -->
                <div id="slickarea">
                    <div id="slider-div">
                        <div class="crop"><img src="/bnna/resources/image/tripplan/homepic/home1.jpg" alt=""></div>
                        <div class="crop"><img src="/bnna/resources/image/tripplan/homepic/home2.jpg" alt=""></div>
                        <div class="crop"><img src="/bnna/resources/image/tripplan/homepic/home3.jpg" alt=""></div>
                        <div class="crop"><img src="/bnna/resources/image/tripplan/homepic/home4.jpg" alt=""></div>
                        <div class="crop"><img src="/bnna/resources/image/tripplan/homepic/home5.jpg" alt=""></div>
                    </div>
                    <div class="black"></div>
                </div>

            </div>
        
        	<hr>
        	
            <!-- 일정 만들기 시작 페이지 -->
            <div class="tp-start" id="tpstart">
                <div class="title">여행지</div>
                <div class="intro">원하시는 여행지를 목록에서 선택해주세요.</div>

                <div id="category">
                    <span id="all" onclick="javascript:doShowAll()">전체</span>
                    <span id="type1" onclick="javascript:doShow1()">수도권</span>
                    <span id="type2" onclick="javascript:doShow2()">특별·광역시</span>
                    <span id="type3" onclick="javascript:doShow3()">지방</span>
                </div>

                <hr>

                <div id="citylist">

                    <div class="city type1 type2" data-name="서울">
                        <div class="crop">
                        <img src="/bnna/resources/image/tripplan/city/서울.jpg">
                        </div>
                        <div class="info">
                            <div class="eng">SEOUL</div>
                            <div class="kor">서울특별시</div>
                            <div class="desc">남산타워,경복궁,명동,남대문</div>
                        </div>
                    </div>

                    <div class="city type1 type2" data-name="인천">
                        <div class="crop">
                        <img src="/bnna/resources/image/tripplan/city/인천.jpg">
                        </div>
                        <div class="info">
                            <div class="eng">INCHEON</div>
                            <div class="kor">인천광역시</div>
                            <div class="desc">차이나타운,인천공항,센트럴파크</div>
                        </div>
                    </div>

                    <div class="city type2 type3" data-name="대전">
                        <div class="crop">
                        <img src="/bnna/resources/image/tripplan/city/대전.jpg">
                        </div>
                        <div class="info">
                            <div class="eng">DAEJEON</div>
                            <div class="kor">대전광역시</div>
                            <div class="desc">성심당,계룡산,대전오월드</div>
                        </div>
                    </div>

                    <div class="city type2 type3" data-name="대구">
                        <div class="crop">
                        <img src="/bnna/resources/image/tripplan/city/대구.jpg">
                        </div>
                        <div class="info">
                            <div class="eng">DAEGU</div>
                            <div class="kor">대구광역시</div>
                            <div class="desc">반곡지,합천영상테마파크,이월드</div>
                        </div>
                    </div>

                    <div class="city type2 type3" data-name="광주">
                        <div class="crop">
                        <img src="/bnna/resources/image/tripplan/city/광주.jpg">
                        </div>
                        <div class="info">
                            <div class="eng">GWANGJU</div>
                            <div class="kor">광주광역시</div>
                            <div class="desc">화담숲,시화문화마을,사직공원</div>
                        </div>
                    </div>

                    <div class="city type2 type3" data-name="부산">
                        <div class="crop">
                        <img src="/bnna/resources/image/tripplan/city/부산.jpg">
                        </div>
                        <div class="info">
                            <div class="eng">BUSAN</div>
                            <div class="kor">부산광역시</div>
                            <div class="desc">해운대,광안리,센텀시티</div>
                        </div>
                    </div>

                    <div class="city type2 type3" data-name="울산">
                        <div class="crop">
                        <img src="/bnna/resources/image/tripplan/city/울산.jpg">
                        </div>
                        <div class="info">
                            <div class="eng">ULSAN</div>
                            <div class="kor">울산광역시</div>
                            <div class="desc">대왕암공원,간절곶,태화강국가정원</div>
                        </div>
                    </div>

                    <div class="city type1" data-name="경기">
                        <div class="crop">
                        <img src="/bnna/resources/image/tripplan/city/경기도.jpg">
                        </div>
                        <div class="info">
                            <div class="eng">GYEONGGI</div>
                            <div class="kor">경기도</div>
                            <div class="desc">헤이리,두물머리,수원화성,한국민속촌</div>
                        </div>
                    </div>

                    <div class="city type3" data-name="강원">
                        <div class="crop">
                        <img src="/bnna/resources/image/tripplan/city/강원도.jpg">
                        </div>
                        <div class="info">
                            <div class="eng">GANGWON</div>
                            <div class="kor">강원도</div>
                            <div class="desc">대관령양때목장,남이섬,오죽헌</div>
                        </div>
                    </div>

                    <div class="city type3" data-name="충청">
                        <div class="crop">
                        <img src="/bnna/resources/image/tripplan/city/충청도.jpg">
                        </div>
                        <div class="info">
                            <div class="eng">CHUNGCHEONG</div>
                            <div class="kor">충청도</div>
                            <div class="desc">꽂지해수욕장,아산온천,해미읍성</div>
                        </div>
                    </div>

                    <div class="city type3" data-name="전라">
                        <div class="crop">
                        <img src="/bnna/resources/image/tripplan/city/전라도.jpg">
                        </div>
                        <div class="info">
                            <div class="eng">JEOLLA</div>
                            <div class="kor">전라도</div>
                            <div class="desc">광한루원,전주한옥마을,여수엑스포</div>
                        </div>
                    </div>

                    <div class="city type3" data-name="경상">
                        <div class="crop">
                        <img src="/bnna/resources/image/tripplan/city/경상도.jpg">
                        </div>
                        <div class="info">
                            <div class="eng">GYEONGSANG</div>
                            <div class="kor">경상도</div>
                            <div class="desc">첨성대,동궁과월지,하회마을,석굴암</div>
                        </div>
                    </div>

                    <div class="city type2 type3" data-name="제주">
                        <div class="crop">
                        <img src="/bnna/resources/image/tripplan/city/제주도.jpg">
                        </div>
                        <div class="info">
                            <div class="eng">JEJU ISLAND</div>
                            <div class="kor">제주도</div>
                            <div class="desc">한라산,애월,섭지코지,서귀포</div>
                        </div>
                    </div>


                </div>

            </div>

		<script src="/bnna/resources/js/tripplan/tpstart.js"></script>
		
		
		<!-- 날짜 선택 하기 Modal -->
		<div class="modal fade" id="pickDate" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="pickDateBody">여행일자 선택</h4>
					</div>
					<div class="modal-body" id="pickDateBody" style="text-align:center;">
						
						<input type="text" name="daterange" id="date" style="width: 200px;">
						
						<input type="hidden" class="citybox" data-name="">
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
						<button type="button" class="btn btn-danger" id="btnMake">여행일정 만들기</button>
					</div>
				</div>
			</div>
		</div>

	<script>
	
	
		moment.locale('ko'); //언어를 한국어로 설정함!

		$('#date').daterangepicker({
			timePicker : false,
			timePicker24Hour : true,
			timePickerSeconds : true,
			singleDatePicker : false,
			locale : {
				format : 'YYYY-MM-DD',
				separator : ' ~ ',
				applyLabel : "적용",
				cancelLabel : "닫기"
			},
		});

		
		
		$(".city").click(function() {

			$(this).attr('data-toggle', 'modal');
			$(this).attr('data-target', '#pickDate');

			let city = $(this).data('name');
			
			$('.citybox').val(city);
		});

		
		
		$("#btnMake").click(function() {
			
			let city = $('.citybox').val();
			
			let date = $('#pickDateBody #date').val();
			
			let startDate = date.substring(0, 10); 	//여행 시작일
			let endDate = date.substring(13, 23);	//여행 끝일
			
			let sdt = new Date(startDate);
			let edt = new Date(endDate);
			
			//여행이 총 며칠인지 계산
			var dateDiff = Math.ceil((edt.getTime()-sdt.getTime())/(1000*3600*24) + 1);
			
			
			location.href="/bnna/member/tripplan/make.action?city=" + city + "&startDate=" + startDate + "&endDate=" + endDate + "&dateDiff=" + dateDiff;
			
		});
		
		
	</script>

</section>    