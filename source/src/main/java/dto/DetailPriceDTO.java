package dto;

public class DetailPriceDTO {
	private String phone_number;
	private String featured_item_name;
	private int price;
	private int average_price;
	
	//コンストラクタ
	public DetailPriceDTO(String phone_number, String featured_item_name, int price, int average_price) {
		super();
		this.phone_number = phone_number;
		this.featured_item_name = featured_item_name;
		this.price = price;
		this.average_price = average_price;
	}
	
	//デフォルトコンストラクタ
	public DetailPriceDTO() {
		super();
		this.phone_number = "";
		this.featured_item_name = "";
		this.price = 0;
		this.average_price = 0;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getFeatured_item_name() {
		return featured_item_name;
	}

	public void setFeatured_item_name(String featured_item_name) {
		this.featured_item_name = featured_item_name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getAverage_price() {
		return average_price;
	}

	public void setAverage_price(int average_price) {
		this.average_price = average_price;
	}
	
	
	
}
