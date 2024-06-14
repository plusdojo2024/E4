package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/TopServlet")
public class TopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//セッションからユーザーIDを取得

		//ユーザーDAOをインスタンス化
		//userIdをuserDao.getUserLocationに渡して都道府県コードを取得してリクエストスコープに詰める（天気予報取得用）

		//EventDAOをインスタンス化
		//userIdをeventDaoに渡してマッチされているイベントを取得・インスタンス化（リストにする）


		//top.jspに遷移
	}

}
