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
 * Servlet implementation class JoinEventListServlet
 */
@WebServlet("/JoinEventListServlet")
public class JoinEventListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
//				HttpSession session = request.getSession();
//				if (session.getAttribute("userId") == null) {
//					response.sendRedirect("/E4/LoginServlet");
//					return;
//				}


				// ユーザーIDに対応した参加イベント一覧を表示する。
				EventDAO eDao = new EventDAO();
//				int userid = Integer.valueOf((String)session.getAttribute("userId"));
				int userid = 1;
				List<Event> cardList = eDao.searchuserId(userid);

				request.setAttribute("cardList", cardList);


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
//
//				String[] prefectures = {"プルダウン選択",
//			            "北海道", "青森県", "岩手県", "宮城県", "秋田県", "山形県", "福島県",
//			            "茨城県", "栃木県", "群馬県", "埼玉県", "千葉県", "東京都", "神奈川県",
//			            "新潟県", "富山県", "石川県", "福井県", "山梨県", "長野県",
//			            "岐阜県", "静岡県", "愛知県", "三重県",
//			            "滋賀県", "京都府", "大阪府", "兵庫県", "奈良県", "和歌山県",
//			            "鳥取県", "島根県", "岡山県", "広島県", "山口県",
//			            "徳島県", "香川県", "愛媛県", "高知県",
//			            "福岡県", "佐賀県", "長崎県", "熊本県", "大分県", "宮崎県", "鹿児島県", "沖縄県"
//			        };
//
//				request.setAttribute("cardList_prefecture", prefectures);


//				数値に対応した募集レベルのテキストのマップ作製
				Map<Integer, String> event_category = new HashMap<>();
				event_category.put(0, "初心者歓迎");
				event_category.put(1, "誰でも歓迎");
				event_category.put(2, "ベテラン向け");

				request.setAttribute("event_level", event_category);

				// 参加イベント一覧ページにフォワードする
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/joinEventList.jsp");
				dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
				HttpSession session = request.getSession();
				if (session.getAttribute("userId") == null) {
					response.sendRedirect("/E4/LoginServlet");
					return;
				}

				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/joinEventList.jsp");
				dispatcher.forward(request, response);
	}

}
