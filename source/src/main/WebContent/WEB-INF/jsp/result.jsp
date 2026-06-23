<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>ホーム</title>
    <link rel ="stylesheet" href="css/user.css">
    <link rel="stylesheet" href="https://unpkg.com/leaflet@1.9.4/dist/leaflet.css" />
</head>
<body>
    <div id="top">
    <!--ヘッダー-->
    <header class="header">
      <div class="hedder-logo">
        <img src="img/image.png" alt="ロゴ">
      </div>
      <nav class="nav">
        <h3>新規ユーザー登録</h3>
      </nav>
    </header>
    </div>
    <!--ヘッダーここまで-->
    <!-- メイン（ここから） -->
    <main>
		<h2><c:out value="${result.title}" /></h2>
		<p><c:out value="${result.message}" /></p>
		<a href="${result.backTo}">戻る</a>
	</main>
    <!--メインここまで-->
    <!--フッター-->
        <footer class="footer">
            <div class="gotop">
                <a href="#top"><img id = "trap" src="img/gotop.svg" alt="ページトップへ戻る"></a>
            </div>
            <p class="copyright">&copy; 名刺管理委員会. All rights reserved.</p>
        </footer>
    <!--フッターここまで-->
    <script>
        'use strict';

    </script>
</body>
</html>
