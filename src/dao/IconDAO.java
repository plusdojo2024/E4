package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IconDAO {

	//アイコンIDからurlを取得
	public String searchuserId(int id) {
		Connection conn = null;
		String result ="";

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/IGNITE", "sa", "");

			// SQL文を準備する
			String sql = "SELECT url FROM  icon  WHERE id = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setInt(1,id);

			ResultSet rs = pStmt.executeQuery();
			rs.next();

			result = rs.getString("url");


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
