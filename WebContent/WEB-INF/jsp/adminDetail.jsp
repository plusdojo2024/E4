<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="UTF-8">
  <meta name="vsiewport" content="width=device-width, initial-scale=1.0">
  <title>主催イベント詳細</title>
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
      <h3 class="title">主催イベント詳細</h3>
      <!-- メッセージエリア -->
      <p id="message"></p>
      <!-- spanタグの中身をJSTLで書き換え -->
      <p class="event-detail">イベント名：<span id="eventName">${requestScope.detailEvent.eventName}</span></p>
      <p class="event-detail">イベント詳細</p>
      <p class="event-description">${requestScope.detailEvent.eventDescription}</p>
      <p class="event-detail">開催日程：<span>${requestScope.detailEvent.holdingSchedule}</span></p>
      <div>
        <p class="event-detail">参加人数：<span>${requestScope.detailEvent.leastCount}</span> ～ <span>${requestScope.detailEvent.maxCount}</span> 人</p>
        <p class="event-detail">現在の参加人数：<span>${requestScope.usersCount}</span> 人</p>
      </div>
      <div class="icon-box">
        <div class="admin-list">
        <!-- JSTLを使ってアイコンとモーダルに表示する内容(参加ユーザー情報)を参加人数分生成予定 -->
		<c:forEach var="participant" items="${requestScope.participantsUsers}">
			<img class="icon-img" data-trigger="btn" data-modal="${participant.id}" src="${requestScope.iconUrl.get(Integer.valueOf(participant.iconId))}" alt="アイコン">
		</c:forEach>
        </div>
      </div>
      <div id="communication-div"  class="communicationArea-inner">
		<c:forEach var="chat" items="${requestScope.chatList}">
			<p><span class="chatname">${chat.user_name}</span>：<span class="content">${chat.content}</span></p>
		</c:forEach>
      </div>
      <input type="text" name="content"  id="content">
      <input type="hidden"  name="user_id"  id="user_id"  value="1">
      <input type="hidden"  name="event_id"  id="event_id"  value="${requestScope.detailEvent.id}">
      <button id="send"  type="submit" >送信</button>
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
      <button type="button"  onclick="history.back()">一覧へ戻る</button>
     <!--  <a href="AdminEventList">一覧へ戻る</a> -->
    </div>
    <!-- モーダル部分 -->
	<div class="modal" data-modal="box">
      <div class="modal__bg" data-modal="bg"></div>
      <div class="modal__inner" data-modal="inner">
        <c:forEach var="userinfo"  items="${requestScope.participantsUsers}">
          <div class="modal-card" data-trigger="item" data-modal="${userinfo.id}">
            <div class="modal-card__close" data-modal="close">
              <div class="modal-card__closeBtn"></div>
            </div>
            <p>ニックネーム：${userinfo.name}</p>
            <p>実績</p>
            <div class="achieve-list">
            <!-- 各項目のパラメータに合わせて画像を表示 -->
            <!-- いいね -->
	            <c:choose>
		            <c:when test="${userinfo.evaluation >= 5000}">
		              <img class="modal-icon"  src="${requestScope.iconUrl.get(Integer.valueOf(15))}" alt="アイコン">
		            </c:when>
		            <c:when test="${userinfo.evaluation >= 100}">
		              <img class="modal-icon"  src="${requestScope.iconUrl.get(Integer.valueOf(14))}" alt="アイコン">
		            </c:when>
		                        <c:when test="${userinfo.evaluation >= 60}">
		              <img class="modal-icon"  src="${requestScope.iconUrl.get(Integer.valueOf(13))}" alt="アイコン">
		            </c:when>
		            <c:when test="${userinfo.evaluation >= 30}">
		              <img class="modal-icon"  src="${requestScope.iconUrl.get(Integer.valueOf(12))}" alt="アイコン">
		            </c:when>
		             <c:otherwise>
		              <img class="modal-icon"  src="${requestScope.iconUrl.get(Integer.valueOf(11))}" alt="アイコン">
		            </c:otherwise>
	          	</c:choose>
	          	<!-- 料理 -->
	          	<c:choose>
		            <c:when test="${userinfo.cookParam >= 3000}">
		              <img class="modal-icon"  src="${requestScope.iconUrl.get(Integer.valueOf(10))}" alt="アイコン">
		            </c:when>
		            <c:when test="${userinfo.cookParam >= 500}">
		              <img class="modal-icon"  src="${requestScope.iconUrl.get(Integer.valueOf(9))}" alt="アイコン">
		            </c:when>
		            <c:when test="${userinfo.cookParam >= 200}">
		              <img class="modal-icon"  src="${requestScope.iconUrl.get(Integer.valueOf(8))}" alt="アイコン">
		            </c:when>
		            <c:when test="${userinfo.cookParam >= 50}">
		              <img class="modal-icon"  src="${requestScope.iconUrl.get(Integer.valueOf(7))}" alt="アイコン">
		            </c:when>
		             <c:otherwise>
		              <img class="modal-icon"  src="${requestScope.iconUrl.get(Integer.valueOf(6))}" alt="アイコン">
		            </c:otherwise>
	          	</c:choose>
	          	<!-- 技量 -->
	            <c:choose>
		            <c:when test="${userinfo.technicParam >= 3000}">
		              <img class="modal-icon"  src="${requestScope.iconUrl.get(Integer.valueOf(20))}" alt="アイコン">
		            </c:when>
		            <c:when test="${userinfo.technicParam >= 500}">
		              <img class="modal-icon"  src="${requestScope.iconUrl.get(Integer.valueOf(19))}" alt="アイコン">
		            </c:when>
		            <c:when test="${userinfo.technicParam >= 200}">
		              <img class="modal-icon"  src="${requestScope.iconUrl.get(Integer.valueOf(18))}" alt="アイコン">
		            </c:when>
		            <c:when test="${userinfo.technicParam >= 50}">
		              <img class="modal-icon"  src="${requestScope.iconUrl.get(Integer.valueOf(17))}" alt="アイコン">
		            </c:when>
		             <c:otherwise>
		              <img class="modal-icon"  src="${requestScope.iconUrl.get(Integer.valueOf(16))}" alt="アイコン">
		            </c:otherwise>
	          	</c:choose>
	          	<!-- コミュニケーション -->
	          	<c:choose>
		            <c:when test="${userinfo.communicationParam >= 3000}">
		              <img class="modal-icon"  src="${requestScope.iconUrl.get(Integer.valueOf(5))}" alt="アイコン">
		            </c:when>
		            <c:when test="${userinfo.communicationParam >= 500}">
		              <img class="modal-icon"  src="${requestScope.iconUrl.get(Integer.valueOf(4))}" alt="アイコン">
		            </c:when>
		            <c:when test="${userinfo.communicationParam >= 200}">
		              <img class="modal-icon"  src="${requestScope.iconUrl.get(Integer.valueOf(3))}" alt="アイコン">
		            </c:when>
		            <c:when test="${userinfo.communicationParam >= 50}">
		              <img class="modal-icon"  src="${requestScope.iconUrl.get(Integer.valueOf(2))}" alt="アイコン">
		            </c:when>
		             <c:otherwise>
		              <img class="modal-icon"  src="${requestScope.iconUrl.get(Integer.valueOf(1))}" alt="アイコン">
		            </c:otherwise>
	          	</c:choose>
	          	<!-- 参加回数 -->
	          	<c:choose>
		            <c:when test="${userinfo.hostedAmount >= 1000}">
		              <img class="modal-icon"  src="${requestScope.iconUrl.get(Integer.valueOf(25))}" alt="アイコン">
		            </c:when>
		            <c:when test="${userinfo.hostedAmount >= 15}">
		              <img class="modal-icon"  src="${requestScope.iconUrl.get(Integer.valueOf(24))}" alt="アイコン">
		            </c:when>
		            <c:when test="${userinfo.hostedAmount >= 7}">
		              <img class="modal-icon"  src="${requestScope.iconUrl.get(Integer.valueOf(23))}" alt="アイコン">
		            </c:when>
		            <c:when test="${userinfo.hostedAmount >= 1}">
		              <img class="modal-icon"  src="${requestScope.iconUrl.get(Integer.valueOf(22))}" alt="アイコン">
		            </c:when>
		             <c:otherwise>
		              <img class="modal-icon"  src="${requestScope.iconUrl.get(Integer.valueOf(21))}" alt="アイコン">
		            </c:otherwise>
	          	</c:choose>
	          	<!-- 主催回数 -->
	          	<c:choose>
		            <c:when test="${userinfo.hostedAmount >= 3000}">
		              <img class="modal-icon"  src="${requestScope.iconUrl.get(Integer.valueOf(30))}" alt="アイコン">
		            </c:when>
		            <c:when test="${userinfo.hostedAmount >= 500}">
		              <img class="modal-icon"  src="${requestScope.iconUrl.get(Integer.valueOf(29))}" alt="アイコン">
		            </c:when>
		            <c:when test="${userinfo.hostedAmount >= 200}">
		              <img class="modal-icon"  src="${requestScope.iconUrl.get(Integer.valueOf(28))}" alt="アイコン">
		            </c:when>
		            <c:when test="${userinfo.hostedAmount >= 50}">
		              <img class="modal-icon"  src="${requestScope.iconUrl.get(Integer.valueOf(27))}" alt="アイコン">
		            </c:when>
		             <c:otherwise>
		              <img class="modal-icon"  src="${requestScope.iconUrl.get(Integer.valueOf(26))}" alt="アイコン">
		            </c:otherwise>
	          	</c:choose>
          	</div>
          	<button type="button" id="kick">キックする</button>
          </div>
        </c:forEach>
      </div>
    </div>
    <!-- モーダル部分終わり -->
  </main>
  <footer></footer>
  <script src="/E4/js/event_detail.js"></script>
<script>
	'use strict';

	const communicationDiv = document.getElementById('communication-div');
	const sendBtn = document.getElementById('send');

	const getData = () => {
	  const request = new XMLHttpRequest();
	  request.open('POST', '/E4/AdminDetail', true);

	  request.setRequestHeader('Content-Type', 'application/json');

	  request.onload = function () {
	    if (request.status >= 200 && request.status < 400) {
	      const jsonObj = JSON.parse(request.responseText);
	      console.log(jsonObj);
	      let result = jsonObj[0].result;
	      let len = Object.keys(jsonObj).length;
	      console.log("length:" + len);
	      console.log(result);

	  	  let chat = ""; // chatをループの外で宣言

     	  let i = 1; // インデックスを初期化
     	  while (i < len) { // 修正: i < len
       		 if (jsonObj[i]) { // jsonObj[i]が存在するかを確認
         		 //chat += jsonObj[i].userName + jsonObj[i].userIcon + jsonObj[i].content;
           		chat += '<p><span class="chatname">' + jsonObj[i]["userName"] + '</span>：<span class="content">' + jsonObj[i]["content"] + '</span></p>';
       			 }
       		 i++; // インデックスをインクリメント
      		}

      		communicationDiv.innerHTML = chat;

	    } else {
	      console.error("データ取得失敗");
	    }
	  }

	  request.onerror = function() {
	      console.error('通信エラー');
	  }

	  let userId = document.getElementById("user_id").value;
	  let eventId = document.getElementById("event_id").value;
	  let content = document.getElementById("content").value;
	  // 送信したい内容をJSONに変換
	  let body = JSON.stringify({
	      user_id: userId,
	      event_id: eventId,
	      content: content
	  });
	  console.log(body);
	  request.send(body);
	}
	sendBtn.addEventListener('click', getData);
</script>
</body>
</html>