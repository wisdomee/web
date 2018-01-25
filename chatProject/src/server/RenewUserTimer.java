package server;

import java.util.Calendar;
import java.util.Timer;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebServlet;

@WebServlet("/renew")
public class RenewUserTimer implements ServletContextListener {
	public RenewUserTimer() {}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext sc = sce.getServletContext();
		try {
			Timer timer = new Timer();
			timer.scheduleAtFixedRate(new RenewUserTask(), Calendar.getInstance().getTime(), 3600000);
			sc.setAttribute("timer", timer);
		} catch (Exception e) {
			sc.log(e.getMessage());
		}
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		((Timer) sce.getServletContext().getAttribute("timer")).cancel();
	}
}