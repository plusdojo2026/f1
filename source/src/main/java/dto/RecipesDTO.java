package dto;

import java.io.Serializable;

public class RecipesDTO implements Serializable {
	private int recipe_id; 		//レシピID
	private String phone_number; //電話番号
	private String recipe_name;	//料理名
	private String recipe;		//レシピ
	private int total_price;  	//材料の合計金額

	//ｺﾝｽﾄﾗｸﾀ
	public RecipesDTO(int recipe_id, String phone_number, String recipe_name, String recipe,int total_price) {
	super();
	this.recipe_id = recipe_id;
	this.phone_number = phone_number;
	this.recipe_name = recipe_name;
	this.recipe = recipe;
	this.total_price = total_price;
	}

	//引数無しのｺﾝｽﾄﾗｸﾀ
	public RecipesDTO() {
		this.recipe_id = 0;
		this.phone_number = "";
		this.recipe_name = "";
		this.recipe = "";
		this.total_price = 0;
		}

	public int getTotal_price() {
		return total_price;
	}

	public void setTotal_price(int total_price) {
		this.total_price = total_price;
	}

	public int getRecipe_id() {
		return recipe_id;
	}

	public void setRecipe_id(int recipe_id) {
		this.recipe_id = recipe_id;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getRecipe_name() {
		return recipe_name;
	}

	public void setRecipe_name(String recipe_name) {
		this.recipe_name = recipe_name;
	}

	public String getRecipe() {
		return recipe;
	}

	public void setRecipe(String recipe) {
		this.recipe = recipe;
	}
	
}
