<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>参加イベント一覧ページ</title>
	<link rel="stylesheet" href="css/list.css">
</head>
<body>
    <header>

    </header>


    <main>
        <div class="main-inner">
            <h2>参加イベント一覧</h2><br>
            <span id="errormessage"></span>

            <c:forEach var="event" items="${cardList}">
            		<form method="post" action="/E4/JoinDetailServlet">
	                  <table>
	                      <tr>
	                          <td>イベント名：${event.eventName}</td>
	                      </tr>
	                      <tr>
	                          <td>開催日程：${event.holdingSchedule}</td>
	                      </tr>
	                      <tr>
	                          <td>場所：${event.locationName}</td><td></td><td></td><td><input type="submit" name="詳細" value="詳細" id="submitBtn"></td><td><input type="hidden" name="eventId" value="${event.id}"></td>
	                      </tr>
	                      <tr>
	                          <%-- <td>住所：<span id="prefecture"></span> ${event.detail_address}</td> --%>
	                          <%-- <td>住所：${cardList_prefecture[event.prefectureId]}${event.detailAddress}</td> --%>
	                          <td>住所：${cardList_prefecture.get(event.prefectureId)}${event.detailAddress}</td>
	                      </tr>
	                      <tr>
	                          <td>募集レベル：${event_level.get(event.eventCategory)}</td>
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

        /* if (${cardList}.size() == 0) {
        	document.getElementById('errormessage').textContent = String("参加予定のイベントはありません");
        } */

        if (${fn:length(cardList)} == 0) {
        	document.getElementById('errormessage').textContent = String("参加予定のイベントはありません");
        }
    </script>
</body>
</html>