<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>イベント作成ページ</title>
	<link rel="stylesheet" href="css/create_event.css">
	<link rel="stylesheet" href="css/header.css">
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

    <form method="post" action="/E4/CreateEventServlet" id="input">
        <main>
            <div class="main-inner">
                <h2>イベント作成</h2><br>
                <div class="profile">
                	<div>
                        <span id="judgemessage">
                        	<c:if test="${isCreateJudge == 'true'}">
				 	 			作成に成功しました。
					    	</c:if>
						    <c:if test="${isCreateJudge == 'false'}">
							    作成に失敗しました。
							</c:if>
                        </span>
                    </div>
                    <div>
                        <span id="errormessage"></span>
                    </div>
                    <table>
                        <tr>
                            <td class="title"><label for="event_name">イベント名</label></td>
                            <td><input type="text" name="event_name" id="event_name"></td>
                        </tr>
                        <tr>
                            <td class="title"><label for="event_summary">イベント説明</label></td>
                            <td><input type="text" name="event_summary" id="event_summary"></td>
                        </tr>
                        <tr>
                            <td class="title"><label for="event_day">開催日程</label></td>
                            <td><input type="date" name="event_day" id="event_day"></td>
                        </tr>
                        <tr>
                            <td class="title">参加最少人数</td><td><button id="min_minus">-</button><input type="text" name="min" id="min" value=4><button id="min_plus">+</button></td>
                        </tr>
                        <tr>
                            <td class="title">参加最大人数</td><td><button id="max_minus">-</button><input type="text" name="max" id="max" value=5><button id="max_plus">+</button></td>
                        </tr>
                        <tr>
                            <td class="title">都道府県</td>
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
                            <td class="title"><label for="detail">市区町村以下</label></td>
                            <td><input type="text" name="detail" id="detail"></td>
                        </tr>
                        <tr>
                            <td class="title"><label for="place">場所名称</label></td>
                            <td><input type="text" name="place" id="place"></td>
                        </tr>
                        <tr>
                            <td class="title">参加希望レベル</td>
                            <td>
                                <div class="multi-way-choice2">

                                    <input type="radio" id="radio-4" name="switch_2" value="0" >
                                    <label for="radio-4">初心者歓迎</label>

                                    <input type="radio" id="radio-5" name="switch_2" value="1" >
                                    <label for="radio-5">誰でも歓迎</label>

                                    <input type="radio" id="radio-6" name="switch_2" value="2" >
                                    <label for="radio-6">ベテラン向け</label>
                                </div>
                            </td>
                        </tr>
                    </table>
                    <input type="submit" id="regist" name="submit" value="作成">
                </div>
            </div>
        </main>
    </form>

    <footer>

    </footer>

    <script src="/E4/js/createEvent.js"></script>
</body>
</html>