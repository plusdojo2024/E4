package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.IconDAO;
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

		IconDAO iconDao = new IconDAO();

		request.setCharacterEncoding("UTF-8");

		request.setAttribute("user", user);

		//登録年月日→登録年
		String[] registerYear = user.getRegisterYear().split(" ");
		user.setRegisterYear(registerYear[0]);

		//設定されているアイコンURL

		request.setAttribute("setIconUrl", iconDao.searchUrl(user.getIconId()));

		//実績→画像URL
		//cook
		if(user.getCookParam() < 50) {
			request.setAttribute("cookParamImg","img/icon/cook/cook_initial.png");
		} else if(user.getCookParam() < 200) {
			request.setAttribute("cookParamImg","img/icon/cook/cook_bronze.png");
		}else if(user.getCookParam() < 500) {
			request.setAttribute("cookParamImg","img/icon/cook/cook_silver.png");
		} else {
			request.setAttribute("cookParamImg","img/icon/cook/cook_gold.png");
		}

		//teck
		if(user.getTechnicParam() < 50) {
			request.setAttribute("technicParamImg","img/icon/technic/technic_initial.png");
		} else if(user.getTechnicParam() < 200) {
			request.setAttribute("technicParamImg","img/icon/technic/technic_bronze.png");
		}else if(user.getTechnicParam()  < 500) {
			request.setAttribute("technicParamImg","img/icon/technic/technic_silver.png");
		} else {
			request.setAttribute("technicParamImg","img/icon/technic/technic_gold.png");
		}

		//com
		if(user.getCommunicationParam() < 50) {
			request.setAttribute("communicationParamImg","img/icon/com/com_initial.png");
		} else if(user.getCommunicationParam() < 200) {
			request.setAttribute("communicationParamImg","img/icon/com/com_bronze.png");
		}else if(user.getCommunicationParam()< 500) {
			request.setAttribute("communicationParamImg","img/icon/com/com_silver.png");
		} else {
			request.setAttribute("communicationParamImg","img/icon/com/com_gold.png");
		}

		//host
		if(user.getHostedAmount() < 1) {
			request.setAttribute("hostedAmoundImg","img/icon/admin/admin_initial.png");
		} else if(user.getHostedAmount() < 7) {
			request.setAttribute("hostedAmoundImg","img/icon/admin/admin_bronze.png");
		}else if(user.getHostedAmount()< 15) {
			request.setAttribute("hostedAmoundImg","img/icon/admin/admin_silver.png");
		} else {
			request.setAttribute("hostedAmoundImg","img/icon/admin/gold.png");
		}

		//part
		if(user.getParticipantsAmount()< 3) {
			request.setAttribute("participantsAmountImg","img/icon/participation/participation_initial.png");
		} else if(user.getParticipantsAmount() < 15) {
			request.setAttribute("participantsAmountImg","img/icon/participation/participation_bronze.png");
		}else if(user.getParticipantsAmount()< 30) {
			request.setAttribute("participantsAmountImg","img/icon/participation/participation_silver.png");
		} else {
			request.setAttribute("participantsAmountImg","img/icon/participation/participation_gold.png");
		}


		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/icon.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userId = 1;


		IconDAO iconDao = new IconDAO();
		UsersDAO userDao = new UsersDAO();
		Users user = userDao.fetchUser(userId);
		request.setCharacterEncoding("UTF-8");
		int setIconId = Integer.parseInt(iconDao.searchId(request.getParameter("url")));

		userDao.setIconUpdate(user,setIconId);
		response.sendRedirect("IconServlet");

	}
}

