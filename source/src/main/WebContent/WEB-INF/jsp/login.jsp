<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/webapp/css/login.css"> 
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>ログイン</title>
</head>
<body>
    <!--ヘッダー-->
    <header class="header">
      <div class="hedder-logo">
        <img src="images/image.png" alt="ロゴ">
      </div>
    </header>
    <!--ヘッダーここまで-->
<!--メイン-->
<main>
<form  id="login_form" method="POST" action="/WebContent/LoginServlet">

    <!--新規登録へのリンク-->
    <div class=regist><a href="/WebContent/UserServlet">新規登録はこちら</a></div>
      <!-- emailとpasswordp入力のテキストボックス -->
      <table class="table">
      <tr>
        <td>
        <div class="email">
          <input type="text" name="email" placeholder="Email">
        </div>
        </td>
      </tr>
      <tr>
        <td>
          <div class="pass">
          <input type="password" name="pw" placeholder="Password">
          </div>
        </td>
      </tr>
      <!-- リセット・ログインボタン -->
      <tr>
        <td colspan="2">
          <input type="submit" name="reset" value="リセット" class="reset">
          <input type="submit" name="login" value="ログイン" class="login">
          <span id="error_message"></span>
        <td>
      </tr>
    </table>
     <p id="msg"></p>
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
        let adress = document.getElementById('email').email.value;
        let pw = document.getElementById('pw').pw.value;
        if(email === '' && pw === ''){
            document.getElementById('msg').textContent = 'メールアドレスとパスワードを入力してください。';
            address.style.backgroundColor = '#FACAC8';
		    pw.style.backgroundColor = '#FACAC8';
            event.preventDefault();
        }
        else if(email === ''){
            document.getElementById('msg').textContent = 'メールアドレスを入力してください。';
            address.style.backgroundColor = '#FACAC8';
		    pw.style.backgroundColor = '#fffdce';
            event.preventDefault();
        }
        else if(pw === ''){
            document.getElementById('msg').textContent = 'パスワードを入力してください。';
            address.style.backgroundColor = '#fffdce';
		    pw.style.backgroundColor = '#FACAC8';
            event.preventDefault();
        }
      }
</script>
</body>
</html>