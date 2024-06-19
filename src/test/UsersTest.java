package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dao.UsersDAO;
import model.Users;

public class UsersTest {
	UsersDAO userDao = new UsersDAO();

	/*@Test
	void ログイン成功() throws Exception {
		String mailAddress = "user1@example.com";
		String password = "Passw0rd!";

		boolean actual = userDao.isLoginSuccess(mailAddress, password); //実際の実行結果
		boolean expected = true; //期待する実行結果

		assertEquals(expected, actual); //実際の実行結果と期待する実行結果が合っているか
	}

	@Test
	void ログイン失敗アドレス無し() throws Exception {
		String mailAddress = "none";
		String password = "none";

		boolean actual = userDao.isLoginSuccess(mailAddress, password);
		boolean expected = false;

		assertEquals(expected, actual);
	}
	@Test
	void ログイン失敗パスワード違い() throws Exception {
		String mailAddress = "user1@example.com";
		String password = "none";

		boolean actual = userDao.isLoginSuccess(mailAddress, password);
		boolean expected = false;

		assertEquals(expected, actual);
	}
	@Test
	void ログイン失敗入力無し() throws Exception {
		String mailAddress = "";
		String password = "";

		boolean actual = userDao.isLoginSuccess(mailAddress, password);
		boolean expected = false;

		assertEquals(expected, actual);
	}*/

	//searchuserId
	@Test
	void ユーザーID検索成功() throws Exception {
	  String mailAddress = "user1@example.com";

	  String actual = userDao.searchuserId(mailAddress); //実際の実行結果
	  String expected = "1";

	  assertEquals(expected, actual); //実際の実行結果と期待する実行結果が合っているか
	}

	//insert
	@Test
	void プロフィール保存成功() throws Exception {
	  Users users = new Users(null,"user1@example.com","Passw0rd!","山田 太郎","1985-05-15","090-1234-5678",0,1,0,10,"2024-09-25 00:00:00",81,11,31,51,4,0,1) ;

	  boolean actual = userDao.insert(users);
	  boolean expected = true;

	  assertEquals(expected, actual); //実際の実行結果と期待する実行結果が合っているか
	}

	//insertSearchPrefecture
	@Test
	void 参加可能範囲の保存成功() throws Exception {
	  int userId = "1";
	  ArrayList<Integer> prefectures = "1";//不安

	  boolean actual = userDao.insertSearchPrefecture(userId,prefectures);
	  boolean expected = true;

	  assertEquals(expected, actual); //実際の実行結果と期待する実行結果が合っているか
	}

	//update
	@Test
	void ユーザーのデータの更新成功() throws Exception {
	    Users users = new Users(null,"user1@example.com","Passw0rd!","山田 太郎","1985-05-15",0,1,0,10,"2024-09-25 00:00:00",81,11,31,51,4,0,1) ;
	    String teNum = "user01@example.com";
	    int prefectureid = 2;
	    int eventCategory = 1;
	    ArrayList<Integer> prefectures = "1";

	    boolean actual = userDao.update(users,telNum,prefectureid,eventCategory);
	    boolean expected = true;
	}

	//reviewParamUpdate
	@Test
	void 評価の更新成功() throws Exception {
	    Users users = new Users(null,"user1@example.com","Passw0rd!","山田 太郎","1985-05-15",0,1,0,10,"2024-09-25 00:00:00",81,11,31,51,4,0,1) ;
	    int evluation = 82;
	    int technicParam = 22;
	    int cookParam = 51;
	    int communicationParam = 81;
	    int targetUserId = 1

	    boolean actual = userDao.reviewParamUpdate(users,evluation,technicParam,cookParam,communicationParam,targetUserId);
	    boolean expected = true;
	}

	//setIconUpdate
	void アイコンの更新成功() throws Exception {
	    Users users = new Users(null,"user1@example.com","Passw0rd!","山田 太郎","1985-05-15",0,1,0,10,"2024-09-25 00:00:00",81,11,31,51,4,0,1) ;
	    int iconId = 2;

	    boolean actual = userDao.setIconUpdate(users.iconId);
	    boolean expected = true;
	}

	//fetchAchievements
	//searchIcon
	@Test
	void アイコンの取得成功() throws Exception {
	  int userId = 1;

	  String actual = userDao.searchIcon(userId);
	  String expected = "1";
	}
	//fetchUser

}