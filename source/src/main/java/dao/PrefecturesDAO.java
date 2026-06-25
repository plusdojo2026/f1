package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.PrefecturesDTO;

public class PrefecturesDAO {

	// 引数userで指定されたレコードを読み込み、一覧を返す
	public List<PrefecturesDTO> distinct(PrefecturesDTO prefecture) {
		Connection conn = null;
		List<PrefecturesDTO> prefectureList = new ArrayList<>();
		
		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/f1?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true"
					+ "&allowPublicKeyRetrieval=true",
					"f1", "xVyQPJuerzK8LB4G");
			
			// SQL文を準備する
			String sql = "SELECT DISTINCT prefecture_name FROM prefectures WHERE prefecture_name IS NOT NULL ORDER BY ";
			PreparedStatement pStmt = conn.prepareStatement(sql);
				

			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				PrefecturesDTO PrefecturesDTO = new PrefecturesDTO(rs.getInt("prefecture_id"), 
						rs.getString("prefecture_name"));
				prefectureList.add(PrefecturesDTO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			prefectureList = null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			prefectureList = null;
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					prefectureList = null;
				}
			}
		}

		// 結果を返す
		return prefectureList;
	}
}
