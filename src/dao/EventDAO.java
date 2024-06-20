package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Event;
import model.EventUser;

public class EventDAO {
	//イベントインスタンスを受け取り、イベントテーブルに登録する
	public boolean keepEvent(Event event) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/IGNITE", "sa", "");

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
			pStmt.setInt(9, event.getEventCategory());
			pStmt.setInt(10, event.getHoldingUserId());
			pStmt.setInt(11, event.getStatus());

			// SQL文を実行する
			if (pStmt.executeUpdate() == 1) {
				result = true;
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
		return result;
	}

	//ユーザーIDからイベント（参加）を表示
	public ArrayList<Event> searchuserId(int userId) {
		Connection conn = null;
		ArrayList<Event> cardList = new ArrayList<Event>();

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/IGNITE", "sa", "");

			// SQL文を準備する
			String sql = "SELECT * FROM event AS ev "
					+ "INNER JOIN event_user ON ev.id = event_user.event_id "
					+ "WHERE event_user.user_id = ?";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, "userId");

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
				cardList.add(record);
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

	//ユーザーIDからイベント（未参加）を表示
	public ArrayList<Event> fetchNotParticipatingList(int userId) {
		Connection conn = null;
		ArrayList<Event> cardList = new ArrayList<Event>();

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/IGNITE", "sa", "");

			// SQL文を準備する
			String sql = "select * from event "
					+ "where id in( "
					+ "　SELECT event_id FROM EVENT_USER "
					+ "　where user_id = ? and PARTICIPATION_STATUS = 0 "
					+ ")";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, "userId");

			ResultSet rs = pStmt.executeQuery();

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
				cardList.add(record);
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
		return cardList;
	}

	//eventIdからイベント詳細を表示
	public Event fetchParticipant(int eventId) {
		Connection conn = null;
		Event event = null;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/IGNITE", "sa", "");

			// SQL文を準備する
			String sql = "SELECT * FROM event WHERE id = ?";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, "eventId");

			ResultSet rs = pStmt.executeQuery();
			rs.next();
			// 結果表をコレクションにコピーする
			event = new Event(
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
		return event;
	}

	//ユーザーIDからその人が開催しているイベントを取得する
	public List<Event> searchHoldingEvent(int userId) {
		Connection conn = null;
		List<Event> cardList = new ArrayList<Event>();

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/IGNITE", "sa", "");

			// SQL文を準備する
			String sql = "SELECT * FROM event WHERE holding_user_id = ?";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, "userId");

			ResultSet rs = pStmt.executeQuery();
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
				cardList.add(record);
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

	//マッチングを行う
	public boolean insertMatchingData(int eventId) {
		Connection conn = null;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/IGNITE", "sa", "");

			//そのイベントの都道府県IDとカテゴリーを取得
			String sql = "select PREFECTURE_ID,EVENT_CATEGORY from event where id = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setInt(1, eventId);
			ResultSet rs = pStmt.executeQuery();
			rs.next();
			//取得値を変数に入れる
			int prefectureId = rs.getInt("prefecture_id"); //主催者が登録した都道府県
			int eventCategory = rs.getInt("EVENT_CATEGORY"); //主催者が登録したカテゴリ

			//都道府県IDとカテゴリをもとに合致するユーザーを取得
			sql = "select users.id from  users "
					+ "inner join user_prefecture "
					+ "on users.id = user_prefecture.user_id "
					+ "where  user_prefecture.PREFECTURE_ID = ? and event.EVENT_CATEGORY = ?";

			pStmt.setInt(1, prefectureId);
			pStmt.setInt(2, eventCategory);
			rs = pStmt.executeQuery();

			//取得したユーザーIDのリスト
			List<Integer> matchUserList = new ArrayList<Integer>();
			while (rs.next()) {
				matchUserList.add(rs.getInt("id"));
			}

			//リストの中身をインサートする
			for (int userId : matchUserList) {
				sql = "INSERT FROM event_user VALUES (NULL, ?,?,0)";
				pStmt.setInt(1, eventId);
				pStmt.setInt(2, userId);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return false;
				}
			}
		}

		// 結果を返す
		return true;
	}

	//イベントIDからユーザーIDを取得
	public List<Integer> sendParticipant(int eventId) {
		Connection conn = null;
		List<Integer> cardList = new ArrayList<Integer>();

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/IGNITE", "sa", "");

			// SQL文を準備する
			String sql = "SELECT user_id FROM event_user WHERE event_id = ? AND participation_status = 1";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, "eventId");

			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				cardList.add(rs.getInt("user_id"));
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

	//参加処理、event_userのステータスを0から1に変更する
	public boolean update(int eventId, int userId) {
		Connection conn = null;
		boolean result = false;
		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");
			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/IGNITE", "sa", "");
			// SQL文を準備する（AUTO_INCREMENTのNUMBER列にはNULLを指定する）
			String sql = "SELECT * FROM EVENT_USER "
					+ "where event_id = ? and user_id = ? and PARTICIPATION_STATUS  = 0";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			// SQL文を完成させる
			pStmt.setInt(1, eventId);
			pStmt.setInt(2, userId);
			// SQL文を実行する
			if (pStmt.executeUpdate() == 1) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					result = false;
				}
			}
		}
		// 結果を返す
		return result;
	}

	//イベントIDからevent_userを検索してリストを返す
	public List<EventUser> searchUserEvent(int userId) {
			Connection conn = null;
			List<EventUser> eventCardList = new ArrayList<EventUser>();

			try {
				// JDBCドライバを読み込む
				Class.forName("org.h2.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/IGNITE", "sa", "");

				// SQL文を準備する
				String sql = "SELECT * FROM event_user WHERE event_id = ? AND participation_status = 1";

				PreparedStatement pStmt = conn.prepareStatement(sql);
				pStmt.setString(1, "userId");

				ResultSet rs = pStmt.executeQuery();
				// 結果表をコレクションにコピーする
				while (rs.next()) {
					EventUser record = new EventUser(
							rs.getInt("id"),
							rs.getInt("event_id"),
							rs.getInt("user_id"),
							rs.getInt("participation_status"));
					eventCardList.add(record);
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
			return eventCardList;
		}

}
