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
      <span style="color: red;" id="error_message"></span><br>
      <div class="email">メールアドレス</div>
          	<input type="text" name="address"  placeholder="Email" class="box" id= "address" pattern="[A-Za-z0-9@.+_]{1,100}" title="英数字, @, ., +, _ のみ,100文字以内で入力してください"><br>
			<div class="pass">パスワード</div>
          	<input type="password" name="password" placeholder="Password" class="box"  id= "password" pattern="[A-Za-z0-9@.+_]{1,32}" title="英数字, @, ., +, _ のみ,32文字以内で入力してください"><br>
       
      <!-- リセット・ログインボタン -->
      <div class="button-area">
      <input type="reset" name="reset" value="Reset" class="reset">
      <input type="submit" name="login" value="Login" class="login">
      </div>
</form>
<!--フッター-->
    <footer class="footer">
    <p class="copyright">&copy;  All rights reserved.</p>
    </footer>
</main>
<script>
    'use strict';

    	let formObj = document.getElementById('login_form');
		let errorMessageObj = document.getElementById('error_message');
		let address = document.getElementById('address');
		let password = document.getElementById('password');
		
		formObj.onsubmit = function(event){
        if(formObj.address.value === '' && formObj.password.value === ''){
        	errorMessageObj.textContent  = '※メールアドレスとパスワードを入力してください!';
            address.style.backgroundColor = '#FACAC8';
		    password.style.backgroundColor = '#FACAC8';
            event.preventDefault();
        }
        else if(formObj.address.value === ''){
        	errorMessageObj.textContent  = '※メールアドレスを入力してください!';
            address.style.backgroundColor = '#FACAC8';
		    password.style.backgroundColor = '#fffdce';
            event.preventDefault();
        }
        else if(formObj.password.value === ''){
        	errorMessageObj.textContent = '※パスワードを入力してください!';
            address.style.backgroundColor = '#fffdce';
		    password.style.backgroundColor = '#FACAC8';
            event.preventDefault();
        }
        //古い警告を消す
        else{
        	errorMessageObj.textContent = '';
        	address.style.backgroundColor = '#fffdce';
        	password.style.backgroundColor = '#fffdce';
        }
      }
</script>
</body>
</html>