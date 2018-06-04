package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.objects.Game;

/**
 * Servlet implementation class AdminKillsServlet
 */
@WebServlet("/AdminKillsServlet")
public class AdminKillsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminKillsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String playerAdminWants = (String)request.getParameter("playerAdminWants");
		Game theGame = (Game)request.getSession().getAttribute("theGame");
		System.out.println("The admin is trying to kill " + playerAdminWants);
		TownDayKill tdk = new TownDayKill(theGame);
		theGame = tdk.killPlayerDead(playerAdminWants);
		request.getSession().setAttribute("theGame", theGame);
		request.getRequestDispatcher("daytime.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
