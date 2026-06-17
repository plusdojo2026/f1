package dto;

import java.io.Serializable;

public class RecipesDTO implements Serializable {
	private int recipi_id; 		//レシピID
	private int store_id; 		//店舗ID
	private String recipi_name;	//料理名
	private String recipi;		//レシピ

	//ｺﾝｽﾄﾗｸﾀ
	public RecipesDTO(int recipi_id, int store_id, String recipi_name, String recipi) {
	super();
	this.recipi_id = recipi_id;
	this.store_id = store_id;
	this.recipi_name = recipi_name;
	this.recipi = recipi;
	}

	//引数無しのｺﾝｽﾄﾗｸﾀ
	public RecipesDTO() {
		this.recipi_id = 0;
		this.store_id = 0;
		this.recipi_name = "";
		this.recipi = "";
		}

	public int getRecipi_id() {
		return recipi_id;
	}

	public void setRecipi_id(int recipi_id) {
		this.recipi_id = recipi_id;
	}

	public int getStore_id() {
		return store_id;
	}

	public void setStore_id(int store_id) {
		this.store_id = store_id;
	}

	public String getRecipi_name() {
		return recipi_name;
	}

	public void setRecipi_name(String recipi_name) {
		this.recipi_name = recipi_name;
	}

	public String getRecipi() {
		return recipi;
	}

	public void setRecipi(String recipi) {
		this.recipi = recipi;
	}
	
}
