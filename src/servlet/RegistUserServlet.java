package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RegistUserServlet")
public class RegistUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//エラーメッセージがあれば変数に入れる
		//新規登録画面jspに遷移
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//リクエストスコープからフォーム入力値を取得
		//エラーがあればメッセージをリクエストスコープに入れてGETへ

		//UserDaoインスタンス化
		//パスワードをMD5でハッシュ化
		//フォーム入力内容を元にuserをインスタンス化
		//userDao.inseartにuserインスタンスを渡してfalseが返ればエラー

		//ログインjspに遷移
	}

}
