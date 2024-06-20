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
import model.Event;

/**
 * Servlet implementation class CreateEventServlet
 */
@WebServlet("/CreateEventServlet")
public class CreateEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateEventServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("userId") == null) {
			response.sendRedirect("/E4/LoginServlet");
			return;
		}

		// イベント作成ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/createEvent.jsp");
		dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("userId") == null) {
			response.sendRedirect("/E4/LoginServlet");
			return;
		}

		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		EventDAO eDao = new EventDAO();
		String event_name = request.getParameter("event_name");
		String event_description = request.getParameter("event_summary");
		String event_holdingschedule = request.getParameter("event_day");
		int least_count = Integer.valueOf(request.getParameter("min"));
		int max_count = Integer.valueOf(request.getParameter("max"));
		int prefectureId = Integer.valueOf(request.getParameter("prefecture"));
		String detail_address = request.getParameter("detail");
		String location_name = request.getParameter("place");
		int event_category = Integer.valueOf(request.getParameter("switch_2"));
		int holding_userid = Integer.valueOf((String)session.getAttribute("userId"));
		int status = 0;

		Event event = new Event(0, event_name, event_description, event_holdingschedule, least_count, max_count, prefectureId, detail_address, location_name, event_category, holding_userid, status);


		// イベント作成を行う
		if (request.getParameter("submit").equals("作成")) {
			if(eDao.keepEvent(event)) {
				// 作成が成功したことを表示
				boolean isCreateOK = true;
				request.setAttribute("isCreateOK", isCreateOK);
			} else {
				// 作成が失敗したことを表示
				boolean isCreateOK = false;
				request.setAttribute("isCreateOK", isCreateOK);
			}
		}

		// イベント作成ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/createEvent.jsp");
		dispatcher.forward(request, response);
	}

}
