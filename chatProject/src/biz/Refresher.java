package biz;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ChatBean;
import bean.UserBean;
import model.RoomDAO;

@SuppressWarnings("serial")
@WebServlet("/refresh")
public class Refresher extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String roomId = (String) session.getAttribute("roomId");
		
		System.out.println("roomId " + roomId);
		System.out.println("lastChatId " + (String) session.getAttribute("lastChatId"));
		ArrayList<ChatBean> recentChats = RoomDAO.getRecentChats(roomId, (String) session.getAttribute("lastChatId"));
		
		String jsonData = "{'chats':[";
		boolean flag = true;
		for (ChatBean chat : recentChats) {
			jsonData += chatBean2Json(chat) + ",";
			if (flag) {
				flag = false;
			}
		}
		if (!flag) {
			jsonData = jsonData.substring(0, jsonData.length()-1);
		}
		jsonData += "],'users':[";
		
		ArrayList<UserBean> userList = RoomDAO.getRoom(roomId).getUserList();
		for (UserBean user : userList) {
			jsonData += userBean2Json(user) + ",";
		}
		jsonData = jsonData.substring(0, jsonData.length()-1);
		jsonData += "]}";
		
		if (recentChats.size() != 0) {
			session.setAttribute("lastChatId", recentChats.get(recentChats.size()-1).getChatId());
		}
		
		System.out.println(jsonData);
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(jsonData);
		out.close();
	}
	
	private String chatBean2Json(ChatBean chat) {
		String jsonData = "{'";
		jsonData += "chatId':'" + chat.getChatId() + "','";
		jsonData += "userNick':'" + chat.getUserNick() + "','";
		jsonData += "msg':'" + chat.getMessage() + "'}";
		return jsonData;
	}	
	private String userBean2Json(UserBean user) {
		String jsonData = "{'";
		jsonData += "userId':'" + user.getId() + "','";
		jsonData += "userNick':'" + user.getNickName() + "'}";
		return jsonData;
	}
}