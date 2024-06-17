package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Users;


public class UsersDAO {
	// ログイン失敗かどうか判定する
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

				if(rs.next() == false) {
					return false;
				} else if(password.equals(rs.getString("password"))){
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
				pStmt.setInt(12, users.getCookParam());
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

		//参加可能範囲の情報
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
					sql = "INSERT INTO users_prefecture VALUES (NULL, ?, ?)";
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
		public boolean update(Users users,String telNum,int prefectureId,int eventCategory,ArrayList<Integer> prefectures) {
			Connection conn = null;
			boolean result = false;

			try {
				// JDBCドライバを読み込む
				Class.forName("org.h2.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/simpleBC", "sa", "");

				// SQL文を準備する（AUTO_INCREMENTのNUMBER列にはNULLを指定する）
				String sql = "UPDATE users SET TEL_NUM = ?,PREFECTURE_ID = ?,EVENT_CATEGORY = ? WHERE USER_ID = ?";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				// SQL文を完成させる
				pStmt.setString(1, telNum);
				pStmt.setInt(2, prefectureId);
				pStmt.setInt(3, eventCategory);
				pStmt.setInt(4, users.getId());

				// SQL文を実行する
				if (pStmt.executeUpdate() == 1) {
					result = true;
				}

				//都道府県更新
				sql = "DELETE FROM users_prefecture WHERE user_id = ?";

				// SQL文を完成させる
				pStmt.setInt(1, users.getId());
				for(int newPrefectureId :prefectures) {
					sql = "INSERT INTO user_prefecture VALUES (NULL, ?, ?)";

					// SQL文を完成させる
					pStmt.setInt(1, users.getId());
					pStmt.setInt(2, newPrefectureId);

					if (pStmt.executeUpdate() != 1) {
						return false;
					}
				}
			}
			catch (Exception e) {
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

		//評価の更新
		public boolean reviewParamUpdate(Users users,int evaluation,int communicationParam,int technicParam,int cookParam,int targetUserId) {
			Connection conn = null;
			boolean result = false;

			try {
				// JDBCドライバを読み込む
				Class.forName("org.h2.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/simpleBC", "sa", "");

				// SQL文を準備する（AUTO_INCREMENTのNUMBER列にはNULLを指定する）
				String sql = "UPDATE users SET EVALUATION = ?,COMMUNICATION_PARAM = ?,TECHINIC_PARAM = ?, COOK_PARAM = ? WHERE USER_ID = ?";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				// SQL文を完成させる

				pStmt.setInt(1,users.getEvaluation() + evaluation);
				pStmt.setInt(2,users.getCommunicationParam() + communicationParam);
				pStmt.setInt(3,users.getTechnicParam() + technicParam);
				pStmt.setInt(4,users.getCookParam() + cookParam);
				pStmt.setInt(5, targetUserId);

				// SQL文を実行する
				if (pStmt.executeUpdate() == 1) {
					result = true;
				}
			}
			catch (Exception e) {
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

		public boolean setIconUpdate(Users users,int iconId) {
			Connection conn = null;
			boolean result = false;

			try {
				// JDBCドライバを読み込む
				Class.forName("org.h2.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/simpleBC", "sa", "");

				// SQL文を準備する（AUTO_INCREMENTのNUMBER列にはNULLを指定する）
				String sql = "UPDATE users SET ICON_ID = ? WHERE USER_ID = ?";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				// SQL文を完成させる

				pStmt.setInt(1,users.getIconId());
				pStmt.setInt(2,users.getId());

				// SQL文を実行する
				if (pStmt.executeUpdate() == 1) {
					result = true;
				}
			}
			catch (Exception e) {
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

		public String[] fetchAchievements(String userId ) {
			Connection conn = null;
			String[] result = new String[7];

			try {
				// JDBCドライバを読み込む
				Class.forName("org.h2.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/BC_PROTTYPE", "sa", "");

				// SQL文を準備する
				String sql = "SELECT name,register_year,hosted_amount,participants_amount,communication_param,technic_param,cook_param FROM user WHERE id = ?";
				PreparedStatement pStmt = conn.prepareStatement(sql);

				pStmt.setString(1,userId);

				ResultSet rs = pStmt.executeQuery();
				rs.next();
				//評価等を配列に入れて終了（アイコン設定画面）
				//月曜日ここから
				result[0] = rs.getString("name");
				result[1] = rs.getString("register_year");
                result[2] = Integer.toString(rs.getInt("hosted_amount"));
                result[3] = Integer.toString(rs.getInt("participants_amount"));
                result[4] = Integer.toString(rs.getInt("communication_param"));
                result[5] = Integer.toString(rs.getInt("technic_param"));
                result[6] = Integer.toString(rs.getInt("cook_param"));


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

        //アイコンの取得
		public String searchIcon(int userId) {
			Connection conn = null;
			String result ="";

			try {
				// JDBCドライバを読み込む
				Class.forName("org.h2.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/BC_PROTTYPE", "sa", "");

				// SQL文を準備する
				String sql = "SELECT ac.url"
						+" FROM icon AS ac INNER JOIN users AS us"
						+" ON us.icon_id = ac.id"
						+" WHERE us.id = ?";
				PreparedStatement pStmt = conn.prepareStatement(sql);

				pStmt.setInt(1,userId);

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
