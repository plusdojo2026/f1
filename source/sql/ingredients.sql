create table ingredients(
ingredients_id INT,
ingredients_name varchar(100),
recipe_id INT,
PRIMARY KEY (ingredients_id,recipe_id),
FOREIGN KEY (recipe_id) REFERENCES recipes(recipe_id)
);