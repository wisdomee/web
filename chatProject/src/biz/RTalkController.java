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

import bean.RoomBean;
import bean.UserBean;
import model.RoomDAO;
import util.Sort;

@SuppressWarnings("serial")
@WebServlet("/rtalk")
public class RTalkController extends HttpServlet {
	protected synchronized void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute("user");
		String roomId = (String) session.getAttribute("roomId");
		String newRoomId = null;
		ArrayList<RoomBean> rooms = RoomDAO.getAllRooms();
		int cdnSize = rooms.size();
		if (roomId != null) { // from service.jsp
			cdnSize -= 1;
		}
		if (cdnSize == 0) { // create new room & push
			newRoomId = RoomDAO.openRoom();
			RoomDAO.enterRoom(newRoomId, user);
		} else {
			RoomBean[] roomArr = new RoomBean[cdnSize];
			int idx = 0;
			for (RoomBean room : rooms) {
				if (!room.getRoomId().equals(roomId)) {
					roomArr[idx++] = room;
				}
			}
			Sort.mergeSort(roomArr, 0, roomArr.length);
			int min = roomArr[0].getUserNum();
			if (min == 7) { // create new room & push
				newRoomId = RoomDAO.openRoom();
				RoomDAO.enterRoom(newRoomId, user);
			} else {
				for (idx = 0; idx < cdnSize; idx++) {
					if (roomArr[idx].getUserNum() != min) {
						break;
					}
				}
				newRoomId = roomArr[(int) (Math.random() * idx)].getRoomId();
				RoomDAO.enterRoom(newRoomId, user); // enter the other room
			}
		}
		
		if (roomId != null) { // not first request
			RoomDAO.leaveRoom(roomId, user.getId()); // leave the old room
		}

		session.setAttribute("roomId", newRoomId);
		session.setAttribute("lastChatId", RoomDAO.getLastChatId(newRoomId));
	}
}