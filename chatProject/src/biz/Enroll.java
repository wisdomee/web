package biz;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.UserBean;
import model.RoomDAO;

@SuppressWarnings("serial")
@WebServlet("/enroll")
public class Enroll extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (request.getParameter("msg") != "") {
			RoomDAO.enrollChat((String) session.getAttribute("roomId"), ((UserBean) session.getAttribute("user")).getNickName(), request.getParameter("msg"));
			//request.getRequestDispatcher("refresh").forward(request, response);
		}
	}
}