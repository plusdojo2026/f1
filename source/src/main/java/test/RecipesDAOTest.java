package test;

import java.util.List;

import dao.RecipesDAO;
import dto.RecipesDTO;

public class RecipesDAOTest {

	public static void showAllData(List<RecipesDTO> recipeList) {
		for (RecipesDTO recipe : recipeList) {
			System.out.println("レシピID：" + recipe.getRecipe_id());
			System.out.println("電話番号：" + recipe.getPhone_number());
			System.out.println("料理名：" + recipe.getRecipe_name());
			System.out.println("レシピ：" + recipe.getRecipe());
			System.out.println();
		}
	}

	public static void main(String[] args) {
		RecipesDAO dao = new RecipesDAO();

		// select()のテスト1
		System.out.println("---------- select()のテスト1 ----------");
		//サンプルデータに応じて中身を入れる
		List<RecipesDTO> recipeListSel1 = dao.select(new RecipesDTO(0, "", "", ""));
		RecipesDAOTest.showAllData(recipeListSel1);

		// select()のテスト2
		System.out.println("---------- select()のテスト2 ----------");
		List<RecipesDTO> recipeListSel2 = dao.select(new RecipesDTO(0, "", "", ""));
		RecipesDAOTest.showAllData(recipeListSel2);
	}

}
