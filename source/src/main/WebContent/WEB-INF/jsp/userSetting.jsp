<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>GoodBuyスコア</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>

<div class="container">
    <h2>GoodBuyスコア</h2>

    <div class="score-section">
        <div class="score-box">
            <label>月間お得額</label>
            <div class="value">500 円</div>
            <label>今月の代替回数</label>
            <div class="count">1 回</div>
        </div>
        <div class="score-box">
            <label>年間お得額</label>
            <div class="value">5000 円</div>
            <label>今年の代替回数</label>
            <div class="count">10 回</div>
        </div>
    </div>

    <hr>

    <div class="user-settings">
        <h3>ユーザー設定</h3>
        <button class="save-btn">保存</button>

        <div class="form-group">
            <label>お住まいの都道府県</label>
            <select>
                <option>香川県</option>
            </select>
        </div>

        <div class="form-group">
            <label>登録店舗</label>
            <button class="location-btn">位置情報を取得</button>
            <div class="store-list-area"></div> </div>
    </div>

    <div class="footer-actions">
        <button class="save-btn">保存</button>
    </div>
</div>

</body>
</html>