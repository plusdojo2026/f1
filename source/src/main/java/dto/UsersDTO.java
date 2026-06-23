package dto;

import java.io.Serializable;

public class UsersDTO implements Serializable {
	private int user_id; 		//ユーザーID
	private String address;		//メールアドレス
	private String password;	//パスワード
	private int prefecture_id;  //都道府県ID
	private String memo;		//memo
	
	//ｺﾝｽﾄﾗｸﾀ
	public UsersDTO(int user_id, String address, String password, int prefecture_id, String memo) {
	super();
	this.user_id = user_id;
	this.address = address;
	this.password = password;
	this.prefecture_id = prefecture_id;
	this.memo = memo;
	}

	//引数無しのｺﾝｽﾄﾗｸﾀ
	public UsersDTO() {
	this.user_id = 0;
	this.address = "";
	this.password = "";
	this.prefecture_id = 0;
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

	public int getPrefecture_id() {
		return prefecture_id;
	}

	public void setPrefecture_id(int prefecture_id) {
		this.prefecture_id = prefecture_id;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}