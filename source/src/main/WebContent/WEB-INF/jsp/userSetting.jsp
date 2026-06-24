<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>ユーザー設定</title>
    <link rel="stylesheet" type="text/css" href="css/userSetting.css">
</head>
<body>
<body>
    <div id="top">
    <!--ヘッダー-->
    <header class="header">
      <div class="hedder-logo">
        <img src="img/image.png" alt="ロゴ">
      </div>
      <nav class="nav">
    <h3>ユーザー設定</h3>
	</nav>
	</header>
    </div>
     <!--※mainタグ消すと多分レスポンシブなくなります-->
    <main>
      <div class="">
        <p class="user">
        <form method="POST" action="/f1/UserSettingServlet" id="usersetting_form">
            <span style="color: red;" id="prefecture_error"></span><br>
          	<div class="prefecture">都道府県</div>
          	<div class="prefectureA">(対応地域は順次拡大予定)</div>
          	<select class="box" name="prefecture_id" id="prefectureBox">
            	<option value="">選択してください</option>
            	<option value="15">新潟県</option>	<!-- valueの部分が登録される予定 -->
            	<option value="37">香川県</option>
            	<option value="43">熊本県</option>
            	<!-- 
            	<c:forEach var="p" items="${prefectureList}">
                	<option value="${p.prefecture_name}">${p.prefecture_name}</option>
            	</c:forEach>
            	 -->
          	</select><br>
          	<input type="button" name="submit" value="位置情報で店舗を検索する" class="info"><br>
          	<!--  地図API 
          	<script src="https://unpkg.com/leaflet@1.9.4/dist/leaflet.js"></script>
    		<style>#map { height: 250px; width: 50%; margin-left:auto; margin-right:auto;}</style>
          	
          	<div id="map"></div>
          	-->
          	
          	
          	<div id="content-15" class="content-area">
          		<p>登録する店舗を1つ選択してください</p>
          		<label><input type="radio" name="phone_number" value="0252452533"> イオンとやの店</label><br>
				<label><input type="radio" name="phone_number" value="0252473455"> 原信柴竹山店</label><br>
				<label><input type="radio" name="phone_number" value="0252487707"> 原信柴南万代店</label><br>
				<label><input type="radio" name="phone_number" value="0252801400"> マルイ女池店</label><br>
				<label><input type="radio" name="phone_number" value="0252812600"> ウオロク出来島店</label><br>
          	</div>
          	
          	<div id="content-37" class="content-area">
          		<p>登録する店舗を1つ選択してください</p>
          		<label><input type="checkbox" name="phone_number" value="0878117001"> マルナカサンポート店</label><br>
				<label><input type="checkbox" name="phone_number" value="0878211227"> エースワン JR高松オルネ店</label><br>
				<label><input type="checkbox" name="phone_number" value="0878227498"> マルナカ通町店</label><br>
				<label><input type="checkbox" name="phone_number" value="0878407350"> ラ・ムー高松東店</label><br>
				<label><input type="checkbox" name="phone_number" value="0878433025"> マルナカ通屋島店</label><br>
				<label><input type="checkbox" name="phone_number" value="0878437726"> マルナカパワーシティ屋島店</label><br>
				<label><input type="checkbox" name="phone_number" value="0878512969"> マルナカ広場店</label><br>
          	</div>
          	
          	<div id="content-43" class="content-area">
          		<p>登録する店舗を1つ選択してください</p>
          		<label><input type="checkbox" name="phone_number" value="0962276693"> ハローデイアミュプラザくまもと店</label><br>
				<label><input type="checkbox" name="phone_number" value="0962271221"> ドラッグストアコスモス田崎店</label><br>
				<label><input type="checkbox" name="phone_number" value="0963125566"> スーパーキッド熊本駅前店</label><br>
				<label><input type="checkbox" name="phone_number" value="0963426384"> KITANO ACEアミュプラザくまもと店</label><br>
          	</div>
          	
          	<script>
    		/*店舗表示*/
    		const selectElement = document.getElementById('prefectureBox');
            const contentAreas = document.querySelectorAll('.content-area'); 
            selectElement.addEventListener('change', (event) => {
                // 一度すべてのコンテンツを非表示にする
                contentAreas.forEach(area => {
                    area.classList.remove('is-active');
                });
                // 選択された値（value）に対応する要素を表示する
                const selectedValue = event.target.value;
                if (selectedValue) {
                    const targetContent = document.getElementById('content-'+selectedValue);
                    if (targetContent) {
                    	console.log('てすと');
                        targetContent.classList.add('is-active');
                    }
                }
            });
          	</script>
          	
			<input type="submit" name="submit" value="保存" class="reg" onclick="return confirm('登録内容を変更します。よろしいですか？');"><br>
			<a href="/f1/HomeServlet" class="backHome">ホーム画面へ戻る</a>
		</form>
		</div>

    </main>
    <!--フッター-->
    <footer class="footer">
      <div class="gotop">
        <a href="#top"><img id = "trap" src="img/gotop.svg" alt="ページトップへ戻る"></a>
      </div>
      <p class="copyright">&copy;  All rights reserved.</p>
    </footer>
    <!--フッターここまで-->
    <script>
    'use strict';    	
		/* HTML要素をオブジェクトとして取得する */
		let formObj = document.getElementById('usersetting_form');
		let prefecture_errorMessageObj = document.getElementById('prefecture_error');
		let prefecture = document.getElementById('prefectureBox');
		
		/* [登録]ボタンをクリック時の処理 (アドレス・パスワード) */
		formObj.onsubmit = function(event) {
		  /*(都道府県)*/
		  if (formObj.prefecture_id.value === ''){
			  prefecture_errorMessageObj.textContent = '※都道府県を選択してください！';
			  event.preventDefault();
		  }else{
			prefecture_errorMessageObj.textContent ="";
		}
		};
    </script>
</body>
</html>