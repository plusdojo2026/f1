package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.IngredientsDTO;

public class IngredientsDAO {

	public List<IngredientsDTO> select(String phone_number, int recipe_id) {
		Connection conn = null;
		List<IngredientsDTO> ingredientsList = new ArrayList<IngredientsDTO>();

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/miya?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true&allowPublicKeyRetrieval=true", "f1",
					"xVyQPJuerzK8LB4G");

			// SQL文を準備する
			String sql = "SELECT f.featured_item_id, f.price, r.recipe_name, i.ingredients_name " + "FROM (recipes r "
					+ "INNER JOIN ingredients i ON r.recipe_id = i.recipe_id) "
					+ "LEFT JOIN featured_items f ON f.featured_item_name = i.ingredients_name "
					+ "WHERE f.phone_number = ? " + "AND featured_item_name "
					+ "IN(SELECT ingredients_name FROM ingredients WHERE recipe_id = ?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, phone_number);
			ps.setInt(2, recipe_id);
			// SQL文を実行し、結果表を取得する
			ResultSet rs = ps.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				IngredientsDTO ingredientsDTO = new IngredientsDTO(rs.getInt("featured_item_id"), rs.getInt("price"),
						rs.getString("recipe_name"), rs.getString("ingredients_name"));
				ingredientsList.add(ingredientsDTO);
			}

			return ingredientsList;

		} catch (SQLException e) {
			e.printStackTrace();
			ingredientsList = null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			ingredientsList = null;
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					ingredientsList = null;
				}
			}
		}
		return ingredientsList;
	}
}
