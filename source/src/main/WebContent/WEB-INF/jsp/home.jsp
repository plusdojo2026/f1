<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GoodBuy | 店舗表示</title>
<link rel="stylesheet" type="text/css" href="css/home.css">
<body>
	<div class="wrapper">
		<!--ヘッダー-->
		<h1 id="top">
			<img src="img/image.png" width="150" height="130" alt="ロゴ"></a>
		</h1>
		<!--メイン-->
		<h2>店舗表示</h2>
		<rabel for="memo">メモ</rabel>
		<br>
		<textarea name="memo"></textarea>

		<p class="regist_store"></p>
		<form class="" action="">
			<div class="store_name">
				<a href="marunaka.html" class="button">マルナカ円座店</a>
			</div>
			<div class="row">
				<div class="cook_name">オムライス</div>
				<div class="ingredients">卵、鶏肉、玉ねぎ</div>
				<div class="total_price">¥532</div>
			</div>
			<div class="pr">毎週火曜！火曜市！</div>
		</form>

		<p class="regist_store"></p>
		<form class="" action="">
			<div class="store_name">
				<a href="hallows.html" class="button">ハローズ円座店</a>
			</div>
			<div class="row">
				<div class="cook_name">焼きそば</div>
				<div class="ingredients">中華麺、豚肉、キャベツ</div>
				<div class="total_price">¥610</div>
			</div>
			<div class="pr">まとめ買いが断然お得！！！</div>
		</form>

		<p class="regist_store"></p>
		<form class="" action="">
			<div class="store_name">
				<a href="cosmos.html" class="button">コスモス円座店</a>
			</div>
			<div class="row">
				<div class="cook_name">親子丼</div>
				<div class="ingredients">卵、玉ねぎ、めんつゆ</div>
				<div class="total_price">¥780</div>
			</div>
			<div class="pr">日用品がオススメ</div>
		</form>

		<div class="menu-wrapper">
			<input type="checkbox" id="menu-toggle" hidden> <label
				class="menu-icon" for="menu-toggle"> <span></span> <!--ハンバーガーメニューの3本線-->
				<span></span> <span></span>
			</label>

			<div class="overlay"></div>

			<nav class="menu">
				<div class="menu-logo">
					<img src="img/logo.png" alt="サイトロゴ">
				</div>
				<ul>
					<li><a href="#">店舗表示</a></li>
					<li><a href="#">ユーザー設定</a></li>
					<li><a href="#" onclick="return confirm('ログアウトします。よろしいですか？');">ログアウト</a></li>
				</ul>
			</nav>
		</div>
		<!--ハンバーガーメニュー-->
		<div id="top">
			<!--フッター-->
			<footer class="footer">
				<div class="gotop">
					<a href="#top"><img id="trap" src="img/gotop.svg"
						alt="ページトップへ戻る"></a>
				</div>
				<p class="copyright">&copy; All rights reserved.</p>
			</footer>
</body>
</html>