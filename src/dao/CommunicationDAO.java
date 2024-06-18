package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Communication;
import model.Event;

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
				Event record = new Event(
						rs.getInt("id"),
						rs.getString("event_name"),
						rs.getString("event_description"),
						rs.getString("holding_schedule"),
						rs.getInt("least_count"),
						rs.getInt("max_count"),
						rs.getInt("prefectureId"),
						rs.getString("detail_address"),
						rs.getString("location_name"),
						rs.getInt("event_category"),
						rs.getInt("holdingUserId"),
						rs.getInt("status"));
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
		return cardList;
	}
}
