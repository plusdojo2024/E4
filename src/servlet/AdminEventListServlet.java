package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.EventDAO;
import model.Event;

/**
 * Servlet implementation class AdminEventListServlet
 */
@WebServlet("/AdminEventList")
public class AdminEventListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("userId") == null) {
			response.sendRedirect("/E4/Login");
			return;
		}

		// ユーザーIDに対応した作成イベント一覧を表示する。
		EventDAO eDao = new EventDAO();
		int userid = Integer.parseInt((String)session.getAttribute("userId"));
		List<Event> cardList_admin = eDao.searchHoldingEvent(userid);

		request.setAttribute("cardList_admin", cardList_admin);

		Map<Integer, String> Prefecture_name = new HashMap<>();

		String[] prefectures = {
	            "北海道", "青森県", "岩手県", "宮城県", "秋田県", "山形県", "福島県",
	            "茨城県", "栃木県", "群馬県", "埼玉県", "千葉県", "東京都", "神奈川県",
	            "新潟県", "富山県", "石川県", "福井県", "山梨県", "長野県",
	            "岐阜県", "静岡県", "愛知県", "三重県",
	            "滋賀県", "京都府", "大阪府", "兵庫県", "奈良県", "和歌山県",
	            "鳥取県", "島根県", "岡山県", "広島県", "山口県",
	            "徳島県", "香川県", "愛媛県", "高知県",
	            "福岡県", "佐賀県", "長崎県", "熊本県", "大分県", "宮崎県", "鹿児島県", "沖縄県"
	        };

		for (int i = 0; i < prefectures.length; i++) {
			Prefecture_name.put(i + 1, prefectures[i]);
		}

		request.setAttribute("cardList_prefecture", Prefecture_name);

		// イベントIDに対応した現在の参加予定人数のマップを作成する
		Map<Integer, Integer> JoinMember = new HashMap<>();
		for (int i = 0; i < cardList_admin.size(); i++) {
			int eventjoin_member = eDao.searchUserEvent(cardList_admin.get(i).getId()).size();
			JoinMember.put(cardList_admin.get(i).getId(), eventjoin_member);
		}

		request.setAttribute("cardList_joinMember", JoinMember);

//		数値に対応した募集レベルのテキストのマップを作成する
		Map<Integer, String> event_category = new HashMap<>();
		event_category.put(0, "初心者歓迎");
		event_category.put(1, "誰でも歓迎");
		event_category.put(2, "ベテラン向け");

		request.setAttribute("event_level", event_category);

		// 作成イベント一覧ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/adminEventList.jsp");
		dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("id") == null) {
			response.sendRedirect("/E4/Login");
			return;
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/adminEventList.jsp");
		dispatcher.forward(request, response);
	}

}
