<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <link rel="stylesheet" href="/bnna/resources/css/tripplan/tpresult.css">
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=55ab69bc871b766267ae1612c1e51daf&libraries=services"></script>
   
        <section class="mainsection">

            
            <div id="tpresult">

                <div class="bodyhead">
                    <div class="userinfo">
                        <div class="user">홍길동 회원님의 여행일정</div>
                        <div class="title">나홀로 떠나는 여행</div>
                    </div>
                    <div class="centerinfo">
                        <span class="city">제주</span> <span class="day">4</span>일 여행
                    </div>
                    <div class="dsc">
                        <button class="btn-general" data-toggle="modal" data-target="#detailCommonInfo" >일정소개보기</button>
                    </div>
                    <div style="clear: both;"></div>
                </div>
                
                <div id="daysection">

                    <button class="btn all">전체일정</button>

                    <button class="btn day">1일차</button>
                    <button class="btn day">2일차</button>
                    <button class="btn day">3일차</button>
                    <button class="btn day">4일차</button>

                </div>

                <div id="bodysection">

                    <div class="planarea">

                        <!-- 1일차 일정 전체박스 -->
                        <div class="day_plan_box">
                            
                            <div class="planinfo">
                                <div class="date">
                                    <span class="day">1일차</span>
                                    <span class="date">3월 19일 금요일</span>
                                </div>
                                
                                <div class="summary">
                                    <span class="nature">자연1</span>
                                    <span class="tour">관광3</span>
                                    <span class="shop">쇼핑2</span>
                                    <span class="leport">레포1</span>
                                    <span class="food">음식1</span>
                                </div>
                            </div>
                            
                            <!-- 1일차의 일정들을 담은 리스트 -->
                            <div class="planlist">
                                
                                <div class="plan">
                                    <div class="rank">1</div>

                                    <div class="crop imgbox">
                                        <img src="/image/tripplan/7.jpg" alt="">
                                    </div>

                                    <div class="txtbox">
                                        <div class="category">카테고리</div>
                                        <div class="name">장소 이름</div>
                                        <div class="dsc">장소 간단 소개</div>
                                    </div>
                                </div>
                                
                                <div class="plan">
                                    <div class="rank">21</div>

                                    <div class="crop imgbox">
                                        <img src="/tripplan/" alt="">
                                    </div>

                                    <div class="txtbox">
                                        <div class="category">카테고리</div>
                                        <div class="name">장소 이름</div>
                                        <div class="dsc">장소 간단 소개</div>
                                    </div>
                                </div>
                                
                                <div class="plan">
                                    <div class="rank">1</div>

                                    <div class="crop imgbox">
                                        <img src="/tripplan/" alt="">
                                    </div>

                                    <div class="txtbox">
                                        <div class="category">카테고리</div>
                                        <div class="name">장소 이름</div>
                                        <div class="dsc">장소 간단 소개</div>
                                    </div>
                                </div>
                                
                                <div class="plan">
                                    <div class="rank">1</div>

                                    <div class="crop imgbox">
                                        <img src="/tripplan/" alt="">
                                    </div>

                                    <div class="txtbox">
                                        <div class="category">카테고리</div>
                                        <div class="name">장소 이름</div>
                                        <div class="dsc">장소 간단 소개</div>
                                    </div>
                                </div>

                            </div>
                        </div>




                        <!-- 2일차 일정 전체박스 -->
                        <div class="day_plan_box">
                                
                            <div class="planinfo">
                                <div class="date">
                                    <span class="day">2일차</span>
                                    <span class="date">3월 20일 금요일</span>
                                </div>
                                
                                <div class="summary">
                                    <span class="nature">자연1</span>
                                    <span class="tour">관광3</span>
                                    <span class="shop">쇼핑2</span>
                                    <span class="leport">레포1</span>
                                    <span class="food">음식1</span>
                                </div>
                            </div>
                            
                            <!-- 1일차의 일정들을 담은 리스트 -->
                            <div class="planlist">
                                
                                <div class="plan">
                                    <div class="rank">1</div>

                                    <div class="crop imgbox">
                                        <img src="/image/tripplan/5.jpg" alt="">
                                    </div>

                                    <div class="txtbox">
                                        <div class="category">카테고리</div>
                                        <div class="name">장소 이름</div>
                                        <div class="dsc">장소 간단 소개</div>
                                    </div>
                                </div>
                                
                                <div class="plan">
                                    <div class="rank">1</div>

                                    <div class="crop imgbox">
                                        <img src="/tripplan/" alt="">
                                    </div>

                                    <div class="txtbox">
                                        <div class="category">카테고리</div>
                                        <div class="name">장소 이름</div>
                                        <div class="dsc">장소 간단 소개</div>
                                    </div>
                                </div>
                                
                                <div class="plan">
                                    <div class="rank">1</div>

                                    <div class="crop imgbox">
                                        <img src="/tripplan/" alt="">
                                    </div>

                                    <div class="txtbox">
                                        <div class="category">카테고리</div>
                                        <div class="name">장소 이름</div>
                                        <div class="dsc">장소 간단 소개</div>
                                    </div>
                                </div>
                                
                                <div class="plan">
                                    <div class="rank">1</div>

                                    <div class="crop imgbox">
                                        <img src="/tripplan/" alt="">
                                    </div>

                                    <div class="txtbox">
                                        <div class="category">카테고리</div>
                                        <div class="name">장소 이름</div>
                                        <div class="dsc">장소 간단 소개</div>
                                    </div>
                                </div>

                            </div>
                        </div>



                        <!-- 2일차 일정 전체박스 -->
                        <div class="day_plan_box">
                                
                            <div class="planinfo">
                                <div class="date">
                                    <span class="day">2일차</span>
                                    <span class="date">3월 20일 금요일</span>
                                </div>
                                
                                <div class="summary">
                                    <span class="nature">자연1</span>
                                    <span class="tour">관광3</span>
                                    <span class="shop">쇼핑2</span>
                                    <span class="leport">레포1</span>
                                    <span class="food">음식1</span>
                                </div>
                            </div>
                            
                            <!-- 1일차의 일정들을 담은 리스트 -->
                            <div class="planlist">
                                
                                <div class="plan">
                                    <div class="rank">1</div>

                                    <div class="crop imgbox">
                                        <img src="/image/tripplan/5.jpg" alt="">
                                    </div>

                                    <div class="txtbox">
                                        <div class="category">카테고리</div>
                                        <div class="name">장소 이름</div>
                                        <div class="dsc">장소 간단 소개</div>
                                    </div>
                                </div>
                                
                                <div class="plan">
                                    <div class="rank">1</div>

                                    <div class="crop imgbox">
                                        <img src="/tripplan/" alt="">
                                    </div>

                                    <div class="txtbox">
                                        <div class="category">카테고리</div>
                                        <div class="name">장소 이름</div>
                                        <div class="dsc">장소 간단 소개</div>
                                    </div>
                                </div>
                                
                                <div class="plan">
                                    <div class="rank">1</div>

                                    <div class="crop imgbox">
                                        <img src="/tripplan/" alt="">
                                    </div>

                                    <div class="txtbox">
                                        <div class="category">카테고리</div>
                                        <div class="name">장소 이름</div>
                                        <div class="dsc">장소 간단 소개</div>
                                    </div>
                                </div>
                                
                                <div class="plan">
                                    <div class="rank">1</div>

                                    <div class="crop imgbox">
                                        <img src="/tripplan/" alt="">
                                    </div>

                                    <div class="txtbox">
                                        <div class="category">카테고리</div>
                                        <div class="name">장소 이름</div>
                                        <div class="dsc">장소 간단 소개</div>
                                    </div>
                                </div>

                            </div>
                        </div>



                    </div>


                  
                </div>

                <div id="mapsection">
                    <div id="map"></div>
                </div>

                <div style="clear: both;"></div>



                <!-- 일정 소개 보기 Modal -->
                <div class="modal fade" id="detailCommonInfo" tabindex="-1"
                role="dialog" aria-labelledby="myModalLabel">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal"
                                aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                            <h4 class="modal-title" id="commonInfoTitle">일정 소개</h4>
                        </div>
                        <div class="modal-body" id="commonInfoBody">
                            일정 소개가 이 안에 들어간다.
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
                        </div>
                    </div>
                </div>




            </div>

            
            <script src="/bnna/resources/js/tripplan/tpresult.js"></script>

        </section>