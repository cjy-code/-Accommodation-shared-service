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






//---------------------2.기본 js
$("#daysection .all").css('background-color', '#666');
$("#daysection .all").css('color', 'white');
$("#daysection .all").css('font-weight', 'bold');

$(document).on('click', '#daysection .day', function() {

    $("#bodysection").css('width', '235px');
    $("#daysection .all").css('background-color', 'white');
    $("#daysection .all").css('color', '#666');
    $("#daysection .all").css('font-weight', 'normal');
    $("#mapsection").css('padding-left', '327px')

});

$(document).on('click', '#daysection .all', function() {

    $("#bodysection").css('width', '702px');
    $("#daysection .all").css('background-color', '#666');
    $("#daysection .all").css('color', 'white');
    $("#daysection .all").css('font-weight', 'bold');

});
