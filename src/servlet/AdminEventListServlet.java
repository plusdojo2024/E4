package servlet;

import java.io.IOException;
import java.util.ArrayList;
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

/**
 * Servlet implementation class AdminEventListServlet
 */
@WebServlet("/AdminEventListServlet")
public class AdminEventListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("userid") == null) {
			response.sendRedirect("Servlet/LoginServlet");
			return;
		}


		// ユーザーIDに対応した作成イベント一覧を表示する。
		EventDAO eDao = new EventDAO();
		PrefectureDAO pDao = new PrefectureDAO();
		int userid = Integer.valueOf((String)session.getAttribute("userid"));
		List<Event> cardList_admin = eDao.searchHoldingEvent(userid);

		request.setAttribute("cardList_admin", cardList_admin);


		List<String> Prefecture_Name = new ArrayList<>();
		for (int i = 0; i < cardList_admin.size(); i++) {
			String prefecture_name = pDao.searchuserId(cardList_admin.get(i).getPrefectureId());
			Prefecture_Name.add(prefecture_name);
		}

		request.setAttribute("cardList_prefecture", Prefecture_Name);


		List<Integer> EventJoin_Member = new ArrayList<>();
		for (int i = 0; i < cardList_admin.size(); i++) {
			int eventjoin_member = eDao.searchUserEvent(cardList_admin.get(i).getId()).size();
			EventJoin_Member.add(eventjoin_member);
		}

		request.setAttribute("cardList_joinMember", Prefecture_Name);

		// 作成イベント一覧ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("WebContent/WEB-INF/jsp/adminEventList.jsp");
		dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("userid") == null) {
			response.sendRedirect("Servlet/LoginServlet");
			return;
		}



		RequestDispatcher dispatcher = request.getRequestDispatcher("WebContent/WEB-INF/jsp/adminEventList.jsp");
		dispatcher.forward(request, response);
	}

}
