<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規登録</title>
<link rel="stylesheet" href="css/regist.css">
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
	              <li><a href="/E4/Top">トップに戻る</a></li>
	              <li><a href="/E4/JoinEventList">参加イベント</a></li>
	              <li><a href="/E4/CreateEvent">イベント作成</a></li>
	              <li><a href="/E4/Profile">プロフィール</a></li>
	              <li><a href="/E4/Logout">ログアウト</a></li>
	          </ul>
	      </nav>
	   	</div>
    </header>

    <form method="post" action="/E4/RegistUser" id="input">
        <main>
            <div class="main-inner">
                <h2>新規登録</h2><br>
                <div class="profile">
                	<div>
                        <span id="judgemessage">
						    <c:if test="${isRegistJudge == 'false'}">
							    作成に失敗しました。
							</c:if>
                        </span>
                    </div>
                    <div>
                        <span id="errormessage"></span>
                    </div>
                    <table>

                        <tr>
                            <td class="title"><label for="name">ニックネーム</label></td>
                            <td><input type="text" name="name" id="name"></td>
                        </tr>
                        <tr>
                            <td class="title"><label for="mail">メールアドレス</label></td>
                            <td><input type="text" name="mail" id="mail"></td>
                        </tr>
                        <tr>
                            <td class="title"><label for="pw">パスワード</label></td>
                            <td><input type="text" name="pw" id="pw"></td>
                        </tr>
                        <tr>
                            <td class="title">生年月日</td>
                            <td>
                                <select name="year">
                                    <option value="0">年選択</option>
                                    <option value="1900">1900</option>
                                    <option value="1901">1901</option>
                                    <option value="1902">1902</option>
                                    <option value="1903">1903</option>
                                    <option value="1904">1904</option>
                                    <option value="1905">1905</option>
                                    <option value="1906">1906</option>
                                    <option value="1907">1907</option>
                                    <option value="1908">1908</option>
                                    <option value="1909">1909</option>
                                    <option value="1910">1910</option>
                                    <option value="1911">1911</option>
                                    <option value="1912">1912</option>
                                    <option value="1913">1913</option>
                                    <option value="1914">1914</option>
                                    <option value="1915">1915</option>
                                    <option value="1916">1916</option>
                                    <option value="1917">1917</option>
                                    <option value="1918">1918</option>
                                    <option value="1919">1919</option>
                                    <option value="1920">1920</option>
                                    <option value="1921">1921</option>
                                    <option value="1922">1922</option>
                                    <option value="1923">1923</option>
                                    <option value="1924">1924</option>
                                    <option value="1925">1925</option>
                                    <option value="1926">1926</option>
                                    <option value="1927">1927</option>
                                    <option value="1928">1928</option>
                                    <option value="1929">1929</option>
                                    <option value="1930">1930</option>
                                    <option value="1931">1931</option>
                                    <option value="1932">1932</option>
                                    <option value="1933">1933</option>
                                    <option value="1934">1934</option>
                                    <option value="1935">1935</option>
                                    <option value="1936">1936</option>
                                    <option value="1937">1937</option>
                                    <option value="1938">1938</option>
                                    <option value="1939">1939</option>
                                    <option value="1940">1940</option>
                                    <option value="1941">1941</option>
                                    <option value="1942">1942</option>
                                    <option value="1943">1943</option>
                                    <option value="1944">1944</option>
                                    <option value="1945">1945</option>
                                    <option value="1946">1946</option>
                                    <option value="1947">1947</option>
                                    <option value="1948">1948</option>
                                    <option value="1949">1949</option>
                                    <option value="1950">1950</option>
                                    <option value="1951">1951</option>
                                    <option value="1952">1952</option>
                                    <option value="1953">1953</option>
                                    <option value="1954">1954</option>
                                    <option value="1955">1955</option>
                                    <option value="1956">1956</option>
                                    <option value="1957">1957</option>
                                    <option value="1958">1958</option>
                                    <option value="1959">1959</option>
                                    <option value="1960">1960</option>
                                    <option value="1961">1961</option>
                                    <option value="1962">1962</option>
                                    <option value="1963">1963</option>
                                    <option value="1964">1964</option>
                                    <option value="1965">1965</option>
                                    <option value="1966">1966</option>
                                    <option value="1967">1967</option>
                                    <option value="1968">1968</option>
                                    <option value="1969">1969</option>
                                    <option value="1970">1970</option>
                                    <option value="1971">1971</option>
                                    <option value="1972">1972</option>
                                    <option value="1973">1973</option>
                                    <option value="1974">1974</option>
                                    <option value="1975">1975</option>
                                    <option value="1976">1976</option>
                                    <option value="1977">1977</option>
                                    <option value="1978">1978</option>
                                    <option value="1979">1979</option>
                                    <option value="1980">1980</option>
                                    <option value="1981">1981</option>
                                    <option value="1982">1982</option>
                                    <option value="1983">1983</option>
                                    <option value="1984">1984</option>
                                    <option value="1985">1985</option>
                                    <option value="1986">1986</option>
                                    <option value="1987">1987</option>
                                    <option value="1988">1988</option>
                                    <option value="1989">1989</option>
                                    <option value="1990">1990</option>
                                    <option value="1991">1991</option>
                                    <option value="1992">1992</option>
                                    <option value="1993">1993</option>
                                    <option value="1994">1994</option>
                                    <option value="1995">1995</option>
                                    <option value="1996">1996</option>
                                    <option value="1997">1997</option>
                                    <option value="1998">1998</option>
                                    <option value="1999">1999</option>
                                    <option value="2000">2000</option>
                                    <option value="2001">2001</option>
                                    <option value="2002">2002</option>
                                    <option value="2003">2003</option>
                                    <option value="2004">2004</option>
                                    <option value="2005">2005</option>
                                    <option value="2006">2006</option>
                                    <option value="2007">2007</option>
                                    <option value="2008">2008</option>
                                    <option value="2009">2009</option>
                                    <option value="2010">2010</option>
                                    <option value="2011">2011</option>
                                    <option value="2012">2012</option>
                                    <option value="2013">2013</option>
                                    <option value="2014">2014</option>
                                    <option value="2015">2015</option>
                                    <option value="2016">2016</option>
                                    <option value="2017">2017</option>
                                    <option value="2018">2018</option>
                                    <option value="2019">2019</option>
                                    <option value="2020">2020</option>
                                    <option value="2021">2021</option>
                                    <option value="2022">2022</option>
                                    <option value="2023">2023</option>
                                    <option value="2024">2024</option>
                                    <option value="2025">2025</option>
                                    <option value="2026">2026</option>
                                    <option value="2027">2027</option>
                                    <option value="2028">2028</option>
                                    <option value="2029">2029</option>
                                    <option value="2030">2030</option>
                                </select> 年

                                <select name="month">
                                    <option value="0">月選択</option>
                                    <option value="1">1</option>
                                    <option value="2">2</option>
                                    <option value="3">3</option>
                                    <option value="4">4</option>
                                    <option value="5">5</option>
                                    <option value="6">6</option>
                                    <option value="7">7</option>
                                    <option value="8">8</option>
                                    <option value="9">9</option>
                                    <option value="10">10</option>
                                    <option value="11">11</option>
                                    <option value="12">12</option>
                                </select> 月

                                <select name="day">
                                    <option value="0">日選択</option>
                                    <option value="1">1</option>
                                    <option value="2">2</option>
                                    <option value="3">3</option>
                                    <option value="4">4</option>
                                    <option value="5">5</option>
                                    <option value="6">6</option>
                                    <option value="7">7</option>
                                    <option value="8">8</option>
                                    <option value="9">9</option>
                                    <option value="10">10</option>
                                    <option value="11">11</option>
                                    <option value="12">12</option>
                                    <option value="13">13</option>
                                    <option value="14">14</option>
                                    <option value="15">15</option>
                                    <option value="16">16</option>
                                    <option value="17">17</option>
                                    <option value="18">18</option>
                                    <option value="19">19</option>
                                    <option value="20">20</option>
                                    <option value="21">21</option>
                                    <option value="22">22</option>
                                    <option value="23">23</option>
                                    <option value="24">24</option>
                                    <option value="25">25</option>
                                    <option value="26">26</option>
                                    <option value="27">27</option>
                                    <option value="28">28</option>
                                    <option value="29">29</option>
                                    <option value="30">30</option>
                                    <option value="31">31</option>
                                </select> 日
                            </td>
                        </tr>
                        <tr>
                            <td class="title">性別</td>
                            <td>
                                <div class="multi-way-choice">

                                    <input type="radio" id="radio-1" name="switch_1" value="0" >
                                    <label for="radio-1">男性</label>

                                    <input type="radio" id="radio-2" name="switch_1" value="1" >
                                    <label for="radio-2">女性</label>

                                    <input type="radio" id="radio-3" name="switch_1" value="2" >
                                    <label for="radio-3">どちらでもない</label>
                                </div>
                            </td>
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
                    <input type="submit" id="regist" value="登録">
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
	    let regist = document.getElementById('regist');
	    let judgeMessageObj = document.getElementById('judgemessage');

	    regist.addEventListener('click', (event) => {
	        if (!formObj.name.value || !formObj.mail.value || !formObj.pw.value || formObj.year.value == 0 || formObj.month.value == 0 || formObj.day.value == 0 || formObj.switch_1.value == "" || !formObj.tell.value || formObj.prefecture.value == 0 || formObj.selected_prefectures.value == "" || formObj.switch_2.value == "") {
	        	event.preventDefault();
	        	errorMessageObj.textContent = '全て入力してください';
	            judgeMessageObj.style.display = "none";
	        } else if (formObj.pw.value.length < 8) {
	        	event.preventDefault();
	            errorMessageObj.textContent = 'パスワードは8桁以上入力してください。';
	            judgeMessageObj.style.display = "none";
	        }
	    });
    </script>

    <script src="/E4/js/prefecture.js"></script>
</body>
</html>