package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import dao.CommunicationDAO;
import dao.EventDAO;
import dao.IconDAO;
import dao.PrefectureDAO;
import dao.UsersDAO;
import model.Communication;
import model.Event;
import model.EventUser;
import model.Users;

/**
 * Servlet implementation class BeforeJoinDetailServlet
 */
@WebServlet("/JoinDetailServlet")
public class JoinDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	/*	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// もしもログインしていなかったらログインサーブレットにリダイレクトする
			HttpSession session = request.getSession();
			if (session.getAttribute("id") == null) {
				response.sendRedirect("/E4/LoginServlet");
				return;
			}
		}*/

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		// 詳細を表示したいイベントのIDを取得
		 int eventId = 1;

		// 必要なDAOインスタンス生成
		EventDAO eventDAO = new EventDAO();
		UsersDAO usersDAO = new UsersDAO();
		PrefectureDAO prefectureDAO = new PrefectureDAO();
		 CommunicationDAO comDAO = new CommunicationDAO();
		 IconDAO iconDAO = new IconDAO();


		 // JSONデータを送る準備
		 JSONArray JsonArrayToSend = new JSONArray(); // 最終的にこれを送る
		//JSONObject result = new JSONObject();
		//JsonArrayToSend.put(result);
		 // チャット表示部分の更新用データを用意
		 ArrayList <Communication> comChatTest = comDAO.searchuserId(eventId);
		 for (Communication chat : comChatTest) {
			 JSONObject chatJson = new JSONObject();
			 Users chatUser = usersDAO.fetchUser(chat.getUser_id());
			 String userName = chatUser.getName();
			 int userIcon = chatUser.getIconId();
			 chatJson.put("userName", userName);
			 chatJson.put("content", chat.getContent());
			 chatJson.put("userIcon", userIcon);
			 JsonArrayToSend.put(chatJson);
		 }

		 System.out.println(comChatTest);
		 System.out.println(JsonArrayToSend);



		//int eventId = Integer.parseInt(request.getParameter("event_id"));

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
		 Map <Integer, Map<String, String>> comChatMap = new LinkedHashMap<Integer, Map<String, String>>();
		 int i = 0;
		 for (Communication chat : comChat) {
			 Map<String, String> tmpMap = new HashMap<String, String>();
			 Users chatUser = usersDAO.fetchUser(chat.getUser_id());
			 String chatUserName = chatUser.getName();
			 int userIcon = chatUser.getIconId();
			 String chatIconUrl = iconUrl.get(Integer.valueOf(userIcon));
			 tmpMap.put("chatUserName", chatUserName);
			 tmpMap.put("content", chat.getContent());
			 tmpMap.put("chatIconUrl", chatIconUrl);
			 comChatMap.put(Integer.valueOf(i), tmpMap);
			 i++;
		 }

		 System.out.println(comChatMap);



		// リクエストスコープに詰めてJSPに渡す
		request.setAttribute("detailEvent", detailEvent);
		request.setAttribute("participantsUsers", participantsUsers);
		request.setAttribute("usersCount", usersCount);
		request.setAttribute("iconUrl", iconUrl);
		request.setAttribute("comChatMap", comChatMap);
		request.setAttribute("address", address);

		// 結果ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/joinDetail.jsp");
		dispatcher.forward(request, response);
	 }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		// 必要なDAOインスタンス生成
		EventDAO eventDAO = new EventDAO();
		UsersDAO usersDAO = new UsersDAO();
		PrefectureDAO prefectureDAO = new PrefectureDAO();
		 CommunicationDAO comDAO = new CommunicationDAO();
		 IconDAO iconDAO = new IconDAO();

		 String chatContent = request.getParameter("content");

		 if (chatContent != null) { // 非同期通信だったら
			// JSONテキストの取り出し
			//ポストデータからJSONテキストを取り出す
			//BufferedReader br = new BufferedReader(request.getReader());
			//String jsonText = br.readLine();
			// 取り出したJSONテキストをUTF-8にエンコード？
			//jsonText = URLDecoder.decode(jsonText, "UTF-8");
			// JSONオブジェクトに変換
			//JSONObject receivedJson = new JSONObject(jsonText);

			//DBへの登録
			// JSONオブジェクトからユーザーID、イベントID、チャット内容を保存
			int  userId =Integer.parseInt(request.getParameter("user_id"));
			int  eventId =Integer.parseInt(request.getParameter("event_id"));
			String content = request.getParameter("content");
			//int  userId =Integer.parseInt( receivedJson.getString("user_id"));
			//int  eventId =Integer.parseInt( receivedJson.getString("event_id"));
			//String content = receivedJson.getString("content");
			// 現在時刻を取得
			DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			LocalDateTime now =LocalDateTime.now();
			String timestamp = now.format(fmt);
			 // Communicationインスタンス生成
			 Communication com = new Communication(0, userId, eventId, timestamp, content);

			 boolean comResult;
			 if ( comDAO.insert(com) ) { // 登録が成功したら
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
			 ArrayList <Communication> comChat = comDAO.searchuserId(eventId);
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
			 int eventId = 1;
			//int eventId = Integer.parseInt(request.getParameter("event_id"));

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
			 Map <Integer, Map<String, String>> comChatMap = new LinkedHashMap<Integer, Map<String, String>>();
			 int i = 0;
			 for (Communication chat : comChat) {
				 Map<String, String> tmpMap = new HashMap<String, String>();
				 Users chatUser = usersDAO.fetchUser(chat.getUser_id());
				 String chatUserName = chatUser.getName();
				 int userIcon = chatUser.getIconId();
				 String chatIconUrl = iconUrl.get(Integer.valueOf(userIcon));
				 tmpMap.put("chatUserName", chatUserName);
				 tmpMap.put("content", chat.getContent());
				 tmpMap.put("chatIconUrl", chatIconUrl);
				 comChatMap.put(Integer.valueOf(i), tmpMap);
				 i++;
			 }

			// リクエストスコープに詰めてJSPに渡す
			request.setAttribute("detailEvent", detailEvent);
			request.setAttribute("participantsUsers", participantsUsers);
			request.setAttribute("usersCount", usersCount);
			request.setAttribute("iconUrl", iconUrl);
			request.setAttribute("comChatMap", comChatMap);
			request.setAttribute("address", address);

			// 結果ページにフォワードする
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/joinDetail.jsp");
			dispatcher.forward(request, response);
		 }
	}
}


