package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UsersDAO;
import model.Users;

/**
 * Servlet implementation class IconServlet
 */
@WebServlet("/IconServlet")
public class IconServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		HttpSession session = request.getSession();
//		if (session.getAttribute("userId") == null) {
//			response.sendRedirect("/E4/LoginServlet");
//			return;
//		}
		int userId = 1;
		UsersDAO userDao = new UsersDAO();
		Users user = userDao.fetchUser(userId);

		request.setAttribute("user", user);

		String[] registerYear = user.getRegisterYear().split(" ");

		user.setRegisterYear(registerYear[0]);

		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/icon.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userId = 1;
		request.setCharacterEncoding("UTF-8");
		String telNum = request.getParameter("NewIconId");

		UsersDAO userDao = new UsersDAO();
		Users user = userDao.fetchUser(userId);

	}
}

