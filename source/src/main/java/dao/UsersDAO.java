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
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/f1?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true"
					+ "&allowPublicKeyRetrieval=true",
					"f1", "xVyQPJuerzK8LB4G");

			// SQL文を準備する
			String sql = "SELECT user_id, address, password, prefecture_id, memo FROM users "
					+ "WHERE address LIKE ? AND password LIKE ? AND prefecture_id LIKE ? AND memo LIKE ? ";
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
			if (user.getPrefecture_id() != 0) {
				pStmt.setString(3, "%" + user.getPrefecture_id() + "%");
			} else {
				pStmt.setString(3, "%");
			}
			if (user.getMemo() != null) {
				pStmt.setString(4, "%" + user.getMemo() + "%");
			} else {
				pStmt.setString(4, "%");
			}
			
			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				UsersDTO UsersDTO = new UsersDTO(rs.getInt("user_id"),  
						rs.getString("address"), 
						rs.getString("password"), 
						rs.getInt("prefecture_id"), 
						rs.getString("memo"));
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
	
	public int insert(UsersDTO user) {
		Connection conn = null;
		int nextUserId = -1;
		
		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/f1?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true"
					+ "&allowPublicKeyRetrieval=true",
					"f1", "xVyQPJuerzK8LB4G");
			
			//現在の最大user_idを取得
			String maxSql = "SELECT COALESCE(MAX(user_id), 0) AS max_id FROM users";
			PreparedStatement maxStmt = conn.prepareStatement(maxSql);
			//データベースからデータを取得
			ResultSet maxRs = maxStmt.executeQuery();
			
			if (maxRs.next()) {
				nextUserId = maxRs.getInt("max_id") + 1;
			} else {
				nextUserId = 1;
			}
			maxRs.close();
			maxStmt.close();
			
			// SQL文を準備する
			String sql = "INSERT INTO users VALUES (?, ?, ?, ?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			// SQL文を完成させる
			pStmt.setInt(1, nextUserId);
			if (user.getAddress() != null) {
				pStmt.setString(2, user.getAddress());
			} else {
				pStmt.setString(2, "");
			}
			if (user.getPassword() != null) {
				pStmt.setString(3, user.getPassword());
			} else {
				pStmt.setString(3, "");
			}
			if (user.getPrefecture_id() != 0) {
				pStmt.setInt(4, user.getPrefecture_id());
			} else {
				pStmt.setString(4, "");
			}
			if (user.getMemo() != null) {
				pStmt.setString(5, user.getMemo());
			} else {
				pStmt.setString(5, "");
			}
			
			
			// SQL文を実行する
			if (pStmt.executeUpdate() != 1) {
				nextUserId = -1;
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
		return nextUserId;
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
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true"
					+ "&allowPublicKeyRetrieval=true",
					"f1", "xVyQPJuerzK8LB4G");

			// SQL文を準備する
			String sql = "UPDATE users SET address=?, password=?, prefecture_id=?, memo=? WHERE user_id = ?";
			
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
			if (user.getPrefecture_id() != 0) {
				pStmt.setInt(3, user.getPrefecture_id());
			} else {
				pStmt.setString(3, "");
			}
			if (user.getMemo() != null) {
				pStmt.setString(4, user.getMemo());
			} else {
				pStmt.setString(4, "");
			}
			pStmt.setInt(5, user.getUser_id());

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

	// 引数cardで指定された番号のレコードを削除し、成功したらtrueを返す　//全部消えるのでは
	/*public boolean delete(UsersDTO user) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/goodbuy?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// SQL文を準備する
			String sql = "DELETE FROM Users WHERE store_name=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setString(1, user.getPhone_number());		//numberは自動採番-->nullの可能性はない

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
	}*/
	
	//memoを更新し、成功したらtrueを返す
	public boolean updateMemo(UsersDTO usersDTO) {
		Connection conn = null;
		boolean result = false;
		
		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/f1?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true"
					+ "&allowPublicKeyRetrieval=true",
					"f1", "xVyQPJuerzK8LB4G");
			
			// SQL文を準備する
			String sql = "UPDATE users SET memo=? WHERE user_id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			// SQL文を完成させる
			if (usersDTO.getMemo() != null) {
				pStmt.setString(1, usersDTO.getMemo());
			} else {
				pStmt.setString(1, "");
			}
			pStmt.setInt(2, usersDTO.getUser_id());
			
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
	/*
	public boolean isAddressCheck(String address) {
		Connection conn = null;
		boolean result = false;
		
		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/goodbuy?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Tokyo&connectTimeout=30000", "root", "password");
			
			// SQL文を準備する
			String sql = "SELECT COUNT(*) FROM users WHERE address = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, address);
            
            ResultSet rs = pStmt.executeQuery();
                if (rs.next()) {
                    // カウントが0より大きければ「存在する」
                    result =  rs.getInt(1) > 0;
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
	*/
	
}


