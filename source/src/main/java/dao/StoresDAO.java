package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.StoresDTO;
import dto.UsersDTO;

public class StoresDAO {

	// 引数storeで指定されたレコードを検索し、取得したデータのリストを返す
	public List<StoresDTO> select(StoresDTO store) {
		Connection conn = null;
		List<StoresDTO> storeList = new ArrayList<>();

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/f1?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true"
					+ "&allowPublicKeyRetrieval=true",
					"f1", "xVyQPJuerzK8LB4G");

			// 検索SQL文を準備する
			String sql = "SELECT phone_number, store_name, prefecture_id, store_appeal_short, store_appeal_long FROM stores "
					+ "WHERE phone_number = ? ";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, store.getPhone_number());
			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();
			// 結果表をコレクションにコピーする
			while (rs.next()) {
				StoresDTO storesDTO = new StoresDTO(
						rs.getString("phone_number"), 
						rs.getString("store_name"),
						rs.getInt("prefecture_id"),
						rs.getString("store_appeal_short"),
						rs.getString("store_appeal_long"));
				storeList.add(storesDTO);
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
		return storeList;
	}
	
	// 引数user_idで指定されたレコードを検索し、取得したデータのリストを返す
	public List<StoresDTO> selectstores(UsersDTO usersDTO) {
		Connection conn = null;
		List<StoresDTO> storeList = new ArrayList<>();

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/f1?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true"
					+ "&allowPublicKeyRetrieval=true",
					"f1", "xVyQPJuerzK8LB4G");

			// 検索SQL文を準備する
			String sql = "SELECT rs.phone_number, s.store_name, s.prefecture_id, s.store_appeal_short, s.store_appeal_long "
					+ "FROM regist_stores rs "
					+ "LEFT JOIN stores s ON rs.phone_number = s.phone_number "
					+ "WHERE rs.user_id = ?;";
			
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, usersDTO.getUser_id());
			
			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();
			
			// 結果表をコレクションにコピーする
			while (rs.next()) {
				StoresDTO storesDTO = new StoresDTO(
						rs.getString("phone_number"), 
						rs.getString("store_name"),
						rs.getInt("prefecture_id"),
						rs.getString("store_appeal_short"),
						rs.getString("store_appeal_long"));
				storeList.add(storesDTO);
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
		return storeList;
	}
}

