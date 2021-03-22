<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

  
   <header>
   
       <div class="head-title">
           <img class="logo" src="/bnna/resources/image/logo.png" alt="">
           <span class="kr">여행 플래너</span>
           <span class="eng">Make Your Trip Plan</span>
       </div>

       <div class="head-menu dropdown">

           <button class="btn-menu dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
           MENU <span class="glyphicon glyphicon-chevron-down"></span>
           </button>
           
           <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
               <ul>
                   <li><a class="dropdown-item" href="/bnna/member/tripplan/start.action">홈으로</a></li>
                   <li><a class="dropdown-item" href="/bnna/member/tripplan/start.action#tpstart">새 일정 만들기</a></li>
                   <li><a class="dropdown-item" href="/bnna/member/tripplan/board.action?order=p">일정 구경 하기</a></li>
                   <li><a class="dropdown-item" href="/bnna/member/tripplan/result.action">일정 확인 하기</a></li>
               </ul>
           </div>
       </div>
       <div style="clear: both;"></div>


   </header>