package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Communication;

public class CommunicationDAO {
	public List<Communication> searchuserId(int eventId) {
		Connection conn = null;
		List<Communication> communicationList = new ArrayList<Communication>();

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/IGNITE", "sa", "");

			// SQL文を準備する
			String sql = "select * from communication "
					+ "where event_id = ? "
					+ "order by posted_time";


			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, "eventId");

			ResultSet rs = pStmt.executeQuery();
			rs.next();
			// 結果表をコレクションにコピーする
			while (rs.next()) {
				Communication record = new Communication(
						rs.getInt("id"),
						rs.getInt("user_is"),
						rs.getInt("event_id"),
						rs.getString("posted_time"),
						rs.getString("content"));
				communicationList.add(record);
			}
		} catch (Exception e) {
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
		return communicationList;
	}
	public boolean insert(Communication communication) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/IGNITE", "sa", "");

			// SQL文を準備する（AUTO_INCREMENTのNUMBER列にはNULLを指定する）
			String sql = "INSERT INTO communication VALUES (NULL, ?, ?,?,?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setInt(1, communication.getUser_id());
			pStmt.setInt(2, communication.getEvent_id());
			pStmt.setString(3, communication.getPosted_time());
			pStmt.setString(4, communication.getContent());

			// SQL文を実行する
			if (pStmt.executeUpdate() == 1) {
				result = true;
			}
		}
		catch (Exception e) {
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
