<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<link rel="stylesheet"
	href="/bnna/resources/css/tripinfo/admin-tripinfo.css">

		<section class="mainsection">
		
			<div id="menupath">
				게시판 <span class="glyphicon glyphicon-menu-right"
					style="position: static; z-index: -1;"></span> 여행정보 게시판 <span
					class="glyphicon glyphicon-menu-right"
					style="position: static; z-index: -1;"></span> 상세보기
			</div>
			<hr>
			<!-- 여기까지 menu path -->
		
			<article>
		
				<h2>여행정보 게시판</h2>
		
				<hr>
		
				<div class="viewarea">
		
					<div class="subject">${dto.subject}</div>
					<div class="btnarea">
						<button class="btn btn-list"
							onclick="location.href='/bnna/admin/board/tripinfo/list.action';">목록으로</button>
						<button class="btn" id="btnDel">삭제</button>
						<button class="btn" id="btnEdit" onclick="location.href='/bnna/admin/board/tripinfo/edit.action?seq=${dto.seq}';">수정</button>
						<button class="btn link">스크랩</button>
					</div>
		
					<div class="imgarea">
						<div class="background">
							<span class="glyphicon glyphicon-chevron-left"></span> 
							
								<c:forEach items="${ilist}" var="idto">
									<img src="/bnna/resources/image/board/tripinfo/${idto.image}" >
									
								</c:forEach>
								<!-- <img src="/bnna/resources/image/tripinfo/1_1.png" alt=""> -->
							<span class="glyphicon glyphicon-chevron-right"></span>
						</div>
					</div>
		
					<div class="content">
						<p style="white-space: pre-line;">${dto.content}</p>
					</div>
		
		
					<div class="related">
						관련 글 보기
						<button class="btn btn-related" onclick="location.href='/bnna/admin/board/tripinfo/write.action?reply=y&thread=${dto.thread}&depth=${dto.depth}';">답글쓰기</button>
		
						<table class="table tbl-related">
							<c:if test="${dto.depth > 0 || empty rlist}">
								<tr>
									<td colspan="2">이 글은 관련글이 없습니다.</td>
								</tr>
							</c:if>
							
							<c:if test="${dto.depth == 0}">
							<c:forEach items="${rlist}" var="rdto">
								<tr>
									<td><a href="/bnna/admin/board/tripinfo/view.action?#">${rdto.subject}</a></td>
									<td style="text-align: right;">${rdto.regdate.substring(0, 10)}</td>
								</tr>
							</c:forEach>
							</c:if>
						</table>
						
		
					</div>
		
					<div class="cmtarea">
						댓글
						<table class="table tbl-cmt">
							<c:if test="${empty cmtlist}">
								<tr>
									<td colspan="4">아직 댓글이 없습니다.</td>
								</tr>
							</c:if>
		
							<c:forEach items="${cmtlist}" var="cdto">
								<tr>
									<td rowspan="2" class="pic">
										<div class="crop">
											<img src="/bnna/resources/image/mempic/${cdto.pic}" alt="">
										</div>
									</td>
									<td class="id small">${cdto.id}</td>
									<td></td>
									<td class="regdate small">${cdto.regdate}</td>
								</tr>
								<tr>
									<td colspan="2" class="ccontent noborder">${cdto.ccontent}</td>
									<td class="btn-del noborder"><span onclick="location.href='/bnna/member/board/tripinfo/delcmtok.action?seq=${cdto.seq}&bseq=${dto.seq}';">[삭제]</span></td>
								</tr>
							</c:forEach>
		
						</table>
						
						<form method="POST" action="/bnna/member/board/tripinfo/cmtok.action">
						<div class="cmt-write">
							<textarea class="form-control" id="ccontent" name="ccontent"
								required placeholder="댓글을 남겨보세요." cols="30" rows="5"></textarea>
							<div id="comment_cnt">(0 / 100)</div>
						</div>
						
						<button type="submit" class="btn btn-cmt">등록</button>
						<input type="hidden" name="bseq" value="${dto.seq}">
						</form>
						<div style="clear: both;"></div>
		
					</div>
		
				</div>
		
			</article>
		
		</section>

        <script>

            $("#btnDel").click(function(){
                if(confirm("정말 삭제하시겠습니까 ?") == true){
                    location.href = "/bnna/admin/board/tripinfo/delok.action?seq=${dto.seq}";
                }
                else{
                    return ;
                }
            });

        </script>