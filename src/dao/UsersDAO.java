package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Users;


public class UsersDAO {
	// ログインの成功失敗を判定
		public boolean isLoginSuccess(String mailAddress,String password) {
			Connection conn = null;
			boolean result = false;

			try {
				// JDBCドライバを読み込む
				Class.forName("org.h2.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/BC_PROTTYPE", "sa", "");

				// SQL文を準備する
				String sql = "SELECT password FROM user WHERE mail_address = ?";
				PreparedStatement pStmt = conn.prepareStatement(sql);

				pStmt.setString(1,mailAddress);

				ResultSet rs = pStmt.executeQuery();
				rs.next();

				if(password.equals(rs.getString("password"))){
					result = true;
				}



			}catch (Exception e) {
				e.printStackTrace();
				result = false;
			} finally {
				// データベースを切断
				if (conn != null) {
					try {
						conn.close();
					}
					catch (SQLException e) {
						e.printStackTrace();
						result = false;
					}
				}
			}

			// 結果を返す
			return result;
		}

		//メールアドレスからuserIDを検索して返す
		public String searchuserId(String mailAddress) {
			Connection conn = null;
			String result ="";

			try {
				// JDBCドライバを読み込む
				Class.forName("org.h2.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/BC_PROTTYPE", "sa", "");

				// SQL文を準備する
				String sql = "SELECT id FROM user WHERE mail_address = ?";
				PreparedStatement pStmt = conn.prepareStatement(sql);

				pStmt.setString(1,mailAddress);

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


		public boolean insert(Users users) {
			Connection conn = null;
			boolean result = false;

			try {
				// JDBCドライバを読み込む
				Class.forName("org.h2.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/simpleBC", "sa", "");

				// SQL文を準備する（AUTO_INCREMENTのNUMBER列にはNULLを指定する）
				String sql = "INSERT INTO users VALUES (NULL, ?, ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				PreparedStatement pStmt = conn.prepareStatement(sql);

				// SQL文を完成させる
				pStmt.setString(1, users.getMailAddress());
				pStmt.setString(2, users.getPassword());
				pStmt.setString(3, users.getName());
				pStmt.setString(4, users.getBirthDate());
				pStmt.setString(5, users.getTelNum());
				pStmt.setInt(6, users.getPrefectureId());
				pStmt.setInt(7, users.getEventCategory());
				pStmt.setInt(8, users.getOutdoorLevel());
				pStmt.setString(9, users. getRegisterYear());
				pStmt.setInt(10, users.getEvaluation());
				pStmt.setInt(11, users.getTechnicParam());
				pStmt.setInt(12, users.getCookPparam());
				pStmt.setInt(13, users.getCommunicationParam());
				pStmt.setInt(14, users.getParticipantsAmount());
				pStmt.setInt(15, users.getHostedAmount());
				pStmt.setInt(16, users.getIconId());

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

		public boolean insertSearchPrefecture(int userId,ArrayList<Integer> prefectures) {
			Connection conn = null;
			boolean result = false;

			try {
				// JDBCドライバを読み込む
				Class.forName("org.h2.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/simpleBC", "sa", "");
				String sql;

				for(int prefectureId :prefectures) {
					sql = "INSERT INTO users VALUES (NULL, ?, ?)";
					PreparedStatement pStmt = conn.prepareStatement(sql);

					// SQL文を完成させる
					pStmt.setInt(1, userId);
					pStmt.setInt(2, prefectureId);

					if (pStmt.executeUpdate() != 1) {
						return false;
					}
				}

				result = true;

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