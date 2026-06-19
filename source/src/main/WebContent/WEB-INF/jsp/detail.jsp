<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <title>Detail | GoodBuy</title>
    <link rel="stylesheet" href="css/detail.css">
</head>
<body>
	<!--ヘッダー-->
        <header>
            <a href="home"><!--homeへ戻るリンクに書き換える-->>
                <img src="img/logo.png" width="150" height="130" alt="ロゴ">
            </a>
        </header>

        <!--メイン-->
        <main>
            <h2>詳細表示</h2>
            <h1>マルナカ円座店<!--店舗名--></h1>

            <!--店舗アピール（長）-->
            <p>毎週恒例！火曜市！今週もお買い得！旬のトマト・きゅうり・玉ねぎが火曜市限定の特別価格で登場。さらに、メイン料理に欠かせない国産鶏肉が驚きの火曜市価格！

            <!--メモ欄-->
            <label for="memo">メモ</label><br>
            <textarea name="memo"></textarea>
            <p><input type="submit" value="更新"></p>
            
            <!--レシピ-->
            <h3>オムライス<!--料理名--></h3>
            <ul>
                <li>卵 2個</li>
                <li>ケチャップ 適量</li>
                <li>白米 1合</li>
            </ul>

            <!--レシピに必要な目玉商品-->
            <h3>必要目玉商品</h3>
            <p>
                <!--代替レシピのためのチェックボックス-->
                <input type="checkbox" name="featuredItems" value="egg">卵<br>
                <input type="checkbox" name="featuredItems" value="chicken">鶏肉<br>
                <input type="checkbox" name="featuredItems" value="onion">玉ねぎ<br>
            </p>
            <!--代替ボタン-->
            <p>
                <input type="submit" value="代替商品">
                <input type="submit" value="代替レシピ">
            </p>

            <!--そのほかの目玉商品-->
            <h3>目玉商品一覧</h3>
            <ul>
                <!--CSSで箇条書きの点を削除する-->
                <li>リンゴ</li>
                <li>水</li>
                <li>キャベツ</li>
                <li>洗剤</li>
                <li>歯磨き粉</li>
            </ul>

        </main>

        <!--フッター-->
        <footer>
            <a href="#top"><img src="img/gotop.svg" alt="ページトップへ戻る"></a>
            <p>&copy; COSMOS all right reserved.</p>
        </footer>
</body>
</html>