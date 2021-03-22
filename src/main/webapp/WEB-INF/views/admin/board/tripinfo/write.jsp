<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet" href="/bnna/resources/css/tripinfo/admin-tripinfo.css">

        <section class="mainsection">
        
            <div id="menupath">
                게시판 <span class="glyphicon glyphicon-menu-right" style="position: static; z-index: -1;"></span> 여행정보 게시판
                <span class="glyphicon glyphicon-menu-right" style="position: static; z-index: -1;"></span> 새 글 작성
            </div>
            <hr>
            <!-- 여기까지 menu path -->
        
            <article>
        
                <h1>여행정보 게시판 <small>게시물 작성</small></h1>

				<form method="POST" action="/bnna/admin/board/tripinfo/writeok.action" enctype="multipart/form-data">
                <div class="writearea">
                
                    <input type="text" class="form-control subject" name="subject" id="subject" placeholder="제목을 입력하세요." required>
                    <textarea name="content" id="content" class="form-control content" cols="30" rows="10" placeholder="내용을 입력하세요." required></textarea>
                    
                    <div id="filearea" style="width: 85%;">
                        <input multiple="multiple" type="file" name="image" class="form-control file" required/>
                    </div>

                    <div style="text-align: right; margin-top: -40px;">
                        <button class="btn btn-add">파일 추가</button>
                    </div>

                    <hr>

                    <div class="btns">
                        <button class="btn" onclick="location.href='/bnna/admin/board/tripinfo/list.action'">취소</button>
                        <button class="btn">미리보기</button>
                    </div>
                    <div class="right">
                        <button class="btn btn-general" type="submit">등록</button>
                    </div>
                    
	                <input type="hidden" id="reply" name="reply" value="${reply}">
	                
	                <c:if test="${empty thread}">
 	                	<input type="hidden" id="thread" name="thread" value="0">
 	                </c:if>
 	                <c:if test="${not empty thread}">
 	                	<input type="hidden" id="thread" name="thread" value="${thread}">
 	                </c:if>
 	                <c:if test="${empty depth}">
 	                	<input type="hidden" id="depth" name="depth" value="0">
 	                </c:if>
 	                <c:if test="${not empty depth}">
	                	<input type="hidden" id="depth" name="depth" value="${depth}"> 
	                </c:if>

                </div>
                </form>
        
            </article>
        
        </section>
	
		<script>
            // 파일업로드 추가 버튼
            $(".btn-add").on('click', function() {
				
            	//var cnt = 1;
            	
                let tmp = '';
                tmp += '<input multiple="multiple" type="file" name="image" class="form-control file"/>'

                $("#filearea").append(tmp);
                //cnt++;

            });
        </script>