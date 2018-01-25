package biz;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.UserBean;
import model.UserDAO;
import util.TimeParser;

@SuppressWarnings("serial")
@WebServlet("/login")
public class LoginController extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String inputId = request.getParameter("id");
			String inputPw = request.getParameter("pw");
			UserBean user = UserDAO.selectUser(inputId);
			if (user != null) {
				if(inputId.equals("admin")&&inputPw.equals("admin")){
					response.sendRedirect("admin.jsp");
				}else if (user.getPw().equals(inputPw)) {
					user.setLastAccessTime(TimeParser.parse(System.currentTimeMillis()));
					request.getSession().setAttribute("user", user);
					request.getRequestDispatcher("rtalk").include(request, response);
					response.sendRedirect("service.jsp");
				} else {
					request.getSession().setAttribute("user", "block");
					response.sendRedirect("input.jsp");
				}
			} else {
				UserBean newUser = new UserBean(inputId, inputPw, util.NickGenerator.getNick());
				UserDAO.insertUser(newUser);
				request.getSession().setAttribute("user", newUser);
				request.getRequestDispatcher("rtalk").include(request, response);
				response.sendRedirect("service.jsp");
			}
		} catch (Exception e) {
			response.sendRedirect("input.jsp");
		}
	}
}