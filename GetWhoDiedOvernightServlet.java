package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.objects.Game;

/**
 * Servlet implementation class GetWhoDiedOvernightServlet
 */
@WebServlet("/GetWhoDiedOvernightServlet")
public class GetWhoDiedOvernightServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetWhoDiedOvernightServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Game theGame = (Game)request.getSession().getAttribute("theGame");
		System.out.println("Number of players playing: " + theGame.getNumOfPlayersPlaying());
		ProcessTheNight ptn = new ProcessTheNight(theGame);
		theGame = ptn.killPlayer();
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
