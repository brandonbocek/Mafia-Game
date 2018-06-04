package controller;

import java.io.IOException;
import java.util.List;
import com.objects.Player;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RevealRoleToPlayerServlet
 */
@WebServlet("/RevealRoleToPlayerServlet")
public class RevealRoleToPlayerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RevealRoleToPlayerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String myName = (String)request.getParameter("myName");
		RoleGetter rg = new RoleGetter(myName);
		String theRole = rg.getPlayerRole();
		
		
		request.getSession().setAttribute("theRole", theRole);
		request.getRequestDispatcher("roleReveal.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
