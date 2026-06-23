<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/login.css"> 
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>ログイン</title>
</head>
<body>
    <!--ヘッダー-->
    <header class="header">
      <div class="header-logo">
        <img src="img/image.png" alt="ロゴ">
      </div>
    </header>
    <!--ヘッダーここまで-->
<!--メイン-->
<main>
<form  id="login_form" method="POST" action="/f1/LoginServlet">

    <!--新規登録へのリンク-->
    <div class=regist><a href="/f1/UserServlet">新規登録はこちら</a></div>
      <!-- addressとpassword入力のテキストボックス -->
      <div class="email">メールアドレス</div>
          	<input type="text" name="address"  placeholder="Email" class="box" id= "address" ><br>
			<div class="pass">パスワード</div>
          	<input type="password" name="password" placeholder="Password" class="box"  name="pw" id= "pw"><br>
          	<span style="color: red;" id="prefecture_error"></span><br>
      <!-- リセット・ログインボタン -->
      <div class="button-area">
      <input type="reset" name="reset" value="Reset" class="reset">
      <input type="submit" name="login" value="Login" class="login">
      </div>
      <span style="color: red;" id="error_message"></span><br>
      <p id="msg"></p>
</form>
<!--フッター-->
    <footer class="footer">
    <p class="copyright">&copy;  All rights reserved.</p>
    </footer>
</main>
<script>
    'use strict';

    document.getElementById('login_form').onsubmit = function(event){
        let address = document.getElementById('login_form').address.value;
        let pw = document.getElementById('login_form').pw.value;
        let errorMessage = document.getElementById('login_form').error_message.value;
        if(address === '' && pw === ''){
        	document.getElementById('msg').textContent  = '※メールアドレスとパスワードを入力してください。';
            address.style.backgroundColor = '#FACAC8';
		    pw.style.backgroundColor = '#FACAC8';
            event.preventDefault();
        }
        else if(address === ''){
        	document.getElementById('msg').textContent  = '※メールアドレスを入力してください。';
            address.style.backgroundColor = '#FACAC8';
		    pw.style.backgroundColor = '#fffdce';
            event.preventDefault();
        }
        else if(pw === ''){
        	document.getElementById('msg').textContent = '※パスワードを入力してください。';
            address.style.backgroundColor = '#fffdce';
		    pw.style.backgroundColor = '#FACAC8';
            event.preventDefault();
        }
      }
</script>
</body>
</html>