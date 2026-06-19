package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.RecipesDTO;


public class RecipesDAO {
	//// 引数recipesで指定されたレコードを検索し、取得したデータのリストを返す
	public List<RecipesDTO> select(RecipesDTO recipes) {
		Connection conn = null;
		List<RecipesDTO> recipeList = new ArrayList<RecipesDTO>();

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/f1?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"f1", "xVyQPJuerzK8LB4G");

			// SQL文を準備する
			String sql = "SELECT recipe_id, phone_number, recipe_name, recipe FROM recipes WHERE phone_number =  ? ";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setString(1, recipes.getPhone_number());

			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				RecipesDTO dto = new RecipesDTO();
				dto.setRecipe_id(rs.getInt("recipe_id"));
				dto.setPhone_number(rs.getString("phone_number"));
				dto.setRecipe_name(rs.getString("recipe_name"));
				dto.setRecipe(rs.getString("recipe"));
				recipeList.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		// 結果を返す
		return recipeList;
	}

}
