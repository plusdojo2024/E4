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
				conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/IGNITE", "sa", "");

				// SQL文を準備する
				String sql = "SELECT password FROM users WHERE mail_address = ?";
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
				conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/IGNITE", "sa", "");

				// SQL文を準備する
				String sql = "SELECT id FROM users WHERE mail_address = ?";
				PreparedStatement pStmt = conn.prepareStatement(sql);

				pStmt.setString(1,mailAddress);

				ResultSet rs = pStmt.executeQuery();
				rs.next();

				result = rs.getString("id");


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

		//ユーザーのデータを保存
		public int insert(Users users) {
			Connection conn = null;
			int result = 0;

			try {
				// JDBCドライバを読み込む
				Class.forName("org.h2.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/IGNITE", "sa", "");

				// SQL文を準備する（AUTO_INCREMENTのNUMBER列にはNULLを指定する）
				String sql = "INSERT INTO users VALUES (NULL,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				PreparedStatement pStmt = conn.prepareStatement(sql);

				// SQL文を完成させる
				pStmt.setString(1, users.getMailAddress());
				pStmt.setString(2, users.getPassword());
				pStmt.setString(3, users.getName());
				pStmt.setString(4, users.getBirthDate());
				pStmt.setString(5, users.getTelNum());
				pStmt.setInt(6, users.getGender());
				pStmt.setInt(7, users.getPrefectureId());
				pStmt.setInt(8, users.getEventCategory());
				pStmt.setInt(9, users.getOutdoorLevel());
				pStmt.setString(10, users. getRegisterYear());
				pStmt.setInt(11, users.getEvaluation());
				pStmt.setInt(12, users.getTechnicParam());
				pStmt.setInt(13, users.getCookParam());
				pStmt.setInt(14, users.getCommunicationParam());
				pStmt.setInt(15, users.getParticipantsAmount());
				pStmt.setInt(16, users.getHostedAmount());
				pStmt.setInt(17, users.getIconId());

				// SQL文を実行する
				if (pStmt.executeUpdate() == 0) {
					result = 0; //登録件数0のパターン
				} else if (pStmt.executeUpdate() == 1) {
					result = 1; //成功パターン
				}
			}
			catch (Exception e) {
				e.printStackTrace();
				return -1;
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
		public int insertSearchPrefecture(int userId,ArrayList<Integer> prefectures) {
			Connection conn = null;
			int result = 0;

			try {
				// JDBCドライバを読み込む
				Class.forName("org.h2.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/IGNITE", "sa", "");
				String sql;

				for(int prefectureId :prefectures) {
					sql = "INSERT INTO user_prefecture VALUES (NULL, ?, ?)";
					PreparedStatement pStmt = conn.prepareStatement(sql);

					// SQL文を完成させる
					pStmt.setInt(1, userId);
					pStmt.setInt(2, prefectureId);

					if (pStmt.executeUpdate() >= 0) {
						 result++;
					}
				}
			}
			catch (Exception e) {
				e.printStackTrace();
				return -1;

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

		public int[] update(Users users,String telNum,int prefectureId,int eventCategory,ArrayList<Integer> prefectures) {
			Connection conn = null;
			int[] result = {0,0};

			try {
				// JDBCドライバを読み込む
				Class.forName("org.h2.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/IGNITE", "sa", "");

				// SQL文を準備する（AUTO_INCREMENTのNUMBER列にはNULLを指定する）
				String sql = "UPDATE users SET TEL_NUM = ?,PREFECTURE_ID = ?,EVENT_CATEGORY = ? WHERE ID = ?";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				// SQL文を完成させる
				pStmt.setString(1, telNum);
				pStmt.setInt(2, prefectureId);
				pStmt.setInt(3, eventCategory);
				pStmt.setInt(4, users.getId());

				if (pStmt.executeUpdate() == 0) {
					result[0] = 0;
				} else if (pStmt.executeUpdate() == 1) {
					result[0] = 1;
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

					if (pStmt.executeUpdate() == 0) {
						result[1] = 0;
					} else if (pStmt.executeUpdate() == 1) {
						result[1] = 1;
					}

				}
			}
			catch (Exception e) {
				e.printStackTrace();
				result[0] = -1;
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

		//評価の更新
		public int reviewParamUpdate(Users targetUsers,int evaluation,int technicParam,int cookParam,int communicationParam) {
			Connection conn = null;
			int result = 0;

			try {
				// JDBCドライバを読み込む
				Class.forName("org.h2.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/IGNITE", "sa", "");

				// SQL文を準備する（AUTO_INCREMENTのNUMBER列にはNULLを指定する）
				String sql = "UPDATE users SET EVALUATION = ?,COMMUNICATION_PARAM = ?,TECHNIC_PARAM = ?, COOK_PARAM = ? WHERE ID = ?";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				// SQL文を完成させる

				pStmt.setInt(1,targetUsers.getEvaluation() + evaluation);
				pStmt.setInt(2,targetUsers.getCommunicationParam() + communicationParam);
				pStmt.setInt(3,targetUsers.getTechnicParam() + technicParam);
				pStmt.setInt(4,targetUsers.getCookParam() + cookParam);
				pStmt.setInt(5, targetUsers.getId());

				// SQL文を実行する
				if (pStmt.executeUpdate() == 0) {
					result = 0;
				}else if (pStmt.executeUpdate() == 1) {
					result = 1;
				}
			}
			catch (Exception e) {
				e.printStackTrace();
				return -1;
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

		//アイコンのアップデート
		public int setIconUpdate(Users user,int iconId) {
			Connection conn = null;
			int result = 0;

			try {
				// JDBCドライバを読み込む
				Class.forName("org.h2.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/IGNITE", "sa", "");

				// SQL文を準備する（AUTO_INCREMENTのNUMBER列にはNULLを指定する）
				String sql = "UPDATE users SET ICON_ID = ? WHERE ID = ?";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				// SQL文を完成させる

				pStmt.setInt(1,iconId);
				pStmt.setInt(2,user.getId());

				// SQL文を実行する
				if (pStmt.executeUpdate() == 0) {
					result = 0;
				} else if (pStmt.executeUpdate() == 1) {
					result = 1;
				}
			}
			catch (Exception e) {
				e.printStackTrace();
				return -1;
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

		//評価されたものを表示
		public String[] fetchAchievements(int userId ) {
			Connection conn = null;
			String[] result = new String[7];

			try {
				// JDBCドライバを読み込む
				Class.forName("org.h2.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/IGNITE", "sa", "");

				// SQL文を準備する
				String sql = "SELECT name,register_year,hosted_amount,participants_amount,communication_param,technic_param,cook_param FROM users WHERE id = ?";
				PreparedStatement pStmt = conn.prepareStatement(sql);

				pStmt.setInt(1,userId);

				ResultSet rs = pStmt.executeQuery();
				rs.next();
				//評価等を配列に入れて終了（アイコン設定画面）
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
				conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/IGNITE", "sa", "");

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

		//idからユーザーのインスタンスを返す
		public Users fetchUser(int userId) {
			Connection conn = null;
			Users users = null;

			try {
				// JDBCドライバを読み込む
				Class.forName("org.h2.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/IGNITE", "sa", "");

				// SQL文を準備する
				String sql = "SELECT * FROM users WHERE id = ?";

				PreparedStatement pStmt = conn.prepareStatement(sql);
				pStmt.setInt(1, userId);

				ResultSet rs = pStmt.executeQuery();
				rs.next();
				// 結果表をコレクションにコピーする
				users = new Users(
						rs.getInt("id"),
						rs.getString("mail_address"),
						rs.getString("password"),
						rs.getString("name"),
						rs.getString("birth_date"),
						rs.getString("tel_num"),
						rs.getInt("gender"),
						rs.getInt("prefecture_id"),
						rs.getInt("event_category"),
						rs.getInt("outdoor_level"),
						rs.getString("register_year"),
						rs.getInt("evaluation"),
						rs.getInt("technic_param"),
						rs.getInt("cook_param"),
						rs.getInt("communication_param"),
						rs.getInt("participants_amount"),
						rs.getInt("hosted_amount"),
						rs.getInt("icon_id"));
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
			return users;
		}

}
