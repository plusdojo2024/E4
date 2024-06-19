<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>参加イベント一覧ページ</title>
	<link rel="stylesheet" href="WebContent/css/list.css">
</head>
<body>
    <header>

    </header>

    <form>
        <main>
            <div class="main-inner">
                <h2>参加イベント一覧</h2><br>
                <span id="errormessage"></span>

                <c:forEach var="event" items="${cardList}">
                	<c:forEach var="prefecture" items="cardList_prefecture">
	                    <table>
	                        <tr>
	                            <td>イベント名：${event.event_name}</td>
	                        </tr>
	                        <tr>
	                            <td>開催日程：${event.holding_schedule}</td>
	                        </tr>
	                        <tr>
	                            <td>場所：${event.location_name}</td><td></td><td></td><td><td></td><td><input type="button" value="詳細"></td>
	                        </tr>
	                        <tr>
	                            <td>住所：${prefecture}${event.detail_address}</td>
	                        </tr>
	                        <tr>
	                            <td>募集レベル：${event.event_category}</td>
	                        </tr>
	                    </table>
                    </c:forEach>
                </c:forEach>
            </div>
        </main>
    </form>

    <footer>

    </footer>

    <script>
        'use strict';

        if (${cardList}.size() == 0) {
        	document.getElementById('errormessage').textContent = String("参加予定のイベントはありません");
        }
    </script>
</body>
</html>