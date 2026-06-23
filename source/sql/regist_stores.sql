CREATE TABLE regist_stores (
    user_id INT,
   phone_number VARCHAR(20),
    PRIMARY KEY (user_id, phone_number),
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (phone_number) REFERENCES stores(phone_number)
);

INSERT INTO regist_stores (
    user_id,
    phone_number
)
VALUES
-- user 1
(1, '0252452533'),
(1, '0252812600'),
(1, '0252487707'),

-- user 2
(2, '0252487707'),
(2, '0252473155'),
(2, '0252801400'),

-- user 3
(3, '0878407350'),
(3, '0878437726'),
(3, '0878433025'),

-- user 4
(4, '0878211227'),
(4, '0878117001'),
(4, '0878227498'),

-- user 5
(5, '0963125566'),
(5, '0962771221'),

-- user 6
(6, '0962276693'),
(6, '0963426384'),
(6, '0963125566');
