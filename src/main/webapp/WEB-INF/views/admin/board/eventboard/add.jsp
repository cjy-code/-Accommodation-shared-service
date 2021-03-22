<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet" href="/bnna/resources/css/admin/event.css">
<section>

	<h2 onclick="location.href='/bnna/admin/board/eventboard/list.action';" style="cursor: pointer;">이벤트 게시판  <small>글 쓰기</small></h2>

	<form method="POST" action="/bnna/admin/board/eventboard/addok.action" enctype="multipart/form-data">
		<table class="table table-bordered evtboard">
			<tr>
				<th style="width: 120px;">제목</th>
				<td style="width: auto;"><input type="text" name="title"
					class="form-control"></td>
			</tr>
			<tr>
				<th>내용</th>
				<td>
					<textarea name="content" class="form-control" style="height: 300px;"></textarea>
				</td>
			</tr>
			<tr>
				<th>파일</th>
				<td >
					<input type="file" name="imagefile" accept="image/*" onchange="setThumbnail(event);">
					<div id="image_container"></div>
				</td>
			</tr>
		</table>
		<div class="btns">
			<button type="submit" class="btn btn-general">쓰기</button>
			<button type="button" class="btn btn-default"
				onclick="location.href='/bnna/admin/board/eventboard/list.action';">목록</button>
		</div>
	</form>



</section>

<script>

	function setThumbnail(event) {
		var reader = new FileReader();
		reader.onload = function(event) {
			//요소 초기화
			$('#image_container').empty();
			
			//새로 선택한 사진 출력
			var img = document.createElement("img");
			img.setAttribute("src", event.target.result);
			document.querySelector("div#image_container").appendChild(img);
		};
		reader.readAsDataURL(event.target.files[0]);
	}
</script>