-- 1. 親テーブル
CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(32) NOT NULL
);

CREATE TABLE prefectures (
    prefecture_id INT AUTO_INCREMENT PRIMARY KEY,
    prefecture_name VARCHAR(10) NOT NULL
);



-- 2. 中間テーブル
CREATE TABLE stores (
    store_id INT AUTO_INCREMENT PRIMARY KEY,
    store_name VARCHAR(100) NOT NULL,
    prefecture_id INT NOT NULL,
    store_appeal_short  VARCHAR(200),
    store_appeal_long text,
    FOREIGN KEY (prefecture_id) REFERENCES prefectures(prefecture_id)
);

-- 3. 外部キー参照があるテーブル
CREATE TABLE featured_items (
    featured_item_id INT AUTO_INCREMENT PRIMARY KEY,
    store_id INT NOT NULL,
    featured_item_name VARCHAR(100) NOT NULL ,
    ap_name VARCHAR(100),
    price INT NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    FOREIGN KEY (store_id) REFERENCES stores(store_id)
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



CREATE TABLE recipes (
    recipe_id INT AUTO_INCREMENT PRIMARY KEY,
    store_id INT,
    recipe_name VARCHAR(100) NOT NULL,
    recipe text,
    FOREIGN KEY (store_id) REFERENCES stores(store_id)
);

CREATE TABLE regist_stores (
    user_id INT,
    store_id INT,
    PRIMARY KEY (user_id, store_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (store_id) REFERENCES stores(store_id)
);

CREATE TABLE user_settings (
    user_id INT PRIMARY KEY,
    prefecture_id INT,
    favorite_store_id INT,
    memo text,
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (prefecture_id) REFERENCES prefectures(prefecture_id),
    FOREIGN KEY (favorite_store_id) REFERENCES stores(store_id)
);

CREATE TABLE gains (
    gain_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    record_date DATE NOT NULL,
    gain_sum INT NOT NULL DEFAULT 0,
    ap_count INT NOT NULL DEFAULT 0,
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);
