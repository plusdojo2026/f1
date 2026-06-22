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
<form  id="login_form" method="POST" action="/WebContent/LoginServlet">

    <!--新規登録へのリンク-->
    <div class=regist><a href="/WebContent/UserServlet">新規登録はこちら</a></div>
      <!-- addressとpasswordp入力のテキストボックス -->
      <div class="email">メールアドレス</div>
          	<input type="text" name="address"  placeholder="Email" class="box" id= "address" ><br>
			<div class="pass">パスワード</div>
          	<input type="password" name="passwprd" placeholder="Password" class="box"  name="pw" id= "pw"><br>
          	<span style="color: red;" id="prefecture_error"></span><br>
      <!-- リセット・ログインボタン -->
      <div class="button-area">
      <input type="submit" name="reset" value="Reset" class="reset">
      <input type="submit" name="login" value="Login" class="login">
      </div>
      <span style="color: red;" id="error_message"></span><br>
</form>
<!--フッター-->
    <footer class="footer">
      <div class="gotop">
        <a href="#top"><img id = "trap" src="img/gotop.svg" alt="ページトップへ戻る"></a>
      </div>
      <p class="copyright">&copy;  All rights reserved.</p>
    </footer>
</main>
<script>
    'use strict';

    document.getElementById('login_form').onsubmit = function(event){
        let adress = document.getElementById('address').address.value;
        let pw = document.getElementById('pw').pw.value;
        let errorMessage = document.getElementById('error_message').error_message.value;
        if(address === '' && pw === ''){
            errorMessage.textContent = '※メールアドレスとパスワードを入力してください。';
            address.style.backgroundColor = '#FACAC8';
		    pw.style.backgroundColor = '#FACAC8';
            event.preventDefault();
        }
        else if(address === ''){
            errorMessage.textContent = '※メールアドレスを入力してください。';
            address.style.backgroundColor = '#FACAC8';
		    pw.style.backgroundColor = '#fffdce';
            event.preventDefault();
        }
        else if(pw === ''){
        	errorMessage.textContent = '※パスワードを入力してください。';
            address.style.backgroundColor = '#fffdce';
		    pw.style.backgroundColor = '#FACAC8';
            event.preventDefault();
        }
      }
</script>
</body>
</html>