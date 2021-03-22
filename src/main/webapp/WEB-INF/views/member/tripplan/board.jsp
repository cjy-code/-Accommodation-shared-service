<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet" href="/bnna/resources/css/tripplan/tpboard.css">

        <section class="mainsection">
            
            <div id="tpboard">
                <div class="title">다른 여행자들의 일정보기</div>
                <div class="intro">다른 여행자들과 여행 일정을 공유해보세요.</div>

                <table class="table tbl-filter">
                    <tr class="filter">
                        <th>필터</th>
                        <td id="selectedTag">
                        </td>
                    </tr>
                    <tr class="city">
                        <th>여행지</th>
                        <td class="citytd">
                            <span class="tag citytag">서울</span>
                            <span class="tag citytag">인천</span>
                            <span class="tag citytag">대전</span>
                            <span class="tag citytag">대구</span>
                            <span class="tag citytag">광주</span>
                            <span class="tag citytag">부산</span>
                            <span class="tag citytag">울산</span>
                            <span class="tag citytag">경기</span>
                            <span class="tag citytag">강원</span>
                            <span class="tag citytag">충청</span>
                            <span class="tag citytag">전라</span>
                            <span class="tag citytag">경상</span>
                            <span class="tag citytag">제주</span>
                        </td>
                    </tr>
                    <tr class="season">
                        <th>여행시기</th>
                        <td >
                            <span class="tag seasontag">봄</span>
                            <span class="tag seasontag">여름</span>
                            <span class="tag seasontag">가을</span>
                            <span class="tag seasontag">겨울</span>
                        </td>
                    </tr>
                    <tr class="period">
                        <th>여행기간</th>
                        <td>
                            <span class="tag periodtag">당일치기</span>
                            <span class="tag periodtag">2-4일</span>
                            <span class="tag periodtag">5-7일</span>
                            <span class="tag periodtag">8일 이상</span>
                        </td>
                    </tr>
                    <tr style="height: 0px;"><td colspan="2"></td></tr>
                </table>

                <hr>

                <div class="btns">
                    <button class="btn-general popular menu-active" onclick="location.href='/bnna/member/tripplan/board.action?order=p';">인기</button>
                    <button class="btn-general new" onclick="location.href='/bnna/member/tripplan/board.action?order=n';">신규</button>
                </div>

				<form method="GET" action="/bnna/admin/board/tripplan/board.action">
                <div class="searcharea">
                    <input type="text" id="search" name="search" required value="${search}" class="form-control">
                    <button class="btn btn-default" type="submit"><span>검색</span></button>
                </div>
                </form>


                <div id="articles">


					<c:forEach items="${list}" var="dto">
                    <div class="article">
                        <div class="crop">
                            <img src="/bnna/resources/image/tripplan/board/${dto.img}" alt="">
                        </div>
                        <div class="onimg">
                            <span class="startdate">${dto.startDate.substring(0,10)}</span>
                            <span class="days">${dto.totalDate}DAYS</span>
                        </div>
                        <div class="txtbox">
                            <div class="title">${dto.title}</div>
                            <div class="city">제주</div>
                            <div class="id">${dto.id}</div>
                            <div class="cnts">
                                <span class="readcnt">👀 ${dto.readcnt}</span>
                                <span class="likecnt">💗 ${dto.likecnt}</span>
                            </div>
                        </div>
                    </div>
                    </c:forEach>

                 


                </div>
                



                <div class=pagearea>
                    <div class="pagination">
						${pagebar}
                    </div>
                </div>

            </div>

		<script src="/bnna/resources/js/tripplan/tpboard.js"></script>

        </section>
        
		
		