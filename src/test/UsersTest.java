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
		String password = "X03MO1qnZdYdgyfeuILPmQ==";

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
	  Users users = new Users(0,"user1@example.com","Passw0rd!","山田 次郎","1985-05-15","090-1234-5678",0,1,0,10,"2024-09-25 00:00:00",81,11,31,51,4,0,1) ;

	  int actual = userDao.insert(users);
	  int expected = 1;

	  assertEquals(expected, actual); //実際の実行結果と期待する実行結果が合っているか
	}

	//insertSearchPrefecture
	@Test
	void 参加可能範囲の保存成功() throws Exception {
	  int userId = 11;
	  ArrayList<Integer> prefectures = new ArrayList<Integer>();
	  prefectures.add(3);


	  int actual = userDao.insertSearchPrefecture(userId,prefectures);
	  int expected = 1;

	  assertEquals(expected, actual); //実際の実行結果と期待する実行結果が合っているか
	}

	//update
	@Test
	void ユーザーのデータの更新成功() throws Exception {
		int userId = 1;
	    Users users = new Users(userId,"user1@example.com","Passw0rd!","山田 三郎","1985-05-15","090-1234-5678",0,1,0,10,"2024-09-25 00:00:00",81,11,31,51,4,0,1) ;

	    String telNum = "0120-444-5555";
	    int prefectureid = 2;
	    int eventCategory = 0;
		  ArrayList<Integer> prefectures = new ArrayList<Integer>();
		  prefectures.add(2);
		  prefectures.add(3);
		  prefectures.add(4);
		  prefectures.add(5);
		  prefectures.add(6);
		  prefectures.add(7);


	    int[] actual = userDao.update(users,telNum,prefectureid,eventCategory,prefectures);
	    int[] expected = {1,1};
	    assertEquals(expected[0], actual[0]); //実際の実行結果と期待する実行結果が合っているか
	}

	//reviewParamUpdate
	@Test
	void 評価の更新成功() throws Exception {
		Users users = new Users(1,"user1@example.com","Passw0rd!","山田 太郎","1985-05-15","090-1234-5678",0,1,0,10,"2024-09-25 00:00:00",81,11,31,51,4,0,1) ;


	    int evluation = 200;
	    int technicParam = 200;
	    int cookParam = 200;
	    int communicationParam = 200;

	    int actual = userDao.reviewParamUpdate(users,evluation,technicParam,cookParam,communicationParam);
	    int expected = 1;

	    assertEquals(expected, actual);
	}

	//setIconUpdate
	@Test
	void アイコンの更新成功() throws Exception {
		Users users = new Users(1,"user1@example.com","Passw0rd!","山田 太郎","1985-05-15","090-1234-5678",0,1,0,10,"2024-09-25 00:00:00",81,11,31,51,4,0,1) ;
	    int iconId = 1;

	    int actual = userDao.setIconUpdate(users,iconId);
	    int expected = 1;
	    assertEquals(expected, actual);
	}

	//fetchAchievements
	@Test
	void 実績パラメータ取得成功() throws Exception {
	  int userId = 2;
	  String actual = userDao.fetchAchievements(userId)[6];

	  String expected = "41";

	  assertEquals(expected, actual);
	}

	//searchIcon
	@Test
	void アイコンの取得成功() throws Exception {
	  int userId = 2;

	  String actual = userDao.searchIcon(userId);
	  String expected = "img/icon/com/com_bronze.png";
	  assertEquals(expected, actual);
	}

	//fetchUser
	@Test
	void ユーザーIDからユーザーの取得成功() throws Exception {
		  int userId = 2;
		  Users user =  userDao.fetchUser(userId);
		  int actual = user.getEvaluation();

		  int expected = 71;
		  assertEquals(expected, actual);
		}
}