package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
		public List<AveragePriceDTO> select(AveragePriceDTO averageprice) {
			Connection conn = null;
			List<AveragePriceDTO> AveragePriceList = new ArrayList<AveragePriceDTO>();

			try {
				// JDBCドライバを読み込む
				Class.forName("com.mysql.cj.jdbc.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/f1?"
						+ "useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Tokyo&connectTimeout=30000",
						"f1", "xVyQPJuerzK8LB4G");

				// SQL文を準備する
				String sql = "SELECT average_price_id,featured_item_name,prefecture_id,average_price,record_date FROM AveragePriceDTO";
				PreparedStatement pStmt = conn.prepareStatement(sql);

				// SQL文を完成させる
				if (averageprice.getRecord_date() != null) {
					pStmt.setString(1, "%" + averageprice.getRecord_date() + "%");
				} else {
					pStmt.setString(1, "%");
				}
				// SQL文を実行し、結果表を取得する
				ResultSet rs = pStmt.executeQuery();
				// 結果表をコレクションにコピーする
				while (rs.next()) {
					AveragePriceDTO av = new AveragePriceDTO(rs.getInt("average_price_id"),rs.getInt("featured_item_id"),rs.getInt("prefecture_id"),rs.getInt("average_price"),rs.getDate("record_date").toLocalDate());
								AveragePriceList.add(av);
							}
			} catch (SQLException e) {
				e.printStackTrace();
				AveragePriceList = null;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				AveragePriceList = null;
			} finally {
				// データベースを切断
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
						AveragePriceList = null;
					}
				}
			}
			// 結果を返す
			return AveragePriceList;
	}
}

