<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		<form method="POST" action="/f1/MemoServlet">
			<rabel for="memo">メモ</rabel>
			<br>
			<div class="memo-box">
				<textarea class="fixed-box" name="memo">${usersList[0].memo}</textarea>
				<div class="button-area">
					<input type="submit" class="update-button"></input>
				</div>
			</div>
		</form>

		<c:forEach var="e" items="${storesList}" varStatus="i">
			<form id="search_result_form" method="GET" action="/f1/HomeServlet">

				<!-- 店舗名 -->
				<div class="store_name">
					<a href="/f1/DetailServlet?phone_number=${e.phone_number}"
						class="button"> ${e.store_name} </a>
				</div>

				<!-- row（料理名・材料名・合計金額） -->
				<div class="row">
					<div class="recipe_name">${recipesList[i.index].recipe_name}</div>
					
					<div class="ingredients_name">
						<c:forEach var="f" items="${ingredientsListList[i.index]}">
							${f.ingredients_name} &nbsp;
						</c:forEach>
					</div>
				</div>
				
				<div class="total_price">¥${recipesList[i.index].total_price}</div>

				<!-- 店舗PR -->
				<div class="store_appeal_short">${e.store_appeal_short}</div>
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
					<li><a href="/f1/HomeServlet">店舗表示</a></li>
					<li><a href="/f1/UserSettingServlet">ユーザー設定</a></li>
					<li><a href="/f1/LogoutServlet"
						onclick="return confirm('ログアウトします。よろしいですか？');">ログアウト</a></li>
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
				<p class="copyright">&copy; COSMOS All rights reserved.</p>
			</footer>
</body>
</html>