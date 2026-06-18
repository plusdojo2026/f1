package dto;

import java.io.Serializable;

public class StoresDTO implements Serializable {
	private int store_id; 		//店舗ID
	private String store_name;		//店舗名
	private String prefecture_id;	//都道府県ID
	private String store_appeal_short; //店舗アピール(短)
	private String store_appeal_long;  //店舗アピール(長)

	//ｺﾝｽﾄﾗｸﾀ
	public StoresDTO(int store_id, String store_name, String prefecture_id, String store_appeal_short, String store_appeal_long) {
	super();
	this.store_id = store_id;
	this.store_name = store_name;
	this.prefecture_id = prefecture_id;
	this.store_appeal_short = store_appeal_short;
	this.store_appeal_long = store_appeal_long;
	}

	public StoresDTO() {
	this.store_id = 0;
	this.store_name = "";
	this.prefecture_id = "";
	this.store_appeal_short = "";
	this.store_appeal_long = "";
	}

	public int getStore_id() {
		return store_id;
	}

	public void setStore_id(int store_id) {
		this.store_id = store_id;
	}

	public String getStore_name() {
		return store_name;
	}

	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}

	public String getPrefecture_id() {
		return prefecture_id;
	}

	public void setPrefecture_id(String prefecture_id) {
		this.prefecture_id = prefecture_id;
	}
	public String getStore_appeal_short() {
		return store_appeal_short;
	}

	public void setStore_appeal_short(String store_appeal_short) {
		this.store_appeal_short = store_appeal_short;
	}
	
	public String getStore_appeal_long() {
		return store_appeal_long;
	}

	public void setStore_appeal_long(String store_appeal_long) {
		this.store_appeal_long = store_appeal_long;
	}
}
