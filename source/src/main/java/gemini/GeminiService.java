package gemini;
//アップロード時にDB情報を書き換えることを忘れない
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class GeminiService {
	
    private final HttpClient httpClient = HttpClient.newHttpClient();

    // DB接続情報
    private final String url = "jdbc:mysql://localhost:3306/yourdb?useSSL=false&characterEncoding=UTF-8";
    private final String user = "root";
    private final String pass = "password";
    
    //リクエスト、パース、DB保存をまとめて実行するメソッド（各Servletでこれを呼び出す）
    public void generateRecipe(String phone_number) throws Exception {
    	
        //店舗IDで目玉商品を取得
        List<String> items = getFeaturedItems(phone_number);

        //プロンプト生成
        String prompt = buildPrompt(items);

        //GeminiAPI呼び出し
        String answer = sendPrompt(prompt);

        //パースしてname/ingredients/recipeに分解
        ParsedRecipe parsed = parseRecipe(answer);

        //recipeテーブルに保存,recipe_idを取得
        int recipeId = insertRecipe(phone_number, parsed.recipe_name, parsed.recipe);

        //材料テーブルに保存
        insertIngredients(recipeId, parsed.ingredients);
    }
    
    //APIキーテーブルからAPIキーを取得
	private String getApiKey() throws Exception {

	    String sql = "SELECT api_name FROM API WHERE api_id = '1'";

	    try (Connection conn = DriverManager.getConnection(url, user, pass);
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            return rs.getString("api_name");
	        }
	    }
	    throw new Exception("Gemini APIキーがDBに存在しません");
	}
    
    //店舗IDで目玉商品を取得
    private List<String> getFeaturedItems(String phone_number) throws Exception {
        List<String> list = new ArrayList<>();

        String sql = "SELECT featured_item_name FROM featured_items WHERE phone_number = ?";

        try (Connection conn = DriverManager.getConnection(url, user, pass);
             PreparedStatement pStmt = conn.prepareStatement(sql)) {

            pStmt.setString(1, phone_number);
            ResultSet rs = pStmt.executeQuery();

            while (rs.next()) {
                list.add(rs.getString("featured_item_name"));
            }
        }
        return list;
    }

    //プロンプト生成
    private String buildPrompt(List<String> items) {
        String list = String.join("、", items);

        return "以下の手順で回答してください。"
                + "1.材料リストを読み取る。"
                + "2.リストの中からGeminiがランダムに3個を選ぶ。"
                + "3.選んだ材料を使ってレシピを作成する。"
                + "4.レシピは以下の形式でのみ作成する。説明文や補足は禁止。"
                + "recipe_name='料理名'、ingredient='材料名' を複数行、"
                + "recipe='手順1。手順2。手順3。' のように、すべての手順を1つの変数にまとめること。"
                + "recipe_name、ingredient、recipe の3項目以外は出力しないこと。"
                + "【材料リスト】" + list;
    }

    //GeminiAPI呼び出し
    private String sendPrompt(String prompt) throws Exception {
    	
    	//APIキーをテーブルから取得する
    	String apiKey = getApiKey();
    	
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create("https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key=" + apiKey))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(
                        "{\"contents\": [{\"parts\":[{\"text\": \"" + prompt + "\"}]}]}",
                        StandardCharsets.UTF_8))
                .build();

        HttpResponse<String> httpResponse =
                httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        return parseResponse(httpResponse.body());
    }

    // JSONレスポンスから text を抽出
    private String parseResponse(String json) {
        int idx = json.indexOf("\"text\":");
        if (idx == -1) return null;

        int start = json.indexOf("\"", idx + 7) + 1;
        int end = json.indexOf("\"", start);

        return json.substring(start, end)
                .replace("\\n", "\n")
                .replace("\\\"", "\"");
    }

    //Gemini返答をパース
    private ParsedRecipe parseRecipe(String text) {

        ParsedRecipe p = new ParsedRecipe();

        for (String line : text.split("\n")) {
            if (line.startsWith("recipe_name=")) {
                p.recipe_name = line.substring(5).replace("'", "");
            } else if (line.startsWith("ingredient=")) {
                p.ingredients.add(line.substring(11).replace("'", ""));
            } else if (line.startsWith("recipe=")) {
                p.recipe = line.substring(7).replace("'", "");
            }
        }
        return p;
    }

    //パース結果を保持
    private static class ParsedRecipe {
        String recipe_name;
        String recipe;
        List<String> ingredients = new ArrayList<>();
    }

    //レシピテーブルに保存
    private int insertRecipe(String phone_number, String recipe_name, String recipe) throws Exception {

        String sql = "INSERT INTO recipes (recipe_name, recipe) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, pass);
             PreparedStatement pStmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            pStmt.setString(1, recipe_name);
            pStmt.setString(2, recipe);
            pStmt.executeUpdate();

            ResultSet rs = pStmt.getGeneratedKeys();
            if (rs.next()) return rs.getInt(1);
        }
        return -1;
    }

    //材料テーブルに保存
    private void insertIngredients(int recipeId, List<String> ingredients) throws Exception {

        String sql = "INSERT INTO ingredients (ingredients_name, recipe_id) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, pass);
             PreparedStatement pStmt = conn.prepareStatement(sql)) {

            for (String ing : ingredients) {
                pStmt.setString(1, ing);
                pStmt.setInt(2, recipeId);
                pStmt.addBatch();
            }
            pStmt.executeBatch();
        }
    }
}
