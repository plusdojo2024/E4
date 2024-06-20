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

import dao.UsersDAO;
import model.Users;


/**
 * Servlet implementation class ProfileServlet
 */
@WebServlet("/ProfileServlet")
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfileServlet() {
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

				// プロフィールページにフォワードする
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/regist.jsp");
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
				UsersDAO uDao = new UsersDAO();
				int userid = Integer.valueOf((String)session.getAttribute("userId"));
				Users users = uDao.fetchUser(userid);
				String telNum = request.getParameter("tell");
				int prefectureId = Integer.valueOf(request.getParameter("prefecture"));
				int eventCategory = Integer.valueOf(request.getParameter("switch-1"));
				ArrayList<Integer> prefectures = new ArrayList<>();

				// splitメソッドを使用して文字列を配列に変換
		        String[] prefecture_string = request.getParameter("selected_prefectures").split(",");

		        for (int i = 0; i < prefecture_string.length; i++) {
		            prefectures.add(Integer.valueOf(prefecture_string[i]));
		        }

				// 更新を行う

				if (request.getParameter("submit").equals("更新")) {
					if(uDao.update(users, telNum, prefectureId, eventCategory, prefectures)[0] == 1 && uDao.update(users, telNum, prefectureId, eventCategory, prefectures)[1] == 1) {
						// 更新が成功した
						boolean isUpdateOK = true;
						request.setAttribute("isUpdateOK", isUpdateOK);
					} else {
						boolean isUpdateOK = false;
						request.setAttribute("isUpdateOK", isUpdateOK);
					}
				}

				// プロフィールページにフォワードする
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/result.jsp");
				dispatcher.forward(request, response);
	}

}
