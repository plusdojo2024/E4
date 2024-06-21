<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>イベント詳細(主催者)</title>
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
      <p class="event-detail">イベント名：<span id="eventName">${request.eventName }</span></p>
      <p class="event-detail">イベント詳細</p>
      <p class="event-description">${request.eventDescription}</p>
      <p class="event-detail">開催日程：<span>${request.holdingSchedule}</span></p>
      <div>
        <p class="event-detail">参加人数：<span>${request.leastCount}</span> ～ <span>${request.maxCount}</span> 人</p>
        <p class="event-detail">現在の参加人数：<span>${request.usersCount }</span> 人</p>
      </div>
      <p class="event-detail">コミュニケーションエリア</p>
      <div id="communication-div" class="communicationArea-inner">
        <!-- ニックネームとチャット内容を表示　どうやってやろうかな… -->
        <p>コミュニケーションエリアです</p>
        <p><span class="nickname">ニックネーム</span><span class="content">内容</span></p>
        <p><span class="nickname">ニックネーム</span><span class="content">内容</span></p>
        <p><span class="nickname">ニックネーム</span><span class="content">内容</span></p>
        <p><span class="nickname">ニックネーム</span><span class="content">内容</span></p>
      </div>
      <form action="#" method="post">
        <input type="text" name="" id=""><input type="submit" value="送信">
      </form>
      <div>
        <p class="event-detail">住所：<span>${request.address}</span></p>
        <p class="event-detail">場所名称：<span>${request.locationName}</span></p>
      </div>
      <div><p class="event-detail">以下地図エリアです</p><iframe src="" ></iframe></div>
      <p class="event-detail">募集レベル：<span id="eventCategory">${request.eventCategory}</span></p>
      <form action="#" method="post">
        <input type="hidden" class="input" name="userId" value="${session.userId}">
        <button type="submit">参加する</button>
      </form>
      <button>戻る</button>
    </div>
    <!-- モーダル部分 -->
    <div class="modal" data-modal="box">
      <div class="modal__bg" data-modal="bg"></div>
      <div class="modal__inner" data-modal="inner">
        <c:forEach var="userinfo"  items="${requestScope.participantUsers}">
          <div class="modal-card" data-trigger="item" data-modal="${userinfo.id}">
            <div class="modal-card__close" data-modal="close">
              <div class="modal-card__closeBtn"></div>
            </div>
            <p>ニックネーム：${user.name}</p>
            <!-- 画像どうしようね～～～ -->
          </div>
        </c:forEach>
      </div>
    </div>
    <!-- モーダル部分終わり -->
  </main>
  <footer></footer>
</body>
</html>