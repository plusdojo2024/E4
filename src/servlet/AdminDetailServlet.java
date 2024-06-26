package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

import dao.CommunicationDAO;
import dao.EventDAO;
import dao.IconDAO;
import dao.PrefectureDAO;
import dao.UsersDAO;
import model.Chat;
import model.Communication;
import model.Event;
import model.EventUser;
import model.Users;

/**
 * Servlet implementation class BeforeJoinDetailServlet
 */
@WebServlet("/AdminDetail")
public class AdminDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("userId") == null) {
			response.sendRedirect("/E4/Login");
			return;
		}

		// イベント詳細はイベントIDがないと表示ができない→トップページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/top.jsp");
		dispatcher.forward(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String requestEventId = request.getParameter("event_id");

		// 必要なDAOインスタンス生成
		EventDAO eventDAO = new EventDAO();
		UsersDAO usersDAO = new UsersDAO();
		PrefectureDAO prefectureDAO = new PrefectureDAO();
		 CommunicationDAO comDAO = new CommunicationDAO();
		 IconDAO iconDAO = new IconDAO();

		 if (requestEventId == null) { // 非同期通信だったら
			// JSONテキストの取り出し
			//ポストデータからJSONテキストを取り出す
			BufferedReader br = new BufferedReader(request.getReader());
			String jsonText = br.readLine();
			// 取り出したJSONテキストをUTF-8にエンコード？
			jsonText = URLDecoder.decode(jsonText, "UTF-8");
			System.out.println("jsonText：" + jsonText);

			// JSONオブジェクトに変換
			JSONObject receivedJson = new JSONObject(jsonText);
			System.out.println("receivedJson：" + receivedJson);
			//DBへの登録
			// JSONオブジェクトからユーザーID、イベントID、チャット内容を保存
			int  jsonuserId =Integer.parseInt(receivedJson.getString("user_id"));
			int  jsoneventId =Integer.parseInt(receivedJson.getString("event_id"));
			String jsoncontent = receivedJson.getString("content");

			System.out.println("jsonuserId：" + jsonuserId);
			System.out.println("jsoneventId：" + jsoneventId);
			System.out.println("jsoncontent：" + jsoncontent);
			// 現在時刻を取得
			DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			LocalDateTime now =LocalDateTime.now();
			String timestamp = now.format(fmt);
			 // Communicationインスタンス生成
			 Communication com = new Communication(0, jsonuserId, jsoneventId, timestamp, jsoncontent);

			 boolean comResult;
			 if ( comDAO.insert(com) == 1 ) { // 登録が成功したら
				 comResult = true;
			 } else { // 失敗したら
				 comResult = false;
			 }

			 // JSONデータを送る準備
			 JSONArray JsonArrayToSend = new JSONArray(); // 最終的にこれを送る
			 JSONObject result = new JSONObject();
			 result.put("result", comResult);
			 JsonArrayToSend.put(result);
			 // チャット表示部分の更新用データを用意
			 ArrayList <Communication> comChat = comDAO.searchuserId(jsoneventId);
			 for (Communication chat : comChat) {
				 JSONObject chatJson = new JSONObject();
				 Users chatUser = usersDAO.fetchUser(chat.getUser_id());
				 String userName = chatUser.getName();
				 int userIcon = chatUser.getIconId();
				 chatJson.put("userName", userName);
				 chatJson.put("content", chat.getContent());
				 chatJson.put("userIcon", userIcon);
				 JsonArrayToSend.put(chatJson);
			 }

			 System.out.println(comChat);
			 System.out.println(JsonArrayToSend);

		    //httpヘッダー送信の登録
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "nocache");
			response.setCharacterEncoding("utf-8");
		    //送信データの登録
			PrintWriter out = response.getWriter();
			//送信データをネットストリームへ書き込む
			out.print(JsonArrayToSend);

		 } else { // 非同期通信でなければ
			// 詳細を表示したいイベントのIDを取得
			int eventId = Integer.parseInt(requestEventId);

			// イベントのIDを元にDBからイベントの情報を取得
			Event detailEvent = eventDAO.fetchParticipant(eventId);
			// 住所の結合
			// 都道府県の取得
			int prefectureId = detailEvent.getPrefectureId();
			String prefecture = prefectureDAO.searchPrefectureName(prefectureId);
			String address = prefecture + detailEvent.getDetailAddress();
			// イベントIDを元にDBからイベント参加者を取得
			List<EventUser> eventUsers = eventDAO.searchUserEvent(eventId); // イベントに参加するユーザーのIDリスト
			List<Users> participantsUsers = new ArrayList<Users>(); // イベント参加者の情報を保持するリスト
			int usersCount = 0;

			for (EventUser eventUser : eventUsers) {
				if (eventUser.getParticipation_status() == 1) {
					int eventUserId = eventUser.getUser_id();
					Users participantUser = usersDAO.fetchUser(eventUserId);
					participantsUsers.add(participantUser);
					usersCount++;
				}
			}

			// アイコンのURLをすべて取得
			Map<Integer, String> iconUrl = new HashMap<Integer, String>();
			for(int i = 1; i <= 20; i++) {
				String imgUrl = iconDAO.searchUrl(i);
				Integer num = Integer.valueOf(i);
				iconUrl.put(num, imgUrl);
			}

			 // チャット表示部分の更新用データを用意
			 ArrayList <Communication> comChat = comDAO.searchuserId(eventId);
			 ArrayList <Chat> chatList = new ArrayList<Chat>();

			 for (Communication chat : comChat ) {
				 String name = "";
				 int iconNum = 0;
				 for (Users user : participantsUsers) {
					 if (chat.getUser_id() == user.getId()) {
						 name = user.getName();
						 iconNum = user.getIconId();
						 break;
					 }
				 }
				chatList.add(new Chat(name, iconNum, chat.getContent()));
			 }
			 System.out.println("chatList" + chatList);

			// リクエストスコープに詰めてJSPに渡す
			request.setAttribute("detailEvent", detailEvent);
			request.setAttribute("participantsUsers", participantsUsers);
			request.setAttribute("usersCount", usersCount);
			request.setAttribute("iconUrl", iconUrl);
			request.setAttribute("chatList", chatList);
			request.setAttribute("address", address);

			// 結果ページにフォワードする
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/adminDetail.jsp");
			dispatcher.forward(request, response);
		 }
	}
}
