CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(32) NOT NULL,
    prefecture_id INT NOT NULL,
    memo TEXT,
    FOREIGN KEY (prefecture_id) REFERENCES prefectures(prefecture_id)
);

INSERT INTO users (
    user_id,
    email,
    password,
    prefecture_id,
    memo
)
VALUES
(
    1,
    'sato@example.com',
    'pass1234',
    15,
    'イオンとやの店をよく利用。牛乳と冷凍食品の価格をチェックしたい。'
),
(
    2,
    'tanaka@example.com',
    'pass1234',
    15,
    '原信南万代店とウオロクを比較したい。'
),
(
    3,
    'suzuki@example.com',
    'pass1234',
    37,
    'ラ・ムー高松東店をメイン利用。安い日用品も確認したい。'
),
(
    4,
    'yamada@example.com',
    'pass1234',
    37,
    'エースワンJR高松オルネ店をよく使う。'
),
(
    5,
    'kobayashi@example.com',
    'pass1234',
    43,
    'スーパーキッド熊本駅前店で食料品をよく買う。'
),
(
    6,
    'nakamura@example.com',
    'pass1234',
    43,
    'ハローデイアミュプラザくまもと店の惣菜をよく買う。'
);