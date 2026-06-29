package gemini;

import java.util.List;

import dao.RecipesDAO;
import dto.RecipesDTO;

public class TestGemini {

	public static void main(String[] args) throws Exception {
		// TODO 自動生成されたメソッド・スタブ

        // テスト用の電話番号（DBに存在する phone_number を指定）
        String testPhoneNumber = "0252452533";

      //レシピを生成する
		GeminiService gemini = new GeminiService();
		Integer recipeId = null;
		try {
			recipeId = gemini.generateRecipe(testPhoneNumber);
			System.out.println(recipeId);
		//もしGeminiAPIの問題で生成に失敗したら、DBにrecipe_idを取得しに行く
		} catch (Exception e) {
            RecipesDAO Rdao = new RecipesDAO();
            RecipesDTO Rdto = new RecipesDTO();
            Rdto.setPhone_number(testPhoneNumber);

            List<RecipesDTO> list = Rdao.selectTest(Rdto);

            //最新のrecipe_idを取得する
            if (!list.isEmpty()) {
                //listはrecipe_id昇順で返ってくるので、一番最後の要素を使う
                recipeId = list.get(list.size() - 1).getRecipe_id();
            }
            //以下、実際の処理を書く（取得したrecipe_idをもとにDBにアクセスする処理など）
            System.out.println("recipeId = " + recipeId);
        }
		
    }
}