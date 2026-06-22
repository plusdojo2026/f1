package dto;

import java.io.Serializable;

public class HomeDisplayDTO implements Serializable {
	private String store_name;          // 店舗名
	private String store_appeal_short;  // 店舗PR(短文)
	private String recipe_name;         // 料理名
	private String featured_item_name;  // 目玉商品名
	private int price;            // 合計金額
	
	public HomeDisplayDTO() {
    }

	//ｺﾝｽﾄﾗｸﾀ
	public HomeDisplayDTO(String store_name, String store_appeal_short, String recipe_name, String featured_item_name, int price) {
	super();
	this.store_name = store_name;
	this.store_appeal_short = store_appeal_short;
	this.recipe_name = recipe_name;
	this.featured_item_name = featured_item_name;
	this.price = price;
	}

	//引数無しのｺﾝｽﾄﾗｸﾀ
	public String getStore_name() {
		return store_name;
	}

	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}

	public String getStore_appeal_short() {
		return store_appeal_short;
	}

	public void setStore_appeal_short(String store_appeal_short) {
		this.store_appeal_short = store_appeal_short;
	}

	public String getRecipe_name() {
		return recipe_name;
	}

	public void setRecipe_name(String recipe_name) {
		this.recipe_name = recipe_name;
	}

	public String getFeatured_item_name() {
		return featured_item_name;
	}

	public void setFeatured_item_name(String featured_item_name) {
		this.featured_item_name = featured_item_name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}
