<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="css/common.css">
	<link rel="stylesheet" href="css/list.css">
	<link rel="stylesheet" href="css/header.css">
	<title>作成イベント一覧ページ</title>
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
            <h2>作成イベント一覧</h2><br>
            <span id="errormessage"></span>

            <c:forEach var="admin" items="${cardList_admin}" >
          		<form method="post" action="AdminDetail">
                  <table>
                      <tr>
                          <td>イベント名：${admin.eventName}</td>
                      </tr>
                      <tr>
                          <td>開催日程：${admin.holdingSchedule}</td>
                      </tr>
                      <tr>
                          <td>場所：${admin.locationName}</td><td></td><td></td><td><input type="submit" name="詳細" value="詳細" id="submitBtn"></td><td><input type="hidden" name="event_id" value="${admin.id}"></td>
                      </tr>
                      <tr>
                          <%-- <td>住所：${cardList_prefecture[${event.prefectureId}]}${admin.detailAddress}</td> --%>
                          <td>住所：${cardList_prefecture.get(admin.prefectureId)}${admin.detailAddress}</td>
                      </tr>
                      <tr>
                          <td>募集レベル：${event_level_admin.get(admin.eventCategory)}</td>
                      </tr>
                      <tr>
                          <td>現在の参加予定人数：
                          	<c:if test="${cardList_joinMember.get(admin.id) == 0 }">1人</c:if>
                          	<c:if test="${cardList_joinMember.get(admin.id) > 0 }">${cardList_joinMember.get(admin.id)}人</c:if></td>
                      </tr>
                  </table>
				</form>
            </c:forEach>
        </div>
    </main>


    <footer>

    </footer>

    <script>
        'use strict';

        /* if (${cardList_admin}.size() == 0) {
        	document.getElementById('errormessage').textContent = String("あなたが作成したイベントはありません");
        } */

        if (${fn:length(cardList_admin)} == 0) {
    		document.getElementById('errormessage').textContent = String("あなたが作成したイベントはありません");
    	}

    </script>
</body>
</html>