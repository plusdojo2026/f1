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
			<img src="img/image.png" alt="ロゴ"></a>
		</h1>
		<!--メイン-->
		<h2>店舗表示</h2>
		<rabel for="memo">メモ</rabel>
		<br>
		<div class="memo-box">
			<textarea class="fixed-box" name="memo"></textarea>
			<div class="button-area">
				<button class="update-button">更新</button>
			</div>
		</div>

		<c:forEach var="e" items="${storeList}">
			<form id="search_result_form" method="POST"
				action="/webapp/HomeServlet">

				<!-- 店舗名 -->
				<div class="store_name">
					<a href="storeDetail.jsp?number=${e.number}" class="button">
						${e.storeName} </a>
				</div>

				<!-- row（料理名・目玉商品名・合計金額） -->
				<div class="row">
					<div class="recipe_name">${e.recipeName}</div>
					<div class="featured_item_name">${e.featuredItem}</div>
					<div class="total_price">¥${e.totalPrice}</div>
				</div>

				<!-- 店舗PR -->
				<div class="store_appeal_short">${e.shortPr}</div>
			</form>
		</c:forEach>

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
					<li><a href="/f1/DetailServlet">店舗表示</a></li>
					<li><a href="/f1/UserSettingServlet">ユーザー設定</a></li>
					<li><a href="/f1/LoginServlet" onclick="return confirm('ログアウトします。よろしいですか？');">ログアウト</a></li>
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