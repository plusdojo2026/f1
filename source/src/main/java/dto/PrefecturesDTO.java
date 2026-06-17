package dto;

import java.io.Serializable;

public class PrefecturesDTO implements Serializable {
	private int prefecture_id;		//都道府県ID
	private String prefecture_name;	//都道府県名
	
	//コンストラクタ
	public PrefecturesDTO(int prefecture_id, String prefecture_name) {
		super();
		this.prefecture_id = prefecture_id;
		this.prefecture_name = prefecture_name;
	}
	
	//デフォルトコンストラクタ
	public PrefecturesDTO() {
		super();
		this.prefecture_id = 0;
		this.prefecture_name = "";
	}

	public int getPrefecture_id() {
		return prefecture_id;
	}

	public void setPrefecture_id(int prefecture_id) {
		this.prefecture_id = prefecture_id;
	}

	public String getPrefecture_name() {
		return prefecture_name;
	}

	public void setPrefecture_name(String prefecture_name) {
		this.prefecture_name = prefecture_name;
	}
	
	
}
