CREATE TABLE recipes (
    recipe_id INT AUTO_INCREMENT PRIMARY KEY,
    phone_number VARCHAR(20)NOT NULL,
    recipe_name VARCHAR(100) NOT NULL,
    recipe text,
    FOREIGN KEY (phone_number) REFERENCES stores(phone_number)
);
