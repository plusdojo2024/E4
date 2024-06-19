package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import dao.UsersDAO;
import model.Users;

public class UsersTest {
	UsersDAO userDao = new UsersDAO();

	@Test
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
	}

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
	  Users users = new Users(0,"user1@example.com","Passw0rd!","山田 太郎","1985-05-15","090-1234-5678",0,1,0,10,"2024-09-25 00:00:00",81,11,31,51,4,0,1) ;

	  boolean actual = userDao.insert(users);
	  boolean expected = true;

	  assertEquals(expected, actual); //実際の実行結果と期待する実行結果が合っているか
	}

	//insertSearchPrefecture
	@Test
	void 参加可能範囲の保存成功() throws Exception {
	  int userId = 11;
	  ArrayList<Integer> prefectures = new ArrayList<Integer>();
	  prefectures.add(3);

	  boolean actual = userDao.insertSearchPrefecture(userId,prefectures);
	  boolean expected = true;

	  assertEquals(expected, actual); //実際の実行結果と期待する実行結果が合っているか
	}

	//update　修正必要
	@Test
	void ユーザーのデータの更新成功() throws Exception {
		int userId = 11;
	    Users users = new Users(userId,"user1@example.com","Passw0rd!","山田 三郎","1985-05-15","090-1234-5678",0,1,0,10,"2024-09-25 00:00:00",81,11,31,51,4,0,1) ;

	    String telNum = "090-0000-0000";
	    int prefectureid = 2;
	    int eventCategory = 1;
		  ArrayList<Integer> prefectures = new ArrayList<Integer>();
		  prefectures.add(1);


	    boolean actual = userDao.update(users,telNum,prefectureid,eventCategory,prefectures);
	    boolean expected = true;
	    assertEquals(expected, actual); //実際の実行結果と期待する実行結果が合っているか
	}

	//reviewParamUpdate tagetどうする？
//	@Test
//	void 評価の更新成功() throws Exception {
//		int userId = 11;
//		Users users = new Users(userId,"user1@example.com","Passw0rd!","山田 太郎","1985-05-15","090-1234-5678",0,1,0,10,"2024-09-25 00:00:00",81,11,31,51,4,0,1) ;
//
//
//	    int evluation = 100;
//	    int technicParam = 100;
//	    int cookParam = 100;
//	    int communicationParam = 100;
//	    int targetUserId = 1;
//
//	    boolean actual = userDao.reviewParamUpdate(users,evluation,technicParam,cookParam,communicationParam,targetUserId);
//	    boolean expected = true;
//	}

	//setIconUpdate
	@Test
	void アイコンの更新成功() throws Exception {
		Users users = new Users(1,"user1@example.com","Passw0rd!","山田 太郎","1985-05-15","090-1234-5678",0,1,0,10,"2024-09-25 00:00:00",81,11,31,51,4,0,1) ;
	    int iconId = 1;

	    boolean actual = userDao.setIconUpdate(users,iconId);
	    boolean expected = true;
	    assertEquals(expected, actual);
	}

	//fetchAchievements
	//searchIcon
	@Test
	void アイコンの取得成功() throws Exception {
	  int userId = 1;

	  String actual = userDao.searchIcon(userId);
	  String expected = "/test";
	  assertEquals(expected, actual);
	}
	//fetchUser

}