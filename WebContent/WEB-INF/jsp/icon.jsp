<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/4.4.1/chart.min.js" integrity="sha512-L0Shl7nXXzIlBSUUPpxrokqq4ojqgZFQczTYlGjzONGTDAcLremjwaWv5A+EDLnxhQzY5xUZPWLOLqYRkY0Cbw==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<link rel="stylesheet" href="css/icon.css" />
<link rel="stylesheet" href="css/common.css" />
<link rel="stylesheet" href="css/header.css" />
<title>アイコン設定画面</title>
</head>

<body>
	<header class="header">
		<div class="container">
			<div class="logoarea">
				<img src="img/headerlogo.png" width=150px alt="ロゴ">
			</div>
			<nav class="header-nav">
				<ul class="list-nav">
					<li><a href="">検索</a></li>
					<li><a href="">参加イベント</a></li>
					<li><a href="">イベント作成</a></li>
					<li><a href="">プロフィール</a></li>
					<li><a href="">ログアウト</a></li>
				</ul>
			</nav>
		</div>
	</header>
	<main>
		<div class="main">
			<div class="main-inner">
				<div class="nameAndIcon">
					<div class="name-area">
						<p>${user.name}</p>
					</div>
					<p>${user.id}</p>
				</div>

				<p class="ziseki">実績</p>

				<ul class="icon-list">
					<li>${technicParamRank}</li>
					<li>${cookParamRank}</li>
					<li>${communicationParamRank}</li>
					<li>${participantsAmountRank}</li>
					<li>${hostedAmountRank}</li>
				</ul>
				<p class="evalution">評価</p>
				<input type="button" id="open" value="評価"> <br> <br>
				<br>
				<div class="registYearAndAmounts">
					<div class="registYear">
						<p>登録日：${user.registerYear}</p>
					</div>
					<div class="amoints">
						<p>開催回数：${user. hostedAmount}</p>
						<p>参加回数：${user.participantsAmount}</p>

					</div>

				</div>
			</div>
		</div>


		<dialog class="modal" width="500px">
		<div class="modal-content">
			<P>${user.technicParam} </P>
			<P>${user.cookParam} </P>
			<P>${user.communicationParam} </P>
			<P>${user.participantsAmount} </P>
			<P>${user.hostedAmount} </P>


		</div>

		<button id="close">閉じる</button>
		</dialog>
		<canvas id="myChart"></canvas>

	</main>
	<script>
		let dialog = document.querySelector('dialog');
		let btn_open = document.getElementById('open');
		let btn_close = document.getElementById('close');

		let out_close = document.getElementById('out-close');
		btn_open.addEventListener('click', function() {

			dialog.showModal();
		}, false);
		btn_close.addEventListener('click', function() {

			dialog.close();
		}, false);

		  new Chart(document.getElementById('myChart'), {
			  type: "bar",
			  data: {
			    labels: ["6/1", "7/1", "8/1", "9/1", "10/1"],
			    datasets: [
			      { label: "1~10位", data: [10, 9, 20], backgroundColor: "rgba(244, 143, 177, 0.6)" },
			      { label: "11~20位", data: [20, 6, 30], backgroundColor: "rgba(255, 235, 59, 0.6)" },
			      { label: "21~30位", data: [30, 12, 40], backgroundColor: "rgba(100, 181, 246, 0.6)" },
			      { label: "31~50位", data: [40, 8, 40], backgroundColor: "rgba(100, 93, 50, 0.6)" },
			      { label: "51~100位", data: [60, 6, 60], backgroundColor: "rgba(100, 23, 88, 0.6)" }
			    ]
			  },
			  options: {
			    plugins: {
			      stacked100: { enable: true }
			    },
			    title: {                           //タイトル設定
			                display: true,                 //表示設定
			                text: '順位上昇達成率'                //ラベル
			            },
			    scales: {                          //軸設定
			      xAxes: [{
			                stacked: true
			            }],
			      yAxes: [{                      //y軸設定
			                  stacked: true,
			                    display: true,             //表示設定
			                    scaleLabel: {              //軸ラベル設定
			                       display: true,          //表示設定
			                       fontSize: 18               //フォントサイズ
			                    },
			                    ticks: {                      //最大値最小値設定
			                        min: 0,                   //最小値
			                        max: 100,                  //最大値
			                        fontSize: 18,             //フォントサイズ
			                        stepSize: 20              //軸間隔
			                    },
			                }],
			              },
			    legend: {
			      display: true,
			    }
			  }
		  });
	</script>


</body>

</html>