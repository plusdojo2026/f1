package dto;

import java.io.Serializable;

public class GainsDTO implements Serializable {
	private int gain_id;		//お得ID
	private int user_id; 		//ユーザーID
	private String record_date;	//年月日
	private int gain_sum;		//お得金額合計
	private int ap_count;		//代替商品購入回数
	
	//ｺﾝｽﾄﾗｸﾀ
	public GainsDTO(int gain_id, int user_id, String record_date, int gain_sum, int ap_count) {
	super();
	this.gain_id = gain_id;
	this.user_id = user_id;
	this.record_date = record_date;
	this.gain_sum = gain_sum;
	this.ap_count = ap_count;
	}

	//引数無しのｺﾝｽﾄﾗｸﾀ
	public GainsDTO() {
	this.gain_id = 0;
	this.user_id = 0;
	this.record_date = "";
	this.gain_sum = 0;
	this.ap_count = 0;
	}

	public int getGain_id() {
		return gain_id;
	}

	public void setGain_id(int gain_id) {
		this.gain_id = gain_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getRecord_date() {
		return record_date;
	}

	public void setRecord_date(String record_date) {
		this.record_date = record_date;
	}

	public int getGain_sum() {
		return gain_sum;
	}

	public void setGain_sum(int gain_sum) {
		this.gain_sum = gain_sum;
	}

	public int getAp_count() {
		return ap_count;
	}

	public void setAp_count(int ap_count) {
		this.ap_count = ap_count;
	}

}
