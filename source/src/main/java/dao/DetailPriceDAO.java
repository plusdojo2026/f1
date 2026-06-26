package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.DetailPriceDTO;

public class DetailPriceDAO {
	public List<DetailPriceDTO> select(DetailPriceDTO detailPrice) {
		Connection conn = null;
		List<DetailPriceDTO> DetailPriceList = new ArrayList<DetailPriceDTO>();

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/f1?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true"
					+ "&allowPublicKeyRetrieval=true",
					"f1", "xVyQPJuerzK8LB4G");

			// SQL文を準備する
			String sql = "SELECT f.featured_item_name, f.price, ap.average_price FROM stores s JOIN featured_items f ON s.phone_number = f.phone_number LEFT JOIN average_prices ap ON s.prefecture_id = ap.prefecture_id AND f.featured_item_name = ap.featured_item_name WHERE s.phone_number = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setString(1, detailPrice.getPhone_number());
			
			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();
			// 結果表をコレクションにコピーする
			while (rs.next()) {
				DetailPriceDTO av = new DetailPriceDTO(rs.getString("phone_number"),rs.getString("featured_item_name"),rs.getInt("price"),rs.getInt("average_price"));
							DetailPriceList.add(av);
						}
		} catch (SQLException e) {
			e.printStackTrace();
			DetailPriceList = null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			DetailPriceList = null;
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					DetailPriceList = null;
				}
			}
		}
		// 結果を返す
		return DetailPriceList;
}
}
