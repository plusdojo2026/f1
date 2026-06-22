package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.UserSettingsDTO;

public class UserSettingsDAO {
	
	// 引数userSettingsで指定されたレコードを検索し、取得したデータのリストを返す
	public List<UserSettingsDTO> select(UserSettingsDTO userSettings) {
		Connection conn = null;
		List<UserSettingsDTO> userSettingList = new ArrayList<UserSettingsDTO>();
		
		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/f1?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Tokyo&connectTimeout=30000", "f1", "xVyQPJuerzK8LB4G");
			
			// 検索SQL文を準備する
			String sql = "SELECT user_id, prefecture_id, favorite_store_id, memo FROM user_settings WHERE user_id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			// SQL文を完成させる
			pStmt.setInt(1, userSettings.getUser_id());
			
			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();
			
			// 結果表をコレクションにコピーする
			while (rs.next()) {
				UserSettingsDTO us = new UserSettingsDTO (rs.getInt("user_id"), rs.getInt("prefecture_id"), rs.getInt("favorite_store_id"), rs.getString("memo"));
				userSettingList.add(us);
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
		return userSettingList;
		}
	
	//引数userSettingsで指定されたレコードを更新し、成功したらtrueを返す
	public boolean update(UserSettingsDTO userSettings) {
		Connection conn = null;
		boolean result = false;
		
		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/f1?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Tokyo&connectTimeout=30000", "f1", "xVyQPJuerzK8LB4G");
			
			// SQL文を準備する
			String sql = "UPDATE user_settings SET prefecture_id=?, favorite_store_id=?, WHERE user_id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			// SQL文を完成させる
			pStmt.setInt(1, userSettings.getPrefecture_id());
			pStmt.setInt(2, userSettings.getFavorite_store_id());
			pStmt.setInt(3, userSettings.getUser_id());
			
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
	
	//memoを更新し、成功したらtrueを返す
		public boolean updateMemo(UserSettingsDTO userSettings) {
			Connection conn = null;
			boolean result = false;
			
			try {
				// JDBCドライバを読み込む
				Class.forName("com.mysql.cj.jdbc.Driver");
				
				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/f1?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Tokyo&connectTimeout=30000", "f1", "xVyQPJuerzK8LB4G");
				
				// SQL文を準備する
				String sql = "UPDATE user_settings SET memo=? WHERE user_id=?";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				
				// SQL文を完成させる
				if (userSettings.getMemo() != null) {
					pStmt.setString(1, userSettings.getMemo());
				} else {
					pStmt.setString(1, "");
				}
				pStmt.setInt(2, 0);
				
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