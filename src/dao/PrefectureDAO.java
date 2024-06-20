package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PrefectureDAO {
	//都道府県IDから都道府県を取得
		public String searchPrefectureName(int Id) {
			Connection conn = null;
			String result ="";

			try {
				// JDBCドライバを読み込む
				Class.forName("org.h2.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/IGNITE", "sa", "");

				// SQL文を準備する
				String sql = "SELECT name FROM prefecture WHERE id = ?";
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

		//都道府県IDから都道府県のウェザーコードを取得
				public String fetchWeatherCode(int Id) {
					Connection conn = null;
					String result ="";

					try {
						// JDBCドライバを読み込む
						Class.forName("org.h2.Driver");

						// データベースに接続する
						conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/IGNITE", "sa", "");

						// SQL文を準備する
						String sql = "SELECT weather_code FROM prefecture WHERE id = ?";
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
