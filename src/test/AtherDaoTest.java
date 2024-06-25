package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import dao.CommunicationDAO;
import dao.IconDAO;
import dao.PrefectureDAO;
import model.Communication;

class AtherDaoTest {
	CommunicationDAO communicationDao = new CommunicationDAO();
	IconDAO iconDao = new IconDAO();
	PrefectureDAO prefectureDao = new PrefectureDAO();
	//コミュニケーションDAO
	//searchuserId
	@Test
	void イベントIdからコミュニケーションを検索() throws Exception {
		int eventId = 1;
		ArrayList<Communication> actual = communicationDao.searchuserId(eventId); //実際の実行結果
		int expected = 3; //期待する実行結果
		assertEquals(expected, actual.size()); //実際の実行結果と期待する実行結果が合っているか
	}

	//insert
	@Test
	void コミュニケーションを登録() throws Exception {
		Communication communication = new Communication(0, 1, 2, "2024-06-20 00:00:00", "ここどこ？");

		int actual = communicationDao.insert(communication); //実際の実行結果
		int expected = 1; //期待する実行結果
		assertEquals(expected, actual); //実際の実行結果と期待する実行結果が合っているか
	}
	//アイコンDAO
	//searchuserId
	@Test
	void アイコンIDからurlを取得() throws Exception {
		int iconId = 1;
		String actual = iconDao.searchUrl(iconId); //実際の実行結果
		String expected = "/test"; //期待する実行結果
		assertEquals(expected, actual); //実際の実行結果と期待する実行結果が合っているか
	}

	//都道府県DAO
	//searchuserId
	@Test
	void 都道府県IDから都道府県名を取得() throws Exception {
		int prefectureId = 1;
		String actual = prefectureDao.searchPrefectureName(prefectureId); //実際の実行結果
		String expected = "北海道"; //期待する実行結果
		assertEquals(expected, actual); //実際の実行結果と期待する実行結果が合っているか
	}
	//fetchWeatherCode
	@Test
	void 都道府県IDから天気予報コードを取得() throws Exception {
		int prefectureId = 1;
		String actual = prefectureDao.fetchWeatherCode(prefectureId); //実際の実行結果
		String expected = "016000"; //期待する実行結果
		assertEquals(expected, actual); //実際の実行結果と期待する実行結果が合っているか
	}

}
