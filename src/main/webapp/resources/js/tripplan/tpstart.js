/*

	tp start JavaScript 
	여행 일정 시작페이지 js
	
*/


//------------------------  tp start  ----------------------------------
//전체 보여주기
function doShowAll() { 
    $('.city').show();
    $('#category #all').attr('class', 'clicked');
    $('#category #type1').attr('class', 'unclicked');
    $('#category #type2').attr('class', 'unclicked');
    $('#category #type3').attr('class', 'unclicked');
} 

//수도권 보여주기
function doShow1() { 
    $('.type2').hide();
    $('.type3').hide();
    $('.type1').show();
    $('#category #all').attr('class', 'unclicked');
    $('#category #type1').attr('class', 'clicked');
    $('#category #type2').attr('class', 'unclicked');
    $('#category #type3').attr('class', 'unclicked');
} 

//특별.광역시 보여주기
function doShow2() { 
    $('.type1').hide();
    $('.type3').hide();
    $('.type2').show();
    $('#category #all').attr('class', 'unclicked');
    $('#category #type1').attr('class', 'unclicked');
    $('#category #type2').attr('class', 'clicked');
    $('#category #type3').attr('class', 'unclicked');
} 

//지방 보여주기
function doShow3() { 
    $('.type1').hide();
    $('.type2').hide();
    $('.type3').show();
    $('#category #all').attr('class', 'unclicked');
    $('#category #type1').attr('class', 'unclicked');
    $('#category #type2').attr('class', 'unclicked');
    $('#category #type3').attr('class', 'clicked');
} 


//city div 클릭시 make page로 이동
//$(".city").click( function() {
//	
//	$(this).attr('data-toggle','modal');
//	$(this).attr('data-target','#pickDate');
//	
//	let city = $(this).data('name');
//	//location.href="/bnna/member/tripplan/make.action?city=" + city;
//
//	
//});



//------------------------  tp home  ----------------------------------

//slick 부분
$(function(){
  $('#slider-div').slick({
      slide: 'div',        //슬라이드 되어야 할 태그 ex) div, li 
      infinite : true,     //무한 반복 옵션     
      slidesToShow : 1,        // 한 화면에 보여질 컨텐츠 개수
      slidesToScroll : 1,        //스크롤 한번에 움직일 컨텐츠 개수
      speed : 100,     // 다음 버튼 누르고 다음 화면 뜨는데까지 걸리는 시간(ms)
      arrows : true,         // 옆으로 이동하는 화살표 표시 여부
      dots : true,         // 스크롤바 아래 점으로 페이지네이션 여부
      autoplay : true,            // 자동 스크롤 사용 여부
      autoplaySpeed : 4000,         // 자동 스크롤 시 다음으로 넘어가는데 걸리는 시간 (ms)
      pauseOnHover : true,        // 슬라이드 이동    시 마우스 호버하면 슬라이더 멈추게 설정
      vertical : false,        // 세로 방향 슬라이드 옵션
      prevArrow : "<button type='button' class='slick-prev'>Previous</button>",        // 이전 화살표 모양 설정
      nextArrow : "<button type='button' class='slick-next'>Next</button>",        // 다음 화살표 모양 설정
      dotsClass : "slick-dots",     //아래 나오는 페이지네이션(점) css class 지정
      draggable : true,     //드래그 가능 여부 

  });
})


//특정 엘리먼트로 부드럽게 이동하기
$(document).ready(function(){

	$('#btnStart').click(function(){

		var offset = $('#tpstart').offset(); //선택한 태그의 위치를 반환

            //animate()메서드를 이용해서 선택한 태그의 스크롤 위치를 지정해서 0.4초 동안 부드럽게 해당 위치로 이동함 

        $('html').animate({scrollTop : offset.top}, 400);
        
        return;
	});

});

