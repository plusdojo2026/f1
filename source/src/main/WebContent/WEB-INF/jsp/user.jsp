<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>ホーム</title>
    <link rel ="stylesheet" href="menu.css">
</head>
<body>
    <div id="top">
    <!--ヘッダー-->
    <header class="header">
      <div class="hedder-logo">
        <img src="images/image.png" alt="ロゴ">
      </div>
      <nav class="nav">
        <h3>新規ユーザー登録</h3>
      </nav>
    </header>
    </div>
    <!--※mainタグ消すと多分レスポンシブなくなります-->
    <main>
      <div class="">
        <p class="user">
        <form method="POST" action="" class="">
			メールアドレス<br>
          	<input type="text" name="address"  placeholder="Email" class="box"><br>
			パスワード<br>
          	<input type="text" name="passwprd" placeholder="32文字以内" class="box"><br>
          	都道府県<br>
          	<select class="box">
            	<option value="">選択してください</option>
          	</select><br>
          	<input type="submit" name="submit" value="位置情報で店舗を検索する" class="box"><br>
			<input type="submit" name="submit" value="登録" class="box">
		</form>
		</div>

	<a href="/webapp/SearchServlet">ログイン画面へ戻る</a>
    </main>
    <!--フッター-->
    <footer class="footer">
      <div class="gotop">
        <a href="#top"><img id = "trap" src="images/gotop.svg" alt="ページトップへ戻る"></a>
      </div>
      <p class="copyright">&copy;  All rights reserved.</p>
    </footer>
    <!--フッターここまで-->
</body>
</html>
