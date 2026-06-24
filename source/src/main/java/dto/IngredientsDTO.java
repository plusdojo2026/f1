package dto;

public class IngredientsDTO {
	private int featured_item_id;		//目玉商品ID
	private int price;					//目玉商品価格
	private String recipe_name;			//料理名
	private String ingredients_name;	//材料名
	
	//ｺﾝｽﾄﾗｸﾀ
	public IngredientsDTO(int featured_item_id, int price, String recipe_name, String ingredients_name) {
	super();
	this.featured_item_id = featured_item_id;
	this.price = price;
	this.recipe_name = recipe_name;
	this.ingredients_name = ingredients_name;
	}
		
	public IngredientsDTO() {
	this.featured_item_id = 0;
	this.price = 0;
	this.recipe_name = "";
	this.ingredients_name = "";
	}

	public int getFeatured_item_id() {
		return featured_item_id;
	}

	public void setFeatured_item_id(int featured_item_id) {
		this.featured_item_id = featured_item_id;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getRecipe_name() {
		return recipe_name;
	}

	public void setRecipe_name(String recipe_name) {
		this.recipe_name = recipe_name;
	}

	public String getIngredients_name() {
		return ingredients_name;
	}

	public void setIngredients_name(String ingredients_name) {
		this.ingredients_name = ingredients_name;
	}
	
	
}

