<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/webapp/css/login.css"> 
<title>ログイン</title>
</head>
<body>
    <!--ヘッダー-->
    <header>
        <div>
        <h1 id="image">
            <a><img src="/WebContent/img/image.png" alt="GOODBUY"></a>
        </h1>
        </div>
    </header>
    <!--ヘッダーここまで-->
<!--メイン-->
<main>
<form  id="login_form" method="POST" action="/webapp/LoginServlet">
    <table class="table">
    <!--新規登録へのリンク-->
    <div><a href="/WebContent/UserServlet">新規登録はこちら</a></div>
      <!-- emailとpasswordp入力のテキストボックス -->
      <tr>
        <td>
        <div>
          <input type="text" name="adress" placeholder="Email">
        </div>
        </td>
      </tr>
      <tr>
        <td>
          <div>
          <input type="password" name="pw" placeholder="Password">
          </div>
        </td>
      </tr>
      <!-- リセット・ログインボタン -->
      <tr>
        <td colspan="2">
          <input type="submit" name="reset" value="リセット">
          <input type="submit" name="login" value="ログイン">
          <span id="error_message"></span>
        <td>
      </tr>
    </table>
     <p id="msg"></p>
</form>
</main>
<script>
    'use strict';

    document.getElementById('login_form').onsubmit = function(event){
        let adress = document.getElementById('login_form').adress.value;
        let pw = document.getElementById('login_form').pw.value;
        if(adress === '' && pw === ''){
            document.getElementById('msg').textContent = 'メールアドレスとパスワードを入力してください。';
            event.preventDefault();
        }
        else if(adress === ''){
            document.getElementById('msg').textContent = 'メールアドレスを入力してください。';
            event.preventDefault();
        }
        else if(pw === ''){
            document.getElementById('msg').textContent = 'パスワードを入力してください。';
            event.preventDefault();
        }
      }
</script>
</body>
</html>