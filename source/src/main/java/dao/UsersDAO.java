package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dto.UsersDTO;

public class UsersDAO {
	
	// 引数userで指定されたレコードを登録し、成功したらtrueを返す
	public boolean insert(UsersDTO user) {
		Connection conn = null;
		boolean result = false;
		
		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/f1?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "xVyQPJuerzK8LB4G");
			
			// SQL文を準備する
			String sql = "INSERT INTO users VALUES (0, ?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			// SQL文を完成させる
			if (user.getAddress() != null) {
				pStmt.setString(1, user.getAddress());
			} else {
				pStmt.setString(1, "");
			}
			if (user.getPassword() != null) {
				pStmt.setString(2, user.getPassword());
			} else {
				pStmt.setString(2, "");
			}
			// SQL文を実行する
			if (pStmt.executeUpdate() == 1) {		//1レコード更新できた-->true
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


