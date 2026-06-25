package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.UsersDTO;

public class IdPwDAO {
	// 引数で指定されたidpwでログイン成功ならtrueを返す
	public int isLoginOK(UsersDTO user) {
		Connection conn = null;
		int user_id = -1;

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/f1?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true"
					+ "&allowPublicKeyRetrieval=true",
					"f1", "xVyQPJuerzK8LB4G");

			// SELECT文を準備する
			String sql = "SELECT user_id FROM users WHERE address=? AND password=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, user.getAddress());
			pStmt.setString(2, user.getPassword());

			// SELECT文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 一致するuser_idを見つける
			if (rs.next()) {
				user_id = rs.getInt("user_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			user_id = -1;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			user_id = -1;
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					user_id = -1;
				}
			}
		}

		// 結果を返す
		return user_id;
	}
}
