package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class NightUpdateServlet
 */
@WebServlet("/NightUpdateServlet")
public class NightUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NightUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String myName = (String)request.getParameter("myName");
		String myRole = (String)request.getParameter("myRole");
		String chosenPlayer = (String)request.getParameter("chosenPlayer");
		System.out.println("myName: " + myName + ", myRole: " + myRole + ", chosenPlayer " + chosenPlayer);
		NightDBChange nc = new NightDBChange(myName, chosenPlayer);
		nc.updateChosenPlayer();
		request.getSession().setAttribute("chosenPlayer", chosenPlayer);
		request.getSession().setAttribute("myName", myName);
		request.getSession().setAttribute("myRole", myRole);
		String message = "you have completed your job by choosing " + chosenPlayer;
		//only let the cop and lawyer see another's roles once
		if(nc.playerHasPermissionToView() && (myRole.equals("Lawyer") || myRole.equals("Cop"))){
			nc.removePlayersChoosePermission();
			String othersRole = nc.getAnothersRole();
			request.getSession().setAttribute("othersRole", othersRole);
			request.getSession().setAttribute("message", message);
			request.getRequestDispatcher("revealOther.jsp").forward(request, response);
		}else if(!nc.playerHasPermissionToView() && (myRole.equals("Lawyer") || myRole.equals("Cop"))){
			message = "wait for permission before investigating ";
			request.getSession().setAttribute("message", message);
			request.getRequestDispatcher("playerDayView.jsp").forward(request, response);
		}else{
			request.getSession().setAttribute("message", message);
			request.getRequestDispatcher("playerDayView.jsp").forward(request, response);
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
