package dto;

import java.io.Serializable;

public class RegistStoresDTO implements Serializable {
	private int user_id; 		//ユーザーID
	private int store_id;		//店舗ID
	
	//ｺﾝｽﾄﾗｸﾀ
	public RegistStoresDTO(int user_id, int store_id) {
	super();
	this.user_id = user_id;
	this.store_id = store_id;
	}
	
	public RegistStoresDTO() {
	this.user_id = 0;
	this.store_id = 0;		
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getStore_id() {
		return store_id;
	}

	public void setStore_id(int store_id) {
		this.store_id = store_id;
	}
	
}
