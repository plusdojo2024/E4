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

import dao.UsersDAO;
import model.Users;


/**
 * Servlet implementation class ProfileServlet
 */
@WebServlet("/Profile")
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
					response.sendRedirect("/E4/Login");
					return;
				}

				request.setCharacterEncoding("UTF-8");
				UsersDAO uDao = new UsersDAO();
				int userid = Integer.parseInt((String) session.getAttribute("userId"));

				Users users = uDao.fetchUser(userid);
				String name = users.getName();
				String birthDate = users.getBirthDate();
				int gender = users.getGender();
				String telnum = users.getTelNum();
				int outlebel = users.getOutdoorLevel();
				int prefecture_ID = users.getPrefectureId();
				int eventcategori = users.getEventCategory();

				String Gender;
				if (gender == 0) {
					Gender = "男性";
				}else if (gender == 1) {
					Gender = "女性";
				}else {
					Gender = "回答しない";
				}

				List<String> user_profile = new ArrayList<>();
				user_profile.add(name);
				user_profile.add(birthDate);
				user_profile.add(Gender);

				List<Integer> user_ProFile = new ArrayList<>();
				user_ProFile.add(outlebel);
				user_ProFile.add(prefecture_ID);
				user_ProFile.add(eventcategori);

				request.setAttribute("user_profile", user_profile);
				request.setAttribute("user_ProFile", user_ProFile);
				request.setAttribute("telnum", telnum);

				// プロフィールページにフォワードする
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/profile.jsp");
				dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
				HttpSession session = request.getSession();
				if (session.getAttribute("userId") == null) {
					response.sendRedirect("/E4/Login");
					return;
				}

				// リクエストパラメータを取得する
				request.setCharacterEncoding("UTF-8");
				UsersDAO uDao = new UsersDAO();
				int userid = Integer.parseInt((String)session.getAttribute("userId"));
				Users users = uDao.fetchUser(userid);

				String name = users.getName();
				String birthDate = users.getBirthDate();
				int gender = users.getGender();
				String Gender;
				if (gender == 0) {
					Gender = "男性";
				}else if (gender == 1) {
					Gender = "女性";
				}else {
					Gender = "回答しない";
				}

				List<String> user_profile = new ArrayList<>();
				user_profile.add(name);
				user_profile.add(birthDate);
				user_profile.add(Gender);

				request.setAttribute("user_profile", user_profile);

				String telNum = request.getParameter("tell");
				int prefectureId = Integer.valueOf(request.getParameter("prefecture"));
				int eventCategory = Integer.parseInt(request.getParameter("switch_1"));
				ArrayList<Integer> prefectures = new ArrayList<>();

				// splitメソッドを使用して文字列を配列に変換
		        String[] prefecture_string = request.getParameter("selected_prefectures").split(",");

		        for (int i = 0; i < prefecture_string.length; i++) {
		            prefectures.add(Integer.valueOf(prefecture_string[i]));
		        }

				// 更新を行う
		        int[] user_update = uDao.update(users, telNum, prefectureId, eventCategory, prefectures);

				if(user_update[0] == 1 && user_update[1] == 1) {
					// 更新が成功した
					boolean isUpdateJudge = true;
					request.setAttribute("isUpdateJudge", isUpdateJudge);
				} else {
					boolean isUpdateJudge = false;
					request.setAttribute("isUpdateJudge", isUpdateJudge);
				}

				// プロフィールページにフォワードする
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/profile.jsp");
				dispatcher.forward(request, response);
	}

}
