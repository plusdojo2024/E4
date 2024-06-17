package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dao.UsersDAO;

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

}