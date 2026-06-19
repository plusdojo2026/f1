package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.UsersDTO;

public class UsersDAO {
	
	// 引数card指定された項目で検索して、取得されたデータのリストを返す
	public List<UsersDTO> select(UsersDTO user) {
		Connection conn = null;
		List<UsersDTO> userList = new ArrayList<UsersDTO>();

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/webapp1?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// SQL文を準備する
			String sql = "SELECT user_id, address, password, prefecture_name, store_name, memo FROM users "
					+ "WHERE address LIKE ? AND password LIKE ? AND prefecture_name LIKE ? "
					+ "AND store_name LIKE ? AND memo LIKE ? ";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			if (user.getAddress() != null) {
				pStmt.setString(1, "%" + user.getAddress() + "%");
			} else {
				pStmt.setString(1, "%");
			}
			if (user.getPassword() != null) {
				pStmt.setString(2, "%" + user.getPassword() + "%");
			} else {
				pStmt.setString(2, "%");
			}
			if (user.getPrefecture_name() != null) {
				pStmt.setString(3, "%" + user.getPrefecture_name() + "%");
			} else {
				pStmt.setString(3, "%");
			}
			if (user.getStore_name() != null) {
				pStmt.setString(4, "%" + user.getStore_name() + "%");
			} else {
				pStmt.setString(4, "%");
			}
			if (user.getMemo() != null) {
				pStmt.setString(5, "%" + user.getMemo() + "%");
			} else {
				pStmt.setString(5, "%");
			}
			
			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				UsersDTO UsersDTO = new UsersDTO(rs.getInt("user_id"),  
						rs.getString("address"), 
						rs.getString("password"), 
						rs.getString("prefecture_name"), 
						rs.getString("store_name"), 
						rs.getString("meomo"));
				userList.add(UsersDTO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			userList = null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			userList = null;
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					userList = null;
				}
			}
		}

		// 結果を返す
		return userList;
	}

	
	
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
			String sql = "INSERT INTO users VALUES (0, ?, ?, ?, ?, ?)";
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
			if (user.getPrefecture_name() != null) {
				pStmt.setString(3, user.getPrefecture_name());
			} else {
				pStmt.setString(3, "");
			}
			if (user.getStore_name() != null) {
				pStmt.setString(4, user.getStore_name());
			} else {
				pStmt.setString(4, "");
			}
			if (user.getMemo() != null) {
				pStmt.setString(5, user.getMemo());
			} else {
				pStmt.setString(5, "");
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
	
	// 引数cardで指定されたレコードを更新し、成功したらtrueを返す
	public boolean update(UsersDTO user) {
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
			String sql = "UPDATE users SET address=?, password=?, prefecture_name=?,"
					+ "store_name=?, memo=?";
			
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
			if (user.getPrefecture_name() != null) {
				pStmt.setString(3, user.getPrefecture_name());
			} else {
				pStmt.setString(3, "");
			}
			if (user.getStore_name() != null) {
				pStmt.setString(4, user.getStore_name());
			} else {
				pStmt.setString(4, "");
			}
			if (user.getMemo() != null) {
				pStmt.setString(5, user.getMemo());
			} else {
				pStmt.setString(5, "");
			}
			

			// SQL文を実行する
			if (pStmt.executeUpdate() == 1) {	//1レコード更新できた-->true
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

	// 引数cardで指定された番号のレコードを削除し、成功したらtrueを返す
	public boolean delete(UsersDTO user) {
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
			String sql = "DELETE FROM Users WHERE store_name=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setString(1, user.getStore_name());		//numberは自動採番-->nullの可能性はない

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


