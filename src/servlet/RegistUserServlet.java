package servlet;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UsersDAO;
import model.Users;

@WebServlet("/RegistUser")
public class RegistUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 新規登録ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/regist.jsp");
		dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//UserDaoインスタンス化
		//userDao.inseartにuserインスタンスを渡してfalseが返ればエラー
		request.setCharacterEncoding("UTF-8");
		UsersDAO uDao = new UsersDAO();
		String mailaddress = request.getParameter("mail");
		String password = request.getParameter("pw");

		//パスワードをMD5でハッシュ化
		String hashpassword = "";
	    try {
	      MessageDigest md = MessageDigest.getInstance("MD5");
	      md.update(password.getBytes()); // ハッシュ化する処置
	      byte[] hashBytes = md.digest(); // ハッシュ化終了の処理
	      System.out.println(hashBytes);
	      hashpassword = Base64.getEncoder().encodeToString(hashBytes); // ハッシュ化したやつをString型に変換
	    } catch (NoSuchAlgorithmException e) {
	      e.printStackTrace();
	    }
	    // フォーム入力値の取得
		String name = request.getParameter("name");
		String birthdate = request.getParameter("year") + "-" + request.getParameter("month") + "-"+ request.getParameter("day");
		String telnum = request.getParameter("tell");
		int gender = Integer.valueOf(request.getParameter("switch_1"));
		int prefecture_id = Integer.valueOf(request.getParameter("prefecture"));
		int event_category = Integer.valueOf(request.getParameter("switch_2"));
		int outdoor_level = Integer.valueOf(request.getParameter("outdoorLevel"));

		// 現在の日付を取得
		LocalDate today = LocalDate.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		String register_year = today.format(formatter);

		int evaluation = 0;
		int technic_param = 0;
		int cook_param = 0;
		int communication_param = 0;
		int participants_amount = 0;
		int hostedAmount = 0;
		int icon_id = 1;

		//フォーム入力内容を元にuserをインスタンス化
		Users users = new Users(0, mailaddress, hashpassword, name, birthdate, telnum, gender, prefecture_id, event_category, outdoor_level, register_year, evaluation, technic_param, cook_param, communication_param, participants_amount, hostedAmount, icon_id);

		//新規登録に成功すれば、ログインjspに遷移
		if (uDao.insert(users) == 1) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/login.jsp");
			dispatcher.forward(request, response);
		} else {
			boolean isRegistJudge = false;
			request.setAttribute("isRegistJudge", isRegistJudge);

			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/regist.jsp");
			dispatcher.forward(request, response);
		}

	}

}
