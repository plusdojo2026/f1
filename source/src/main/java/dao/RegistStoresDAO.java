package dao;
//店舗の登録・削除を行うDAO
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.RegistStoresDTO;

public class RegistStoresDAO {

	public List<RegistStoresDTO> select(RegistStoresDTO regist) {
		Connection conn = null;
		List<RegistStoresDTO> storelist = new ArrayList<RegistStoresDTO>();

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/f1?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"f1", "xVyQPJuerzK8LB4G");

			// SQL文を準備する
			String sql = "SELECT user_id, store_id FROM regist_stores"
					+ "WHERE user_id LIKE ? AND phone_number LIKE ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			if (regist.getUser_id() != 0) {
				pStmt.setString(1, "%" + regist.getUser_id() + "%");
			} else {
				pStmt.setString(1, "%");
			}
			if (regist.getPhone_number() != null) {
				pStmt.setString(2, "%" + regist.getPhone_number() + "%");
			} else {
				pStmt.setString(2, "%");
			}
			
			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();
			
			// 結果表をコレクションにコピーする
			while (rs.next()) {
				RegistStoresDTO dto = new RegistStoresDTO();
				dto.setUser_id(rs.getInt("user_id"));
				dto.setPhone_number(rs.getString("Phone_number"));
				storelist.add(dto);
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
		return storelist;
	}

	// regist_storesに登録する
	public boolean insert(RegistStoresDTO regist) {
		Connection conn = null;
		boolean registResult = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/f1?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"f1", "xVyQPJuerzK8LB4G");

			// SQL文を準備する
			String sql = "INSERT INTO regist_stores (user_id,phone_number) VALUES(?,?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setInt(1, regist.getUser_id());
			pStmt.setString(2, regist.getPhone_number());

			// SQL文を実行する
			if(pStmt.executeUpdate() ==1) {
				registResult = true;
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
		return registResult;
	}

	//regist_storesから削除する
	public boolean delete(RegistStoresDTO regist) {
		Connection conn = null;
		boolean registResult = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/f1?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"f1", "xVyQPJuerzK8LB4G");

			// SQL文を準備する
			String sql = "DELETE FROM regist_stores WHERE user_id=? AND phone_number=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setInt(1, regist.getUser_id());
			pStmt.setString(2, regist.getPhone_number());

			// SQL文を実行する
			if (pStmt.executeUpdate() == 1) {
				registResult = true;
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
		return registResult;
	}
}
