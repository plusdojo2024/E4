package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//リクエストスコープにエラーメッセージがあればjspにつめる
		//ログイン用jspに遷移
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//リクエストパラメータから入力されたメールアドレスとパスワードを取得
		//パスワードをMD5でハッシュ化

		//UserDAOをインスタンス化
		//メールアドレスをuserDAO.isOnlyMailAddressに渡して戻り値がfalseならエラー
		//パスワードをuserDAO.isMatchPasswordに渡して戻り値がfalseならエラー

		//エラーならメッセージをリクエストスコープに詰めてGETへ

		//メールアドレス、パスワードをuserDAO.getUserIdに渡して戻り値をセッションスコープ「userId」に詰める
		//top.jspに遷移
	}

}
