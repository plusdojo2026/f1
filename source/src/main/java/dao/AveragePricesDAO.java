package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dto.AveragePriceDTO;

public class AveragePricesDAO {

	// 引数cardで指定されたレコードを登録し、成功したらtrueを返す
		public boolean insert(AveragePriceDTO averageprice) {
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
				String sql = "INSERT INTO AveragePriceDTO VALUES (0, 0, 0, 0, ?)";
				PreparedStatement pStmt = conn.prepareStatement(sql);

				// SQL文を完成させる
				if (averageprice.getRecord_date() != null) {
					pStmt.setString(1, averageprice.getRecord_date().toString());
				} else {
					pStmt.setString(1, "");
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

