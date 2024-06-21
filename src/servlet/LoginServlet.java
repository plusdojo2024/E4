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

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//リクエストスコープにエラーメッセージがあればjspにつめる
		//ログイン用jspに遷移}
		// ログインページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
		dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//リクエストパラメータから入力されたメールアドレスとパスワードを取得
		String mailAddress = request.getParameter("mailAddress");
	    String password = request.getParameter("password"); //右辺書き換えてください
	    String hashedpassword = null;
		//パスワードをMD5でハッシュ化
	    try {
	      MessageDigest md = MessageDigest.getInstance("MD5");
	      md.update(password.getBytes()); // ハッシュ化する処置
	      byte[] hashBytes = md.digest(); // ハッシュ化終了の処理

	      hashedpassword = Base64.getEncoder().encodeToString(hashBytes); // ハッシュ化したやつをString型に変換
	      //System.out.println("Hashed Password: " + hash);
	    } catch (NoSuchAlgorithmException e) {
	      e.printStackTrace();
	    }
		//UserDAOをインスタンス化
	    UsersDAO uDao = new UsersDAO();
		//メールアドレスとパスワードをuserDAO.isloginsuccessに渡して戻り値がfalseならエラー
	    if (uDao.isLoginSuccess(mailAddress,hashedpassword)) { //成功の場合
	    	String id = uDao.searchuserId(mailAddress);

	        HttpSession session = request.getSession();
	        session.setAttribute("id",id);

	        response.sendRedirect("/E4/TopServlet");
	    }
	    else {
	    //エラーならメッセージをリクエストスコープに詰めてGETへ
	    	request.setAttribute("error","※メールアドレスまたはパスワードに誤りがあります");
		//login.jspに遷移
	    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
	    dispatcher.forward(request,response);
	}
	}
}
