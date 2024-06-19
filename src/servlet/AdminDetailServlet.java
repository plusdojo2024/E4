package servlet;

import java.io.IOException;

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



/**
 * Servlet implementation class BeforeJoinDetailServlet
 */
@WebServlet("/AdminDetailServlet")
public class AdminDetailServlet extends HttpServlet {
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
		String prefecture = prefectureDAO.searchuserId(detailEvent.getPrefectureId());
		String address = prefecture + detailEvent.getDetailAddress();
		// イベントIDを元にDBからイベント参加者の数を取得
		int usersCount = eventDAO.sendParticipant(eventId).size(); // EventDAOのsendParticipant()の返り値であるリストの数を使用する

		// リクエストスコープに詰めてJSPに渡す
		request.setAttribute("eventName", detailEvent.getEventName());
		request.setAttribute("eventDescription", detailEvent.getEventDescription());
		request.setAttribute("eventHoldingSchedule", detailEvent.getHoldingSchedule());
		request.setAttribute("leastCount", detailEvent.getLeastCount());
		request.setAttribute("maxCount", detailEvent.getMaxCount());
		request.setAttribute("usersCount", usersCount);
		request.setAttribute("address", address);
		request.setAttribute("locationName", detailEvent.getLocationName());
		request.setAttribute("eventCategory", detailEvent.getEventCategory());
		request.setAttribute("holdingUserId", detailEvent.getHoldingUserId());
		request.setAttribute("status", detailEvent.getStatus());

		// 結果ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/eventDetail.jsp");
		dispatcher.forward(request, response);
	}

}
