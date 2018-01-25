package server;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.TimerTask;

import bean.RoomBean;
import bean.UserBean;
import model.RoomDAO;
import model.UserDAO;
import util.TimeParser;

public class RenewUserTask extends TimerTask {
	@Override
	public void run() {
		try {
			boolean flag = true;
			ArrayList<UserBean> userList = UserDAO.selectAllUser();
			int parsedCurrentTime = TimeParser.parse(System.currentTimeMillis());
			for (UserBean user : userList) {
				flag = true;
				String userId = user.getId();
				if (TimeParser.isExpired(user.getLastAccessTime(), parsedCurrentTime)) { // expired
					UserDAO.deleteUser(userId);
					ArrayList<RoomBean> roomList = RoomDAO.getAllRooms();
					for (RoomBean room : roomList) {
						if (flag) {
							ArrayList<UserBean> roomUserList = room.getUserList();
							int size = roomUserList.size();
							for (int idx = 0; idx < size; idx++) {
								if (roomUserList.get(idx).getId().equals(userId)) {
									roomUserList.remove(idx);
									flag = false; // user당 참여하는 채팅방이 2개 이상일 경우 수정 필요
								}
							}
						}
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
