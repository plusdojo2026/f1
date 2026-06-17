package dto;

import java.io.Serializable;

public class UsersDTO implements Serializable {
	private int user_id; 		//ユーザーID
	private String address;		//メールアドレス
	private String password;	//パスワード

	//ｺﾝｽﾄﾗｸﾀ
	public UsersDTO(int user_id, String address, String password) {
	super();
	this.user_id = user_id;
	this.address = address;
	this.password = password;
	}

	//引数無しのｺﾝｽﾄﾗｸﾀ
	public UsersDTO() {
	this.user_id = 0;
	this.address = "";
	this.password = "";
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

}