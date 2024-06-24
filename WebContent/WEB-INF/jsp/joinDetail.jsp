<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>イベント詳細(参加者)</title>
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
              <li>
                  <a href="">検索</a>
              </li>
              <li>
                  <a href="">参加イベント</a>
              </li>
              <li>
                  <a href="">イベント作成</a>
              </li>
              <li>
                  <a href="">プロフィール</a>
              </li>
              <li>
                  <a href="">ログアウト</a>
              </li>
          </ul>
      </nav>
   </div>
  </header>
  <main>
    <div class="main-inner">
      <h3 class="title">イベント詳細</h3>
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
			<img class="icon-img" data-trigger="btn" data-modal="${participant.id}" src="/E4/img/${requestScope.iconUrl.get(Integer.valueOf(participant.iconId))}" alt="アイコン">
		</c:forEach>
        </div>
      </div>
      <div id="communication-div"  class="communicationArea-inner">
      	<c:forEach var="chat"  items="${requestScope.comChatMap.keySet}">
      		<c:forEach var="i" begin="1" end="${requestScope.comChatMap.size}" step="1">
      			<c:set var="tmp"  value="${Integer.valueOf(i)}" />
      			<p>${chat.tmp.chatUserName }：${chat.tmp.content }</p>
      		</c:forEach>
      	</c:forEach>
      </div>
      <form action="JoinDetailServlet" method="post">
      <input type="text" name="content"  id="content">
      <input type="hidden"  name="user_id"  id="user_id"  value="1">
      <input type="hidden"  name="event_id"  id="event_id"  value="${requestScope.detailEvent.id}">
      <button id="send"  type="submit" >送信</button>
      </form>
     <div>
        <p class="event-detail">住所：<span>${requestScope.address}</span></p>
        <p class="event-detail">場所名称：<span>${requestScope.detailEvent.locationName}</span></p>
      </div>
      <div><p class="event-detail">以下地図エリアです</p><iframe src="" ></iframe></div>
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
      <button>戻る</button>
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
		              <img class="icon-img"  src="/E4/img/${requestScope.iconUrl.get(Integer.valueOf(15))}" alt="アイコン">
		            </c:when>
		            <c:when test="${userinfo.evaluation >= 100}">
		              <img class="icon-img"  src="/E4/img/${requestScope.iconUrl.get(Integer.valueOf(14))}" alt="アイコン">
		            </c:when>
		                        <c:when test="${userinfo.evaluation >= 60}">
		              <img class="icon-img"  src="/E4/img/${requestScope.iconUrl.get(Integer.valueOf(13))}" alt="アイコン">
		            </c:when>
		            <c:when test="${userinfo.evaluation >= 30}">
		              <img class="icon-img"  src="/E4/img/${requestScope.iconUrl.get(Integer.valueOf(12))}" alt="アイコン">
		            </c:when>
		             <c:otherwise>
		              <img class="icon-img"  src="/E4/img/${requestScope.iconUrl.get(Integer.valueOf(11))}" alt="アイコン">
		            </c:otherwise>
	          	</c:choose>
	          	<!-- 技量 -->
	            <c:choose>
		            <c:when test="${userinfo.technicParam >= 3000}">
		              <img class="icon-img"  src="/E4/img/${requestScope.iconUrl.get(Integer.valueOf(20))}" alt="アイコン">
		            </c:when>
		            <c:when test="${userinfo.technicParam >= 500}">
		              <img class="icon-img"  src="/E4/img/${requestScope.iconUrl.get(Integer.valueOf(19))}" alt="アイコン">
		            </c:when>
		            <c:when test="${userinfo.technicParam >= 200}">
		              <img class="icon-img"  src="/E4/img/${requestScope.iconUrl.get(Integer.valueOf(18))}" alt="アイコン">
		            </c:when>
		            <c:when test="${userinfo.technicParam >= 50}">
		              <img class="icon-img"  src="/E4/img/${requestScope.iconUrl.get(Integer.valueOf(17))}" alt="アイコン">
		            </c:when>
		             <c:otherwise>
		              <img class="icon-img"  src="/E4/img/${requestScope.iconUrl.get(Integer.valueOf(16))}" alt="アイコン">
		            </c:otherwise>
	          	</c:choose>
	          	<!-- 料理 -->
	          	<c:choose>
		            <c:when test="${userinfo.cookParam >= 3000}">
		              <img class="icon-img"  src="/E4/img/${requestScope.iconUrl.get(Integer.valueOf(10))}" alt="アイコン">
		            </c:when>
		            <c:when test="${userinfo.cookParam >= 500}">
		              <img class="icon-img"  src="/E4/img/${requestScope.iconUrl.get(Integer.valueOf(9))}" alt="アイコン">
		            </c:when>
		            <c:when test="${userinfo.cookParam >= 200}">
		              <img class="icon-img"  src="/E4/img/${requestScope.iconUrl.get(Integer.valueOf(8))}" alt="アイコン">
		            </c:when>
		            <c:when test="${userinfo.cookParam >= 50}">
		              <img class="icon-img"  src="/E4/img/${requestScope.iconUrl.get(Integer.valueOf(7))}" alt="アイコン">
		            </c:when>
		             <c:otherwise>
		              <img class="icon-img"  src="/E4/img/${requestScope.iconUrl.get(Integer.valueOf(6))}" alt="アイコン">
		            </c:otherwise>
	          	</c:choose>
	          	<!-- コミュニケーション -->
	          	<c:choose>
		            <c:when test="${userinfo.communicationParam >= 3000}">
		              <img class="icon-img"  src="/E4/img/${requestScope.iconUrl.get(Integer.valueOf(5))}" alt="アイコン">
		            </c:when>
		            <c:when test="${userinfo.communicationParam >= 500}">
		              <img class="icon-img"  src="/E4/img/${requestScope.iconUrl.get(Integer.valueOf(4))}" alt="アイコン">
		            </c:when>
		            <c:when test="${userinfo.communicationParam >= 200}">
		              <img class="icon-img"  src="/E4/img/${requestScope.iconUrl.get(Integer.valueOf(3))}" alt="アイコン">
		            </c:when>
		            <c:when test="${userinfo.communicationParam >= 50}">
		              <img class="icon-img"  src="/E4/img/${requestScope.iconUrl.get(Integer.valueOf(2))}" alt="アイコン">
		            </c:when>
		             <c:otherwise>
		              <img class="icon-img"  src="/E4/img/${requestScope.iconUrl.get(Integer.valueOf(1))}" alt="アイコン">
		            </c:otherwise>
	          	</c:choose>
          	</div>
          </div>
        </c:forEach>
      </div>
    </div>
    <!-- モーダル部分終わり -->
  </main>
  <footer></footer>
  <script src="/E4/js/event_detail.js"></script>
<!--   <script>
	   /**
	   * コミュニケーションエリア非同期通信
	   */
	   const communicationDiv = document.getElementById('communication-div');
	   const sendBtn = document.getElementById('send');

	   const getData = () => {
	      let request = new XMLHttpRequest();
	      request.open('POST', '/E4/JoinDetailServlet', true);
	      request.setRequestHeader('Content-Type', 'application/json');
	      // データを受け取る処理
	      request.onreadystatechange = function () {
	        if (request.readyState == 4) { // 受信待ちチェック
	          if (request.status == 200) { // 通信チェック
	            const jsonObject = JSON.parse(request.responseText); // JSONデータ受け取り
		    	//console.log(jsonObject.data[0][0]);
		    	//console.log(jsonObject.data[0][1]);
		    	//let jsonData = JSON.stringify(jsonObject);
		        let chat = new Array();
		        let len = Object.keys(jsonObject.data).length;
				console.log("len:"+len);
		        for(let i=0; i < len; i++){
		        	chat += '<p><span class="chatname">'
		        				 + jsonObject.data[i].userName
		        				 + '</span><span class="content">'
		        				 + jsonObject.data[i].content + '</span></p>';
		        }
		        communicationDiv.innerHTML = chat;
	          } else {
	              console.error('データの取得に失敗しました。');
	          }
	          request.onerror = function() {
	              console.error('通信エラーが発生しました。');
	            }
	          // 以下 データ送信処理
	  	      // 送信したい内容を取得
	  	      let userId = document.getElementById("user_id").value;
	  	      let eventId = document.getElementById("event_id").value;
	  	      let content = document.getElementById("content").value;
	  	      request.type="json";
	  	      request.open('POST', '/E4/JoinDetailServlet', true);
	  	      // 送信したい内容をJSONに変換
	  	      //let body = JSON.stringify({
		  	 //     user_id: userId,
		  	  //    event_id: eventId,
		  	 //     body: content
	  	   // });
	  	    // サーブレットで非同期通信かどうか判別するためのフラグ
	  	    console.log("送信準備完了");
	  	    request.send(); // 送信
	  	    console.log("送信完了");
	        }
	      }
	  }

	  sendBtn.addEventListener('click', getData);

  </script> -->
    <script>
    const communicationDiv = document.getElementById('communication-div');
    const chatForm = document.getElementById('chat-form');
    const sendBtn = document.getElementById('send');

    const getData = () => {
      const request = new XMLHttpRequest();
      request.open('POST', '/E4/JoinDetailServlet', true);
      request.setRequestHeader('Content-Type', 'application/json');

      request.onload = function() {
        if (request.status >= 200 && request.status < 400) {
          const jsonObj = JSON.parse(request.responseText);

          let result = jsonObj.data[0]['result'];
          if (result) {
        	  window.alert('メッセージの送信に成功しました');
          } else {
        	  window.alert('メッセージの送信に失敗しました');
          }

	      let chatArray = new Array();
	      let len = Object.keys(jsonObj.data).length;

	      for ( let i = 1; i < len; i++) { // 0番目の要素はDB更新の結果が入っているので i = 1 でスタート
	        chat Array+= jsonObj.data[i];
	      }

	      let chat = "";

	      for ( let message of chatArray ) {
	    	  chat += '<p><span class="chatname">' + message["userName"] + '</span>：<span class="content">' + message[content] + '</span></p>';'
	      }

          communicationDiv.innerHTML = chat;
        } else {
          console.error('データの取得に失敗しました。');
        }
      };

      request.onerror = function() {
        console.error('通信エラーが発生しました。');
      };
	      let userId = document.getElementById("user_id").value;
  	      let eventId = document.getElementById("event_id").value;
  	      let content = document.getElementById("content").value;
      request.send();
    };

    sendBtn.addEventListener('click', getData);
  </script>
</body>
</html>