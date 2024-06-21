package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.EventDAO;
import dao.PrefectureDAO;
import model.Event;
import model.EventUser;



/**
 * Servlet implementation class BeforeJoinDetailServlet
 */
@WebServlet("/BeforeJoinDetailServlet")
public class BeforeJoinDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("id") == null) {
			response.sendRedirect("/E4/LoginServlet");
			return;
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 詳細を表示したいイベントのIDを取得
		int eventId = Integer.parseInt(request.getParameter("event_id"));

		// イベントのIDを元にDBからイベントの情報を取得
		EventDAO eventDAO = new EventDAO();
		Event detailEvent = eventDAO.fetchParticipant(eventId);
		// 住所の結合
		// 都道府県の取得
		PrefectureDAO prefectureDAO = new PrefectureDAO();
		String prefecture = prefectureDAO.searchPrefectureName(detailEvent.getPrefectureId());
		String address = prefecture + detailEvent.getDetailAddress();
		// イベントIDを元にDBからイベント参加者を取得
		List<EventUser> eventUsers = eventDAO.searchUserEvent(eventId);
		int usersCount = 0; // 参加者のカウント数を保持
		//
		for (EventUser eventUser : eventUsers) {
			if (eventUser.getParticipation_status() == 1) {
				usersCount++;
			}
		}

		// リクエストスコープに詰めてJSPに渡す
		request.setAttribute("detailEvent", detailEvent);
		request.setAttribute("usersCount", usersCount);
		request.setAttribute("address", address);

		// 結果ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/eventDetail.jsp");
		dispatcher.forward(request, response);
	}
}
