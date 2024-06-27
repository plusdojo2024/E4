package servlet;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UsersDAO;

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//ログイン用jspにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//リクエストパラメータから入力されたメールアドレスとパスワードを取得
		// 35～37行目 0622修正(フォーム入力値をString型に変換)：紺野
		request.setCharacterEncoding("UTF-8");
		String mailAddress = (String)request.getParameter("mailAddress");
		String password = (String)request.getParameter("password");
	    String hashedpassword = null;
		//パスワードをMD5でハッシュ化
	    try {
	      MessageDigest md = MessageDigest.getInstance("MD5");
	      md.update(password.getBytes()); // ハッシュ化する処置
	      byte[] hashBytes = md.digest(); // ハッシュ化終了の処理
	      hashedpassword = Base64.getEncoder().encodeToString(hashBytes); // ハッシュ化したやつをString型に変換

	    } catch (NoSuchAlgorithmException e) {
	      e.printStackTrace();
	    }
		//UserDAOをインスタンス化
	    UsersDAO uDao = new UsersDAO();
		//メールアドレスとパスワードをuserDAO.isloginsuccessに渡して戻り値がfalseならエラー
	    if (uDao.isLoginSuccess(mailAddress, hashedpassword)) { //成功の場合
	    	String id = uDao.searchuserId(mailAddress);
	        HttpSession session = request.getSession();
	        session.setAttribute("userId",id);
	        response.sendRedirect("/E4/Top");
	    }  else {
		    //エラーならメッセージをリクエストスコープに詰めてGETへ
		   request.setAttribute("error","※メールアドレスまたはパスワードに誤りがあります");
			//login.jspに遷移
		    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
		    dispatcher.forward(request,response);
	}
	}
}
