<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>詳細表示 | GoodBuy</title>
        <link rel="stylesheet" href="detail.css">
    </head>
    <body>
        <!--ヘッダー-->
        <div id="top">
        <header class="header">
            <div class="header-logo">
            <a href="/HomeServlet"><!--homeへ戻るリンクに書き換える-->
                <img src="img/logo.png" width="150" height="130" alt="ロゴ">
            </a>
            </div>
            <nav class="nav">
                <h3>詳細表示</h3>
            </nav>
        </header>
        </div>

        <!--メイン-->
        <main>
        	
            <h1 class="store-name"><c:out value="${detailList.store_name}"/></h1>

            <!--店舗アピール（長）-->
            <p class="appeal-long"><c:out value="${detailList.store_appeal_long}"/></p>

            <!--メモ欄-->
            <form method="post" action="">
            <label for="memo">メモ</label><br>
            <textarea name="memo" class="memo"><c:out value="${detailList.memo}"/></textarea>
            <p class="memo-update"><input type="submit" value="更新" onclick="return confirm('メモを更新します。よろしいですか？');" ></p>
            </form>

            <!--レシピ-->
            <div class="recipe">
            <h3><c:out value="${detailList.recipe_name}"/></h3>
            <ul>
            	<c:forEach var="item" items="${itemList}">
                <li><c:out value="${item.featured_item_name}"/></li>
                </c:forEach>
            </ul>
            </div>

            <form action="ChangeItemServlet" method="post">
            <table class="featuredItems">
                <!--代替レシピのためのチェックボックスあり-->
                <tr>
                    <th>必要目玉商品</th>
                    <th>価格</th>
                    <th>都道府県相場</th>
                    <th>お得金額</th>
                </tr>
                <c:forEach var="detail" items="${detailList.featuredItems}">
                <tr>
                    <td><input type="checkbox" name="featuredItems" value="${detail.featured_item_id}"><c:out value="${detail.featured_item_name}"/></td>
                    <td class="price item_price"><c:out value="${detail.price}"/>円</td>
                    <td class="price average_price"><c:out value="${detail.average_price}"/>円</td>
                    <td class="price gain"></td> <!-- gainはJavaScriptで計算した値を表示する -->
                </tr>
                </c:forEach>
            </table>
            
            <!--代替ボタン-->
            <p class="change-button">
                <!-- <input type="submit" value="代替商品" onclick="return confirm('チェックした商品を代替します。よろしいですか？');" class="change-items">-->
                <input type="submit" value="代替レシピ" onclick="return confirm('代替レシピを表示します。よろしいですか？');" class="change-recipe" action="/">
            </p>
            </form>

            <!--そのほかの目玉商品-->
            <table>
                <tr>
                    <th>目玉商品一覧</th>
                    <th>価格</th>
                    <th>都道府県相場</th>
                    <th>お得金額</th>
                </tr>
                <c:forEach var="detail" items="${detailList}">
                <tr>
                    <td><c:out value="${detail.featured_item_name}"/></td>
                    <td class="price item_price"><c:out value="${detail.price}"/>円</td>
                    <td class="price average_price"><c:out value="${detail.average_price}"/>円</td>
                    <td class="price gain"></td>
                </tr>
                </c:forEach>
            </table>

        </main>

        <!--フッター-->
        <footer class="footer">
            <div class="gotop">
                <a href="#top"><img id = "trap" src="img/gotop.svg" alt="ページトップへ戻る"></a>
            </div>
            <p class="copyright">&copy; COSMOS All rights reserved.</p>
        </footer>
        
        <!--JavaScript-->
        <script>
        	'use strict';

            // 全ての行を取得
            const rows = document.querySelectorAll(".featuredItems tr");

            rows.forEach(row => {
                const priceEl = row.querySelector(".item_price");
                const avgEl   = row.querySelector(".average_price");
                const gainEl  = row.querySelector(".gain");

                if (priceEl && avgEl && gainEl) {
                	//取得した文字列から"円"を取り除き、数値に変換する
                    const price = parseInt(priceEl.textContent.replace("円", ""));
                    const average = parseInt(avgEl.textContent.replace("円", ""));

                    const gain = price - average;

                    // HTML に書き込む
                    gainEl.textContent = gain + "円";
                }
            });
        	
        </script>
        
    </body>
</html>