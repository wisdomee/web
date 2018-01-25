package biz;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.RoomDAO;
import model.UserDAO;

@SuppressWarnings("serial")
@WebServlet("/admin")
public class AdminController extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("euc-kr");
		String command = request.getParameter("command");
		try {
			if (command.equals("userAll")) {
				userAll(request, response);
			}else if(command.equals("userList")){
				userList(request,response);
			}else if(command.equals("chatRoom")) {
				chatRoom(request,response);
			}
		} catch (Exception s) {
			System.out.println("userAll service Exception");
		}
	}

	public void userAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			request.setAttribute("userAll", UserDAO.selectAllUser());
			request.getRequestDispatcher("userAll.jsp").forward(request, response);
		} catch (Exception s) {
			System.out.println("userAll Exception");
		}
	}
	public void userList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			request.setAttribute("userList",RoomDAO.getUserList(RoomDAO.getAllRooms().size()) );
			request.getRequestDispatcher("userList.jsp").forward(request, response);
		} catch (Exception s) {
			System.out.println("chatRoom Exception");
		}
	}



	public void chatRoom(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			request.setAttribute("chatRoom", RoomDAO.getAllRooms());
			request.getRequestDispatcher("chatRoom.jsp").forward(request, response);
		} catch (Exception s) {
			System.out.println("chatRoom Exception");
		}
	}
}