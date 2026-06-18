package dto;

import java.io.Serializable;

public class UserSettingsDTO implements Serializable {

	private int user_id;			//ユーザーID
	private int prefecture_id;		//都道府県ID
	private int favorite_store_id;	//店舗ID
	private String memo;			//メモ
	
	//コンストラクタ
	public UserSettingsDTO(int user_id, int prefecture_id, int favorite_store_id, String memo) {
		super();
		this.user_id = user_id;
		this.prefecture_id = prefecture_id;
		this.favorite_store_id = favorite_store_id;
		this.memo = memo;
	}
	
	//デフォルトコンストラクタ
	public UserSettingsDTO() {
		super();
		this.user_id = 0;
		this.prefecture_id = 0;
		this.favorite_store_id = 0;
		this.memo = "";
	}
	
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getPrefecture_id() {
		return prefecture_id;
	}
	public void setPrefecture_id(int prefecture_id) {
		this.prefecture_id = prefecture_id;
	}
	public int getFavorite_store_id() {
		return favorite_store_id;
	}
	public void setFavorite_store_id(int favorite_store_id) {
		this.favorite_store_id = favorite_store_id;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	

}
