<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="/bnna/resources/js/header.js"></script>
	<!-- header -->
        <header>

            <!-- 상단메뉴 -->
            <div id="headerlink">
                <a href="#" class="headerlink-item">마이페이지</a>
                <a href="#" class="headerlink-item">로그아웃</a>
                <a href="#" class="headerlink-item">회원가입</a>
                <a href="#" class="headerlink-item">호스트되기</a>
                <a href="#" class="headerlink-item">예약확인</a>
                <a href="#" class="headerlink-item">게시판</a>
                <a href="#" class="headerlink-item last">고객센터</a>
            </div>


            <!-- 로고 및 캐릭터 -->
            <img src="/bnna/resources/image/logo.png" id="logo">
			
			<form method="GET" action="/bnna/member/bnbsearch/result.action">
	            <div class="header-search">
	                <select id="location" name="location" required class="form-control inline" value="${condition.location}">
                        <option value="서울특별시">서울특별시</option>
                        <option value="부산광역시">부산광역시</option>
                        <option value="대구광역시">대구광역시</option>
                        <option value="인천광역시">인천광역시</option>
                        <option value="광주광역시">광주광역시</option>
                        <option value="대전광역시">대전광역시</option>
                        <option value="울산광역시">울산광역시</option>
                        <option value="세종특별자치시">세종특별자치시</option>
                        <option value="경기도">경기도</option>
                        <option value="강원도">강원도</option>
                        <option value="충청북도">충청북도</option>
                        <option value="충청남도">충청남도</option>
                        <option value="전라북도">전라북도</option>
                        <option value="전라남도">전라남도</option>
                        <option value="경상북도">경상북도</option>
                        <option value="경상남도">경상남도</option>
                        <option value="제주특별자치도">제주특별자치도</option>
                    </select>
	                <input type="date" id="checkIn" name="checkIn" required value="${condition.checkIn}" min="1900-01-01" max="2999-01-01">
	                <input type="date" id="checkOut" name="checkOut" required value="${condition.checkOut}" min="1900-01-02" max="2999-01-01" readonly>
	                <input type="number" id="guest" name="guest" min=1 max=10 value=${condition.guest} placeholder="인원 추가" class="last" required>
					<input type="hidden" id="gap" name="gap" value=${condition.gap}>
					<button type="submit" id="submitbtn" class="btn-search">
	                    <span class="glyphicon glyphicon-search"></span>
	                </button>
	            </div>
            </form>
        </header>
        
        
        <script>
        
			$(document).ready(function() {
	    	   
				// 검색조건의 지역으로 설정
	            $('select[id="location"]').find('option:contains("${condition.location}")').attr("selected",true);
	        
	            // 체크인, 체크아웃 날짜 제한
	            var today = new Date();
	            var dd = today.getDate();
	            var mm = today.getMonth()+1; //January is 0!
	            var yyyy = today.getFullYear();
				
	            // 오늘 날짜 설정
	            if(dd<10){
					dd='0'+dd
				} 
				if(mm<10){
				    mm='0'+mm
				}
				
	            today = yyyy+'-'+mm+'-'+dd;
	            
	            // 체크인 최소날짜 오늘
	            document.getElementById("checkIn").setAttribute("min", today);

			});
			
			$(function(){
				$("#checkIn").on("input", function(){
					if ($("#checkIn").val()=='')
						$("checkOut").attr("readonly", true);
					else
						// 체크인날짜가 입력되었을 경우 할 일
						// 1. 체크아웃날짜 입력할 수 있도록 바꾼다.
						// 2. 체크아웃날짜가 체크인날짜보다 이르지 못하게 제한을 걸어둔다.
						
						// 1.
						$("#checkOut").attr("readonly", false);
					
						// 2.
						var checkIn=new Date($("#checkIn").val());
						var dd=checkIn.getDate()+1;
						var mm=checkIn.getMonth()+1;
						var yyyy=checkIn.getFullYear();
						
			            if(dd<10){
							dd='0'+dd
						} 
						if(mm<10){
						    mm='0'+mm
						}
						
			            checkOut = yyyy+'-'+mm+'-'+dd;
			            
			            // 체크아웃 최소날짜 체크인날짜+1일
			            document.getElementById("checkOut").setAttribute("min", checkOut);
						
				});
			});
			
			$(function(){
				$("#checkOut").on("input", function(){
					// 체크아웃날짜가 입력되었을 경우 할 일
					// 1. 체크인날짜와 체크아웃날짜의 gap을 구해서 value로 설정해준다.
					var checkIn=new Date($("#checkIn").val());
					var checkOut=new Date($("#checkOut").val());
		            var gap=(checkOut.getTime()-checkIn.getTime())/1000/60/60/24;
		            $("#gap").val(gap);
				})
			});
			
			
			
        </script>