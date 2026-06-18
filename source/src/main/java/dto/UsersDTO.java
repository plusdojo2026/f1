package dto;

import java.io.Serializable;

public class UsersDTO implements Serializable {
	private int user_id; 		//ユーザーID
	private String address;		//メールアドレス
	private String password;	//パスワード
	private String prefecture_name;  //都道府県
	private String store_name;		//店舗ID
	private String memo;		//memo
	
	//ｺﾝｽﾄﾗｸﾀ
	public UsersDTO(int user_id, String address, String password, String prefecture_name, String store_name, String memo) {
	super();
	this.user_id = user_id;
	this.address = address;
	this.password = password;
	this.prefecture_name = prefecture_name;
	this.store_name = store_name;
	this.memo = memo;
	}

	//引数無しのｺﾝｽﾄﾗｸﾀ
	public UsersDTO() {
	this.user_id = 0;
	this.address = "";
	this.password = "";
	this.prefecture_name = "";
	this.store_name = "";
	this.memo = "";
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPrefecture_name() {
		return prefecture_name;
	}

	public void setPrefecture_name(String prefecture_name) {
		this.prefecture_name = prefecture_name;
	}

	public String getStore_name() {
		return store_name;
	}

	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}