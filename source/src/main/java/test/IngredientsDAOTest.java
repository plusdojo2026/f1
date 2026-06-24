package test;

import java.util.List;

import dao.IngredientsDAO;
import dto.IngredientsDTO;

public class IngredientsDAOTest {
	public static void showAllData(List<IngredientsDTO> ingredientsList) {
		for (IngredientsDTO ingredients : ingredientsList) {
			System.out.println("目玉商品ID：" + ingredients.getFeatured_item_id());
			System.out.println("目玉商品価格：" + ingredients.getPrice());
			System.out.println("料理名：" + ingredients.getRecipe_name());
			System.out.println("材料名：" + ingredients.getIngredients_name());
			System.out.println();
		}
	}
	public static void main(String[] args) {
		IngredientsDAO ingredientsDao = new IngredientsDAO();
		// select()のテスト1
		System.out.println("---------- select()のテスト1 ----------");
		List<IngredientsDTO> ingredientsListSel1 = ingredientsDao.select("0252801400",3);
		IngredientsDAOTest.showAllData(ingredientsListSel1);
		
	
	}
}
