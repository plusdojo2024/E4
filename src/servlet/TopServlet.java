package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.EventDAO;
import dao.PrefectureDAO;
import dao.UsersDAO;
import model.Event;
import model.Users;

@WebServlet("/TopServlet")
public class TopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//セッションからユーザーIDを取得
		HttpSession session = request.getSession();
		session.setAttribute("user_id",2);
		/*if (session.getAttribute("userId") == null) {
		  response.sendRedirect("/E4/LoginServlet");
		  return;
		}*/

		//ユーザーDAOをインスタンス化
		UsersDAO userDAO = new UsersDAO();

		//userIdをuserDao.fetchUserに渡して都道府県コードを取得してリクエストスコープに詰める（天気予報取得用）
		int userId = (int)session.getAttribute("user_id");
		//int userId = Integer.parseInt(session.getAttribute("user_id"));
		//↓実際に使用するコード
		//int userId = Integer.parseInt(request.getParameter("user_id"));

		Users users = userDAO.fetchUser(2);
		//Users users = userDAO.fetchUser(userId);
		int prefectureId = users.getPrefectureId();
		request.setAttribute("prefectureId",prefectureId);

		//都道府県IDから都道府県のウェザーコードを取得
		PrefectureDAO prefecture = new PrefectureDAO();
		String prefectures = prefecture.fetchWeatherCode(prefectureId);

		request.setAttribute("prefectures",prefectures);

		//都道府県IDから都道府県名を取得
		String prefectureName = prefecture.searchPrefectureName(prefectureId);

		request.setAttribute("prefectureName",prefectureName);

		//EventDAOをインスタンス化
		EventDAO eventDAO = new EventDAO();

		//userIdをeventDaoに渡して通知されているイベントを取得・インスタンス化（リストにする）
		ArrayList<Event> eventList = eventDAO.fetchNotParticipatingList(2);
		//ArrayList<Event> eventList = eventDAO.fetchNotParticipatingList(userId);

		request.setAttribute("eventList",eventList);

		int iconId = users.getIconId();
		String iconUrl= userDAO.searchIcon(iconId);


		request.setAttribute("iconUrl",iconUrl);

		//top.jspに遷移
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/top.jsp");
		dispatcher.forward(request, response);
	}

}
