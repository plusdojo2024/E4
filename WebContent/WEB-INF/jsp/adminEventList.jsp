<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="css/list.css">
	<title>作成イベント一覧ページ</title>
</head>
<body>
    <header>

    </header>


    <main>
        <div class="main-inner">
            <h2>作成イベント一覧</h2><br>
            <span id="errormessage"></span>

            <c:forEach var="admin" items="${cardList_admin}" >
          		<form method="post" action="/E4/AdminDetailServlet">
                  <table>
                      <tr>
                          <td>イベント名：${admin.event_name}</td>
                      </tr>
                      <tr>
                          <td>開催日程：${admin.holding_schedule}</td>
                      </tr>
                      <tr>
                          <td>場所：${admin.location_name}</td><td></td><td></td><td><input type="button" name="詳細" value="詳細"></td><td><input type="hidden" name="eventID" value="${admin.id}"></td>
                      </tr>
                      <tr>
                          <%-- <td>住所：${cardList_prefecture[${event.prefectureId}]}${event.detail_address}</td> --%>
                          <td>住所：${cardList_prefecture}.get(${event.prefectureId})${event.detail_address}</td>
                      </tr>
                      <tr>
                          <td>募集レベル：${admin.event_category}</td>
                      </tr>
                      <tr>
                          <td>現在の参加予定人数：${cardList_joinMember}.get(${admin.id})</td>
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