<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/4.4.1/chart.min.js"
	integrity="sha512-L0Shl7nXXzIlBSUUPpxrokqq4ojqgZFQczTYlGjzONGTDAcLremjwaWv5A+EDLnxhQzY5xUZPWLOLqYRkY0Cbw=="
	crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<link rel="stylesheet" href="css/icon.css" />
<link rel="stylesheet" href="css/common.css" />
<link rel="stylesheet" href="css/header.css" />
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
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
					<img src=${setIconUrl } width="150px" class="setIcon"></img>
				</div>

				<p class="ziseki">実績</p>

				<ul class="icon-list">

					<li class="icon-list_icon">

						<form method="post" action="IconServlet">
							<input type="image" name="test" value="テスト" src=${cookParamImg }
								width="70px"> <input type="hidden" name="url"
								value=${cookParamImg }>
						</form>
					</li>

					<li class="icon-list_icon">
						<form method="post" action="IconServlet">
							<input type="image" src=${technicParamImg } width="70px">
							<input type="hidden" name="url" value=${technicParamImg}>
						</form>
					</li>
					<li class="icon-list_icon">
						<form method="post" action="IconServlet">
							<input type="image" src=${communicationParamImg}
								width="70px"> <input type="hidden" name="url"
								value=${communicationParamImg}>
						</form>
					</li>
					<li class="icon-list_icon">
						<form method="post" action="IconServlet">
							<input type="image" src=${hostedAmoundImg } width="70px">
							<input type="hidden" id="userid" name="url"
								value=${hostedAmoundImg}>
						</form>
					</li>
					<li class="icon-list_icon">
						<form method="post" action="IconServlet">
							<input type="image" src=${participantsAmountImg } width="70px">
							<input type="hidden" name="url" value=${participantsAmountImg}>
						</form>
					</li>
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


		<dialog class="modal">
		<div class="icons">
			<div class="icon">
				<div class="icon_and_discription">
					<img src=${cookParamImg } width="70px">

				</div>
				<canvas id="myChart1" class="chart_area" style="padding: 10px"
					height="40px">
        </canvas>
			</div>

			<div class="icon">
				<div class="icon_and_discription">
					<img src=${technicParamImg } width="70px">
					<p class="icon_discription">技術値</p>
				</div>
				<canvas id="myChart2" class="chart_area" style="padding: 10px"
					height="40px">
        </canvas>
			</div>
			<div class="icon">
				<div class="icon_and_discription">
					<img src=${communicationParamImg } height="70px">
					<p class="icon_discription">コミュニケーション値</p>
				</div>
				<canvas id="myChart3" class="chart_area" style="padding: 10px"
					height="40px">
        </canvas>
			</div>
			<div class="icon">
				<div class="icon_and_discription">
					<img src=${hostedAmoundImg } height="70px">
					<p class="icon_discription">主催回数</p>
				</div>
				<canvas id="myChart4" class="chart_area" style="padding: 10px"
					height="40px">
        </canvas>
			</div>
			<div class="icon">
				<div class="icon_and_discription">
					<img src=${participantsAmountImg } height="70px">
					<p class="icon_discription">参加回数</p>
				</div>
				<canvas id="myChart5" class="chart_area" style="padding: 10px"
					height="40px">
        </canvas>
</div>
				<button id="close">閉じる</button>
		</dialog>
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
	</script>

	<script>
		var param = [ 50, 200, 500, 3000 ]
		var rankrbgcolor = "rgba(224, 255, 255,1)" //初期色（ブロンズ以下）
		var param = ${user.cookParam}; //現在の実績値
		var max; //現在のランクの最大値

		//現在の実績値によって背景色とグラフの最大値を振り分け
		if (param < 50) {
			max = 50;
		} else if (param < 200) {
			max = 200;
			rankrbgcolor = "rgba(154, 98, 41, 1)";
		} else if (param < 500) {
			max = 500;
			rankrbgcolor = "rgba(192, 192, 192, 1)";
		} else {
			max = 3000;
			rankrbgcolor = "rgba(208, 169, 0, 1)";
		}
		var maxminusparam = max - param; //右側の表示用（ランクの最大値 - 現在の実績値）

		// 縦軸のラベル
		var ylabels = [ "料理" ];
		// １つ目の系列の情報を設定(左側)
		var series01name = "実績"; // 系列１の名前（ホバーしたときに表示される）
		var series01data = [ param ]; // 系列１データ（実績値）
		series01bgcolor = rankrbgcolor; // 系列１の塗りつぶし色（シルバーとかゴールドとか）
		var series01linecolor = rankrbgcolor; // 系列１の線の色（背景色と同色に）

		// ２つ目の系列の情報を設定(右側)
		var series02name = "次のランクまで";
		var series02data = [ maxminusparam ];
		var series02bgcolor = "rgba(0, 0, 0, 0)";
		var series02linecolor = rankrbgcolor;

		// グラフ縦軸の最大／最小／目盛りの間隔を設定
		var xmax = max; // グラフ縦軸の最大
		var xmin = param; // グラフ縦軸の最小
		var xstep = max; // グラフ縦軸の目盛り線を引く間隔

		//↓グラフ描写
		var ctx = document.getElementById("myChart1").getContext("2d");
		var myChart1 = new Chart(ctx, {
			type : "bar",
			data : {
				labels : ylabels,
				datasets : [ {
					//左側の領域
					axis : 'y',
					label : series01name,
					data : series01data,
					backgroundColor : series01bgcolor,
					borderColor : series01linecolor,
					borderWidth : 1,
				}, {
					//右側の領域
					axis : 'y',
					label : series02name,
					data : series02data,
					backgroundColor : series02bgcolor,
					borderColor : series02linecolor,
					borderWidth : 1,
				} ]
			},
			options : {
				indexAxis : 'y',
				scales : {
					x : {
						display : true,
						stacked : true,
						suggestedMax : xmax,
						suggestedMin : xmin,
						ticks : {
							stepSize : xstep,
						},
					},
					y : {
						display : false, //グラフ名を非表示に
						stacked : true,
					}
				},
				plugins : {
					legend : {
						display : false, //ラベルを非表示に
					},
				},

			}

		});
	</script>

	<script>
		var rankrbgcolor = "rgba(224, 255, 255,1)" //初期色（ブロンズ以下）
		var param = ${user.technicParam};
		var max;
		if (param < 50) {
			max = 50;
		} else if (param < 200) {
			max = 200;
			rankrbgcolor = "rgba(154, 98, 41, 1)";
		} else if (param < 500) {
			max = 500;
			rankrbgcolor = "rgba(192, 192, 192, 1)";
		} else {
			max = 3000;
			rankrbgcolor = "rgba(208, 169, 0, 1)";
		}
		var maxminusparam = max - param;

		// 縦軸のラベル
		var ylabels = [ "技量" ];
		// １つ目の系列の情報を設定(左側)
		var series01name = "実績"; // 系列１の名前
		var series01data = [ param ]; // 系列１データ
		series01bgcolor = rankrbgcolor; // 系列１の塗りつぶし色
		var series01linecolor = rankrbgcolor; // 系列１の線の色
		// ２つ目の系列の情報を設定(右側)
		var series02name = "次のランクまで";
		var series02data = [ maxminusparam ];
		var series02bgcolor = "rgba(0, 0, 0, 0)";
		var series02linecolor = rankrbgcolor;

		// グラフ縦軸の最大／最小／目盛りの間隔を設定
		var xmax = max; // グラフ縦軸の最大
		var xmin = param; // グラフ縦軸の最小
		var xstep = max; // グラフ縦軸の目盛り線を引く間隔

		var ctx = document.getElementById("myChart2").getContext("2d");
		var myChart2 = new Chart(ctx, {
			type : "bar",
			data : {
				labels : ylabels,
				datasets : [ {
					axis : 'y',
					label : series01name,
					data : series01data,
					backgroundColor : series01bgcolor,
					borderColor : series01linecolor,
					borderWidth : 1,
				}, {
					axis : 'y',
					label : series02name,
					data : series02data,
					backgroundColor : series02bgcolor,
					borderColor : series02linecolor,
					borderWidth : 1,
				}
				// ５つ以上系列を描画する場合は上のセットを追加する
				]
			},
			options : {
				indexAxis : 'y',
				scales : {
					x : {
						display : true,
						stacked : true,
						suggestedMax : xmax,
						suggestedMin : xmin,
						ticks : {
							stepSize : xstep,
						},
					},
					y : {
						display : false,
						stacked : true,
					}
				},
				plugins : {
					legend : {
						display : false,
					},
				},

			}

		});
	</script>

	<script>
		var rankrbgcolor = "rgba(224, 255, 255,1)" //初期色（ブロンズ以下）
		var param = ${user.communicationParam};
		var max;
		if (param < 50) {
			max = 50;
		} else if (param < 200) {
			max = 200;
			rankrbgcolor = "rgba(154, 98, 41, 1)";
		} else if (param < 500) {
			max = 500;
			rankrbgcolor = "rgba(192, 192, 192, 1)";
		} else {
			max = 3000;
			rankrbgcolor = "rgba(208, 169, 0, 1)";
		}
		var maxminusparam = max - param;

		// 縦軸のラベル
		var ylabels = [ "コミュニケーション" ];
		// １つ目の系列の情報を設定(左側)
		var series01name = "実績"; // 系列１の名前
		var series01data = [ param ]; // 系列１データ
		series01bgcolor = rankrbgcolor; // 系列１の塗りつぶし色
		var series01linecolor = rankrbgcolor; // 系列１の線の色
		// ２つ目の系列の情報を設定(右側)
		var series02name = "次のランクまで";
		var series02data = [ maxminusparam ];
		var series02bgcolor = "rgba(0, 0, 0, 0)";
		var series02linecolor = rankrbgcolor;

		// グラフ縦軸の最大／最小／目盛りの間隔を設定
		var xmax = max; // グラフ縦軸の最大
		var xmin = param; // グラフ縦軸の最小
		var xstep = max; // グラフ縦軸の目盛り線を引く間隔

		var ctx = document.getElementById("myChart3").getContext("2d");
		var myChart3 = new Chart(ctx, {
			type : "bar",
			data : {
				labels : ylabels,
				datasets : [ {
					axis : 'y',
					label : series01name,
					data : series01data,
					backgroundColor : series01bgcolor,
					borderColor : series01linecolor,
					borderWidth : 1,
				}, {
					axis : 'y',
					label : series02name,
					data : series02data,
					backgroundColor : series02bgcolor,
					borderColor : series02linecolor,
					borderWidth : 1,
				}
				// ５つ以上系列を描画する場合は上のセットを追加する
				]
			},
			options : {
				indexAxis : 'y',
				scales : {
					x : {
						display : true,
						stacked : true,
						suggestedMax : xmax,
						suggestedMin : xmin,
						ticks : {
							stepSize : xstep,
						},
					},
					y : {
						display : false,
						stacked : true,
					}
				},
				plugins : {
					legend : {
						display : false,
					},
				},

			}

		});
	</script>

	<script>
		var rankrbgcolor = "rgba(224, 255, 255,1)" //初期色（ブロンズ以下）
		var param = ${user.hostedAmount};
		var max;
		if (param < 3) {
			max = 3;
		} else if (param < 15) {
			max = 15;
			rankrbgcolor = "rgba(154, 98, 41, 1)";
		} else if (param < 30) {
			max = 30;
			rankrbgcolor = "rgba(192, 192, 192, 1)";
		} else {
			max = 1000;
			rankrbgcolor = "rgba(208, 169, 0, 1)";
		}
		var maxminusparam = max - param;

		// 縦軸のラベル
		var ylabels = [ "主催回数" ];
		// １つ目の系列の情報を設定(左側)
		var series01name = "実績"; // 系列１の名前
		var series01data = [ param ]; // 系列１データ
		series01bgcolor = rankrbgcolor; // 系列１の塗りつぶし色
		var series01linecolor = rankrbgcolor; // 系列１の線の色
		// ２つ目の系列の情報を設定(右側)
		var series02name = "次のランクまで";
		var series02data = [ maxminusparam ];
		var series02bgcolor = "rgba(0, 0, 0, 0)";
		var series02linecolor = rankrbgcolor;

		// グラフ縦軸の最大／最小／目盛りの間隔を設定
		var xmax = max; // グラフ縦軸の最大
		var xmin = param; // グラフ縦軸の最小
		var xstep = max; // グラフ縦軸の目盛り線を引く間隔

		var ctx = document.getElementById("myChart4").getContext("2d");
		var myChart4 = new Chart(ctx, {
			type : "bar",
			data : {
				labels : ylabels,
				datasets : [ {
					axis : 'y',
					label : series01name,
					data : series01data,
					backgroundColor : series01bgcolor,
					borderColor : series01linecolor,
					borderWidth : 1,
				}, {
					axis : 'y',
					label : series02name,
					data : series02data,
					backgroundColor : series02bgcolor,
					borderColor : series02linecolor,
					borderWidth : 1,
				}
				// ５つ以上系列を描画する場合は上のセットを追加する
				]
			},
			options : {
				indexAxis : 'y',
				scales : {
					x : {
						display : true,
						stacked : true,
						suggestedMax : xmax,
						suggestedMin : xmin,
						ticks : {
							stepSize : xstep,
						},
					},
					y : {
						display : false,
						stacked : true,
					}
				},
				plugins : {
					legend : {
						display : false,
					},
				},

			}

		});
	</script>

	<script>
		var rankrbgcolor = "rgba(224, 255, 255,1)" //初期色（ブロンズ以下）
		var param = ${user.participantsAmount};
		var max;
		if (param < 1) {
			max = 1;
		} else if (param < 7) {
			max = 7;
			rankrbgcolor = "rgba(154, 98, 41, 1)";
		} else if (param < 15) {
			max = 15;
			rankrbgcolor = "rgba(192, 192, 192, 1)";
		} else {
			max = 1000;
			rankrbgcolor = "rgba(208, 169, 0, 1)";
		}
		var maxminusparam = max - param;

		// 縦軸のラベル
		var ylabels = [ "参加回数" ];
		// １つ目の系列の情報を設定(左側)
		var series01name = "実績"; // 系列１の名前
		var series01data = [ param ]; // 系列１データ
		series01bgcolor = rankrbgcolor; // 系列１の塗りつぶし色
		var series01linecolor = rankrbgcolor; // 系列１の線の色
		// ２つ目の系列の情報を設定(右側)
		var series02name = "次のランクまで";
		var series02data = [ maxminusparam ];
		var series02bgcolor = "rgba(0, 0, 0, 0)";
		var series02linecolor = rankrbgcolor;

		// グラフ縦軸の最大／最小／目盛りの間隔を設定
		var xmax = max; // グラフ縦軸の最大
		var xmin = param; // グラフ縦軸の最小
		var xstep = max; // グラフ縦軸の目盛り線を引く間隔

		var ctx = document.getElementById("myChart5").getContext("2d");
		var myChart5 = new Chart(ctx, {
			type : "bar",
			data : {
				labels : ylabels,
				datasets : [ {
					axis : 'y',
					label : series01name,
					data : series01data,
					backgroundColor : series01bgcolor,
					borderColor : series01linecolor,
					borderWidth : 1,
				}, {
					axis : 'y',
					label : series02name,
					data : series02data,
					backgroundColor : series02bgcolor,
					borderColor : series02linecolor,
					borderWidth : 1,
				}
				// ５つ以上系列を描画する場合は上のセットを追加する
				]
			},
			options : {
				indexAxis : 'y',
				scales : {
					x : {
						display : true,
						stacked : true,
						suggestedMax : xmax,
						suggestedMin : xmin,
						ticks : {
							stepSize : xstep,
						},
					},
					y : {
						display : false,
						stacked : true,
					}
				},
				plugins : {
					legend : {
						display : false,
					},
				},

			}

		});
	</script>

</body>

</html>