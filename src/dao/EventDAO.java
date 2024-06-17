package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Event;

public class EventDAO {
	//イベント作成
	public boolean keepEvent(Event event) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/simpleBC", "sa", "");

			// SQL文を準備する
			String sql = "INSERT FROM event VALUES (NULL, ?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setString(1, event.getEventName());
			pStmt.setString(2, event.getEventDescription());
			pStmt.setString(3, event.getHoldingSchedule());
			pStmt.setInt(4, event.getLeastCount());
			pStmt.setInt(5, event.getMaxCount());
			pStmt.setInt(6, event.getPrefectureId());
			pStmt.setString(7, event.getDetailAddress());
			pStmt.setString(8, event.getLocationName());
			pStmt.setInt(9, event. getEventCategory());
			pStmt.setInt(10, event.getHoldingUserId());
			pStmt.setInt(11, event.getStatus());

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
