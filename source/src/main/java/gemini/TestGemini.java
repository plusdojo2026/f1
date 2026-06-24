package gemini;



public class TestGemini {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

        // テスト用の電話番号（DBに存在する phone_number を指定）
        String testPhoneNumber = "0252452533";

        GeminiService service = new GeminiService();

        try {
            service.generateRecipe(testPhoneNumber);
            System.out.println("レシピ生成処理が完了しました。");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("エラーが発生しました。");
        }
    }
}