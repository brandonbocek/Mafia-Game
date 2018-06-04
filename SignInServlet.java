package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SignInServlet
 */
@WebServlet("/SignInServlet")
public class SignInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignInServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String myName = (String)request.getParameter("myName");
		String message = "";
		if(myName.equals("admin")){
			System.out.println("Name is -> " + myName);
			request.getRequestDispatcher("options.jsp").forward(request, response);
		}else{
			
			//if the name is not in the db then add it
			AddNewPlayerToDB anp = new AddNewPlayerToDB();
			if(anp.didAddPlayer(myName)){
				System.out.println("Added " + myName + " to db");
				message = "Welcome to Mafia ";
				request.getSession().setAttribute("myName", myName);
				request.getSession().setAttribute("message", message);
				request.getRequestDispatcher("playerDayView.jsp").forward(request, response);
			//if the name is in the db then sign in
			}else{
				System.out.println(myName + " signed in");
				message = "Welcome back to Mafia ";
				request.getSession().setAttribute("myName", myName);
				request.getSession().setAttribute("message", message);
				request.getRequestDispatcher("playerDayView.jsp").forward(request, response);
			}
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
