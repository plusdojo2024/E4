<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" href="/E4/css/login.css">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login | IGNITE</title>
</head>
<body>
  <div class="loginbox">
    <div class="logoarea">
      <img src="img/loginlogo.png" class="logo" alt="ignitelogo" width="150" >
    </div>
    <form id="login_form" method="post" action="/E4/LoginServlet">
      <div class="input-box">
        <input type="text" name="mailAddress" placeholder="メールアドレス">
      </div>
      <div class="input-box">
        <input type="password" name="password" placeholder="パスワード">
      </div>
      <p class="forgot-password">
        <a href="#" class="forgot-password-a">パスワードをお忘れですか？</a>
      </p>

      <div class="message" style="color:red;">${error}</div>

      <div class="loginbuttun"><input type="submit" id="loginbuttun" value="ログイン"></div>

      <div class="register">
        <a href="#">初めての方はこちら</a>
      </div>
    </form>
  </div>
</body>
</html>