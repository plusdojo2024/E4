package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import dao.EventDAO;
import model.Event;

class EventTest {
	EventDAO eventDao = new EventDAO();

	@Test
	void ユーザーIDから参加イベント検索成功() throws Exception {
	  int userId = 1;

	  Event event = new Event(1, "花火大会","花火上げます","2020-09-08",4,10,1,"札幌市中央区北1条西2丁目1-1","のびのびキャンプ",2,1,0);
	  Event event2 = new Event(3, "鬼ごっこ","走ります","2021-02-03",6,12,3,"盛岡市中央通1丁目1-1","ふわふわキャンプ",0,30,0);
	  Event event3 = new Event(4, "逃走中","ハンターから逃げろ","	2022-05-09",7,13,4,"仙台市青葉区一番町1丁目1-1","グリーンキャンプ",2,4,0);
	  ArrayList<Event> actualList = eventDao.searchuserId(userId);
	  ArrayList<Event> expectedList = new ArrayList<>();

	  //List<Integer>

	  expectedList.add(event);
	  expectedList.add(event2);
	  expectedList.add(event3);

	  assertEquals(actualList.size(),expectedList.size());

	}

}
