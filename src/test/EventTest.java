package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dao.EventDAO;

class EventTest {
	EventDAO eventDao = new EventDAO();
	@Test
	void インサート成功() throws Exception {
		int  eventId = 7;

		boolean actual = eventDao.insertMatchingData(eventId); //実際の実行結果
		boolean expected = true; //期待する実行結果

		assertEquals(expected, actual); //実際の実行結果と期待する実行結果が合っているか
	}

}
