<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>レビュー</title>
  <link rel="stylesheet" href="css/common.css">
  <link rel="stylesheet" href="css/review.css">
</head>
<body>
  <header></header>
  <main>
    <div class="main-inner">
      <h3 class="title">レビュー</h3>
      <!-- メッセージエリア -->
      <p id="message"></p>
      <form action="#" method="post">
        <p>評価</p>
        <!-- JSTLでニックネームを当てはめる -->
        <p>ニックネームさんはいかがでしたか？(必須)</p>
        <div class="evaluation-inner"><label>
          <input type="radio" name="evaluation" value=""><img src="img/good.jpg" alt="">
          <input type="radio" name="evaluation" value=""><img src="img/bad.jpg" alt="">
        </label></div>
        <p>コメント</p>
        <textarea name="comment" class="comment"></textarea>

          <p>コミュニケーション</p>
          <div><label class="communication-inner">
            <input class="communication" type="radio" name="communicationParam" value="1"><img class="communication-star" src="img/star_flame.png" alt="">
            <input class="communication" type="radio" name="communicationParam" value="2"><img class="communication-star" src="img/star_flame.png" alt="">
            <input class="communication" type="radio" name="communicationParam" value="3"><img class="communication-star" src="img/star_flame.png" alt="">
            <input class="communication" type="radio" name="communicationParam" value="4"><img class="communication-star" src="img/star_flame.png" alt="">
            <input class="communication" type="radio" name="communicationParam" value="5"><img class="communication-star" src="img/star_flame.png" alt="">
          </label></div>

          <p>技量</p>
          <div><label class="technic-inner">
            <input class="technic" type="radio" name="technicParam" value="1"><img class="technic-star" src="img/star_flame.png" alt="">
            <input class="technic" type="radio" name="technicParam" value="2"><img class="technic-star" src="img/star_flame.png" alt="">
            <input class="technic" type="radio" name="technicParam" value="3"><img class="technic-star" src="img/star_flame.png" alt="">
            <input class="technic" type="radio" name="technicParam" value="4"><img class="technic-star" src="img/star_flame.png" alt="">
            <input class="technic" type="radio" name="technicParam" value="5"><img class="technic-star" src="img/star_flame.png" alt="">
          </label></div>

          <p>料理</p>
          <div><label class="cook-inner">
            <input class="cook" type="radio" name="cookParam" value="1"><img class="cook-star" src="img/star_flame.png" alt="">
            <input class="cook" type="radio" name="cookParam" value="2"><img class="cook-star" src="img/star_flame.png" alt="">
            <input class="cook" type="radio" name="cookParam" value="3"><img class="cook-star" src="img/star_flame.png" alt="">
            <input class="cook" type="radio" name="cookParam" value="4"><img class="cook-star" src="img/star_flame.png" alt="">
            <input class="cook" type="radio" name="cookParam" value="5"><img class="cook-star" src="img/star_flame.png" alt="">
          </label></div>

        <input id="submit" type="submit" value="送信">
      </form>
    </div>
  </main>
  <footer></footer>
  <script>
    'use strict';

    // 送信ボタンを押したらアラート表示(更新されたら、に切り替え予定)
    let submitButton = document.getElementById("submit");
    submitButton.addEventListener("click", (event) => {
      window.alert("送信完了しました");
    });

    document.addEventListener("DOMContentLoaded", (event) => { // HTMLの読み込み終了時に発生
      /* コミュニケーションの評価部分 */
      // コミュニケーションの評価で使用する部分を取得
      let comStars = document.getElementsByClassName("communication-star");
      // クリックされたかどうかのフラグ
      let comClicked = false;
      for (let i = 0; i < comStars.length; i++) {
        comStars[i].addEventListener("mouseover", (event) => { // マウスオーバー時の処理
          if (!comClicked) { // クリックされていないとき(フラグがfalseのとき)
            for (let j = 0; j <= i; j++) {// 一番左の星から上にカーソルがある星まで
              comStars[j].src = "img/star.png"; // imgタグのsrc属性を書き換え
            }
          }
        });
        comStars[i].addEventListener("mouseout", (event) => { // カーソルが離れた時の処理
          if (!comClicked) { // クリックされていないとき(フラグがfalseのとき)
            for (let j = 0; j <= i; j++) {// 一番左の星から上にカーソルがある星まで
              comStars[j].src = "img/star_flame.png"; // imgタグのsrc属性を書き換え
            }
          }
        });
        comStars[i].addEventListener("click", (event) => {
          comClicked = true; // フラグをtureに
          for (let j = 0; j <= i; j++) {// 一番左の星から上にカーソルがある星まで
              comStars[j].src = "img/star.png"; // imgタグのsrc属性を書き換え
          }
        });
      }

      /* 技量の評価部分 */
      // 技量の評価で使用する部分を取得
      let technicStars = document.getElementsByClassName("technic-star");
      // クリックされたかどうかのフラグ
      let technicClicked = false;
      for (let i = 0; i < technicStars.length; i++) {
        technicStars[i].addEventListener("mouseover", (event) => { // マウスオーバー時の処理
          if (!technicClicked) { // クリックされていないとき(フラグがfalseのとき)
            for (let j = 0; j <= i; j++) {// 一番左の星から上にカーソルがある星まで
              technicStars[j].src = "img/star.png"; // imgタグのsrc属性を書き換え
            }
          }
        });
        technicStars[i].addEventListener("mouseout", (event) => { // カーソルが離れた時の処理
          if (!technicClicked) { // クリックされていないとき(フラグがfalseのとき)
            for (let j = 0; j <= i; j++) {// 一番左の星から上にカーソルがある星まで
              technicStars[j].src = "img/star_flame.png"; // imgタグのsrc属性を書き換え
            }
          }
        });
        technicStars[i].addEventListener("click", (event) => {
          technicClicked = true; // フラグをtureに
          for (let j = 0; j <= i; j++) {// 一番左の星から上にカーソルがある星まで
            technicStars[j].src = "img/star.png"; // imgタグのsrc属性を書き換え
          }
        });
      }

      /* 料理の評価部分 */
      // 料理の評価で使用する部分を取得
      let cookStars = document.getElementsByClassName("cook-star");
      // クリックされたかどうかのフラグ
      let cookClicked = false;
      for (let i = 0; i < cookStars.length; i++) {
        cookStars[i].addEventListener("mouseover", (event) => { // マウスオーバー時の処理
          if (!cookClicked) { // クリックされていないとき(フラグがfalseのとき)
            for (let j = 0; j <= i; j++) {// 一番左の星から上にカーソルがある星まで
              cookStars[j].src = "img/star.png"; // imgタグのsrc属性を書き換え
            }
          }
        });
        cookStars[i].addEventListener("mouseout", (event) => { // カーソルが離れた時の処理
          if (!cookClicked) { // クリックされていないとき(フラグがfalseのとき)
            for (let j = 0; j <= i; j++) {// 一番左の星から上にカーソルがある星まで
              cookStars[j].src = "img/star_flame.png"; // imgタグのsrc属性を書き換え
            }
          }
        });
        cookStars[i].addEventListener("click", (event) => {
          cookClicked = true; // フラグをtureに
          for (let j = 0; j <= i; j++) {// 一番左の星から上にカーソルがある星まで
            cookStars[j].src = "img/star.png"; // imgタグのsrc属性を書き換え
          }
        });
      }
    });
  </script>
</body>
</html>