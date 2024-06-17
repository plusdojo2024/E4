package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Bc;
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

     //ユーザーIDから参加イベントを表示
	public List<Event> searchuserId(int userId) {
		Connection conn = null;
		List<Event> cardList = new ArrayList<Event>();

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/BC_PROTTYPE", "sa", "");

			// SQL文を準備する
			String sql = "SELECT ev.event_name,ev.event_description,ev.holding_schedule,ev.least_count,ev.max_count,"
					+ "prefecture.name,ev.detail_address,ev.location_name,ev.event_category,ev.status  "
					+ "FROM event AS ev "
					+ "INNER JOIN event_user ON ev.id = event_user.event_id "
					+ "INNER JOIN prefecture ON ev.prefecture_id = prefecture.id "
					+ "WHERE event_user.user_id = ?";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1,"userId");

			ResultSet rs = pStmt.executeQuery();
			rs.next();
			// 結果表をコレクションにコピーする
			//火曜日ここから
			while (rs.next()) {
			  Event record = new Event(
			    rs.getString("event_name");
			    rs.getString("event_description");
				rs.getString("holding_schedule");
				rs.getInt("least_count");
				rs.getInt("max_count");
				rs.getString("name");
				rs.getString("detail_address");
				rs.getString("location_name");
				rs.getInt("event_category");
				rs.getInt("status");
			  );
			    cardList.add(record);
						}
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
