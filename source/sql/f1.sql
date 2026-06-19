CREATE TABLE prefectures (
    prefecture_id INT AUTO_INCREMENT PRIMARY KEY,
    prefecture_name VARCHAR(10) NOT NULL
);

INSERT INTO prefectures (prefecture_name)
VALUES
('北海道'),
('青森県'),
('岩手県'),
('宮城県'),
('秋田県'),
('山形県'),
('福島県'),
('茨城県'),
('栃木県'),
('群馬県'),
('埼玉県'),
('千葉県'),
('東京都'),
('神奈川県'),
('新潟県'),
('富山県'),
('石川県'),
('福井県'),
('山梨県'),
('長野県'),
('岐阜県'),
('静岡県'),
('愛知県'),
('三重県'),
('滋賀県'),
('京都府'),
('大阪府'),
('兵庫県'),
('奈良県'),
('和歌山県'),
('鳥取県'),
('島根県'),
('岡山県'),
('広島県'),
('山口県'),
('徳島県'),
('香川県'),
('愛媛県'),
('高知県'),
('福岡県'),
('佐賀県'),
('長崎県'),
('熊本県'),
('大分県'),
('宮崎県'),
('鹿児島県'),
('沖縄県');


CREATE TABLE stores (
    phone_number VARCHAR(20) PRIMARY KEY,
    store_name VARCHAR(100) NOT NULL,
    prefecture_id INT NOT NULL,
    store_appeal_short  VARCHAR(200),
    store_appeal_long text,
    FOREIGN KEY (prefecture_id) REFERENCES prefectures(prefecture_id)
);

INSERT INTO stores (
    phone_number,
    store_name,
    prefecture_id,
    store_appeal_short,
    store_appeal_long
)
VALUES
(
    '0252452533',
    'イオンとやの店',
    15,
    '買い物しやすい大型スーパー',
    '食品から日用品まで幅広く取り扱う地域密着型の店舗です。'
),
(
    '0252812600',
    'ウオロク出来島店',
    15,
    '新鮮な食材が豊富',
    '地元で人気があり、野菜や鮮魚などの生鮮食品が充実しています。'
),
(
    '0252801400',
    'マルイ女池店',
    15,
    '毎日の買い物に便利',
    '地域住民に親しまれ、特売商品も豊富に取り揃えています。'
),
(
    '0878407350',
    'ラ・ムー高松東店',
    37,
    '圧倒的な低価格',
    'ディスカウント価格で食品や日用品を購入できる人気店舗です。'
),
(
    '0878437726',
    'マルナカパワーシティ屋島店',
    37,
    '大型ショッピング施設',
    '豊富な品揃えと広い売場が魅力の店舗です。'
),
(
    '0878433025',
    'マルナカ屋島店',
    37,
    '地域密着型スーパー',
    '地元の利用者が多く、日常の買い物に便利な店舗です。'
),
(
    '0878512969',
    'マルナカ広場店',
    37,
    'アクセス良好',
    '駅や住宅地から利用しやすく、品揃えも充実しています。'
),
(
    '0963125566',
    'スーパーキッド熊本駅前店',
    43,
    '駅近ディスカウントストア',
    '熊本駅近くで手軽に買い物ができる便利な店舗です。'
),
(
    '0962771221',
    'ドラッグストアコスモス田崎店',
    43,
    '医薬品と食品が充実',
    '医薬品だけでなく食品や日用品も低価格で購入できます。'
);

CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(32) NOT NULL,
    prefecture_name VARCHAR(10)NOT NULL,
    phone_number VARCHAR(20),
    memo text,
    FOREIGN KEY(phone_number)
REFERENCES stores(phone_number)
);


CREATE TABLE featured_items (
    featured_item_id INT AUTO_INCREMENT PRIMARY KEY,
    phone_number VARCHAR(20)NOT NULL,
    featured_item_name VARCHAR(100) NOT NULL ,
    ap_name VARCHAR(100),
    price INT NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    FOREIGN KEY (phone_number) REFERENCES stores(phone_number)
);

INSERT INTO featured_items (
    phone_number,
    featured_item_name,
    ap_name,
    price,
    start_date,
    end_date
)
VALUES
(
    '0252452533',
    '牛乳',
    '豆乳',
    158,
    '2026-06-01',
    '2026-06-30'
),
(
    '0252812600',
    '卵',
    '納豆',
    198,
    '2026-06-01',
    '2026-06-30'
),
(
    '0252801400',
    '食パン',
    'ロールパン',
    108,
    '2026-06-01',
    '2026-06-30'
);


CREATE TABLE average_prices (
    average_price_id INT AUTO_INCREMENT PRIMARY KEY,
    featured_item_id INT NOT NULL,
    prefecture_id INT NOT NULL,
    average_price INT NOT NULL,
    record_date DATE NOT NULL,
    FOREIGN KEY (featured_item_id)REFERENCES featured_items(featured_item_id),
    FOREIGN KEY (prefecture_id) REFERENCES prefectures(prefecture_id)
);

INSERT INTO average_prices (
    featured_item_id,
    prefecture_id,
    average_price,
    record_date
)
VALUES
(1,15,198,'2026-06-01'),
(2,15,248,'2026-06-01'),
(3,15,128,'2026-06-01');


CREATE TABLE recipes (
    recipe_id INT AUTO_INCREMENT PRIMARY KEY,
    phone_number VARCHAR(20)NOT NULL,
    recipe_name VARCHAR(100) NOT NULL,
    recipe text,
    FOREIGN KEY (phone_number) REFERENCES stores(phone_number)
);

CREATE TABLE regist_stores (
    user_id INT,
   phone_number VARCHAR(20),
    PRIMARY KEY (user_id, phone_number),
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (phone_number) REFERENCES stores(phone_number)
);



CREATE TABLE gains (
    gain_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    record_date DATE NOT NULL,
    gain_sum INT NOT NULL DEFAULT 0,
    ap_count INT NOT NULL DEFAULT 0,
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);