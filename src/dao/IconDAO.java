package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IconDAO {

	//ユーザーIDからアイコン表示
	public String searchuserId(int userId) {
		Connection conn = null;
		String result ="";

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/IGNITE", "sa", "");

			// SQL文を準備する
			String sql = "SELECT ic.url FROM users AS us INNER JOIN icon AS ic ON us.icon_id = ic.id WHERE user_id = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setInt(1,userId);

			ResultSet rs = pStmt.executeQuery();
			rs.next();

			result = rs.getString("mail_address");


		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		// 結果を返す
		return result;
	}

}
