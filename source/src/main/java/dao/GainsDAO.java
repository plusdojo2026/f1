package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.GainsDTO;

public class GainsDAO {
	public List<GainsDTO> select(GainsDTO gain) {
		Connection conn = null;
		List<GainsDTO> gainList = new ArrayList<GainsDTO>();
		
		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/f1?"
					+ "useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Tokyo&connectTimeout=30000",
					"f1", "xVyQPJuerzK8LB4G");
			
			// SQL文を準備する
			String sql = "SELECT gain_id,user_id,record_date,gain_sum,ap_count";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			// SQL文を完成させる
			if (gain.getRecord_date() != null) {
				pStmt.setString(1, "%" + gain.getRecord_date() + "%");
			} else {
				pStmt.setString(1, "%");
			}
			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();
			// 結果表をコレクションにコピーする
			while (rs.next()) {
				GainsDTO ga = new GainsDTO(rs.getInt("gain_id"),rs.getInt("user_id"),rs.getString("record_date"),rs.getInt("gain_sum"),rs.getInt("ap_count"));
							gainList.add(ga);
						}
		} catch (SQLException e) {
			e.printStackTrace();
			gainList = null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			gainList = null;
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					gainList = null;
				}
			}
		}
		// 結果を返す
		return gainList;
	}
	public boolean insert(GainsDTO gain) {
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
			String sql = "INSERT INTO GainsDTO VALUES (0, 0, ?, 0, 0)";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			if (gain.getRecord_date() != null) {
				pStmt.setString(1, gain.getRecord_date());
			} else {
				pStmt.setString(1, "");
			
			// SQL文を実行する
			if (pStmt.executeUpdate() == 1) {
				result = true;
			}
		} 
		}catch (SQLException e) {
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
