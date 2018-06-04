package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.objects.*;


/**
 * Servlet implementation class AssignRolesServlet
 */
@WebServlet("/AssignRolesServlet")
public class AssignRolesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AssignRolesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer numOfMafia = Integer.parseInt(request.getParameter("numOfMafia"));
		String copIsPlay = (String)request.getParameter("copIsPlay");
		String nurseIsPlay = (String)request.getParameter("nurseIsPlay");
		String lawyerIsPlay = (String)request.getParameter("lawyerIsPlay");
		String gmaIsPlay = (String)request.getParameter("gmaIsPlay");
		String underCopIsPlay = (String)request.getParameter("underCopIsPlay");
		Game theGame = new Game(numOfMafia, copIsPlay, nurseIsPlay, lawyerIsPlay, gmaIsPlay, underCopIsPlay);
		if(theGame.optionsAreAppropriate()){
			theGame.assignAllRoles();
			request.getSession().setAttribute("theGame", theGame);
			request.getRequestDispatcher("daytime.jsp").forward(request, response);
		}else{
			System.out.println("Not enough players for those options");
			request.getRequestDispatcher("options.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
