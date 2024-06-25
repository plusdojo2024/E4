<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/profile.css">
<link rel="stylesheet" href="css/header.css">
<title>プロフィール</title>
</head>
<body>
	<header>
		<div class="container">
	      <div class="logoarea">
	          <img src="img/headerlogo.png" width="150px" alt="ロゴ">
	      </div>
	      <nav class="header-nav">
	          <ul class="list-nav">
	              <li>
	                  <a href="/E4/TopServlet">トップに戻る</a>
	              </li>
	              <li>
	                  <a href="/E4/JoinEventListServlet">参加イベント</a>
	              </li>
	              <li>
	                  <a href="/E4/CreateEventServlet">イベント作成</a>
	              </li>
	              <li>
	                  <a href="/E4/ProfileServlet">プロフィール</a>
	              </li>
	              <li>
	                  <a href="/E4/Logout">ログアウト</a>
	              </li>
	          </ul>
	      </nav>
	   	</div>
    </header>

    <form method="post" action="/E4/ProfileServlet" id="input">
        <main>
            <div class="main-inner">
                <h2>マイプロフィール</h2><br>
                <div class="profile">
                    <table>
                    	<div>
                            <span id="judgemessage">
                            	<c:if test="${isUpdateJudge == 'true'}">
								 	 更新に成功しました。
							    </c:if>
							    <c:if test="${isUpdateJudge == 'false'}">
								     更新に失敗しました。
								</c:if>
                            </span>
                        </div>
                        <div>
                            <span id="errormessage"></span>
                        </div>

                        <tr>
                            <td class="title">ニックネーム</td><td>${user_profile.get(0)}</td>
                        </tr>
                        <tr>
                            <td class="title">生年月日</td><td>${user_profile.get(1)}</td>
                        </tr>
                        <tr>
                            <td class="title">性別</td><td>${user_profile.get(2)}</td>
                        </tr>
                        <tr>
                            <td class="title"><label for="tell">電話番号</label></td>
                            <td><input type="text" name="tell" id="tell"></td>
                        </tr>
                        <tr>
                            <td class="title">居住地</td>
                            <td>
                                <select name="prefecture">
                                    <option value="0">プルダウン選択</option>
                                    <option value="1">北海道</option>
                                    <option value="2">青森県</option>
                                    <option value="3">岩手県</option>
                                    <option value="4">宮城県</option>
                                    <option value="5">秋田県</option>
                                    <option value="6">山形県</option>
                                    <option value="7">福島県</option>
                                    <option value="8">茨城県</option>
                                    <option value="9">栃木県</option>
                                    <option value="10">群馬県</option>
                                    <option value="11">埼玉県</option>
                                    <option value="12">千葉県</option>
                                    <option value="13">東京都</option>
                                    <option value="14">神奈川県</option>
                                    <option value="15">新潟県</option>
                                    <option value="16">富山県</option>
                                    <option value="17">石川県</option>
                                    <option value="18">福井県</option>
                                    <option value="19">山梨県</option>
                                    <option value="20">長野県</option>
                                    <option value="21">岐阜県</option>
                                    <option value="22">静岡県</option>
                                    <option value="23">愛知県</option>
                                    <option value="24">三重県</option>
                                    <option value="25">滋賀県</option>
                                    <option value="26">京都府</option>
                                    <option value="27">大阪府</option>
                                    <option value="28">兵庫県</option>
                                    <option value="29">奈良県</option>
                                    <option value="30">和歌山県</option>
                                    <option value="31">鳥取県</option>
                                    <option value="32">島根県</option>
                                    <option value="33">岡山県</option>
                                    <option value="34">広島県</option>
                                    <option value="35">山口県</option>
                                    <option value="36">徳島県</option>
                                    <option value="37">香川県</option>
                                    <option value="38">愛媛県</option>
                                    <option value="39">高知県</option>
                                    <option value="40">福岡県</option>
                                    <option value="41">佐賀県</option>
                                    <option value="42">長崎県</option>
                                    <option value="43">熊本県</option>
                                    <option value="44">大分県</option>
                                    <option value="45">宮崎県</option>
                                    <option value="46">鹿児島県</option>
                                    <option value="47">沖縄県</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td class="title">参加可能範囲</td>
                            <td><input type="button" id="openModalBtn" value="参加範囲を選択"></td>
                        </tr>
                        <tr>
                            <td></td><td><span id="selected-items"></span><input type="hidden" name="selected_prefectures" id="selected_prefectures"></td>
                        </tr>
                        <tr>
                            <td></td><td><span id="selected-items2"></span></td>
                        </tr>
                        <tr>
                            <td class="title">アウトドアレベル</td>
                            <td>
                                <!-- <input type="range" name="xxx" min="0" max="100" step="10"> -->
                                <div class="range_slider">
                                    <span class="range_slider_min"></span>
                                    <div class="range_slider_input">
                                        <div class="range_slider_input_current">
                                            <span></span>
                                        </div>
                                        <input type="range" name="outdoorLevel" min="0" max="100" step="10">
                                    </div>
                                    <span class="range_slider_max"></span>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td class="title">参加希望レベル</td>
                            <td>
                                <div class="multi-way-choice">

                                    <input type="radio" id="radio-1" name="switch_1" value="0" >
                                    <label for="radio-1">初心者歓迎</label>

                                    <input type="radio" id="radio-2" name="switch_1" value="1" >
                                    <label for="radio-2">誰でも歓迎</label>

                                    <input type="radio" id="radio-3" name="switch_1" value="2" >
                                    <label for="radio-3">ベテラン向け</label>
                                </div>
                            </td>
                        </tr>
                    </table>
                    <input type="submit" id="update" value="更新">
                </div>
            </div>
        </main>
    </form>

    <div id="myModal" class="modal">
        <div class="modal-content">
            <span class="close">&times;</span>
            <h2>都道府県選択</h2>

            <h2>北海道地方 <input type="checkbox" id="selectall_hokkaido">すべて選択</h2>
            <input type="checkbox" name="prefectures" class="selectable_hokkaido" value="1">北海道

            <h2>東北地方 <input type="checkbox" id="selectall_touhoku">すべて選択</h2>
            <input type="checkbox" name="prefectures" class="selectable_touhoku" value="2">青森県
            <input type="checkbox" name="prefectures" class="selectable_touhoku" value="3">岩手県
            <input type="checkbox" name="prefectures" class="selectable_touhoku" value="4">宮城県
            <input type="checkbox" name="prefectures" class="selectable_touhoku" value="5">秋田県
            <input type="checkbox" name="prefectures" class="selectable_touhoku" value="6">山形県
            <input type="checkbox" name="prefectures" class="selectable_touhoku" value="7">福島県

            <h2>関東地方 <input type="checkbox" id="selectall_kantou">すべて選択</h2>
            <input type="checkbox" name="prefectures" class="selectable_kantou" value="8">茨城県
            <input type="checkbox" name="prefectures" class="selectable_kantou" value="9">栃木県
            <input type="checkbox" name="prefectures" class="selectable_kantou" value="10">群馬県
            <input type="checkbox" name="prefectures" class="selectable_kantou" value="11">埼玉県
            <input type="checkbox" name="prefectures" class="selectable_kantou" value="12">千葉県
            <input type="checkbox" name="prefectures" class="selectable_kantou" value="13">東京都
            <input type="checkbox" name="prefectures" class="selectable_kantou" value="14">神奈川県

            <h2>中部 <input type="checkbox" id="selectall_tyubu">すべて選択</h2>
            <input type="checkbox" name="prefectures" class="selectable_tyubu" value="15">新潟県
            <input type="checkbox" name="prefectures" class="selectable_tyubu" value="16">富山県
            <input type="checkbox" name="prefectures" class="selectable_tyubu" value="17">石川県
            <input type="checkbox" name="prefectures" class="selectable_tyubu" value="18">福井県
            <input type="checkbox" name="prefectures" class="selectable_tyubu" value="19">山梨県
            <input type="checkbox" name="prefectures" class="selectable_tyubu" value="20">長野県
            <input type="checkbox" name="prefectures" class="selectable_tyubu" value="21">岐阜県
            <input type="checkbox" name="prefectures" class="selectable_tyubu" value="22">静岡県
            <input type="checkbox" name="prefectures" class="selectable_tyubu" value="23">愛知県

            <h2>近畿地方 <input type="checkbox" id="selectall_kinki">すべて選択</h2>
            <input type="checkbox" name="prefectures" class="selectable_kinki" value="24">三重県
            <input type="checkbox" name="prefectures" class="selectable_kinki" value="25">滋賀県
            <input type="checkbox" name="prefectures" class="selectable_kinki" value="26">京都府
            <input type="checkbox" name="prefectures" class="selectable_kinki" value="27">大阪府
            <input type="checkbox" name="prefectures" class="selectable_kinki" value="28">兵庫県
            <input type="checkbox" name="prefectures" class="selectable_kinki" value="29">奈良県
            <input type="checkbox" name="prefectures" class="selectable_kinki" value="30">和歌山県

            <h2>中国地方 <input type="checkbox" id="selectall_tyugoku">すべて選択</h2>
            <input type="checkbox" name="prefectures" class="selectable_tyugoku" value="31">鳥取県
            <input type="checkbox" name="prefectures" class="selectable_tyugoku" value="32">島根県
            <input type="checkbox" name="prefectures" class="selectable_tyugoku" value="33">岡山県
            <input type="checkbox" name="prefectures" class="selectable_tyugoku" value="34">広島県
            <input type="checkbox" name="prefectures" class="selectable_tyugoku" value="35">山口県

            <h2>四国地方 <input type="checkbox" id="selectall_shikoku">すべて選択</h2>
            <input type="checkbox" name="prefectures" class="selectable_shikoku" value="36">徳島県
            <input type="checkbox" name="prefectures" class="selectable_shikoku" value="37">香川県
            <input type="checkbox" name="prefectures" class="selectable_shikoku" value="38">愛媛県
            <input type="checkbox" name="prefectures" class="selectable_shikoku" value="39">高知県

            <h2>九州地方 <input type="checkbox" id="selectall_kyusyu">すべて選択</h2>
            <input type="checkbox" name="prefectures" class="selectable_kyusyu" value="40">福岡県
            <input type="checkbox" name="prefectures" class="selectable_kyusyu" value="41">佐賀県
            <input type="checkbox" name="prefectures" class="selectable_kyusyu" value="42">長崎県
            <input type="checkbox" name="prefectures" class="selectable_kyusyu" value="43">熊本県
            <input type="checkbox" name="prefectures" class="selectable_kyusyu" value="44">大分県
            <input type="checkbox" name="prefectures" class="selectable_kyusyu" value="45">宮崎県
            <input type="checkbox" name="prefectures" class="selectable_kyusyu" value="46">鹿児島県

            <h2>沖縄地方 <input type="checkbox" id="selectall_okinawa">すべて選択</h2>
            <input type="checkbox" name="prefectures" class="selectable_okinawa" value="47">沖縄県

            <div class="prefecture_button">
                <button id="submitBtn">チェックした都道府県に設定する</button>
            </div>
        </div>
    </div>


    <footer>

    </footer>

    <script type="text/javascript">
	    'use strict';


	    let formObj = document.getElementById('input');
	    let errorMessageObj = document.getElementById('errormessage');
	    let Update = document.getElementById('update');
	    let judgeMessageObj = document.getElementById('judgemessage');

	    Update.addEventListener('click', (event) => {
	        if (!formObj.tell.value || formObj.prefecture.value == 0 || formObj.selected_prefectures.value =="" || formObj.switch_1.value == "") {
	            event.preventDefault();
	            errorMessageObj.textContent = '必須項目を入力してください';
	            judgeMessageObj.style.display = "none";
	        }
	    });


    </script>

    <script src="/E4/js/prefecture.js"></script>
</body>
</html>