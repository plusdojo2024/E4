<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="WebContent/css/list.css">
	<title>作成イベント一覧ページ</title>
</head>
<body>
    <header>

    </header>

    <form>
        <main>
            <div class="main-inner">
                <h2>参加イベント一覧</h2><br>
                <span id="errormessage"></span>

                <c:forEach var="admin" items="${cardList_admin}" >
                	<c:forEach var="prefecture" items="cardList_prefecture">
                		<c:forEach var="joinMember" items="cardList_joinMember">
		                    <table>
		                        <tr>
		                            <td>イベント名：${admin.event_name}</td>
		                        </tr>
		                        <tr>
		                            <td>開催日程：${admin.holding_schedule}</td>
		                        </tr>
		                        <tr>
		                            <td>場所：${admin.location_name}</td><td></td><td></td><td><td></td><td><input type="button" value="詳細"></td>
		                        </tr>
		                        <tr>
		                            <td>住所：${prefecture}${admin.detail_address}</td>
		                        </tr>
		                        <tr>
		                            <td>募集レベル：${admin.event_category}</td>
		                        </tr>
		                        <tr>
		                            <td>現在の参加予定人数：${joinMember}</td>
		                        </tr>
		                    </table>
	                    </c:forEach>
                    </c:forEach>
                </c:forEach>
            </div>
        </main>
    </form>

    <footer>

    </footer>

    <script>
        'use strict';

        if (${cardList_admin}.size() == 0) {
        	document.getElementById('errormessage').textContent = String("あなたが作成したイベントはありません");
        }

    </script>
</body>
</html>