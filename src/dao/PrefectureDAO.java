package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PrefectureDAO {
	//ユーザーIDから都道府県を取得
		public String searchuserId(int Id) {
			Connection conn = null;
			String result ="";

			try {
				// JDBCドライバを読み込む
				Class.forName("org.h2.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/IGNITE", "sa", "");

				// SQL文を準備する
				String sql = "SELECT prefecture.name FROM users INNER JOIN prefecture ON users.prefecture_id = prefecture.id WHERE user_id = ?";
				PreparedStatement pStmt = conn.prepareStatement(sql);

				pStmt.setInt(1,Id);

				ResultSet rs = pStmt.executeQuery();
				rs.next();

				result = rs.getString("name");


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

		//ユーザーIDから都道府県のウェザーコードを取得
				public String fetchWeatherCode(int Id) {
					Connection conn = null;
					String result ="";

					try {
						// JDBCドライバを読み込む
						Class.forName("org.h2.Driver");

						// データベースに接続する
						conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/IGNITE", "sa", "");

						// SQL文を準備する
						String sql = "SELECT user_prefecture.weather_code FROM users INNER JOIN user_prefecture ON users.prefecture_id = user_prefecture.id WHERE user_id = ?";
						PreparedStatement pStmt = conn.prepareStatement(sql);

						pStmt.setInt(1,Id);

						ResultSet rs = pStmt.executeQuery();
						rs.next();

						result = rs.getString("weather_code");


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
