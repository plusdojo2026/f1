package dto;

import java.io.Serializable;
import java.time.LocalDate;

public class AveragePriceDTO implements Serializable {
	private int average_price_id;	//平均価格ID
	private int featured_item_id;//目玉商品ID
	private int prefecture_id;		//都道府県ID
	private int average_price;		//平均価格
	private LocalDate record_date;	//更新された時間
	
	//コンストラクタ
	public AveragePriceDTO(int average_price_id, int featured_item_id, int prefecture_id, int average_price,
			LocalDate record_date) {
		super();
		this.average_price_id = average_price_id;
		this.featured_item_id = featured_item_id;
		this.prefecture_id = prefecture_id;
		this.average_price = average_price;
		this.record_date = record_date;
	}
	//デフォルトコンストラクタ
	public AveragePriceDTO() {
		super();
		this.average_price_id = 0;
		this.featured_item_id = 0;
		this.prefecture_id = 0;
		this.average_price = 0;
		this.record_date = null;
	}
	
	public int getAverage_price_id() {
		return average_price_id;
	}
	public void setAverage_price_id(int average_price_id) {
		this.average_price_id = average_price_id;
	}
	public int getFeatured_item_id() {
		return featured_item_id;
	}
	public void setFeatured_item_id(int featured_item_id) {
		this.featured_item_id = featured_item_id;
	}
	public int getPrefecture_id() {
		return prefecture_id;
	}
	public void setPrefecture_id(int prefecture_id) {
		this.prefecture_id = prefecture_id;
	}
	public int getAverage_price() {
		return average_price;
	}
	public void setAverage_price(int average_price) {
		this.average_price = average_price;
	}
	public LocalDate getRecord_date() {
		return record_date;
	}
	public void setRecord_date(LocalDate record_date) {
		this.record_date = record_date;
	}
	
	
}
