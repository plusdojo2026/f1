package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.FeaturedItemsDTO;

public class FeaturedItemsDAO {
	
	// 引数featuredItemsで指定されたレコードを検索し、取得したデータのリストを返す
	public List<FeaturedItemsDTO> select(FeaturedItemsDTO featuredItems) {
		Connection conn = null;
		List<FeaturedItemsDTO> itemList = new ArrayList<FeaturedItemsDTO>();
		
		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/f1?"
					+ "useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Tokyo&connectTimeout=30000",
					"f1", "xVyQPJuerzK8LB4G");
			
			// 検索SQL文を準備する
			String sql = "SELECT featured_item_id, store_id, price, featured_item_name, start_date, end_date, ap_date FROM featured_items"
					+ "WHERE (featured_item_id = ? OR ? = 0) AND (store_id = ? OR ? = 0) AND (price = ? OR ? = 0) AND featured_item_name LIKE ? AND start_date <= ? AND end_date >= ? AND ap_name LIKE ? ORDER BY featured_item_id";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			// SQL文を完成させる
			if (featuredItems.getFeatured_item_id() != 0) {
				pStmt.setInt(1, featuredItems.getFeatured_item_id());
			} else {
				pStmt.setInt(1, 0);
			}
			if (featuredItems.getStore_id() != 0) {
				pStmt.setInt(2, featuredItems.getStore_id());
			} else {
				pStmt.setInt(2, 0);
			}
			if (featuredItems.getPrice() != 0) {
				pStmt.setInt(3, featuredItems.getPrice());
			} else {
				pStmt.setInt(3, 0);
			}
			//LIKEは部分一致なので、%入力した文字%になるようにif文を作る
			if (featuredItems.getFeatured_item_name() != null) {
				pStmt.setString(4, "%" + featuredItems.getFeatured_item_name() + "%");
			} else {
				pStmt.setString(4, "%");
			}
			if (featuredItems.getStart_date() != null) {
				pStmt.setString(5, "%" + featuredItems.getStart_date().toString() + "%");
			} else {
				pStmt.setString(5, "%");
			}
			if (featuredItems.getEnd_date() != null) {
				pStmt.setString(6, "%" + featuredItems.getEnd_date().toString() + "%");
			} else {
				pStmt.setString(6, "%");
			}
			if (featuredItems.getAp_name() != null) {
				pStmt.setString(7, featuredItems.getAp_name());
			} else {
				pStmt.setString(7, "");
			}
			
			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();
			
			// 結果表をコレクションにコピーする
			while (rs.next()) {
				FeaturedItemsDTO fi = new FeaturedItemsDTO (rs.getInt("featured_item_id"), rs.getInt("store_id"),
									  rs.getInt("price"), rs.getString("featured_item_name"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("ap_name"));
				itemList.add(fi);
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
		return itemList;
	}
	
	//引数featuredItemsで指定されたレコードを更新し、成功したらtrueを返す
	public boolean update(FeaturedItemsDTO featuredItems) {
		Connection conn = null;
		boolean result = false;
		
		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/f1?"
					+ "useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Tokyo&connectTimeout=30000",
					"f1", "xVyQPJuerzK8LB4G");

			// SQL文を準備する
			String sql = "UPDATE FeaturedItemsDTO SET ap_name=? WHERE featured_item_id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			if (featuredItems.getAp_name() != null) {
				pStmt.setString(1, "%" + featuredItems.getAp_name() + "%");
			} else {
				pStmt.setString(1, "%");
			}
			if (featuredItems.getFeatured_item_id() != 0) {
				pStmt.setInt(2, featuredItems.getFeatured_item_id());
			} else {
				pStmt.setInt(2, 0);
			}
			// SQL文を実行する
			if (pStmt.executeUpdate() == 1) {
				result = true;
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
		return result;
	}
}



