package dto;

import java.io.Serializable;

public class RegistStoresDTO implements Serializable {
	private int user_id; 		//ユーザーID
	private String phone_number;		//電話番号
	
	//ｺﾝｽﾄﾗｸﾀ
	public RegistStoresDTO(int user_id, String phone_number) {
	super();
	this.user_id = user_id;
	this.phone_number = phone_number;
	}
	
	public RegistStoresDTO() {
	this.user_id = 0;
	this.phone_number = "";		
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	
}
