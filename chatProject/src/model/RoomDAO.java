package model;

import java.util.ArrayList;
import java.util.HashMap;

import bean.ChatBean;
import bean.RoomBean;
import bean.UserBean;

public class RoomDAO {
	private static ArrayList<RoomBean> roomList;
	private static HashMap<String, RoomBean> roomMap;
	private static HashMap<String, ArrayList<ChatBean>> chatMap;
	private static HashMap<String, String> lastChatIdMap;
	
	static {
		roomList = new ArrayList<RoomBean>();
		roomMap = new HashMap<String, RoomBean>();
		chatMap = new HashMap<String, ArrayList<ChatBean>>();
		lastChatIdMap = new HashMap<String, String>();
	}
	
	public static ArrayList<RoomBean> getAllRooms() {
		return roomList;
	}
	public static RoomBean getRoom(String roomId) {
		return roomMap.get(roomId);
	}
	public static String openRoom() {
		RoomBean newRoom = new RoomBean();
		String newRoomId = newRoom.getRoomId();
		roomList.add(newRoom);
		roomMap.put(newRoomId, newRoom);
		chatMap.put(newRoomId, new ArrayList<ChatBean>());
		lastChatIdMap.put(newRoomId, "0");
		return newRoomId;
	}
	public static boolean enterRoom(String roomId, UserBean user) {
		RoomBean room = roomMap.get(roomId);
		boolean ret = false;
		if (room != null) {
			synchronized (room) {
				ArrayList<UserBean> userList = room.getUserList();
				if (userList.size() < 7) {
					userList.add(user);
					room.setUserNum(room.getUserNum()+1);
					ret = true;
				}
			}
		}
		return ret;
	}
	public static boolean leaveRoom(String roomId, String userId) {
		RoomBean room = roomMap.get(roomId);
		boolean ret = false;
		if (room != null) {
			synchronized (room) {
				ArrayList<UserBean> userList = room.getUserList();
				int size = userList.size();
				for (int idx = 0; idx < size; idx++) {
					UserBean user = userList.get(idx);
					if (user.getId().equals(userId)) {
						userList.remove(idx);
						room.setUserNum(room.getUserNum()-1);
						ret = true;
					}
				}
			}
		}
		return ret;
	}
	public static boolean closeRoom(String roomId) {
		if (roomMap.containsKey(roomId)) {
			int size = roomList.size();
			for (int idx = 0; idx < size; idx++) {
				if (roomList.get(idx).getRoomId().equals(roomId)) {
					roomList.remove(idx);
					break;
				}
			}
			roomMap.remove(roomId);
			chatMap.remove(roomId);
			lastChatIdMap.remove(roomId);
			return true;
		}
		return false;
	}
	public static ArrayList<ChatBean> getRecentChats(String roomId, String lastChatId) {
		if (chatMap.containsKey(roomId)) {
			ArrayList<ChatBean> chatList = chatMap.get(roomId);
			ArrayList<ChatBean> recentChats = new ArrayList<ChatBean>();
			for (ChatBean chat : chatList) {
				if (Integer.parseInt(chat.getChatId()) > Integer.parseInt(lastChatId)) {
					recentChats.add(chat);
				}
			}
			return recentChats;
		}
		return null;
	}
	public static boolean enrollChat(String roomId, String userNick, String msg) {
		if (chatMap.containsKey(roomId)) {
			ArrayList<ChatBean> chatList = chatMap.get(roomId);
			synchronized (chatList) {
				int size = chatList.size();
				String lastChatId = "1";
				if (size != 0) {
					lastChatId = String.valueOf(Integer.parseInt(lastChatIdMap.get(roomId)) + 1);
					if (size == 7) {
						chatList.remove(0);
					}
				}
				chatList.add(new ChatBean(lastChatId, userNick, msg));
				lastChatIdMap.put(roomId, lastChatId);
			}
			return true;
		}
		return false;
	}
	public static String getLastChatId(String roomId) {		
		return lastChatIdMap.get(roomId);
	}
}