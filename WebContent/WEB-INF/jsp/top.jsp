<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="CSS/top.css">
<title>トップ</title>
</head>
<body style="background-color: #dcdbc5;">
<main>
<!--アイコン画像-->
<img src="アイコン" width="150px" height="150px" alt="アイコン">
<!--天気予報-->
<div class="weatherlocation">
<div id="location"><a href="https://www.jma.go.jp/bosai/forecast/" target="_blank">天気予報</a></div>
<div class="weatherForecast">
    <div class="weather">
      <div class="date">--/--(-)</div>
      <img class="weatherImg">
      <div class="weatherTelop">--</div>
      <div><span class="tempMin">-℃</span>/<span class="tempMax">-℃</span></div>
    </div>
  </div>
  <script>
    for (let i = 0; i < 6; i++) {
      const el = document.querySelector('.weather').cloneNode(true);
      document.querySelector('.weatherForecast').appendChild(el);
    }
  </script>
  <script src="JS/top.js"></script>
  </div>
<!--天気予報ここまで-->

<!--画像でほかのページ移動-->
<a href="createEevent.html"><img src="img/キャンプ.jpg" class="kyanp1"><span class="kyanp11">イベント作成</span></a><!--createEevent.jsp-->
<a href="adminEventList.html"><img src="img/キャンプ横.jpg" class="kyanp2"><span class="kyanp22">作成イベント一覧</span></a><!--adminEventList.jsp-->
<a href="joinEventList.html"><img src="img/キャンプ横２.jpg" class="kyanp3"><span class="kyanp33">参加イベント一覧</span></a><!--joinEventList.jsp-->
<!--通知エリア-->
<textarea class="textarea">通知エリア</textarea>

<!--画像でほかのページ移動-->
<a href=""><img src="img/キャンプ横２.jpg" class="kyanp4">><span class="kyanp44">検索</span></a>
</main>
</body>
</html>