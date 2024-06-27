<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>通知イベント詳細</title>
  <link rel="stylesheet" href="/E4/css/common.css">
  <link rel="stylesheet" href="/E4/css/header.css">
  <link rel="stylesheet" href="/E4/css/event_detail.css">
</head>
<body>
	<header>
		<div class="container">
	      <div class="logoarea">
	          <img src="img/headerlogo.png" width="150px" alt="ロゴ">
	      </div>
	      <nav class="header-nav">
	          <ul class="list-nav">
	              <li><a href="/E4/Top">トップに戻る</a></li>
	              <li><a href="/E4/JoinEventList">参加イベント</a></li>
	              <li><a href="/E4/CreateEvent">イベント作成</a></li>
	              <li><a href="/E4/Profile">プロフィール</a></li>
	              <li><a href="/E4/Logout">ログアウト</a></li>
	          </ul>
	      </nav>
	   	</div>
    </header>
  <main>
    <div class="main-inner">
      <h3 class="title">イベント詳細</h3>
      <!-- メッセージエリア -->
      <p id="message"><c:if test="${not empty error}">${error}</c:if></p>
      <!-- spanタグの中身をJSTLで書き換え -->
       <p class="event-detail">イベント名：<span id="eventName">${requestScope.detailEvent.eventName}</span></p>
      <p class="event-detail">イベント詳細</p>
      <p class="event-description">${requestScope.detailEvent.eventDescription}</p>
      <p class="event-detail">開催日程：<span>${requestScope.detailEvent.holdingSchedule}</span></p>
      <div>
        <p class="event-detail">参加人数：<span>${requestScope.detailEvent.leastCount}</span> ～ <span>${requestScope.detailEvent.maxCount}</span> 人</p>
        <p class="event-detail">現在の参加人数：<span>${requestScope.usersCount}</span> 人</p>
      </div>
      <div>
        <p class="event-detail">住所：<span>${requestScope.address}</span></p>
        <p class="event-detail">場所名称：<span>${requestScope.detailEvent.locationName}</span></p>
      </div>
      <div><iframe  class="map" src="https://maps.google.co.jp/maps?output=embed&q=${requestScope.address}}"></iframe></div>
      <p class="event-detail">募集レベル：
          <c:choose>
            <c:when test="${requestScope.detailEvent.eventCategory == 1}">
              <span id="eventCategory">初心者歓迎</span>
            </c:when>
            <c:when test="${requestScope.detailEvent.eventCategory == 2}">
              <span id="eventCategory">誰でも歓迎</span>
            </c:when>
            <c:when test="${requestScope.detailEvent.eventCategory == 3}">
              <span id="eventCategory">ベテラン向け</span>
            </c:when>
          </c:choose>
      </p>
      <form action="/E4/BeforeJoinDetail" method="post">
        <input type="hidden"  name="event_id" value="${requestScope.event_id}">
        <input type="hidden"  name="flag" value="flag">
        <div class="center"><button type="submit"  class="redBtn">参加する</button></div>
      </form>
      <div><button  class="greenBtn"  type="button"  onclick="history.back()"> 戻る</button></div>
    </div>
  </main>
  <footer></footer>
</body>
</html>