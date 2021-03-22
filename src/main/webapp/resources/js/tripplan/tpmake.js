/*
	여행 일정 만들기 js
	tpmake.js
	
	1. CSS 관련
	2. 관광정보 관련
	3. KAKAO MAP 관련

 */

//관광정보 검색 테마
let thememap = new Map();

thememap.set('A01', '자연');
thememap.set('A02', '관광');
thememap.set('A03', '레포츠');
thememap.set('A04', '쇼핑');
thememap.set('A05', '음식');


// ------------------- 1. CSS 관련 -----------------------




// 일정 선택 CSS
$(document).on('click', '#dayContent .selectAble', function() {

	$("#dayContent .selectAble").each(function() {
		$(this).removeClass('selected');
	});

	$(this).addClass('selected');
});

// 테마 선택시 CSS 변경
$(document).on('click', '#searchTheme .btn', function() {

	$("#searchTheme .btn").each(function() {
		$(this).css('opacity', '1');
	});

	$(this).css('background-color', '#444');
	$(this).css('color', 'white');
	$(this).css('outline', 'none');
});

// DAY 추가 버튼
$(".day-add").on('click', function() {

	let tmp = '';

	tmp += '<div class="dayitem selectAble">'
	tmp += '<div class="day-num">DAY'
	tmp += '<span class="num">2</span></div>'
	tmp += '<span class="day-date">03.15</span>'
	tmp += '<span class="day-day">(월)</span>'

	$("#dayList").append(tmp);
});



$(function() {
	
	let index = 0;
	// 일정 경로
	 $("#planDetail").sortable({
		 revert : true,
		 placeholder : "plan-placeholder",
		 update : function() {
			 rearrangeItem(); // 경로 숫자 재설정
		 }
	 });
	 

	// 검색 결과 아이템
	$(document).on('mousedown', '#searchResult .resultItem', function() {
		console.log('드래그 이벤트 등록');

		$(this).draggable({
			connectToSortable : "#planDetail",
			helper : "clone",
			revert : "invalid"
		});
	});
	

	// 검색 결과 아이템 드래그 시작 - 동적 생성된 태그에도 적용
	$(document).on('dragstart', '.resultItem', function(event, ui) {
		console.log('dragstart =======');
		$(ui).width("205");
		$(ui).height("90");
		$(ui.helper).children('.btn_box').hide();

	});

	// 검색 결과 아이템 드래그 끝 - 동적 생성된 태그에도 적용
	$(document).on('dragstop', '.resultItem', function(event, ui) {
		// console.log(e.relatedTarget); //null
		console.log('dragstop =======');
//		console.log(this);
//		console.log(ui);


		$(ui.helper).children('.btn_del').show();
		$(ui.helper).children('.img_box').children('.item_number').show();
		
		rearrangeItem(); // 경로 숫자 재설정

	});
	
	// 검색 결과 아이템 드래그 시작
	// $(".resultItem").on("dragstart", function (event, ui) {
	// console.log(event);
	// // console.log(ui.helper.children('.btn_box').hide());
	// console.log(ui);
	// console.log(this);
	//
	// $(ui).width("205");
	// $(ui).height("90");
	// $(ui.helper).children('.btn_box').hide();
	//
	// });

	// 검색 결과 아이템 드래그 끝
	// $(".resultItem").on("dragstop", function (event, ui) {
	// console.log(event)
	// console.log(ui);
	// console.log(this);
	//
	// // $(ui.helper).append('<input type="button" class="deleteScheduleItem"
	// value="삭제">');
	// $(ui.helper).children('.btn_del').show();
	// $(ui.helper).children('.img_box').children('.item_number').show();
	// });

});


// 일정 목록에 있는 아이템을 삭제
$(document).on('click', '.deleteScheduleItem', function(e) {
	// console.log(e.relatedTarget); //null
//	console.log(e.target);

	$(e.target).parent().parent().detach();
	rearrangeItem();

});

// 검색 목록에 있는 일정추가
$(document).on('click', '.addScheduleItem', function(e) {

	let resultItem = $(e.target).parent().parent().clone();
	resultItem.children('.btn_box').hide();
	resultItem.children('.btn_del').show();
	resultItem.children('.img_box').children('.item_number').show();

	resultItem.appendTo('#planDetail');

	rearrangeItem();

});

// 경로 일정을 재정렬 하는 메서드
function rearrangeItem() {
	$("#planDetail .img_box").each(function(index) {
//		console.log(this);
		index = index + 1;
		$(this).children('.item_number').text(index);
//		console.log(index);
		$(this).children('.item_number').attr('name', index);
	});
}










// ------------------- 2. 관광정보 관련 -----------------------


/*
 * API 데이터 리스트를 받아오는 메서드
 */

// 검색어를 저장해둘 전역변수
let keyword = "";

function getData(number, cat) {
	let searchText = document.getElementById('searchText');
	let content = document.getElementById('searchResult');
	let pagination = document.getElementById('pagination');
	let areaCode = document.getElementById('areaCode');
	let cat1;
	
	//category 선택 안했을땐 공백, 하면 그 선택지로 검색
	if (cat == undefined) {
		cat1 = "";
	} else {
		cat1 = cat;
	}

	// 검색어가 있으면 keyword 변수에 검색어를 넣는다
	if (searchText.value != "") {
		keyword = searchText.value;
	}

	content.innerHTML = "";
	pagination.innerHTMl = "";

	if (number == undefined) {
		number = 1;
	}

		$.ajax({
				url : 'plandata.action',
				type : 'GET',
				dataType : 'json',
				data : 'pageNo='+number+'&keyword='+keyword+'&cat1='+cat1,
				success : function(data) {
					console.log(data);
					myItem = data.response.body.items.item;
					itemInfo = data.response.body;
					
					console.log(myItem);

					// 페이징 관련 변수
					// ==================================================
					var totalCount = itemInfo.totalCount; // 총 게시물 수
					var pageSize = itemInfo.numOfRows; // 한페이지당 출력 개수
					var nowPage = number; // 현재 페이지 번호
					var totalPage = 0;
					var n = 0;
					var loop = 0;
					var blockSize = 5; // 페이지바 개수

					// ====================================================================
					
					
					$(".resultnum").html(totalCount);
					

					// 페이징 관련 코드
					// ==================================================
					var pagebar = "";
					totalPage = Math.ceil(totalCount / pageSize);
					loop = 1;
					n = parseInt((nowPage - 1) / blockSize) * blockSize + 1;
					
					// 이전 페이지로 이동
//					if (n == 1) {
//						pagebar += '<li class="disabled"><a href=\"#!\" aria-label=\"Previous\"><span aria-hidden=\"true\">&laquo;</span></a></li>';
//
//					} else {
//						pagebar += '<li><a onclick="getData('
//								+ (n - 1)
//								+ ')" aria-label=\"Previous\"><span aria-hidden=\"true\">&laquo;</span></a></li>';
//					}
					if (n == 1) {
						pagebar += '<a class="disabled" href=\"#!\" aria-label=\"Previous\">&laquo;</a>';
						
					} else {
						pagebar += '<a onclick="getData('
							+ (n - 1)
							+ ')" aria-label=\"Previous\">&laquo;</a>';
					}

					// 페이지바 코드 동적 생성
					while (!(loop > blockSize || n > totalPage)) {

						if (nowPage == n) {
							pagebar += "<a class='active' ";
						} else {
							pagebar += "<a ";
						}
						pagebar += 'onclick="getData(' + n + ')">' + n
								+ '</a>';

						loop++;
						n++;

					}

					// 다음 10페이지로 이동
//					if (n > totalPage) {
//						pagebar += '<li class="disabled"><a href=\"#!\" aria-label=\"Previous\"><span aria-hidden=\"true\">&raquo;</span></a></li>';
//
//					} else {
//						pagebar += '<li><a onclick="getData('
//								+ n
//								+ ')" aria-label=\"Previous\"><span aria-hidden=\"true\">&raquo;</span></a></li>';
//					}
					
					if (n > totalPage) {
						pagebar += "<a class='disabled' href=\"#!\" aria-label=\"Next\">&raquo;</a>";
						
					} else {
						pagebar += '<a href="getData('
							+ n
							+ ')" aria-label=\"Next\">&raquo;</a>';
					}

					// ===================================================================

					for (var i = 0; i < myItem.length ; i++) {
						var output = '';

						output += '<div class="resultItem" data-contentid="'+myItem[i].contentid+'" data-contenttypeid="'+myItem[i].contenttypeid+'">';
						output += '<div class="img_box">';

						if (myItem[i].firstimage == undefined) {
							output += '<img src="/bnna/resources/image/tripplan/noimage.jpg">';
						} else {
							output += '<img src="' + myItem[i].firstimage
									+ '">';
						}
						output += '<div class="item_number" style="display: none;"></div>';
						output += '</div>';
						output += '<div class="content_box">';
						
						let title = '';
						
						if (myItem[i].title.length >= 10) {
							title = myItem[i].title.substring(0, 10) + '..';
						} else {
							title = myItem[i].title;
						}
						
						output += '<p class="content_name" ondragstart="return false" onselectstart="return false">'
								+ title
								+ '</p>';
						output += '<p class="content_type" ondragstart="return false" onselectstart="return false">'
								+ thememap.get(myItem[i].cat1)
								+ '</p>';
						output += '</div>';
						output += '<button class="btn_del btn" style="display: none;">';
						output += '<span class="glyphicon glyphicon-remove deleteScheduleItem"></span>';
						output += '</button>';
						output += '<div class="btn_box">';
						output += '<input type="button" class="btn btn-default" data-toggle="modal" data-target="#detailCommonInfo" value="정보보기">';
						output += '<input type="button" class="btn btn-default addScheduleItem" value="일정추가">';
						output += '</div>';
						output += '</div>';

						content.innerHTML += output;
						

					}

					pagination.innerHTML = pagebar;
					
					// 마우스 클릭했을때 draggable 이벤트 등록이 되므로
					// ajax의 결과로 동적 태그를 생성한 후에 강제로 해당 태그들에게 클릭 이벤트를
					// 한번씩 발생시켜서 draggable 이벤트를 등록해준다 
//					console.log($("#searchResult .resultItem"));
					$("#searchResult .resultItem").trigger("mousedown");

				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					console.log("Status: " + textStatus);
					console.log("Error: " + errorThrown);
					console.log(errorThrown);
					console.warn(XMLHttpRequest.responseText);
				}
			});
	

}



//검색 테마 버튼 CSS
$('.theme_area').children().on('click', function() {

	$('.theme').html($(this).data('theme'));
	
	let cat = thememap.get($(this).data('theme'));
	
	getData(1, cat);
	
});



//장소 정보보기 - ajax로 api 데이터 받아오기 *********************************
$('#detailCommonInfo').on('show.bs.modal', function (e) {
   console.log('call show.bs.modal event');
   
   $('#commonInfoTitle').html("");
   $('#commonInfoBody .image').html("");
   $('#commonInfoBody .category').html("");
   $('#commonInfoBody .tel').html("");
   $('#commonInfoBody .overview').html("");
   
   let contentid = $(e.relatedTarget).parent().parent().data('contentid');
   let contenttypeid = $(e.relatedTarget).parent().parent().data('contenttypeid');
   
   $.ajax({
      url : '/bnna/member/tripplan/detaildata.action',
      type : 'GET',
      data : "contentid=" + contentid + "&contenttypeid="+ contenttypeid,
      dataType : 'json',
      success : function(data) {
         console.log(data);
         console.log(data.response.body.items.item);
         let commonData = data.response.body.items.item;
         
         let image;
          if (commonData.firstimage == undefined) {
             image = "/bnna/resources/image/tripplan/noimage.jpg";
          } else {
             image = commonData.firstimage;
          }

         $('#commonInfoTitle').html(commonData.title);
         $('#commonInfoBody .image').html('<img src="'+image+'">');
         $('#commonInfoBody .category').append(commonData.addr1);
         $('#commonInfoBody .tel').append(commonData.tel);
         $('#commonInfoBody .overview').append(commonData.overview);
      }
   });

});





// ------------------- 3. KAKAO MAP 관련 -----------------------


var mapContainer = document.getElementById('map'), // 지도를 표시할 div
mapOption = {
	center : new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
	level : 3
// 지도의 확대 레벨
};

var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

// 마커가 표시될 위치입니다
var markerPosition = new kakao.maps.LatLng(33.450701, 126.570667);

// 마커를 생성합니다 -> 검색의 결과로 생성되는 마커
var marker = new kakao.maps.Marker({
	position : markerPosition
});

// 마커가 지도 위에 표시되도록 설정합니다
marker.setMap(map);

// 아래 코드는 지도 위의 마커를 제거하는 코드입니다
// marker.setMap(null);

/*
 * 지정한 좌표로 이동하고 마커를 생성하는 메서드 @param mapx : x좌표 값 @param mapy : y좌표 값
 */

var infowindow; // 인포윈도우 용 변수

function setMarker(mapx, mapy, title) {
	marker.setMap(null); // 맵에서 마커 초기화
	console.log(infowindow);
	if (infowindow != undefined) {
		infowindow.close(); // 인포윈도우 초기화
	}

	console.log(mapx);
	console.log(mapy);
	console.log(title);

	// 마커가 표시될 위치입니다 (좌표 객체)
	let selectPosition = new kakao.maps.LatLng(mapx, mapy)

	// 마커를 생성합니다
	marker = new kakao.maps.Marker({
		position : selectPosition
	});

	// 마커가 지도 위에 표시되도록 설정합니다
	marker.setMap(map);

	// 생성한 마커를 지도의 중심으로 이동
	map.setCenter(selectPosition);

	// 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
	// var iwContent = '<div style="padding:5px;">Hello World! <br><a
	// href="https://map.kakao.com/link/map/Hello World!,33.450701,126.570667"
	// style="color:blue" target="_blank">큰지도보기</a> <a
	// href="https://map.kakao.com/link/to/Hello World!,33.450701,126.570667"
	// style="color:blue" target="_blank">길찾기</a></div>',
	var iwContent = title, iwPosition = selectPosition; // 인포윈도우 표시 위치입니다

	// 인포윈도우를 생성합니다
	infowindow = new kakao.maps.InfoWindow({
		position : iwPosition,
		content : iwContent
	});

	// 마커 위에 인포윈도우를 표시합니다. 두번째 파라미터인 marker를 넣어주지 않으면 지도 위에 표시됩니다
	infowindow.open(map, marker);

}
