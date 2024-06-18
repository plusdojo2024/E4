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
				UsersDAO uDao = new UsersDAO();
				String mailAddress = request.getParameter("");
				String password = request.getParameter("");
				if (uDao.isLoginSuccess(mailAddress,password) == false) {
					response.sendRedirect("Servlet/LoginServlet");
					return;
				}

				// プロフィールページにフォワードする
				RequestDispatcher dispatcher = request.getRequestDispatcher("WebContent/WEB-INF/jsp/regist.jsp");
				dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
				HttpSession session = request.getSession();
				if (session.getAttribute("id") == null) {
					response.sendRedirect("Servlet/LoginServlet");
					return;
				}

				// リクエストパラメータを取得する
				request.setCharacterEncoding("UTF-8");
				Users users = ;
				String telNum = request.getParameter("tell");
				int prefectureId = request.getParameter("prefecture");
				int eventCategory = request.getParameter("switch-1");
				ArrayList<Integer> prefectures = new ArrayList<>();

				// splitメソッドを使用して文字列を配列に変換
		        String[] prefecture_string = request.getParameter("selected_prefectures").split(",");

		        for (int i = 0; i < prefecture_string.length; i++) {
		            prefectures.add(Integer.parseInt(prefecture_string[i]));
		        }

				// 更新を行う
				UsersDAO uDao = new UsersDAO();
				if (request.getParameter("submit").equals("更新")) {
					uDao.update(users, telNum, prefectureId, eventCategory, prefectures);
				}

				// プロフィールページにフォワードする
				RequestDispatcher dispatcher = request.getRequestDispatcher("WebContent/WEB-INF/jsp/result.jsp");
				dispatcher.forward(request, response);
	}

}
