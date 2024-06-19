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
 * Servlet implementation class JoinEventListServlet
 */
@WebServlet("/JoinEventListServlet")
public class JoinEventListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
				HttpSession session = request.getSession();
				if (session.getAttribute("userid") == null) {
					response.sendRedirect("Servlet/LoginServlet");
					return;
				}


				// ユーザーIDに対応した参加イベント一覧を表示する。
				EventDAO eDao = new EventDAO();
				PrefectureDAO pDao = new PrefectureDAO();
				int userid = Integer.valueOf((String)session.getAttribute("userid"));
				List<Event> cardList = eDao.searchuserId(userid);

				request.setAttribute("cardList", cardList);


				List<String> Prefecture_Name = new ArrayList<>();
				for (int i = 0; i < Prefecture_Name.size(); i++) {
					String prefecture_name = pDao.searchuserId(cardList.get(i).getPrefectureId());
					Prefecture_Name.add(prefecture_name);
				}

				request.setAttribute("cardList_prefecture", Prefecture_Name);

				// 参加イベント一覧ページにフォワードする
				RequestDispatcher dispatcher = request.getRequestDispatcher("WebContent/WEB-INF/jsp/joinEventList.jsp");
				dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
				HttpSession session = request.getSession();
				if (session.getAttribute("userid") == null) {
					response.sendRedirect("Servlet/LoginServlet");
					return;
				}



				RequestDispatcher dispatcher = request.getRequestDispatcher("WebContent/WEB-INF/jsp/joinEventList.jsp");
				dispatcher.forward(request, response);
	}

}
