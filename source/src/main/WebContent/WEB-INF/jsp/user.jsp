<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>ホーム</title>
    <link rel ="stylesheet" href="css/user.css">
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
    <!--※mainタグ消すと多分レスポンシブなくなります-->
    <main>
      <div class="">
        <p class="user">
        <form method="POST" action="" class="" id="regist_form">
        <span style="color: red;" id="error_message"></span><br>
			<div class="email">メールアドレス</div>
          	<input type="text" name="address"  placeholder="Email" class="box" id= "address" ><br>
			<div class="pass">パスワード</div>
          	<input type="password" name="passwprd" placeholder="32文字以内" class="box"  name="pw" id= "pw"><br>
          	<span style="color: red;" id="prefecture_error"></span><br>
          	<div class="prefecture">都道府県(対応地域は順次拡大予定)</div>
          	<select class="box" name="prefecture" id="prefectureBox">
            	<option value="">選択してください</option>
            	<option value="15">新潟県</option>
            	<option value="37">香川県</option>
            	<option value="43">熊本県</option>
            	<!-- 
            	<c:forEach var="prefecture" items="${prefectureList}">
                	<option value="${prefecture}">${prefecture}</option>
            	</c:forEach>
            	 -->
          	</select><br>
          	<input type="button" name="submit" value="位置情報で店舗を検索する" class="info"><br>
          	
          	<div id="content-15" class="content-area">
          		<label><input type="checkbox" name="phone_number" value="0252452533"> イオンとやの店</label><br>
				<label><input type="checkbox" name="phone_number" value="0252473455"> 原信柴竹山店</label><br>
				<label><input type="checkbox" name="phone_number" value="0252487707"> 原信柴南万代店</label><br>
				<label><input type="checkbox" name="phone_number" value="0252801400"> マルイ女池店</label><br>
				<label><input type="checkbox" name="phone_number" value="0252812600"> ウオロク出来島店</label><br>
          	</div>
          	
          	<div id="content-37" class="content-area">
          		<label><input type="checkbox" name="phone_number" value="0878117001"> マルナカサンポート店</label><br>
				<label><input type="checkbox" name="phone_number" value="0878211227"> エースワン JR高松オルネ店</label><br>
				<label><input type="checkbox" name="phone_number" value="0878227498"> マルナカ通町店</label><br>
				<label><input type="checkbox" name="phone_number" value="0878407350"> ラ・ムー高松東店</label><br>
				<label><input type="checkbox" name="phone_number" value="0878433025"> マルナカ通屋島店</label><br>
				<label><input type="checkbox" name="phone_number" value="0878437726"> マルナカパワーシティ屋島店</label><br>
				<label><input type="checkbox" name="phone_number" value="0878512969"> マルナカ広場店</label><br>
          	</div>
          	
          	<div id="content-43" class="content-area">
          		<label><input type="checkbox" name="phone_number" value="0962276693"> ハローデイアミュプラザくまもと店</label><br>
				<label><input type="checkbox" name="phone_number" value="0962271221"> ドラッグストアコスモス田崎店</label><br>
				<label><input type="checkbox" name="phone_number" value="0963125566"> スーパーキッド熊本駅前店</label><br>
				<label><input type="checkbox" name="phone_number" value="0963426384"> KITANO ACEアミュプラザくまもと店</label><br>
          	</div>
          	
			<input type="submit" name="submit" value="登録" class="reg" onclick="return confirm('登録します。よろしいですか？');"><br>
			<a href="/webContent/LoginServlet" class="backLogin">ログイン画面へ戻る</a>
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
		let formObj = document.getElementById('regist_form');
		let errorMessageObj = document.getElementById('error_message');
		let prefecture_errorMessageObj = document.getElementById('prefecture_error');
		let address = document.getElementById('address');    //変数宣言せずに「document.getElementById('id1')」を打ち込んでもよい
		let pw = document.getElementById('pw');
		let prefecture = document.getElementById('prefectureBox');
		
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
		
		/* [登録]ボタンをクリック時の処理 (アドレス・パスワード) */
		formObj.onsubmit = function(event) {
		  if (formObj.address.value === '' && formObj.pw.value === '') {
		    errorMessageObj.textContent = '※メールアドレスとパスワードを入力してください！';
		    address.style.backgroundColor = '#FACAC8';
		    pw.style.backgroundColor = '#FACAC8';
		    event.preventDefault();
		  } else if(formObj.address.value === '') {
		    errorMessageObj.textContent = '※メールアドレスを入力してください！';
		    address.style.backgroundColor = '#FACAC8';
		    pw.style.backgroundColor = '#fffdce';
		    event.preventDefault();
		  } else if(formObj.pw.value === ''){
		    errorMessageObj.textContent = '※パスワードを入力してください！';
		    address.style.backgroundColor = '#fffdce';
		    pw.style.backgroundColor = '#FACAC8';
		    event.preventDefault();
		  }else{}
		  /*(都道府県)*/
		  if (formObj.prefecture.value === ''){
			  prefecture_errorMessageObj.textContent = '※都道府県を選択してください！';
			  event.preventDefault(); /*選択されていない場合は送信を止める*/
		}else{
			prefecture_errorMessageObj.textContent ="";
		}
		};
    </script>
</body>
</html>
