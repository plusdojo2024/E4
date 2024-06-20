package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import dao.EventDAO;
import model.Event;
import model.EventUser;

class EventTest {
	EventDAO eventDao = new EventDAO();

	@Test
	void イベントテーブル登録成功() throws Exception {
	  Event event = new Event(0,"花火大会","花火上げます","2020-09-08",4,10,1,"札幌市中央区北1条西2丁目1-1","のびのびキャンプ",2,1,0);
	  boolean actual = eventDao.keepEvent(event); //実際の実行結果
	  boolean expected = true; //期待する実行結果
	  assertEquals(expected, actual); //実際の実行結果と期待する実行結果が合っているか
	}

	@Test
	void ユーザーIDから参加イベント検索成功() throws Exception {
	  int userId = 1;

	  Event event = new Event(1, "花火大会","花火上げます","2020-09-08",4,10,1,"札幌市中央区北1条西2丁目1-1","のびのびキャンプ",2,1,0);
	  Event event2 = new Event(3, "鬼ごっこ","走ります","2021-02-03",6,12,3,"盛岡市中央通1丁目1-1","ふわふわキャンプ",0,30,0);
	  Event event3 = new Event(4, "逃走中","ハンターから逃げろ","	2022-05-09",7,13,4,"仙台市青葉区一番町1丁目1-1","グリーンキャンプ",2,4,0);
	  ArrayList<Event> actualList = eventDao.searchuserId(userId);
	  ArrayList<Event> expectedList = new ArrayList<>();

	  expectedList.add(event);
	  expectedList.add(event2);
	  expectedList.add(event3);

	  assertEquals(actualList.size(),expectedList.size());
	}

	//fetchNotParticipatingList
	@Test
	void ユーザーIDから未参加イベント検索成功() throws Exception {
	  int userId = 2;

	  Event event = new Event(1, "花火大会","花火上げます","2020-09-08",4,10,1,"札幌市中央区北1条西2丁目1-1","のびのびキャンプ",2,1,0);
	  Event event2 = new Event(2, "肉焼き退会","肉焼きます","2020-12-09",5,11,2,"青森市中央1丁目1-1","がっちりキャンプ",1,2,0);

	  ArrayList<Event> actualList = eventDao.fetchNotParticipatingList(userId);
	  ArrayList<Event> expectedList = new ArrayList<>();

	  expectedList.add(event);
	  expectedList.add(event2);

	  assertEquals(actualList.size(),expectedList.size());
	}

	//fetchParticipant
	@Test
	void イベントIDからイベント詳細検索成功() throws Exception {
	  int eventId = 1;

	  Event actual = eventDao.fetchParticipant(eventId);
	  Event expected = new Event(1, "花火大会","花火上げます","2020-09-08",4,10,1,"札幌市中央区北1条西2丁目1-1","のびのびキャンプ",2,1,0);

	  assertEquals(actual.getLocationName(),expected.getLocationName());
	}

	//searchHoldingEvent
	@Test
	void ユーザーIDからその人が主催しているイベント検索成功() throws Exception {
	  int userId = 2;

	  Event event = new Event(2, "肉焼き退会","肉焼きます","2020-12-09",5,11,2,"青森市中央1丁目1-1","がっちりキャンプ",1,2,0);

	  ArrayList<Event> actualList = eventDao.searchHoldingEvent(userId);
	  ArrayList<Event> expectedList = new ArrayList<>();

	  expectedList.add(event);

	  assertEquals(actualList.size(),expectedList.size());
	}

	//update
	@Test
	void 参加処理event_userのステータスを0から1に変更する() throws Exception {
		int eventId = 4;
		int userId = 1;
		boolean actual = eventDao.update(eventId, userId);
		boolean expected = true;
		assertEquals(actual,expected);
	}

	//insertMatchingData
//	@Test
//	void イベントIDからマッチング通知対象のユーザーを登録() throws Exception {
//	  int userId = 2;
//
//	  Event event = new Event(2, "肉焼き退会","肉焼きます","2020-12-09",5,11,2,"青森市中央1丁目1-1","がっちりキャンプ",1,2,0);
//
//	  ArrayList<Event> actualList = eventDao.searchuserId(userId);
//	  ArrayList<Event> expectedList = new ArrayList<>();
//
//	  expectedList.add(event);
//
//	  assertEquals(actualList.size(),expectedList.size());
//	}





	//searchUserEven
	@Test
	void イベントIDからevent_userを検索してリストを返す() throws Exception {
		int eventId = 1;
		ArrayList<EventUser> actual = eventDao.searchUserEvent(eventId);
		int expected = 2;
		assertEquals(actual.size(),expected);
	}
}
