package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.objects.Player;

/**
 * Servlet implementation class PlayerGetsListServlet
 */
@WebServlet("/PlayerGetsListServlet")
public class PlayerGetsListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PlayerGetsListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String myName = (String)request.getParameter("myName");
		RoleGetter rg = new RoleGetter(myName);
		List<Player> others = rg.getOtherPlayers();
		String myRole = rg.getPlayerRole();
		
		request.getSession().setAttribute("myName", myName);
		request.getSession().setAttribute("myRole", myRole);
		request.getSession().setAttribute("others", others);
		request.getRequestDispatcher("listOfOthers.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
