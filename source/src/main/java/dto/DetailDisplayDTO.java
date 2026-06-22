package dto;
//本クラスはdetail.jspのための専用DTOです
import java.io.Serializable;

public class DetailDisplayDTO implements Serializable {
	private String store_name;
	private String store_appeal_long;
	private String memo;
	private String recipe_name;
	private String recipe;
	private String featured_item_name;
	private int average_price;
	private String ap_name;
	
	//コンストラクタ
	public DetailDisplayDTO(String store_name, String store_appeal_long, String memo, String recipe_name, String recipe,
			String featured_item_name, int average_price, String ap_name) {
		super();
		this.store_name = store_name;
		this.store_appeal_long = store_appeal_long;
		this.memo = memo;
		this.recipe_name = recipe_name;
		this.recipe = recipe;
		this.featured_item_name = featured_item_name;
		this.average_price = average_price;
		this.ap_name = ap_name;
	}
	
	//デフォルトコンストラクタ
	public DetailDisplayDTO() {
		super();
		this.store_name = "";
		this.store_appeal_long = "";
		this.memo = "";
		this.recipe_name = "";
		this.recipe = "";
		this.featured_item_name = "";
		this.average_price = 0;
		this.ap_name = "";
	}
	
	//getterとsetter
	public String getStore_name() {
		return store_name;
	}

	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}

	public String getStore_appeal_long() {
		return store_appeal_long;
	}

	public void setStore_appeal_long(String store_appeal_long) {
		this.store_appeal_long = store_appeal_long;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getRecipe_name() {
		return recipe_name;
	}

	public void setRecipe_name(String recipe_name) {
		this.recipe_name = recipe_name;
	}

	public String getRecipe() {
		return recipe;
	}

	public void setRecipe(String recipe) {
		this.recipe = recipe;
	}

	public String getFeatured_item_name() {
		return featured_item_name;
	}

	public void setFeatured_item_name(String featured_item_name) {
		this.featured_item_name = featured_item_name;
	}

	public int getAverage_price() {
		return average_price;
	}

	public void setAverage_price(int average_price) {
		this.average_price = average_price;
	}

	public String getAp_name() {
		return ap_name;
	}

	public void setAp_name(String ap_name) {
		this.ap_name = ap_name;
	}
	
}
