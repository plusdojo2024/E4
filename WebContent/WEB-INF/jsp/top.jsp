<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"  href="css/top.css">
<link rel="stylesheet" href="css/header.css">
<title>トップ</title>
</head>
<body>
	<!-- ヘッダーここから -->
	<header>
		<div class="container">
	      <div class="logoarea">
	          <img src="img/headerlogo.png" width="150px" alt="ロゴ">
	      </div>
	      <nav class="header-nav">
	          <ul class="list-nav">
	              <li><a href="/E4/JoinEventList">参加イベント</a></li>
	              <li><a href="/E4/CreateEvent">イベント作成</a></li>
	              <li><a href="/E4/Profile">プロフィール</a></li>
	              <li><a href="/E4/Logout">ログアウト</a></li>
	          </ul>
	      </nav>
	   	</div>
    </header>
		<!--アイコン画像-->
		<div class="center">
		<div class="icon">
   		 <c:choose>
        <c:when test="${empty iconUrl}">
            <a href="/E4/Icon"><img src="img/icon/com/com_initial" width="200px" height="200px"></a>
        </c:when>
        <c:otherwise>
            <a href="/E4/Icon"><img src="${iconUrl}" width="200px" height="200px"></a>
        </c:otherwise>
    </c:choose>

		</div>
		<!--天気予報-->
		<div class="weatherlocation">
			<div id="location">
				<span class="location">:${prefectureName}</span>
				<a href="https://www.jma.go.jp/bosai/forecast/" target="_blank"><span class="tenki">天気予報</span></a>
			</div>
			<p class="moji1">今日</p>
			<p class="moji2">1日後</p>
			<p class="moji3">2日後</p>
			<p class="moji4">3日後</p>
			<p class="moji5">4日後</p>
			<p class="moji6">5日後</p>
			<p class="moji7">6日後</p>
			<div class="weatherForecast">
				<div class="weather">
					<p></p>
					<div class="date">--/--(-)</div>
					<img class="weatherImg">
					<div class="weatherTelop">--</div>
					<div>
						<span class="tempMin">-℃</span>/<span class="tempMax">-℃</span>
					</div>
				</div>
			</div>
			<script>
				for (let i = 0; i < 6; i++) {
					const el = document.querySelector('.weather').cloneNode(
							true);
					document.querySelector('.weatherForecast').appendChild(el);
				}

				const weatherCode = {
					100 : [ "100.svg", "500.svg", "晴れ" ],
					101 : [ "101.svg", "501.svg", "晴れ時々曇り" ],
					102 : [ "102.svg", "502.svg", "晴れ一時雨" ],
					103 : [ "102.svg", "502.svg", "晴れ時々雨" ],
					104 : [ "104.svg", "504.svg", "晴れ一時雪" ],
					105 : [ "104.svg", "504.svg", "晴れ時々雪" ],
					106 : [ "102.svg", "502.svg", "晴れ一時雨か雪" ],
					107 : [ "102.svg", "502.svg", "晴れ時々雨か雪" ],
					108 : [ "102.svg", "502.svg", "晴れ一時雨か雷雨" ],
					110 : [ "110.svg", "510.svg", "晴れ後時々曇り" ],
					111 : [ "110.svg", "510.svg", "晴れ後曇り" ],
					112 : [ "112.svg", "512.svg", "晴れ後一時雨" ],
					113 : [ "112.svg", "512.svg", "晴れ後時々雨" ],
					114 : [ "112.svg", "512.svg", "晴れ後雨" ],
					115 : [ "115.svg", "515.svg", "晴れ後一時雪" ],
					116 : [ "115.svg", "515.svg", "晴れ後時々雪" ],
					117 : [ "115.svg", "515.svg", "晴れ後雪" ],
					118 : [ "112.svg", "512.svg", "晴れ後雨か雪" ],
					119 : [ "112.svg", "512.svg", "晴れ後雨か雷雨" ],
					120 : [ "102.svg", "502.svg", "晴れ朝夕一時雨" ],
					121 : [ "102.svg", "502.svg", "晴れ朝の内一時雨" ],
					122 : [ "112.svg", "512.svg", "晴れ夕方一時雨" ],
					123 : [ "100.svg", "500.svg", "晴れ山沿い雷雨" ],
					124 : [ "100.svg", "500.svg", "晴れ山沿い雪" ],
					125 : [ "112.svg", "512.svg", "晴れ午後は雷雨" ],
					126 : [ "112.svg", "512.svg", "晴れ昼頃から雨" ],
					127 : [ "112.svg", "512.svg", "晴れ夕方から雨" ],
					128 : [ "112.svg", "512.svg", "晴れ夜は雨" ],
					130 : [ "100.svg", "500.svg", "朝の内霧後晴れ" ],
					131 : [ "100.svg", "500.svg", "晴れ明け方霧" ],
					132 : [ "101.svg", "501.svg", "晴れ朝夕曇り" ],
					140 : [ "102.svg", "502.svg", "晴れ時々雨と雷" ],
					160 : [ "104.svg", "504.svg", "晴れ一時雪か雨" ],
					170 : [ "104.svg", "504.svg", "晴れ時々雪か雨" ],
					181 : [ "115.svg", "515.svg", "晴れ後雪か雨" ],
					200 : [ "200.svg", "200.svg", "曇り" ],
					201 : [ "201.svg", "601.svg", "曇り時々晴れ" ],
					202 : [ "202.svg", "202.svg", "曇り一時雨" ],
					203 : [ "202.svg", "202.svg", "曇り時々雨" ],
					204 : [ "204.svg", "204.svg", "曇り一時雪" ],
					205 : [ "204.svg", "204.svg", "曇り時々雪" ],
					206 : [ "202.svg", "202.svg", "曇り一時雨か雪" ],
					207 : [ "202.svg", "202.svg", "曇り時々雨か雪" ],
					208 : [ "202.svg", "202.svg", "曇り一時雨か雷雨" ],
					209 : [ "200.svg", "200.svg", "霧" ],
					210 : [ "210.svg", "610.svg", "曇り後時々晴れ" ],
					211 : [ "210.svg", "610.svg", "曇り後晴れ" ],
					212 : [ "212.svg", "212.svg", "曇り後一時雨" ],
					213 : [ "212.svg", "212.svg", "曇り後時々雨" ],
					214 : [ "212.svg", "212.svg", "曇り後雨" ],
					215 : [ "215.svg", "215.svg", "曇り後一時雪" ],
					216 : [ "215.svg", "215.svg", "曇り後時々雪" ],
					217 : [ "215.svg", "215.svg", "曇り後雪" ],
					218 : [ "212.svg", "212.svg", "曇り後雨か雪" ],
					219 : [ "212.svg", "212.svg", "曇り後雨か雷雨" ],
					220 : [ "202.svg", "202.svg", "曇り朝夕一時雨" ],
					221 : [ "202.svg", "202.svg", "曇り朝の内一時雨" ],
					222 : [ "212.svg", "212.svg", "曇り夕方一時雨" ],
					223 : [ "201.svg", "601.svg", "曇り日中時々晴れ" ],
					224 : [ "212.svg", "212.svg", "曇り昼頃から雨" ],
					225 : [ "212.svg", "212.svg", "曇り夕方から雨" ],
					226 : [ "212.svg", "212.svg", "曇り夜は雨" ],
					228 : [ "215.svg", "215.svg", "曇り昼頃から雪" ],
					229 : [ "215.svg", "215.svg", "曇り夕方から雪" ],
					230 : [ "215.svg", "215.svg", "曇り夜は雪" ],
					231 : [ "200.svg", "200.svg", "曇り海岸霧か霧雨" ],
					240 : [ "202.svg", "202.svg", "曇り時々雨と雷" ],
					250 : [ "204.svg", "204.svg", "曇り時々雪と雷" ],
					260 : [ "204.svg", "204.svg", "曇り一時雪か雨" ],
					270 : [ "204.svg", "204.svg", "曇り時々雪か雨" ],
					281 : [ "215.svg", "215.svg", "曇り後雪か雨" ],
					300 : [ "300.svg", "300.svg", "雨" ],
					301 : [ "301.svg", "701.svg", "雨時々晴れ" ],
					302 : [ "302.svg", "302.svg", "雨時々止む" ],
					303 : [ "303.svg", "303.svg", "雨時々雪" ],
					304 : [ "300.svg", "300.svg", "雨か雪" ],
					306 : [ "300.svg", "300.svg", "大雨" ],
					308 : [ "308.svg", "308.svg", "雨で暴風を伴う" ],
					309 : [ "303.svg", "303.svg", "雨一時雪" ],
					311 : [ "311.svg", "711.svg", "雨後晴れ" ],
					313 : [ "313.svg", "313.svg", "雨後曇り" ],
					314 : [ "314.svg", "314.svg", "雨後時々雪" ],
					315 : [ "314.svg", "314.svg", "雨後雪" ],
					316 : [ "311.svg", "711.svg", "雨か雪後晴れ" ],
					317 : [ "313.svg", "313.svg", "雨か雪後曇り" ],
					320 : [ "311.svg", "711.svg", "朝の内雨後晴れ" ],
					321 : [ "313.svg", "313.svg", "朝の内雨後曇り" ],
					322 : [ "303.svg", "303.svg", "雨朝晩一時雪" ],
					323 : [ "311.svg", "711.svg", "雨昼頃から晴れ" ],
					324 : [ "311.svg", "711.svg", "雨夕方から晴れ" ],
					325 : [ "311.svg", "711.svg", "雨夜は晴れ" ],
					326 : [ "314.svg", "314.svg", "雨夕方から雪" ],
					327 : [ "314.svg", "314.svg", "雨夜は雪" ],
					328 : [ "300.svg", "300.svg", "雨一時強く降る" ],
					329 : [ "300.svg", "300.svg", "雨一時みぞれ" ],
					340 : [ "400.svg", "400.svg", "雪か雨" ],
					350 : [ "300.svg", "300.svg", "雨で雷を伴う" ],
					361 : [ "411.svg", "811.svg", "雪か雨後晴れ" ],
					371 : [ "413.svg", "413.svg", "雪か雨後曇り" ],
					400 : [ "400.svg", "400.svg", "雪" ],
					401 : [ "401.svg", "801.svg", "雪時々晴れ" ],
					402 : [ "402.svg", "402.svg", "雪時々止む" ],
					403 : [ "403.svg", "403.svg", "雪時々雨" ],
					405 : [ "400.svg", "400.svg", "大雪" ],
					406 : [ "406.svg", "406.svg", "風雪強い" ],
					407 : [ "406.svg", "406.svg", "暴風雪" ],
					409 : [ "403.svg", "403.svg", "雪一時雨" ],
					411 : [ "411.svg", "811.svg", "雪後晴れ" ],
					413 : [ "413.svg", "413.svg", "雪後曇り" ],
					414 : [ "414.svg", "414.svg", "雪後雨" ],
					420 : [ "411.svg", "811.svg", "朝の内雪後晴れ" ],
					421 : [ "413.svg", "413.svg", "朝の内雪後曇り" ],
					422 : [ "414.svg", "414.svg", "雪昼頃から雨" ],
					423 : [ "414.svg", "414.svg", "雪夕方から雨" ],
					425 : [ "400.svg", "400.svg", "雪一時強く降る" ],
					426 : [ "400.svg", "400.svg", "雪後みぞれ" ],
					427 : [ "400.svg", "400.svg", "雪一時みぞれ" ],
					450 : [ "400.svg", "400.svg", "雪で雷を伴う" ],
				};

				const url = "https://www.jma.go.jp/bosai/forecast/data/forecast/${prefectures}.json";

				const dayList = [ "日", "月", "火", "水", "木", "金", "土" ];

				const timeDefinesList = new Array();
				const weatherCodeList = new Array();
				const tempsMinList = new Array();
				const tempsMaxList = new Array();

				fetch(url)
						.then(function(response) {
							return response.json();
						})
						.then(
								function(weather) {
									document.getElementById("location")/*.textContent = ${prefectureName};*/
									const isTodaysData = weather[0].timeSeries[2].timeDefines.length === 4;
									const weatherCodes = weather[0].timeSeries[0].areas[0].weatherCodes;
									const timeDefines = weather[0].timeSeries[0].timeDefines;
									const temps = weather[0].timeSeries[2].areas[0].temps;
									weatherCodeList.push(weatherCodes[0],
											weatherCodes[1]);
									timeDefinesList.push(timeDefines[0],
											timeDefines[1]);
									if (isTodaysData) {
										tempsMinList.push(
												temps[0] === temps[1] ? "--"
														: temps[0], temps[2]);
										tempsMaxList.push(temps[1], temps[3]);
									} else {
										tempsMinList.push("--", temps[0]);
										tempsMaxList.push("--", temps[1]);
									}

									const startCount = weather[1].timeSeries[0].timeDefines
											.indexOf(timeDefines[1]) + 1;
									for (let i = startCount; i < startCount + 5; i++) {
										weatherCodeList
												.push(weather[1].timeSeries[0].areas[0].weatherCodes[i]);
										timeDefinesList
												.push(weather[1].timeSeries[0].timeDefines[i]);
										tempsMinList
												.push(weather[1].timeSeries[1].areas[0].tempsMin[i]);
										tempsMaxList
												.push(weather[1].timeSeries[1].areas[0].tempsMax[i]);
									}

									const date = document
											.getElementsByClassName("date");
									const weatherImg = document
											.getElementsByClassName("weatherImg");
									const weatherTelop = document
											.getElementsByClassName("weatherTelop");
									const tempMin = document
											.getElementsByClassName("tempMin");
									const tempMax = document
											.getElementsByClassName("tempMax");

									weatherCodeList
											.forEach(function(el, i) {
												let dt = new Date(
														timeDefinesList[i]);
												let weekdayCount = dt.getDay();
												if (weekdayCount === 0)
													date[i].style.color = "red";
												if (weekdayCount === 6)
													date[i].style.color = "blue";
												var m = ("00" + (dt.getMonth() + 1))
														.slice(-2);
												var d = ("00" + dt.getDate())
														.slice(-2);

												var isNight = Number(i === 0
														&& !isTodaysData)
												weatherImg[i].src = "https://www.jma.go.jp/bosai/forecast/img/"
														+ weatherCode[el][isNight];
												weatherTelop[i].textContent = weatherCode[el][2];
												tempMin[i].textContent = tempsMinList[i]
														+ "℃";
												tempMax[i].textContent = tempsMaxList[i]
														+ "℃";
											});
								});
			</script>
			<!--<script type="text/javascript" src="http://localhost:8080/E4/js/weather_news.js"></script>  -->
			</div>
		</div>
		<!--天気予報ここまで-->
		<main class="main">
		<!--画像でほかのページ移動-->
		<div class="top-img-inner">
			<img class="top-img" src="/E4/img/topimage.png" alt="トップ画面" usemap="#top-link">
			<map name="top-link">
				<area shape="rect" coords="80, 35, 430, 175" href="/E4/JoinEventList" alt="参加イベント一覧へ">
				<area shape="rect" coords="570, 35, 930, 175" href="/E4/AdminEventList" alt="作成イベント一覧へ">
				<area shape="rect" coords="80, 190, 430, 330" href="/E4/CreateEvent" alt="イベント作成へ">
				<area shape="rect" coords="580, 190, 930, 330" href="/E4/Icon" alt="実績・アイコン確認へ">
			</map>
		</div>
		<!--通知エリア-->
		<!--background = "img/notice.png"  -->

		<div class="textarea">
		<div class="notic-area">
			<%-- <c:if test="${empty eventList}">
				<p>現在通知はありません</p>
			</c:if> --%>
			<p><a href="Review">イベントが終了しました！藤崎 里奈さんのレビューをお願いします！</a></p>
		 <c:forEach var="Notic" items="${eventList}">
				<div><span class="eventNotic">イベント「${Notic.eventName}」の招待が届きました！</span><br>
				<form method="post" action="BeforeJoinDetail">
				<span class="eventNotic">開催日程：${Notic.holdingSchedule}</span>
					<input class="greenBtn"  type="submit" name="詳細" value="詳細" id="submitBtn">
					<input type="hidden" name="event_id" value="${Notic.id}">

				</form>
				</div>
			</c:forEach>
			</div>
		</div>
	</main>
	<footer class="footer">
		<p>フッター</p>
	</footer>
</body>
</html>