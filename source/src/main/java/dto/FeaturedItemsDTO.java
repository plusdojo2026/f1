package dto;

import java.io.Serializable;

public class FeaturedItemsDTO implements Serializable {
	private int featured_item_id;		//目玉商品ID
	private int store_id;				//店舗ID
	private int price;					//目玉商品価格
	private String featured_item_name;	//目玉商品名
	private String start_date;			//目玉商品開始日
	private String end_date;			//目玉商品終了日
	private String ap_name;				//代替商品名
	
	//コンストラクタ
	public FeaturedItemsDTO(int featured_item_id, int store_id, int price, String featured_name, String start_date,
			String end_date, String ap_name) {
		super();
		this.featured_item_id = featured_item_id;
		this.store_id = store_id;
		this.price = price;
		this.featured_item_name = featured_name;
		this.start_date = start_date;
		this.end_date = end_date;
		this.ap_name = ap_name;
	}
	
	//デフォルトコンストラクタ
	public FeaturedItemsDTO() {
		super();
		this.featured_item_id = 0;
		this.store_id = 0;
		this.price = 0;
		this.featured_item_name = "";
		this.start_date = "";
		this.end_date = "";
		this.ap_name = "";
	}

	public int getFeatured_item_id() {
		return featured_item_id;
	}

	public void setFeatured_item_id(int featured_item_id) {
		this.featured_item_id = featured_item_id;
	}

	public int getStore_id() {
		return store_id;
	}

	public void setStore_id(int store_id) {
		this.store_id = store_id;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getFeatured_item_name() {
		return featured_item_name;
	}

	public void setFeatured_item_name(String featured_name) {
		this.featured_item_name = featured_name;
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	public String getAp_name() {
		return ap_name;
	}

	public void setAp_name(String ap_name) {
		this.ap_name = ap_name;
	}
	
	
}
