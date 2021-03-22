
$(document).ready(function () {
	
	$("#title").focus();
	
	//add > 이미지 파일명 출력
	$("#image").on("change", function() {
		
		//초기화
		$("#imagename").text("");
		
		for (var i=0; i<$("#image")[0].files.length; i++) {
			//console.log($("#image")[0].files[i].name);
			let imagename = $("#image")[0].files[i].name;
			
			//add > 이미지 이외의 파일 추가한 경우
			if (!(imagename.toLowerCase().endsWith("jpg") || imagename.toLowerCase().endsWith("gif") || imagename.toLowerCase().endsWith("png"))) {
				alert("이미지 파일을 선택해주세요.");
				$("#imagename").text("");
				$("#image").val("");
				return;
			}
			
			$("#imagename").append(imagename + "<br>");
		}
		
	});

	//add > 신고대상회원 버튼 클릭
	$("#btnSearch").click(function() {

		//Reset keyword and tblResult's tbody
		$("#tblResult tbody").html("");
		$("#keyword").val("");
		
		//open searchModal
		$('#searchModal').modal('show');
	});
	
	//add > after open searchModal
	$("#searchModal").on('shown.bs.modal', function() {
		$("#keyword").focus();
	});

	//add > searchModal > enter
	$("#keyword").keyup(function () {
		if (event.keyCode == 13) {
			
			//remove whitespace
			trim();
			
			//is whitespace?
			if ($("#keyword").val() == "") {
				alert("검색어를 입력해주세요.");
				$("#keyword").focus();
				return;
			}
			
			getSearchResult();
		}
	});

	//add > searchModal > 검색 버튼 클릭
	$("#btnSearchResult").click(function () {
		
		//remove whitespace
		trim();

		//is whitespace?
		if ($("#keyword").val() == "") {
			alert("검색어를 입력해주세요.");
			$("#keyword").focus();
			return;
		}
		
		getSearchResult();
	});

	//remove whitespace
	function trim() {
		$("#keyword").val($.trim($("#keyword").val()));		
	}
	
	//add > searchModal > 신고대상회원 검색 결과 호출
	function getSearchResult() {
		$.ajax({
			type: "GET",
			url: "/bnna/member/board/blackboard/searchmember.action",
			data: "condition=" + $("#condition").val() + "&keyword=" + $("#keyword").val(),
			dataType: "json",
			success: function(result) {
				$("#tblResult tbody").html("");
				
				var n = 1;
				
				$(result).each(function(index, item) {
					
					console.log(item.name);
					
					let temp = "";
					
					temp += "<tr>";
					temp += "<td><input type='radio' id='select"+ n +"' name='select' value="+ item.seq +"></td>";
					temp += "<td><label for='select"+ n +"'>"+ item.name +"</td>";
					temp += "<td><label for='select"+ n +"'>"+ item.id +"</td>";
					temp += "</tr>";
					
					$("#tblResult tbody").append(temp);
					
					n++;
					
				});
				
			},
			error: function(a, b, c) {
				console.log(a, b, c);
			}
		});
	}
	
	//add > searchModal > 선택 버튼 클릭
	$("#choice").click(function() {
		
		//get seq, name id
		var target = $("#tblResult tbody tr input[type=radio]:checked");
		var seq = target.val();
		var name = target.parent().next().text();
		var id = target.parent().next().next().text();
		
		//print seq, name, id
		$("#seqIssueMember").val(seq);	
		$("#issueMemberInfo").text(name + " ("+ id +")");
		
		//close searchModal
		$("#searchModal").modal('hide');
		
	});
	
	//신고대상회원 검색하지 않은경우 submit 막기
	$("#add").submit(function(event) {
		if ($("#reply").val().equals("n")) {
			if ($("#issueMemberInfo").text() == "") {
				alert("신고대상회원을 검색해주세요.");
				event.preventDefault();
			}			
		}
	});
	
	var seqBlackBoard;
	var seqParent;
	
	//view > 삭제 버튼 클릭
	$("#btnDel").click(function() {
		//delModal open
		$("#delModal").modal('show');
	});
	
	//view > 삭제모달에서 삭제버튼 클릭
	$("#btnDelOk").click(function() {
		seqBlackBoard = $("#seqBlackBoard").val();
		seqParent = $("#seqParent").val();
		location.href="/bnna/member/board/blackboard/del.action?seq="+ seqBlackBoard +"&seqParent=" + seqParent;		
	});
	
	//view > 댓글 추가
	$("#addcmt").click(function() {
		
		//첫 댓글 추가시 "댓글이 없습니다." 삭제
		if ($("#tblComment tbody tr td").attr("colspan") == 3) {
			$("#tblComment tbody").html("");
		}
		
		//개행문자 처리하기
		$("#commentContent").val($("#commentContent").val().replace(/(?:\r\n|\r|\n)/g, '<br />'));

		//ajax
		$.ajax({
			type: "GET",
			url: "/bnna/member/board/blackboard/addcmt.action",
			dataType: "json",
			data: "seq=" + $("#seq").val() + "&content=" + $("#commentContent").val(),
			success: function(result) {
				$(result).each(function(index, item) {
					$("#tblComment tbody").append("<tr><td>"+ item.name +" ("+ item.id +")<input type='hidden' id='seqBlackBoardCmt' value='"+ item.seq +"'><input type='hidden' id='seqMember' value='"+ item.seqMember +"'></td><td><span class='cmtContent'>"+ item.content +"</span> <button type='button' class='btn btn-danger btn-sm btnCmtDel'>삭제</button></td><td>"+ item.regdate +"</td></tr>");					
				});
				$("#commentContent").val("");
				$("#commentContent").focus();
			},
			error: function(a, b, c) {
				console.log(a, b, c);
			}
		});

	});
	
	//댓글 수정
	
	//댓글 삭제 클릭
	
	/*
	글 삭제 모달 위에서 선언
	var seqBlackBoard;
	*/
	var seqBlackBoardCmt;
	var nowPage;
	var reply;
	
	//ajax로 생성한 버튼도 모달이 열리도록
	//변경 전 : $(".btnCmtDel").click(function() {
	//변경 후 : $(document).on('click', '.btnCmtDel', function() {
	$(document).on('click', '.btnCmtDel', function() {
		//게시글 번호 가져오기
		seqBlackBoard = $("#seqBlackBoard").val();
		
		//댓글 번호 가져오기
		seqBlackBoardCmt = $(this).parent().prev().children("input[type=hidden]:first-child").val();
		
		//페이지 번호 가져오기
		nowPage = $("#nowPage").val();
		
		//답글여부 가져오기
		reply = $("#reply").val();
		
		//open cmtDelModal
		$("#cmtDelModal").modal('show');
	});
	
	
	//cmtDelModal > 삭제 버튼 클릭
	$("#btnCmtDel").click(function() {
		location.href="/bnna/member/board/blackboard/delComment.action?page="+ nowPage +"&seqBlackBoard="+ seqBlackBoard +"&seqBlackBoardCmt=" + seqBlackBoardCmt + "&reply=" + reply;
	});
	
	//list > 검색
	var searchCondition;
	var searchKeyword;
	
	$("#searchKeyword").keydown(function() {
		if (event.keyCode == 13) {
			$("#btnSearchList").click();
		}
	});
	
	//list > 검색 버튼 클릭
	$("#btnSearchList").click(function() {
		
		searchKeyword = $("#searchKeyword").val();
		
		if ($("#searchCondition").val() == "제목") {
			//제목
			searchCondition = "title";
		} else if ($("#searchCondition").val() == "이름") {
			//이름
			searchCondition = "name";
		} else {
			//아이디
			searchCondition = "id";
		}
		
		location.href="/bnna/member/board/blackboard/list.action?condition=" + searchCondition + "&keyword=" + searchKeyword;
	});	
	
	
});
