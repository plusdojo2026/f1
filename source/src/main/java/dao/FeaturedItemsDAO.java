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
			String sql = "SELECT featured_item_id, store_id, price, featured_item_name, start_date, end_date, ap_name FROM featured_items "
					+ "WHERE (featured_item_id = ? OR ?=0) AND (store_id = ? OR ?=0) ORDER BY featured_item_id";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			// SQL文を完成させる
			pStmt.setInt(1, featuredItems.getFeatured_item_id());
			pStmt.setInt(2, featuredItems.getFeatured_item_id());
			
			pStmt.setString(3, featuredItems.getStore_id());
			pStmt.setString(4, featuredItems.getStore_id());
			
			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();
			
			// 結果表をコレクションにコピーする
			while (rs.next()) {
				FeaturedItemsDTO fi = new FeaturedItemsDTO (rs.getInt("featured_item_id"), rs.getString("store_id"),
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
			String sql = "UPDATE featured_items SET ap_name=? WHERE featured_item_id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setString(1, featuredItems.getAp_name());
			pStmt.setInt(2, featuredItems.getFeatured_item_id());
			
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



